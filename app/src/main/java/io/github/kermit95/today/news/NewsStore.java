package io.github.kermit95.today.news;

import android.support.annotation.NonNull;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import io.github.kermit95.today.data.remote.bean.news.News;
import io.github.kermit95.today.data.remote.bean.news.NewsDisplay;
import io.github.kermit95.today.fluxbase.Action;
import io.github.kermit95.today.fluxbase.Dispatcher;
import io.github.kermit95.today.fluxbase.Store;

/**
 * Created by kermit on 16/3/26.
 */
public class NewsStore extends Store {

    private static final String TAG = "NewsStore";

    private Dispatcher mDispatcher;
    private static NewsStore instance;

    private List<NewsDisplay> mNewsDisplays;

    @NonNull
    public List<NewsDisplay> getNewsDisplays(){
        return mNewsDisplays;
    }


    protected NewsStore(Dispatcher dispatcher) {
        super(dispatcher);
        this.mDispatcher = dispatcher;
    }

    public static NewsStore getInstance(Dispatcher dispatcher){
        if (instance == null){
            instance = new NewsStore(dispatcher);
        }
        return instance;
    }

    @Subscribe
    @Override
    public void onAction(Action action) {
        switch (action.getType()){
            case NewsAction.UPDATE_NEWS:
                mNewsDisplays = (List<NewsDisplay>) action.getData().get(NewsAction.KEY_NEWSLIST);
                emitUpdateNews();
                break;
            case NewsAction.LOAD_MORE:

                emitLoadMore();
                break;
        }
        emitStoreChange();
    }


    public class NewsStoreChangeEvent implements StoreChangeEvent{}
    public class UpdateNewsEvent implements StoreChangeEvent{}
    public class LoadMoreEvent implements StoreChangeEvent{}

    public void emitUpdateNews(){
        mDispatcher.post(new UpdateNewsEvent());
    }

    public void emitLoadMore(){
        mDispatcher.post(new LoadMoreEvent());
    }

    @Override
    public StoreChangeEvent changeEvent() {
        return new NewsStoreChangeEvent();
    }
}
