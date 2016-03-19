package io.github.kermit95.today.main;

import org.greenrobot.eventbus.Subscribe;

import io.github.kermit95.today.fluxbase.Action;
import io.github.kermit95.today.fluxbase.Dispatcher;
import io.github.kermit95.today.fluxbase.Store;

/**
 * Created by kermit on 16/3/19.
 */
public class MainStore extends Store {

    private static MainStore instance;
    private Dispatcher mDispatcher;


    public static MainStore getInstance(Dispatcher dispatcher){
        if (instance == null){
            instance = new MainStore(dispatcher);
        }
        return instance;
    }


    protected MainStore(Dispatcher dispatcher) {
        super(dispatcher);
        mDispatcher = dispatcher;
    }

    @Subscribe
    @Override
    public void onAction(Action action) {
        switch (action.getType()){
        }
        emitStoreChange();
    }

    @Override
    public StoreChangeEvent changeEvent() {
        return new MainStoreChangeEvent();
    }

    public class MainStoreChangeEvent implements StoreChangeEvent{}
}
