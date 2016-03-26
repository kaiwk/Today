package io.github.kermit95.today.data;

import io.github.kermit95.today.data.local.bean.LeftMenuItem;
import io.github.kermit95.today.data.local.bean.Todo;
import io.github.kermit95.today.data.remote.bean.weather.WeatherDisplay;

/**
 * Created by kermit on 16/3/22.
 */
public class DataProvider {

    private static final String TAG = "DataProvider";

    //Todos
    public static Todo produceTodo(){
        return new Todo();
    }

    public static Todo produceTodo(long id, String text){
        return new Todo(id, text);
    }

    //Weather
    public static WeatherDisplay produceWeather(){
        return new WeatherDisplay();
    }

    //LeftMenuItem
    public static LeftMenuItem produceLeftMenuItem(String item, int picId){
        return new LeftMenuItem(item, picId);
    }

    public static LeftMenuItem produceLeftMenuItem(){
        return new LeftMenuItem();
    }

    //News
}
