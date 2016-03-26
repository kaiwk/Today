package io.github.kermit95.today.weather;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import io.github.kermit95.today.data.remote.bean.weather.WeatherDisplay;
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
                int position = (int) action.getData().get(WeatherAction.DETAIL_POSITION);
                emitCheckDetail(position);
                break;
            case WeatherAction.LOAD_LOCALDATA:
                mWeathers = (List<WeatherDisplay>) action.getData().get(WeatherAction.KEY_WEATHERLIST);
                emitLoadLocaldata();
                break;
        }

        emitStoreChange();
    }

    private void emitUpdate(){
        mDispatcher.emitChange(new WeatherUpdateEvent());
    }

    private void emitCheckDetail(int position){
        mDispatcher.emitChange(new WeatherCheckDetailEvent(position));
    }

    private void emitLoadLocaldata(){
        mDispatcher.emitChange(new WeahterLoadLocaldataEvent());
    }

    @Override
    public StoreChangeEvent changeEvent() {
        return new WeatherChangeEvent();
    }

    public class WeatherChangeEvent implements StoreChangeEvent{}
    public class WeatherUpdateEvent implements StoreChangeEvent{}
    public class WeatherCheckDetailEvent implements StoreChangeEvent{
        private int position;

        private WeatherCheckDetailEvent(int position){
            this.position = position;
        }

        public int getPosition() {
            return position;
        }
    }
    public class WeahterLoadLocaldataEvent implements StoreChangeEvent{}
}
