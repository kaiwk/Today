package io.github.kermit95.today.todo;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import io.github.kermit95.today.data.DataProvider;
import io.github.kermit95.today.data.JSONSerializer;
import io.github.kermit95.today.data.TodoModel;
import io.github.kermit95.today.data.local.Todo;
import io.github.kermit95.today.fluxbase.Action;
import io.github.kermit95.today.fluxbase.Dispatcher;
import io.github.kermit95.today.fluxbase.Store;

/**
 * Created by kermit on 16/3/13.
 */
public class TodoStore extends Store {

    private static final String TAG = "TodoStore";

    private static TodoStore sTodoStore;
    private final List<Todo> mTodoList;
    private Dispatcher mDispatcher;
    private Todo lastDeleted;


    public static TodoStore get(@NonNull Dispatcher dispatcher){
        if (sTodoStore == null){
            sTodoStore = new TodoStore(dispatcher);
        }
        return sTodoStore;
    }

    protected TodoStore(Dispatcher dispatcher) {
        super(dispatcher);
        this.mDispatcher = dispatcher;
        mTodoList = new ArrayList<>();
    }

    @Nullable
    public List<Todo> getTodoList() {
        return mTodoList;
    }

    public boolean canUndo(){
        if (lastDeleted != null){
            return true;
        }
        return false;
    }

    @Subscribe
    @Override
    public void onAction(Action action) {
        long id;
        switch (action.getType()){
            case TodoAction.TODO_CREATE:
                String text = (String) action.getData().get(TodoAction.KEY_TEXT);
                create(text);
                mDispatcher.emitChange(new AddTodoEvent());
                break;
            case TodoAction.TODO_DELETE:
                id = (long) action.getData().get(TodoAction.KEY_ID);
                delete(id);
                mDispatcher.emitChange(new DeleteTodoEvent());
                break;
            case TodoAction.TODO_UNDO_DELETE:
                undoDelete();
                break;
            case TodoAction.TODO_COMPLETE:
                id = (long) action.getData().get(TodoAction.KEY_ID);
                updateComplete(id, true);
                break;
            case TodoAction.TODO_UNDO_COMPLETE:
                id = (long) action.getData().get(TodoAction.KEY_ID);
                updateComplete(id, false);
                break;
            case TodoAction.TODO_DELETE_COMPLETED:
                deleteCompleted();
                break;
            case TodoAction.TODO_TOGGLE_COMPLETE_ALL:
                updateCompleteAll();
                break;
            default:
                throw new IllegalArgumentException("Illegal action!");
        }
        new Thread(){
            @Override
            public void run() {
                super.run();
                TodoModel.getInstance().saveList(mTodoList, TODOLIST_FILENAME, Context.MODE_PRIVATE);
            }
        }.start();
        emitStoreChange();
    }


    public static final String TODOLIST_FILENAME = "todos";
    private void create(String text){
        long id = System.currentTimeMillis();
        Todo todo = DataProvider.produceTodo(id, text);
        put(todo);
    }

    private void put(@NonNull Todo todo) {
        mTodoList.add(todo);
        Collections.sort(mTodoList);
    }

    private void delete(long id){
        Iterator<Todo> iter = mTodoList.iterator();
        while(iter.hasNext()){
            Todo todo = iter.next();
            if (todo.getId() == id){
                lastDeleted = todo.clone();
                iter.remove();
                break;
            }
        }
    }

    @Nullable
    private Todo getById(long id){
        for (Todo todo : mTodoList) {
            if (todo.getId() == id){
                return todo;
            }
        }
        return null;
    }

    private void undoDelete() {
        if (lastDeleted != null){
            put(lastDeleted.clone());
            lastDeleted = null;
        }
    }

    private void updateComplete(long id, boolean isComplete){
        Todo todo = getById(id);
        if (todo != null){
            todo.setComplete(isComplete);
        }
    }

    private void updateAllComplete(boolean isComplete){
        for (Todo todo : mTodoList){
            todo.setComplete(isComplete);
        }
    }

    private boolean areAllComplete(){
        for (Todo todo : mTodoList) {
            if (!todo.isComplete()){
                return false;
            }
        }
        return true;
    }

    private void updateCompleteAll(){
        if (areAllComplete()){
            updateAllComplete(false);
        }else{
            updateAllComplete(true);
        }
    }

    private void deleteCompleted(){
        Iterator<Todo> iter = mTodoList.iterator();
        while (iter.hasNext()){
            Todo todo = iter.next();
            if (todo.isComplete()){
                iter.remove();
            }
        }
    }


    @Override
    public StoreChangeEvent changeEvent() {
        return new TodoStoreChangeEvent();
    }

    public class TodoStoreChangeEvent implements StoreChangeEvent{}
    public class AddTodoEvent implements StoreChangeEvent{}
    public class DeleteTodoEvent implements StoreChangeEvent{}
}
