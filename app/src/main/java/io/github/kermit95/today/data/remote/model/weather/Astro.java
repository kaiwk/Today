
package io.github.kermit95.today.data.remote.model.weather;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Astro {

    @SerializedName("sr")
    @Expose
    private String sr;
    @SerializedName("ss")
    @Expose
    private String ss;

    /**
     * 
     * @return
     *     The sr
     */
    public String getSr() {
        return sr;
    }

    /**
     * 
     * @param sr
     *     The sr
     */
    public void setSr(String sr) {
        this.sr = sr;
    }

    /**
     * 
     * @return
     *     The ss
     */
    public String getSs() {
        return ss;
    }

    /**
     * 
     * @param ss
     *     The ss
     */
    public void setSs(String ss) {
        this.ss = ss;
    }

}
