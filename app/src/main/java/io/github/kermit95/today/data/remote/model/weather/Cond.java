
package io.github.kermit95.today.data.remote.model.weather;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Cond {

    @SerializedName("code_d")
    @Expose
    private String codeD;
    @SerializedName("code_n")
    @Expose
    private String codeN;
    @SerializedName("txt_d")
    @Expose
    private String txtD;
    @SerializedName("txt_n")
    @Expose
    private String txtN;

    /**
     * 
     * @return
     *     The codeD
     */
    public String getCodeD() {
        return codeD;
    }

    /**
     * 
     * @param codeD
     *     The code_d
     */
    public void setCodeD(String codeD) {
        this.codeD = codeD;
    }

    /**
     * 
     * @return
     *     The codeN
     */
    public String getCodeN() {
        return codeN;
    }

    /**
     * 
     * @param codeN
     *     The code_n
     */
    public void setCodeN(String codeN) {
        this.codeN = codeN;
    }

    /**
     * 
     * @return
     *     The txtD
     */
    public String getTxtD() {
        return txtD;
    }

    /**
     * 
     * @param txtD
     *     The txt_d
     */
    public void setTxtD(String txtD) {
        this.txtD = txtD;
    }

    /**
     * 
     * @return
     *     The txtN
     */
    public String getTxtN() {
        return txtN;
    }

    /**
     * 
     * @param txtN
     *     The txt_n
     */
    public void setTxtN(String txtN) {
        this.txtN = txtN;
    }

}
