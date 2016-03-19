package io.github.kermit95.today.weather;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import io.github.kermit95.today.data.remote.model.weather.WeatherDisplay;
import io.github.kermit95.today.fluxbase.Action;
import io.github.kermit95.today.fluxbase.Dispatcher;
import io.github.kermit95.today.fluxbase.Store;

/**
 * Created by kermit on 16/3/16.
 */
public class WeatherStore extends Store {

    private static final String TAG = "WeatherStore";

    private Dispatcher mDispatcher;

    private static WeatherStore instance;
    private WeatherDisplay mWeatherDisplay;
    private List<WeatherDisplay> mWeathers;

    public static WeatherStore getInstance(@Nullable Dispatcher dispatcher){
        if (instance == null){
            instance = new WeatherStore(dispatcher);
        }
        return instance;
    }

    protected WeatherStore(Dispatcher dispatcher) {
        super(dispatcher);
        mDispatcher = dispatcher;
    }

    @NonNull
    public List<WeatherDisplay> getWeathers() {
        return mWeathers;
    }

    @NonNull
    public WeatherDisplay getWeatherDisplay(){
        return mWeatherDisplay;
    }

    /**
     * 响应来自ActionCreator的事件,并通知view更新
     * @param action
     */
    @Subscribe
    @Override
    public void onAction(Action action) {
        switch (action.getType()){
            case WeatherAction.UPDATE_WEATHER:
                mWeathers = (List<WeatherDisplay>) action.getData().get(WeatherAction.KEY_WEATHERLIST);
                emitUpdate();
                break;
            case WeatherAction.CHECK_DETAIL:
                mWeatherDisplay = (WeatherDisplay) action.getData().get(WeatherAction.KEY_WEATHER);
                emitCheckDetail();
                break;
        }

        emitStoreChange();
    }

    private void emitUpdate(){
        mDispatcher.post(new WeatherUpdateEvent());
    }

    private void emitCheckDetail(){
        mDispatcher.post(new WeatherCheckDetailEvent());
    }

    @Override
    public StoreChangeEvent changeEvent() {
        return new WeatherChangeEvent();
    }

    public class WeatherChangeEvent implements StoreChangeEvent{}
    public class WeatherUpdateEvent implements StoreChangeEvent{}
    public class WeatherCheckDetailEvent implements StoreChangeEvent{}
}
