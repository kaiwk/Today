
package io.github.kermit95.today.data.remote.model.weather;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class City {

    @SerializedName("aqi")
    @Expose
    private String aqi;
    @SerializedName("co")
    @Expose
    private String co;
    @SerializedName("no2")
    @Expose
    private String no2;
    @SerializedName("o3")
    @Expose
    private String o3;
    @SerializedName("pm10")
    @Expose
    private String pm10;
    @SerializedName("pm25")
    @Expose
    private String pm25;
    @SerializedName("qlty")
    @Expose
    private String qlty;
    @SerializedName("so2")
    @Expose
    private String so2;

    /**
     * 
     * @return
     *     The aqi
     */
    public String getAqi() {
        return aqi;
    }

    /**
     * 
     * @param aqi
     *     The aqi
     */
    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    /**
     * 
     * @return
     *     The co
     */
    public String getCo() {
        return co;
    }

    /**
     * 
     * @param co
     *     The co
     */
    public void setCo(String co) {
        this.co = co;
    }

    /**
     * 
     * @return
     *     The no2
     */
    public String getNo2() {
        return no2;
    }

    /**
     * 
     * @param no2
     *     The no2
     */
    public void setNo2(String no2) {
        this.no2 = no2;
    }

    /**
     * 
     * @return
     *     The o3
     */
    public String getO3() {
        return o3;
    }

    /**
     * 
     * @param o3
     *     The o3
     */
    public void setO3(String o3) {
        this.o3 = o3;
    }

    /**
     * 
     * @return
     *     The pm10
     */
    public String getPm10() {
        return pm10;
    }

    /**
     * 
     * @param pm10
     *     The pm10
     */
    public void setPm10(String pm10) {
        this.pm10 = pm10;
    }

    /**
     * 
     * @return
     *     The pm25
     */
    public String getPm25() {
        return pm25;
    }

    /**
     * 
     * @param pm25
     *     The pm25
     */
    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    /**
     * 
     * @return
     *     The qlty
     */
    public String getQlty() {
        return qlty;
    }

    /**
     * 
     * @param qlty
     *     The qlty
     */
    public void setQlty(String qlty) {
        this.qlty = qlty;
    }

    /**
     * 
     * @return
     *     The so2
     */
    public String getSo2() {
        return so2;
    }

    /**
     * 
     * @param so2
     *     The so2
     */
    public void setSo2(String so2) {
        this.so2 = so2;
    }

}
