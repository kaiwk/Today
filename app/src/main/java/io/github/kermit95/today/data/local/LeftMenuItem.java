package io.github.kermit95.today.data.local;

/**
 * Created by kermit on 16/3/15.
 */
public class LeftMenuItem {

    private String item;
    private int picId;

    public LeftMenuItem(String item, int picId) {
        this.item = item;
        this.picId = picId;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }
}
