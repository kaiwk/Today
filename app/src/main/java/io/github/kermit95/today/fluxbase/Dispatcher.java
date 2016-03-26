package io.github.kermit95.today.fluxbase;

import android.support.annotation.NonNull;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

/**
 * Created by kermit on 16/3/13.
 */
public class Dispatcher {

    private final EventBus mBus;
    private static Dispatcher sDispatcher;

    public static Dispatcher get(@NonNull EventBus bus){
        if (sDispatcher == null){
            sDispatcher = new Dispatcher(bus);
        }
        return sDispatcher;
    }

    public Dispatcher(EventBus bus){
        this.mBus = bus;
    }

    public void register(final Object object){
        mBus.register(object);
    }

    public void unregister(final Object object){
        mBus.unregister(object);
    }

    public void dispatch(@NonNull String type){
        Action.Builder builder = Action.getBuilderWithType(type);
        post(builder.build());
    }

    //这里把对用户操作进行的封装
    public void dispatch(@NonNull final String type, @NonNull final HashMap<String, Object> data){
        Action.Builder builder = Action.getBuilderWithType(type);
        builder.putData(data);
        post(builder.build());
    }

    public void dispatch(@NonNull final String type, @NonNull final String key, @NonNull final Object object){
        Action.Builder builder = Action.getBuilderWithType(type);
        builder.putData(key, object);
        post(builder.build());
    }

    //该方法会post任何事件
    public void post(@NonNull final Object event){
        mBus.post(event);
    }

    //该方法会post指定事件
    public void emitChange(Store.StoreChangeEvent event){
        post(event);
    }
}
