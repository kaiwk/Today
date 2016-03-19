package io.github.kermit95.today.todo;

import io.github.kermit95.today.data.local.Todo;
import io.github.kermit95.today.fluxbase.Dispatcher;

/**
 * Created by kermit on 16/3/13.
 */
public class TodoActionsCreator {

    private static TodoActionsCreator instance;
    private final Dispatcher mDispatcher;

    private TodoActionsCreator(Dispatcher dispatcher) {
        mDispatcher = dispatcher;
    }

    public static TodoActionsCreator get(Dispatcher dispatcher){
        if (instance == null){
            instance = new TodoActionsCreator(dispatcher);
        }
        return instance;
    }

    public void create(String text){
        mDispatcher.dispatch(
                TodoAction.TODO_CREATE,
                TodoAction.KEY_TEXT, text
        );
    }

    public void delete(long id){
        mDispatcher.dispatch(
                TodoAction.TODO_DELETE,
                TodoAction.KEY_ID, id
        );
    }

    public void undoDelete(){
        mDispatcher.dispatch(TodoAction.TODO_UNDO_DELETE);
    }

    public void toggleComplete(Todo todo){
        long id = todo.getId();
        String type = todo.isComplete() ? TodoAction.TODO_UNDO_COMPLETE : TodoAction.TODO_COMPLETE;
        mDispatcher.dispatch(
                type,
                TodoAction.KEY_ID, id
        );
    }

    public void toggleCompleteAll(){
        mDispatcher.dispatch(TodoAction.TODO_TOGGLE_COMPLETE_ALL);
    }

    public void deleteCompleted(){
        mDispatcher.dispatch(TodoAction.TODO_DELETE_COMPLETED);
    }
}
