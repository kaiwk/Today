
package io.github.kermit95.today.data.remote.model.weather;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class HourlyForecast {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("hum")
    @Expose
    private String hum;
    @SerializedName("pop")
    @Expose
    private String pop;
    @SerializedName("pres")
    @Expose
    private String pres;
    @SerializedName("tmp")
    @Expose
    private String tmp;
    @SerializedName("wind")
    @Expose
    private Wind_ wind;

    /**
     * 
     * @return
     *     The date
     */
    public String getDate() {
        return date;
    }

    /**
     * 
     * @param date
     *     The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * 
     * @return
     *     The hum
     */
    public String getHum() {
        return hum;
    }

    /**
     * 
     * @param hum
     *     The hum
     */
    public void setHum(String hum) {
        this.hum = hum;
    }

    /**
     * 
     * @return
     *     The pop
     */
    public String getPop() {
        return pop;
    }

    /**
     * 
     * @param pop
     *     The pop
     */
    public void setPop(String pop) {
        this.pop = pop;
    }

    /**
     * 
     * @return
     *     The pres
     */
    public String getPres() {
        return pres;
    }

    /**
     * 
     * @param pres
     *     The pres
     */
    public void setPres(String pres) {
        this.pres = pres;
    }

    /**
     * 
     * @return
     *     The tmp
     */
    public String getTmp() {
        return tmp;
    }

    /**
     * 
     * @param tmp
     *     The tmp
     */
    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    /**
     * 
     * @return
     *     The wind
     */
    public Wind_ getWind() {
        return wind;
    }

    /**
     * 
     * @param wind
     *     The wind
     */
    public void setWind(Wind_ wind) {
        this.wind = wind;
    }

}
