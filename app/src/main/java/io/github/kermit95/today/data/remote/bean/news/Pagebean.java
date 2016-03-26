
package io.github.kermit95.today.data.remote.bean.news;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Pagebean {

    @SerializedName("allNum")
    @Expose
    private Integer allNum;
    @SerializedName("allPages")
    @Expose
    private Integer allPages;
    @SerializedName("contentlist")
    @Expose
    private List<Contentlist> contentlist = new ArrayList<Contentlist>();
    @SerializedName("currentPage")
    @Expose
    private Integer currentPage;
    @SerializedName("maxResult")
    @Expose
    private Integer maxResult;

    /**
     * 
     * @return
     *     The allNum
     */
    public Integer getAllNum() {
        return allNum;
    }

    /**
     * 
     * @param allNum
     *     The allNum
     */
    public void setAllNum(Integer allNum) {
        this.allNum = allNum;
    }

    /**
     * 
     * @return
     *     The allPages
     */
    public Integer getAllPages() {
        return allPages;
    }

    /**
     * 
     * @param allPages
     *     The allPages
     */
    public void setAllPages(Integer allPages) {
        this.allPages = allPages;
    }

    /**
     * 
     * @return
     *     The contentlist
     */
    public List<Contentlist> getContentlist() {
        return contentlist;
    }

    /**
     * 
     * @param contentlist
     *     The contentlist
     */
    public void setContentlist(List<Contentlist> contentlist) {
        this.contentlist = contentlist;
    }

    /**
     * 
     * @return
     *     The currentPage
     */
    public Integer getCurrentPage() {
        return currentPage;
    }

    /**
     * 
     * @param currentPage
     *     The currentPage
     */
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * 
     * @return
     *     The maxResult
     */
    public Integer getMaxResult() {
        return maxResult;
    }

    /**
     * 
     * @param maxResult
     *     The maxResult
     */
    public void setMaxResult(Integer maxResult) {
        this.maxResult = maxResult;
    }

}
