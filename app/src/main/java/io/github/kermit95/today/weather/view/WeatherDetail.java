package io.github.kermit95.today.weather.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.kermit95.today.R;
import io.github.kermit95.today.data.remote.model.weather.WeatherDisplay;

/**
 * Created by kermit on 16/3/16.
 */
public class WeatherDetail extends DialogFragment {

    public static final String TAG = "WeatherDetail";

    @Bind(R.id.tv_weatherdetail_weather)
    TextView mTvWeather;
    @Bind(R.id.tv_weatherdetail_maxtemp)
    TextView mTvMaxtemp;
    @Bind(R.id.tv_weatherdetail_mintemp)
    TextView mTvMintemp;
    @Bind(R.id.tv_weatherdetail_pollution)
    TextView mTvPollution;
    @Bind(R.id.tv_weatherdetail_suggestion)
    TextView mTvSuggestion;

    private WeatherDisplay mWeatherDisplay;

    public static WeatherDetail newInstance(Bundle bundle){
        WeatherDetail weatherDetail = new WeatherDetail();
        weatherDetail.setArguments(bundle);
        return weatherDetail;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mWeatherDisplay = (WeatherDisplay) bundle.getSerializable(WeatherFragment.WEATHERDISPLAY_SERIALIZABLE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(STYLE_NO_TITLE);
        View view = inflater.inflate(R.layout.fragment_weatherdetail, container, false);
        ButterKnife.bind(this, view);
        setUpView();
        return view;
    }

    private void setUpView() {
        mTvWeather.setText(mWeatherDisplay.getWeatherString());
        mTvPollution.setText(mWeatherDisplay.getPollution());
        mTvMintemp.setText(mWeatherDisplay.getTempMin());
        mTvMaxtemp.setText(mWeatherDisplay.getTempMax());
        mTvSuggestion.setText(mWeatherDisplay.getSuggestion());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
