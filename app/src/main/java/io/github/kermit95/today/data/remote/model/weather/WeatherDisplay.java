package io.github.kermit95.today.data.remote.model.weather;

import android.support.annotation.DrawableRes;

import java.io.Serializable;

/**
 * Created by kermit on 16/3/17.
 */
public class WeatherDisplay implements Serializable{

    private int weatherIcon;
    private String pm;
    private String tempMax;
    private String tempMin;
    private String date;
    private String location;
    private String weatherString;
    private String suggestion;
    private String pollution;

    public String getPollution() {
        return pollution;
    }

    public void setPollution(String pollution) {
        this.pollution = pollution;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @DrawableRes
    public int getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(@DrawableRes int weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }

    public String getTempMax() {
        return tempMax;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }

    public String getTempMin() {
        return tempMin;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }

    public String getWeatherString() {
        return weatherString;
    }

    public void setWeatherString(String weatherString) {
        this.weatherString = weatherString;
    }


    @Override
    public String toString() {
        return "WeatherDisplay{" +
                "weatherIcon=" + weatherIcon +
                ", pm='" + pm + '\'' +
                ", tempMax='" + tempMax + '\'' +
                ", tempMin='" + tempMin + '\'' +
                ", date='" + date + '\'' +
                ", location='" + location + '\'' +
                ", weatherString='" + weatherString + '\'' +
                ", suggestion='" + suggestion + '\'' +
                ", pollution='" + pollution + '\'' +
                '}';
    }
}
