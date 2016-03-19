package io.github.kermit95.today.weather.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.github.kermit95.today.R;

/**
 * Created by kermit on 16/3/16.
 */
public class WeatherTag extends LinearLayout{

    private TextView mTvDate;
    private TextView mTvTemperature;
    private ImageView mImgWeather;
    private LayoutParams mLayoutParams;

    private String mDate;
    private String mTemperature;
    private float mDateSize;
    private float mTemperatureSize;
    private int mWeatherId;


    public WeatherTag(Context context) {
        super(context);
    }


    public WeatherTag(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.WeatherTag);
        mDate = array.getString(R.styleable.WeatherTag_date);
        mDateSize = array.getDimension(R.styleable.WeatherTag_date_size, 0);
        mTemperature = array.getString(R.styleable.WeatherTag_temperature);
        mTemperatureSize = array.getDimension(R.styleable.WeatherTag_temperature_size, 0);
        mWeatherId = array.getResourceId(R.styleable.WeatherTag_weather, 0);
        array.recycle();

        mTvDate = new TextView(context);
        mTvTemperature = new TextView(context);
        mImgWeather = new ImageView(context);

        mTvDate.setText(mDate);
        mTvDate.setTextSize(mDateSize);
        mTvDate.setGravity(Gravity.CENTER);

        mTvTemperature.setText(mTemperature);
        mTvTemperature.setTextSize(mTemperatureSize);
        mTvTemperature.setGravity(Gravity.CENTER);


        mImgWeather.setImageResource(mWeatherId);

        setOrientation(VERTICAL);
        mLayoutParams = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                0,
                1
        );
        mLayoutParams.gravity = Gravity.CENTER;

        addView(mTvDate, mLayoutParams);
        addView(mImgWeather, mLayoutParams);
        addView(mTvTemperature, mLayoutParams);
    }

    public void setDate(String date){
        mTvDate.setText(date);
    }

    public void setTemperature(String temperature){
        mTvTemperature.setText(temperature);
    }

    public void setImgWeather(@DrawableRes int picId){
        mImgWeather.setImageResource(picId);
    }

}
