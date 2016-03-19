
package io.github.kermit95.today.data.remote.model.weather;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class HeWeatherDataService30 {

    @SerializedName("aqi")
    @Expose
    private Aqi aqi;
    @SerializedName("basic")
    @Expose
    private Basic basic;
    @SerializedName("daily_forecast")
    @Expose
    private List<DailyForecast> dailyForecast = new ArrayList<DailyForecast>();
    @SerializedName("hourly_forecast")
    @Expose
    private List<HourlyForecast> hourlyForecast = new ArrayList<HourlyForecast>();
    @SerializedName("now")
    @Expose
    private Now now;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("suggestion")
    @Expose
    private Suggestion suggestion;

    /**
     * 
     * @return
     *     The aqi
     */
    public Aqi getAqi() {
        return aqi;
    }

    /**
     * 
     * @param aqi
     *     The aqi
     */
    public void setAqi(Aqi aqi) {
        this.aqi = aqi;
    }

    /**
     * 
     * @return
     *     The basic
     */
    public Basic getBasic() {
        return basic;
    }

    /**
     * 
     * @param basic
     *     The basic
     */
    public void setBasic(Basic basic) {
        this.basic = basic;
    }

    /**
     * 
     * @return
     *     The dailyForecast
     */
    public List<DailyForecast> getDailyForecast() {
        return dailyForecast;
    }

    /**
     * 
     * @param dailyForecast
     *     The daily_forecast
     */
    public void setDailyForecast(List<DailyForecast> dailyForecast) {
        this.dailyForecast = dailyForecast;
    }

    /**
     * 
     * @return
     *     The hourlyForecast
     */
    public List<HourlyForecast> getHourlyForecast() {
        return hourlyForecast;
    }

    /**
     * 
     * @param hourlyForecast
     *     The hourly_forecast
     */
    public void setHourlyForecast(List<HourlyForecast> hourlyForecast) {
        this.hourlyForecast = hourlyForecast;
    }

    /**
     * 
     * @return
     *     The now
     */
    public Now getNow() {
        return now;
    }

    /**
     * 
     * @param now
     *     The now
     */
    public void setNow(Now now) {
        this.now = now;
    }

    /**
     * 
     * @return
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The suggestion
     */
    public Suggestion getSuggestion() {
        return suggestion;
    }

    /**
     * 
     * @param suggestion
     *     The suggestion
     */
    public void setSuggestion(Suggestion suggestion) {
        this.suggestion = suggestion;
    }

}
