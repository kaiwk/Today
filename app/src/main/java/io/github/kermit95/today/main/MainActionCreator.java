package io.github.kermit95.today.main;

import io.github.kermit95.today.fluxbase.Dispatcher;

/**
 * Created by kermit on 16/3/19.
 */
public class MainActionCreator{

    private static MainActionCreator instance;
    private Dispatcher mDispatcher;

    public static MainActionCreator getInstance(Dispatcher dispatcher){
        if (instance == null){
            instance = new MainActionCreator(dispatcher);
        }
        return instance;
    }

    private MainActionCreator(Dispatcher dispatcher){
        this.mDispatcher = dispatcher;
    }

    public void clickMenuItem(){

    }

    public void openDrawer(){

    }

}
