package io.github.kermit95.today.data.local.bean;

import com.google.gson.Gson;

import io.github.kermit95.today.data.JSONSerializer;

/**
 * Created by kermit on 16/3/15.
 */
public class Todo implements Cloneable, Comparable<Todo>, JSONSerializer{

    private long id;
    private boolean isComplete;
    private String text;

    public Todo(long id, boolean isComplete, String text) {
        this.id = id;
        this.isComplete = isComplete;
        this.text = text;
    }

    public Todo(long id, String text){
        this.id = id;
        this.text = text;
    }

    public Todo(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Todo clone(){
        return new Todo(id, isComplete, text);
    }

    @Override
    public int compareTo(Todo todo) {
        if (id == todo.id) {
            return 0;
        } else if (id < todo.id) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public String toJSON() {
        return new Gson().toJson(this);
    }
}
