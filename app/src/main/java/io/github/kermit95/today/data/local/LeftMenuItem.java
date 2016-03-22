package io.github.kermit95.today.data.local;

import com.google.gson.Gson;

import io.github.kermit95.today.data.JSONSerializer;

/**
 * Created by kermit on 16/3/15.
 */
public class LeftMenuItem implements JSONSerializer{

    private String item;
    private int picId;

    public LeftMenuItem(){}

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

    @Override
    public String toJSON() {
        return new Gson().toJson(this);
    }
}
