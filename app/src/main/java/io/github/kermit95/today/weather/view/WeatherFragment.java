package io.github.kermit95.today.weather.view;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.kermit95.today.R;
import io.github.kermit95.today.data.remote.model.weather.WeatherDisplay;
import io.github.kermit95.today.fluxbase.Dispatcher;
import io.github.kermit95.today.weather.WeatherActionsCreatort;
import io.github.kermit95.today.weather.WeatherStore;
import io.github.kermit95.today.util.DateUtil;
import io.github.kermit95.today.weather.widget.WeatherTag;

/**
 * Created by kermit on 16/3/13.
 */
public class WeatherFragment extends Fragment {

    private static final String TAG = "WeatherFragment";

    public interface Callback{
        void getWeatherIcon(@DrawableRes int picId);
    }
    private Callback mCallback;



    @Bind(R.id.tv_weatherfragment_location)
    TextView mTvLocation;
    @Bind(R.id.tv_weatherfragment_pm)
    TextView mTvPm;
    @Bind(R.id.weather_0)
    WeatherTag mWeather0;
    @Bind(R.id.weather_1)
    WeatherTag mWeather1;
    @Bind(R.id.weather_2)
    WeatherTag mWeather2;
    @Bind(R.id.weather_3)
    WeatherTag mWeather3;
    @Bind(R.id.img_weatherfragment_weather)
    ImageView mImgWeather;
    @Bind(R.id.tv_weatherfragment_temp)
    TextView mTvTemp;

    private WeatherActionsCreatort mActionsCreatort;
    private Dispatcher mDispatcher;
    private WeatherStore mWeatherStore;

    private List<WeatherDisplay> mWeatherDisplays;
    private WeatherDisplay mWeatherDisplay;

    public static WeatherFragment newInstance() {
        return new WeatherFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCallback = (Callback) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.bind(this, v);
        initDependencies();
        setWeather4();

        return v;
    }

    private void initDependencies() {
        mDispatcher = new Dispatcher(EventBus.getDefault());
        mActionsCreatort = WeatherActionsCreatort.getInstance(mDispatcher);
        mWeatherStore = WeatherStore.getInstance(mDispatcher);
    }

    private void setWeather4(){
        mWeather0.setOnClickListener(v -> mActionsCreatort.checkDetail(0));
        mWeather1.setOnClickListener(v -> mActionsCreatort.checkDetail(1));
        mWeather2.setOnClickListener(v -> mActionsCreatort.checkDetail(2));
        mWeather3.setOnClickListener(v -> mActionsCreatort.checkDetail(3));
    }

    @Override
    public void onResume() {
        super.onResume();

        mActionsCreatort.getData();

        mDispatcher.register(this);
        mDispatcher.register(mWeatherStore);
    }

    @Override
    public void onPause() {
        super.onPause();
        mDispatcher.unregister(this);
        mDispatcher.unregister(mWeatherStore);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Subscribe
    public void onWeatherStoreChange(WeatherStore.WeatherChangeEvent event) {
        Log.d(TAG, "onWeatherStoreChange: something changed!");
    }

    @Subscribe
    public void onWeatherUpdate(WeatherStore.WeatherUpdateEvent event) {
        Log.d(TAG, "onWeatherUpdate: I got it!");
        getData();
    }

    public static final String WEATHERDISPLAY_SERIALIZABLE= "WeatherDisplay";
    @Subscribe
    public void onWeatherCheckDetail(WeatherStore.WeatherCheckDetailEvent event) {
        Log.d(TAG, "onWeatherCheckDetail: I got it!");
        mWeatherDisplay = mWeatherDisplays.get(event.getPosition());

        if (mWeatherDisplay == null) return;

        Bundle bundle = new Bundle();
        bundle.putSerializable(WEATHERDISPLAY_SERIALIZABLE, mWeatherDisplay);
        WeatherDetail weatherDetail = WeatherDetail.newInstance(bundle);
        weatherDetail.show(getFragmentManager(), WeatherDetail.TAG);
    }

    @Subscribe
    public void onLoadLocaldata(WeatherStore.WeahterLoadLocaldataEvent event){
        Log.d(TAG, "onLoadLocaldata: got it!");
        getData();
    }

    private void getData(){
        mWeatherDisplays = mWeatherStore.getWeathers();
        mWeatherDisplay = mWeatherDisplays.get(0);

        int picId = mWeatherDisplay.getWeatherIcon();
        mCallback.getWeatherIcon(picId);

        mImgWeather.setImageResource(picId);
        mTvLocation.setText(mWeatherDisplay.getLocation());
        mTvPm.setText(mWeatherDisplay.getPm());
        mTvTemp.setText(mWeatherDisplay.getTempMin());
        mTvLocation.setText(mWeatherDisplay.getLocation());

        setWeatherTag(mWeather0, mWeatherStore.getWeathers().get(0));
        setWeatherTag(mWeather1, mWeatherStore.getWeathers().get(1));
        setWeatherTag(mWeather2, mWeatherStore.getWeathers().get(2));
        setWeatherTag(mWeather3, mWeatherStore.getWeathers().get(3));
    }

    //set detail and format time
    public static final String FORMAT_STRING = "yyyy-MM-dd";
    public void setWeatherTag(WeatherTag weatherTag, WeatherDisplay weatherDisplay) {
        String week = DateUtil.getWeekDay(weatherDisplay.getDate(), FORMAT_STRING);
        weatherTag.setDate(week);
        weatherTag.setImgWeather(weatherDisplay.getWeatherIcon());
        weatherTag.setTemperature(weatherDisplay.getTempMin());
    }
}
