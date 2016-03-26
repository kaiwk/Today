package io.github.kermit95.today.data.local.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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

import io.github.kermit95.today.data.local.bean.Todo;
import io.github.kermit95.today.main.App;

/**
 * Created by kermit on 16/3/22.
 */
public class TodoModel {

    private static final String TAG = "TodoModel";

    private TodoModel(){}

    private static class SingletonHodler{
        private static final TodoModel instance = new TodoModel();
    }

    public static TodoModel getInstance(){
        return SingletonHodler.instance;
    }

    public void save(Todo todo, String fileName, int mode){
        Writer writer = null;
        OutputStream outputStream;
        try {
            outputStream = App.getInstance().openFileOutput(fileName, mode);
            writer = new OutputStreamWriter(outputStream);
            writer.write(todo.toJSON());
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

    public void saveList(List<Todo> todoList, String fileName, int mode){
        String jsonString = new Gson().toJson(todoList);

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

    @Nullable
    public Todo read(@NonNull final String fileName){
        Todo todo = null;
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
            Log.e(TAG, "read: " + jsonString.toString());
            if (TextUtils.isEmpty(jsonString.toString())) return null;
            todo = new Gson().fromJson(jsonString.toString(), Todo.class);
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
        return todo;
    }

    public List<Todo> readList(@NonNull final String fileName){
        List<Todo> todos = null;
        BufferedReader reader = null;
        try {
            InputStream inputStream = App.getInstance().openFileInput(fileName);
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder jsonString = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null){
                jsonString.append(line);
            }

            if (TextUtils.isEmpty(jsonString)) return new ArrayList<>();

            Type type = new TypeToken<List<Todo>>(){}.getType();
            todos = new Gson().fromJson(jsonString.toString(), type);
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
        return todos;
    }
}
