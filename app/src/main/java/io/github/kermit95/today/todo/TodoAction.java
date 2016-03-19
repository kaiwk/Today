package io.github.kermit95.today.todo;

/**
 * Created by kermit on 16/3/13.
 */

public final class TodoAction {

    private TodoAction(){}

    public static final String TODO_CREATE = "todo-create";
    public static final String TODO_COMPLETE = "todo-complete";
    public static final String TODO_DELETE = "todo-destroy";
    public static final String TODO_DELETE_COMPLETED = "todo-destroy-completed";
    public static final String TODO_TOGGLE_COMPLETE_ALL = "todo-toggle-complete-all";
    public static final String TODO_UNDO_COMPLETE = "todo-undo-complete";
    public static final String TODO_UNDO_DELETE = "todo-undo-destroy";


    public static final String KEY_TEXT = "key-text";
    public static final String  KEY_ID = "key-id";
}
