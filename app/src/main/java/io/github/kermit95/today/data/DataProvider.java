package io.github.kermit95.today.data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.github.kermit95.today.data.local.LeftMenuItem;
import io.github.kermit95.today.data.local.Todo;
import io.github.kermit95.today.data.remote.model.weather.WeatherDisplay;
import io.github.kermit95.today.fluxbase.App;

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

    public static LeftMenuItem produceLeftMenuItem(String item, int picId){
        return new LeftMenuItem(item, picId);
    }

    //LeftMenuItem
    public static LeftMenuItem produceLeftMenuItem(){
        return new LeftMenuItem();
    }
}
