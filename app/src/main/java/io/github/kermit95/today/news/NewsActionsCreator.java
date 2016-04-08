package io.github.kermit95.today.news;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.github.kermit95.today.data.Constants;
import io.github.kermit95.today.data.remote.bean.news.Contentlist;
import io.github.kermit95.today.data.remote.bean.news.News;
import io.github.kermit95.today.data.remote.bean.news.NewsDisplay;
import io.github.kermit95.today.data.remote.webservice.NewsService;
import io.github.kermit95.today.fluxbase.Dispatcher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kermit on 16/3/26.
 */
public class NewsActionsCreator {

    private static final String TAG = "NewsActionsCreator";

    private Dispatcher mDispatcher;
    private static NewsActionsCreator instance;

    private NewsActionsCreator(Dispatcher dispatcher){
        this.mDispatcher = dispatcher;
    }

    public static NewsActionsCreator getInstance(Dispatcher dispatcher){
        if (instance == null){
            instance = new NewsActionsCreator(dispatcher);
        }
        return instance;
    }

    public void getData(){
        update();
    }

    private void update(){
        NewsService.Factory.getInstance().getNews(Constants.News.DEFAULT_CHANNEL_ID,
                Constants.News.DEFAULT_CHANNEL_NAME,
                Constants.News.DEFAULT_TITLE, 1).enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                List<Contentlist> contentlist = response.body().getShowapiResBody().getPagebean().getContentlist();
                List<NewsDisplay> newsDisplays = DataFilter.filterNewsList(contentlist);
                mDispatcher.dispatch(NewsAction.UPDATE_NEWS, NewsAction.KEY_NEWSLIST, newsDisplays);
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Log.e(TAG, "onFailure: Oops!");
            }
        });
    }

    public void loadMore(){
        // TODO: 16/4/8 load more
    }

    private static class DataFilter{

        @NonNull
        public static List<NewsDisplay> filterNewsList(@NonNull List<Contentlist> contentlists){
            List<NewsDisplay> newsDisplays = new ArrayList<>();
            int size = contentlists.size();

            for (int i = 0; i < size; i++) {
                Contentlist content = contentlists.get(i);
                NewsDisplay news = new NewsDisplay();

                news.setDesc(content.getDesc());
                news.setLongDesc(content.getLongDesc());
                if (content.getImageurls() != null && content.getImageurls().size() != 0) {
                    news.setImgUrls(content.getImageurls());
                }

                news.setPubDate(content.getPubDate());
                news.setTitle(content.getTitle());
                news.setSource(content.getSource());
                news.setLink(content.getLink());

                newsDisplays.add(news);
            }
            return newsDisplays;
        }

    }
}
