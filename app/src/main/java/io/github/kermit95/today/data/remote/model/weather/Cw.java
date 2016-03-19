
package io.github.kermit95.today.data.remote.model.weather;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Cw {

    @SerializedName("brf")
    @Expose
    private String brf;
    @SerializedName("txt")
    @Expose
    private String txt;

    /**
     * 
     * @return
     *     The brf
     */
    public String getBrf() {
        return brf;
    }

    /**
     * 
     * @param brf
     *     The brf
     */
    public void setBrf(String brf) {
        this.brf = brf;
    }

    /**
     * 
     * @return
     *     The txt
     */
    public String getTxt() {
        return txt;
    }

    /**
     * 
     * @param txt
     *     The txt
     */
    public void setTxt(String txt) {
        this.txt = txt;
    }

}
