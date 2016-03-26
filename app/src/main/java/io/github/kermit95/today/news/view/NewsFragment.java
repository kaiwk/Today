package io.github.kermit95.today.news.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.kermit95.today.R;
import io.github.kermit95.today.data.remote.bean.news.NewsDisplay;
import io.github.kermit95.today.fluxbase.Dispatcher;
import io.github.kermit95.today.news.NewsActionsCreator;
import io.github.kermit95.today.news.NewsStore;

/**
 * Created by kermit on 16/3/26.
 */
public class NewsFragment extends Fragment {

    private static final String TAG = "NewsFragment";

    @Bind(R.id.recycler_newsfragment_newslist)
    RecyclerView mRecyclertNewslist;


    private Dispatcher mDispatcher;
    private NewsActionsCreator mActionsCreator;
    private NewsStore mNewsStore;

    //recycler
    private RecyclerView.LayoutManager mLayoutManager;
    private NewsRecyclerAdapter mAdapter;

    //data
    private List<NewsDisplay> mNewsDisplays;

    public static Fragment newInstance() {
        return new NewsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, view);

        initDependencies();

        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new NewsRecyclerAdapter(getContext());
        mRecyclertNewslist.setLayoutManager(mLayoutManager);
        mRecyclertNewslist.setAdapter(mAdapter);
        mRecyclertNewslist.setHasFixedSize(true);

        mActionsCreator.getData();

        return view;
    }

    private void initDependencies(){
        mDispatcher = new Dispatcher(EventBus.getDefault());
        mActionsCreator = NewsActionsCreator.getInstance(mDispatcher);
        mNewsStore = NewsStore.getInstance(mDispatcher);
    }

    @Override
    public void onResume() {
        super.onResume();
        mDispatcher.register(this);
        mDispatcher.register(mNewsStore);
    }

    @Override
    public void onPause() {
        super.onPause();
        mDispatcher.unregister(this);
        mDispatcher.unregister(mNewsStore);
    }

    @Subscribe
    public void onLoadMore(NewsStore.LoadMoreEvent event){
    }

    @Subscribe
    public void onUpdateNews(NewsStore.UpdateNewsEvent event){
        mNewsDisplays = mNewsStore.getNewsDisplays();
        mAdapter.addAll(mNewsDisplays);
    }

    @Subscribe
    public void onNewsStoreChange(NewsStore.NewsStoreChangeEvent event){
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
