package io.github.kermit95.today.weather;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.kermit.exutils.utils.NetUtils;

import java.util.ArrayList;
import java.util.List;

import io.github.kermit95.today.R;
import io.github.kermit95.today.data.WeatherModel;
import io.github.kermit95.today.data.remote.WeatherService;
import io.github.kermit95.today.data.remote.model.weather.DailyForecast;
import io.github.kermit95.today.data.remote.model.weather.HeWeatherDataService30;
import io.github.kermit95.today.data.remote.model.weather.Weather;
import io.github.kermit95.today.data.remote.model.weather.WeatherDisplay;
import io.github.kermit95.today.fluxbase.App;
import io.github.kermit95.today.fluxbase.Dispatcher;
import io.github.kermit95.today.util.AMapProvider;
import io.github.kermit95.today.util.MyUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kermit on 16/3/16.
 */
public class WeatherActionsCreatort {

    private static final String TAG = "WeatherActionsCreatort";
    private static final int UPDATELOCATION_MESSAGE = 0;
    private static final int LOAD_LOCAL_DATA = 1;

    private static WeatherActionsCreatort instance;
    private Dispatcher mDispatcher;
    private List<WeatherDisplay> mWeathers;

    public static WeatherActionsCreatort getInstance(Dispatcher dispatcher){
        if (instance == null){
            instance = new WeatherActionsCreatort(dispatcher);
        }
        return instance;
    }

    private WeatherActionsCreatort(Dispatcher dispatcher){
        this.mDispatcher = dispatcher;
        mWeathers = new ArrayList<>();
    }

    private AMapLocationListener mAMapLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            Message message = mHandler.obtainMessage();
            message.obj = aMapLocation.getCity();
            message.what = UPDATELOCATION_MESSAGE;
            Log.e(TAG, "onLocationChanged: " +  aMapLocation.getCity());
            message.sendToTarget();
        }
    };

    private AMapProvider mAMapProvider;

    /**
     * 更新数据并把事件发送给Store
     */
    public void getData(){
        if (NetUtils.hasNetwork() || MyUtils.isWifi()) {
            //高德地图的回调是在非主线程,虽然EventBus支持线程调度,但是为了程序的可维护性,依然使用了handler
            mAMapProvider = new AMapProvider.Builder(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy, true)
                    .isWifiActiveScan(true).interval(1000).build();
            mAMapProvider.startLocation(mAMapLocationListener);
        }else{
            loadLoadFile();
        }
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case UPDATELOCATION_MESSAGE:
                    String city = (String) msg.obj;
                    update(city);
                    break;
                case LOAD_LOCAL_DATA:
                    Log.e(TAG, "loadLoadFile: " + mWeathers.get(0).getLocation());
                    if (mWeathers == null) return;
                    mDispatcher.dispatch(WeatherAction.LOAD_LOCALDATA, WeatherAction.KEY_WEATHERLIST, mWeathers);
                    break;
            }
        }
    };


    private void loadLoadFile(){
        new Thread(){
            @Override
            public void run() {
                super.run();
                mWeathers = WeatherModel.getInstance().readList(WEATHERLIST_FILENAME);
                Message message = mHandler.obtainMessage();
                message.what = LOAD_LOCAL_DATA;
                message.sendToTarget();
            }
        }.start();
    }


    //文件目录
    public static final String WEATHERLIST_FILENAME = "weathers";
    private void update(String city){
        Log.e(TAG, "update: " + " city: " + city);
        if (city.endsWith("市")){
            city = city.substring(0, city.length()-1);
        }

        Log.e(TAG, "onLocationChanged: " + city);

        //// TODO: 16/3/24 高德api无法使用
        if (TextUtils.isEmpty(city) || city == null){
            city = "重庆";
        }
        final String finalCity = city;
        WeatherService.Factory.getInstance().getWeather(finalCity).enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                List<HeWeatherDataService30> mWeatherDataService30List = response.body().getHeWeatherDataService30();


                if (mWeatherDataService30List == null || mWeatherDataService30List.size() == 0){
                    Log.e(TAG, "getData: getData(): datumlist is null or nothing in the datumlist");
                    return;
                }

                List<DailyForecast> mDailyForecasts = mWeatherDataService30List.get(0).getDailyForecast();
                Log.e(TAG, "onResponse: " + mDailyForecasts.get(0).getTmp());

                String pm25 = mWeatherDataService30List.get(0).getAqi().getCity().getPm25();
                String suggestion = mWeatherDataService30List.get(0).getSuggestion().getDrsg().getTxt();
                String pollution = mWeatherDataService30List.get(0).getAqi().getCity().getQlty();
                for(int i = 0; i < mDailyForecasts.size(); ++i){
                    WeatherDisplay weather = DataFilter.getWeatherDisplay(mDailyForecasts.get(i));
                    weather.setPm(pm25);
                    weather.setLocation(finalCity);
                    weather.setSuggestion(suggestion);
                    weather.setPollution(pollution);
                    mWeathers.add(weather);
                }
                WeatherModel.getInstance().saveList(mWeathers, WEATHERLIST_FILENAME, Context.MODE_PRIVATE);
                mDispatcher.dispatch(WeatherAction.UPDATE_WEATHER, WeatherAction.KEY_WEATHERLIST, mWeathers);
                mAMapProvider.stopLocation(mAMapLocationListener);
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.e(TAG, "onFailure: fetch data failed");
            }
        });
    }

    public void checkDetail(int position){
        if (mWeathers == null){
            Log.d(TAG, "getData: checkDetail(): datumlist is null");
            return;
        }
        if (mWeathers.size() == 0){
            Log.d(TAG, "checkDetail: checkDetail(): datumlist.size() == 0");
            return;
        }
        mDispatcher.dispatch(WeatherAction.CHECK_DETAIL, WeatherAction.DETAIL_POSITION, position);
    }




    /**
     * A helper class
     */
    private final static class DataFilter{

        private DataFilter(){}

        @NonNull
        public static WeatherDisplay getWeatherDisplay(@NonNull DailyForecast dailyForecast){
            WeatherDisplay weatherDisplay = new WeatherDisplay();

            //Set Weather Wether
            String weatherString = dailyForecast.getCond().getTxtD();
            weatherDisplay.setWeatherString(weatherString);

            //Set Weather Icon
            Resources resources = App.getInstance().getResources();
            if (weatherString.contains(resources.getString(R.string.sunny))){
                weatherDisplay.setWeatherIcon(R.drawable.sunny);
            }else if (weatherString.contains(resources.getString(R.string.cloudy)) ||
                    weatherString.contains(resources.getString(R.string.dark))){
                weatherDisplay.setWeatherIcon(R.drawable.cloudy);
            }else if (weatherString.contains(resources.getString(R.string.rainy))){
                weatherDisplay.setWeatherIcon(R.drawable.rainny);
            }else if (weatherString.contains(resources.getString(R.string.foggy))){
                weatherDisplay.setWeatherIcon(R.drawable.foggy);
            }else if (weatherString.contains(resources.getString(R.string.windy))){
                weatherDisplay.setWeatherIcon(R.drawable.sand);
            }else if (weatherString.contains(resources.getString(R.string.snowy))){
                weatherDisplay.setWeatherIcon(R.drawable.rainny_and_snowy);
            }

            //Set Weather Date
            weatherDisplay.setDate(dailyForecast.getDate());


            //Set Temperature
            weatherDisplay.setTempMax(dailyForecast.getTmp().getMax() + "℃");
            weatherDisplay.setTempMin(dailyForecast.getTmp().getMin() + "℃");


            return weatherDisplay;
        }
    }

}
