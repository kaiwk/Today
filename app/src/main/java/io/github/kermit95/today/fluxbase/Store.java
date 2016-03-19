package io.github.kermit95.today.fluxbase;

/**
 * Created by kermit on 16/3/13.
 */
public abstract class Store {
    private final Dispatcher mDispatcher;

    protected Store(Dispatcher dispatcher){
        this.mDispatcher = dispatcher;
    }

    public void emitStoreChange(){
        mDispatcher.emitChange(changeEvent());
    }

    public interface StoreChangeEvent{}

    public abstract void onAction(Action action);

    public abstract StoreChangeEvent changeEvent();
}
