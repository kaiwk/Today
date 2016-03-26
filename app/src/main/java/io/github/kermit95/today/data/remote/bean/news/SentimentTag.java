
package io.github.kermit95.today.data.remote.bean.news;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class SentimentTag {

    @SerializedName("count")
    @Expose
    private String count;
    @SerializedName("dim")
    @Expose
    private String dim;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("isbooked")
    @Expose
    private Integer isbooked;
    @SerializedName("ishot")
    @Expose
    private String ishot;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;

    /**
     * 
     * @return
     *     The count
     */
    public String getCount() {
        return count;
    }

    /**
     * 
     * @param count
     *     The count
     */
    public void setCount(String count) {
        this.count = count;
    }

    /**
     * 
     * @return
     *     The dim
     */
    public String getDim() {
        return dim;
    }

    /**
     * 
     * @param dim
     *     The dim
     */
    public void setDim(String dim) {
        this.dim = dim;
    }

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The isbooked
     */
    public Integer getIsbooked() {
        return isbooked;
    }

    /**
     * 
     * @param isbooked
     *     The isbooked
     */
    public void setIsbooked(Integer isbooked) {
        this.isbooked = isbooked;
    }

    /**
     * 
     * @return
     *     The ishot
     */
    public String getIshot() {
        return ishot;
    }

    /**
     * 
     * @param ishot
     *     The ishot
     */
    public void setIshot(String ishot) {
        this.ishot = ishot;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

}
