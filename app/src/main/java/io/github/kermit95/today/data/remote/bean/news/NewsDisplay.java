package io.github.kermit95.today.data.remote.bean.news;

import java.util.List;

import io.github.kermit95.baserecycler.ItemViewModel;


/**
 * Created by kermit on 16/3/27.
 */
public class NewsDisplay implements ItemViewModel {

    public static final int WITH_IMG = 0x01;
    public static final int NO_IMG = 0x02;

    @Override
    public int getViewType() {
        if (imgUrls == null || imgUrls.isEmpty()){
            return NO_IMG;
        }else{
            return WITH_IMG;
        }
    }

    private String title;
    private String desc;
    private String longDesc;
    private String pubDate;
    private List<Imageurl> imgUrls;
    private String source;
    private String link;

    public List<Imageurl> getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(List<Imageurl> imgUrls) {
        this.imgUrls = imgUrls;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
