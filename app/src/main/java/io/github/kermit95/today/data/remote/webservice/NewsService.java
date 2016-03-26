package io.github.kermit95.today.data.remote.webservice;

import io.github.kermit95.today.data.Constants;
import io.github.kermit95.today.data.remote.bean.news.News;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by kermit on 16/3/26.
 */
public interface NewsService {

    String BASE_URL = "http://apis.baidu.com/showapi_open_bus/";
    String CHANNEL_ID = "channelId";
    String CHANNEL_NAME = "channelName";
    String TITLE = "title";
    String PAGE = "page";

    @GET("channel_news/search_news")
    Call<News> getNews(@Query(CHANNEL_ID) String channelId,
                       @Query(CHANNEL_NAME) String channelName,
                       @Query(TITLE) String title,
                       @Query(PAGE) int page);


    final class Factory{

        private static NewsService sNewsService;
        public static NewsService getInstance(){
            if (sNewsService == null){
                OkHttpClient okHttpClient = new OkHttpClient.Builder()
                        .addInterceptor(chain -> {
                            Request request = chain
                                    .request()
                                    .newBuilder()
                                    .addHeader(Constants.HEADER_KEY, Constants.HEADER)
                                    .build();
                            return chain.proceed(request);
                        }).build();

                Retrofit retrofit = new Retrofit.Builder()
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(BASE_URL)
                        .build();
                sNewsService = retrofit.create(NewsService.class);
            }
            return sNewsService;
        }
    }
}
