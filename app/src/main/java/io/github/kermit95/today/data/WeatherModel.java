package io.github.kermit95.today.data;

import android.support.annotation.NonNull;
import android.text.TextUtils;
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

import io.github.kermit95.today.data.remote.model.weather.WeatherDisplay;
import io.github.kermit95.today.fluxbase.App;

/**
 * Created by kermit on 16/3/22.
 */
public class WeatherModel {

    private static final String TAG = "WeatherModel";


    private static class SingletonHodler{
        private static final WeatherModel instance = new WeatherModel();
    }

    public static WeatherModel getInstance(){
        return SingletonHodler.instance;
    }

    public void save(WeatherDisplay weatherDisplay, String fileName, int mode){
        Writer writer = null;
        OutputStream outputStream;
        try {
            outputStream = App.getInstance().openFileOutput(fileName, mode);
            writer = new OutputStreamWriter(outputStream);
            writer.write(weatherDisplay.toJSON());
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void saveList(List<WeatherDisplay> weatherDisplays, String fileName, int mode){
        String jsonString = new Gson().toJson(weatherDisplays);

        Writer writer = null;
        OutputStream outputStream;

        try {
            outputStream = App.getInstance().openFileOutput(fileName, mode);
            writer = new OutputStreamWriter(outputStream);
            writer.write(jsonString);
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public WeatherDisplay read(@NonNull final String fileName){
        WeatherDisplay weatherDisplay = null;
        BufferedReader reader = null;
        InputStream inputStream;
        try {
            inputStream = App.getInstance().openFileInput(fileName);
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder jsonString = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null){
                jsonString.append(line);
            }
            weatherDisplay = new Gson().fromJson(jsonString.toString(), WeatherDisplay.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return weatherDisplay;
    }

    public List<WeatherDisplay> readList(@NonNull final String fileName){
        List<WeatherDisplay> weatherDisplays = null;
        BufferedReader reader = null;
        try {
            InputStream inputStream = App.getInstance().openFileInput(fileName);
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder jsonString = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null){
                jsonString.append(line);
            }

            //if the jsonString is empty return a empty list.
            if (TextUtils.isEmpty(jsonString)) return new ArrayList<>();

            Type type = new TypeToken<ArrayList<WeatherDisplay>>(){}.getType();
            weatherDisplays = new Gson().fromJson(jsonString.toString(), type);
            Log.d(TAG, "read: " + weatherDisplays.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return weatherDisplays;
    }
}
