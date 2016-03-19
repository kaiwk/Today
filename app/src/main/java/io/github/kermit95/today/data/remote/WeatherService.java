package io.github.kermit95.today.data.remote;

import io.github.kermit95.today.data.remote.model.weather.Weather;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by kermit on 16/3/15.
 */
public interface WeatherService {

    String BASE_URL = "http://apis.baidu.com/heweather/weather/";

    String HEADER_KEY = "apikey";
    String HEADER = "989b65572c74e2f1045fdc80fa7f0027";

    @GET("free")
    Call<Weather> getWeather(@Query("city") String city);

    final class Factory{
        private static WeatherService sService;
        public static WeatherService getInstance(){
            if (sService == null){
                OkHttpClient okHttpClient = new OkHttpClient.Builder()
                        .addInterceptor((chain) -> {
                        Request request = chain
                                .request()
                                .newBuilder()
                                .addHeader(HEADER_KEY, HEADER)
                                .build();
                        return chain.proceed(request);
                    }).build();

                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okHttpClient)
                        .baseUrl(BASE_URL)
                        .build();
                sService = retrofit.create(WeatherService.class);
            }
            return sService;
        }



    }

}
