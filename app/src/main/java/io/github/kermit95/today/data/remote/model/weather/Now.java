
package io.github.kermit95.today.data.remote.model.weather;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Now {

    @SerializedName("cond")
    @Expose
    private Cond_ cond;
    @SerializedName("fl")
    @Expose
    private String fl;
    @SerializedName("hum")
    @Expose
    private String hum;
    @SerializedName("pcpn")
    @Expose
    private String pcpn;
    @SerializedName("pres")
    @Expose
    private String pres;
    @SerializedName("tmp")
    @Expose
    private String tmp;
    @SerializedName("vis")
    @Expose
    private String vis;
    @SerializedName("wind")
    @Expose
    private Wind__ wind;

    /**
     * 
     * @return
     *     The cond
     */
    public Cond_ getCond() {
        return cond;
    }

    /**
     * 
     * @param cond
     *     The cond
     */
    public void setCond(Cond_ cond) {
        this.cond = cond;
    }

    /**
     * 
     * @return
     *     The fl
     */
    public String getFl() {
        return fl;
    }

    /**
     * 
     * @param fl
     *     The fl
     */
    public void setFl(String fl) {
        this.fl = fl;
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
    public Wind__ getWind() {
        return wind;
    }

    /**
     * 
     * @param wind
     *     The wind
     */
    public void setWind(Wind__ wind) {
        this.wind = wind;
    }

}
