
package io.github.kermit95.today.data.remote.model.weather;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Suggestion {

    @SerializedName("comf")
    @Expose
    private Comf comf;
    @SerializedName("cw")
    @Expose
    private Cw cw;
    @SerializedName("drsg")
    @Expose
    private Drsg drsg;
    @SerializedName("flu")
    @Expose
    private Flu flu;
    @SerializedName("sport")
    @Expose
    private Sport sport;
    @SerializedName("trav")
    @Expose
    private Trav trav;
    @SerializedName("uv")
    @Expose
    private Uv uv;

    /**
     * 
     * @return
     *     The comf
     */
    public Comf getComf() {
        return comf;
    }

    /**
     * 
     * @param comf
     *     The comf
     */
    public void setComf(Comf comf) {
        this.comf = comf;
    }

    /**
     * 
     * @return
     *     The cw
     */
    public Cw getCw() {
        return cw;
    }

    /**
     * 
     * @param cw
     *     The cw
     */
    public void setCw(Cw cw) {
        this.cw = cw;
    }

    /**
     * 
     * @return
     *     The drsg
     */
    public Drsg getDrsg() {
        return drsg;
    }

    /**
     * 
     * @param drsg
     *     The drsg
     */
    public void setDrsg(Drsg drsg) {
        this.drsg = drsg;
    }

    /**
     * 
     * @return
     *     The flu
     */
    public Flu getFlu() {
        return flu;
    }

    /**
     * 
     * @param flu
     *     The flu
     */
    public void setFlu(Flu flu) {
        this.flu = flu;
    }

    /**
     * 
     * @return
     *     The sport
     */
    public Sport getSport() {
        return sport;
    }

    /**
     * 
     * @param sport
     *     The sport
     */
    public void setSport(Sport sport) {
        this.sport = sport;
    }

    /**
     * 
     * @return
     *     The trav
     */
    public Trav getTrav() {
        return trav;
    }

    /**
     * 
     * @param trav
     *     The trav
     */
    public void setTrav(Trav trav) {
        this.trav = trav;
    }

    /**
     * 
     * @return
     *     The uv
     */
    public Uv getUv() {
        return uv;
    }

    /**
     * 
     * @param uv
     *     The uv
     */
    public void setUv(Uv uv) {
        this.uv = uv;
    }

}
