
package io.github.kermit95.today.data.remote.model.weather;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class DailyForecast {

    @SerializedName("astro")
    @Expose
    private Astro astro;
    @SerializedName("cond")
    @Expose
    private Cond cond;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("hum")
    @Expose
    private String hum;
    @SerializedName("pcpn")
    @Expose
    private String pcpn;
    @SerializedName("pop")
    @Expose
    private String pop;
    @SerializedName("pres")
    @Expose
    private String pres;
    @SerializedName("tmp")
    @Expose
    private Tmp tmp;
    @SerializedName("vis")
    @Expose
    private String vis;
    @SerializedName("wind")
    @Expose
    private Wind wind;

    /**
     * 
     * @return
     *     The astro
     */
    public Astro getAstro() {
        return astro;
    }

    /**
     * 
     * @param astro
     *     The astro
     */
    public void setAstro(Astro astro) {
        this.astro = astro;
    }

    /**
     * 
     * @return
     *     The cond
     */
    public Cond getCond() {
        return cond;
    }

    /**
     * 
     * @param cond
     *     The cond
     */
    public void setCond(Cond cond) {
        this.cond = cond;
    }

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
     *     The pcpn
     */
    public String getPcpn() {
        return pcpn;
    }

    /**
     * 
     * @param pcpn
     *     The pcpn
     */
    public void setPcpn(String pcpn) {
        this.pcpn = pcpn;
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
    public Tmp getTmp() {
        return tmp;
    }

    /**
     * 
     * @param tmp
     *     The tmp
     */
    public void setTmp(Tmp tmp) {
        this.tmp = tmp;
    }

    /**
     * 
     * @return
     *     The vis
     */
    public String getVis() {
        return vis;
    }

    /**
     * 
     * @param vis
     *     The vis
     */
    public void setVis(String vis) {
        this.vis = vis;
    }

    /**
     * 
     * @return
     *     The wind
     */
    public Wind getWind() {
        return wind;
    }

    /**
     * 
     * @param wind
     *     The wind
     */
    public void setWind(Wind wind) {
        this.wind = wind;
    }

}
