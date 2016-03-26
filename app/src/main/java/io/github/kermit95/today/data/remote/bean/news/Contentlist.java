
package io.github.kermit95.today.data.remote.bean.news;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Contentlist {

    @SerializedName("channelId")
    @Expose
    private String channelId;
    @SerializedName("channelName")
    @Expose
    private String channelName;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("imageurls")
    @Expose
    private List<Imageurl> imageurls = new ArrayList<Imageurl>();
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("long_desc")
    @Expose
    private String longDesc;
    @SerializedName("nid")
    @Expose
    private String nid;
    @SerializedName("pubDate")
    @Expose
    private String pubDate;
    @SerializedName("sentiment_display")
    @Expose
    private Integer sentimentDisplay;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("sentiment_tag")
    @Expose
    private SentimentTag sentimentTag;

    /**
     * 
     * @return
     *     The channelId
     */
    public String getChannelId() {
        return channelId;
    }

    /**
     * 
     * @param channelId
     *     The channelId
     */
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    /**
     * 
     * @return
     *     The channelName
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * 
     * @param channelName
     *     The channelName
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    /**
     * 
     * @return
     *     The desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 
     * @param desc
     *     The desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 
     * @return
     *     The imageurls
     */
    public List<Imageurl> getImageurls() {
        return imageurls;
    }

    /**
     * 
     * @param imageurls
     *     The imageurls
     */
    public void setImageurls(List<Imageurl> imageurls) {
        this.imageurls = imageurls;
    }

    /**
     * 
     * @return
     *     The link
     */
    public String getLink() {
        return link;
    }

    /**
     * 
     * @param link
     *     The link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * 
     * @return
     *     The longDesc
     */
    public String getLongDesc() {
        return longDesc;
    }

    /**
     * 
     * @param longDesc
     *     The long_desc
     */
    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    /**
     * 
     * @return
     *     The nid
     */
    public String getNid() {
        return nid;
    }

    /**
     * 
     * @param nid
     *     The nid
     */
    public void setNid(String nid) {
        this.nid = nid;
    }

    /**
     * 
     * @return
     *     The pubDate
     */
    public String getPubDate() {
        return pubDate;
    }

    /**
     * 
     * @param pubDate
     *     The pubDate
     */
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    /**
     * 
     * @return
     *     The sentimentDisplay
     */
    public Integer getSentimentDisplay() {
        return sentimentDisplay;
    }

    /**
     * 
     * @param sentimentDisplay
     *     The sentiment_display
     */
    public void setSentimentDisplay(Integer sentimentDisplay) {
        this.sentimentDisplay = sentimentDisplay;
    }

    /**
     * 
     * @return
     *     The source
     */
    public String getSource() {
        return source;
    }

    /**
     * 
     * @param source
     *     The source
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The sentimentTag
     */
    public SentimentTag getSentimentTag() {
        return sentimentTag;
    }

    /**
     * 
     * @param sentimentTag
     *     The sentiment_tag
     */
    public void setSentimentTag(SentimentTag sentimentTag) {
        this.sentimentTag = sentimentTag;
    }

}
