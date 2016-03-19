package io.github.kermit95.today.weather;

import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import java.util.ArrayList;
import java.util.List;

import io.github.kermit95.today.R;
import io.github.kermit95.today.data.remote.WeatherService;
import io.github.kermit95.today.data.remote.model.weather.DailyForecast;
import io.github.kermit95.today.data.remote.model.weather.HeWeatherDataService30;
import io.github.kermit95.today.data.remote.model.weather.Weather;
import io.github.kermit95.today.data.remote.model.weather.WeatherDisplay;
import io.github.kermit95.today.fluxbase.Dispatcher;
import io.github.kermit95.today.util.AMapProvider;
import io.github.kermit95.today.fluxbase.App;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kermit on 16/3/16.
 */
public class WeatherActionsCreatort {

    private static final String TAG = "WeatherActionsCreatort";
    private static final int UPDATELOCATION_MESSAGE = 0;

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
            message.sendToTarget();
        }
    };

    private AMapProvider mAMapProvider;

    /**
     * 更新数据并把事件发送给Store
     */
    public void updateData(){

        //高德地图的回调是在非主线程,虽然EventBus支持线程调度,但是为了程序的可维护性,依然使用了handler
        mAMapProvider = new AMapProvider.Builder(AMapLocationClientOption.AMapLocationMode.Battery_Saving, true)
                .isWifiActiveScan(true).interval(1000).build();
        mAMapProvider.startLocation(mAMapLocationListener);

    }

    private void update(String city){
        Log.e(TAG, "update: " + " city: " + city);
        if (city.endsWith("市")){
            city = city.substring(0, city.length()-1);
        }

        final String finalCity = city;
        WeatherService.Factory.getInstance().getWeather(finalCity).enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                List<HeWeatherDataService30> mWeatherDataService30List = response.body().getHeWeatherDataService30();


                if (mWeatherDataService30List == null || mWeatherDataService30List.size() == 0){
                    Log.e(TAG, "updateData: updateData(): datumlist is null or nothing in the datumlist");
                    return;
                }

                List<DailyForecast> mDailyForecasts = mWeatherDataService30List.get(0).getDailyForecast();

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
                mDispatcher.dispatch(WeatherAction.UPDATE_WEATHER, WeatherAction.KEY_WEATHERLIST, mWeathers);
                mAMapProvider.stopLocation(mAMapLocationListener);
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.e(TAG, "onFailure: fetch data failed");
            }
        });
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
            }
        }
    };


    public void checkDetail(int position){
        if (mWeathers == null){
            Log.d(TAG, "updateData: checkDetail(): datumlist is null");
            return;
        }
        if (mWeathers.size() == 0){
            Log.d(TAG, "checkDetail: checkDetail(): datumlist.size() == 0");
            return;
        }
        WeatherDisplay weahterDisplay = mWeathers.get(position);
        Log.e(TAG, "checkDetail: " + weahterDisplay.toString());
        mDispatcher.dispatch(
                WeatherAction.CHECK_DETAIL,
                WeatherAction.KEY_WEATHER, weahterDisplay
        );
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
            }else if (weatherString.contains(resources.getString(R.string.cloudy))){
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
