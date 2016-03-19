
package io.github.kermit95.today.data.remote.model.weather;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Weather {

    @SerializedName("HeWeather data service 3.0")
    @Expose
    private List<HeWeatherDataService30> HeWeatherDataService30 = new ArrayList<>();

    /**
     * 
     * @return
     *     The HeWeatherDataService30
     */
    public List<HeWeatherDataService30> getHeWeatherDataService30() {
        return HeWeatherDataService30;
    }

    /**
     * 
     * @param HeWeatherDataService30
     *     The HeWeather data service 3.0
     */
    public void setHeWeatherDataService30(List<HeWeatherDataService30> HeWeatherDataService30) {
        this.HeWeatherDataService30 = HeWeatherDataService30;
    }

}
