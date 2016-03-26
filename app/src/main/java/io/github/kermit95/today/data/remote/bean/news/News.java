
package io.github.kermit95.today.data.remote.bean.news;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class News {

    @SerializedName("showapi_res_code")
    @Expose
    private Integer showapiResCode;
    @SerializedName("showapi_res_error")
    @Expose
    private String showapiResError;
    @SerializedName("showapi_res_body")
    @Expose
    private ShowapiResBody showapiResBody;

    /**
     * 
     * @return
     *     The showapiResCode
     */
    public Integer getShowapiResCode() {
        return showapiResCode;
    }

    /**
     * 
     * @param showapiResCode
     *     The showapi_res_code
     */
    public void setShowapiResCode(Integer showapiResCode) {
        this.showapiResCode = showapiResCode;
    }

    /**
     * 
     * @return
     *     The showapiResError
     */
    public String getShowapiResError() {
        return showapiResError;
    }

    /**
     * 
     * @param showapiResError
     *     The showapi_res_error
     */
    public void setShowapiResError(String showapiResError) {
        this.showapiResError = showapiResError;
    }

    /**
     * 
     * @return
     *     The showapiResBody
     */
    public ShowapiResBody getShowapiResBody() {
        return showapiResBody;
    }

    /**
     * 
     * @param showapiResBody
     *     The showapi_res_body
     */
    public void setShowapiResBody(ShowapiResBody showapiResBody) {
        this.showapiResBody = showapiResBody;
    }

}
