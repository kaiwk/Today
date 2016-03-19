package io.github.kermit95.today.fluxbase;

import android.support.annotation.NonNull;

import java.util.HashMap;

/**
 * Created by kermit on 16/3/13.
 */
public class Action {

    //Event type
    private final String type;
    //Event data
    private final HashMap<String, Object> data;

    public Action(String type, HashMap<String, Object> data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public static Builder getBuilderWithType(String type){
        return new Builder().with(type);
    }


    public static class Builder{

        private String type;
        private HashMap<String, Object> data;

        private Builder(){}

        public Builder with(@NonNull String type){
            this.type = type;
            this.data = new HashMap<>();
            return this;
        }

        public void putData(@NonNull HashMap<String, Object> data){
            this.data = data;
        }

        public Builder putData(@NonNull String key, @NonNull Object object){
            if (data == null){
                throw new NullPointerException("Please invoke 'Builder.with()' method first!");
            }
            data.put(key, object);
            return this;
        }

        /**
         * Don't have to consider value is null string or other stuff. after all,  who will do the
         * thing like "pass a null string or null map".  : )
         * @return
         */
        public Action build(){
            if (data == null || type == null){
                throw new NullPointerException("Please invoke 'Builder.with()' and 'Builder.putData()' first!");
            }
            return new Action(type, data);
        }
    }
}
