package io.github.kermit95.today.todo.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.kermit95.today.R;
import io.github.kermit95.today.fluxbase.Dispatcher;
import io.github.kermit95.today.todo.TodoActionsCreator;
import io.github.kermit95.today.todo.TodoStore;

/**
 * Created by kermit on 16/3/14.
 */
public class TodoFragment extends Fragment {

    public static final String TAG = "TodoFragment";

    @Bind(R.id.todo_checkbox)
    CheckBox mTodoCheckbox;
    @Bind(R.id.todo_input)
    EditText mTodoInput;
    @Bind(R.id.todo_add)
    Button mTodoAdd;
    @Bind(R.id.todo_list)
    RecyclerView mTodoList;
    @Bind(R.id.todo_clear_completed)
    Button mTodoClearCompleted;
    @Bind(R.id.todo_layout)
    LinearLayout mTodoLayout;

    private Dispatcher dispatcher;
    private TodoActionsCreator mTodoActionsCreator;
    private TodoStore todoStore;
    private TodoRecyclerAdapter listAdapter;

    public static TodoFragment newInstance() {
        return new TodoFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo, container, false);
        ButterKnife.bind(this, view);
        initDependencies();

        mTodoAdd.setOnClickListener(v -> {
            addTodo();
            resetMainInput();
        });

        mTodoCheckbox.setOnClickListener(v -> checkAll());

        mTodoClearCompleted.setOnClickListener(v -> {
            clearCompleted();
            resetMainCheck();
        });


        mTodoList.setLayoutManager(new LinearLayoutManager(getContext()));
        listAdapter = new TodoRecyclerAdapter(mTodoActionsCreator);
        mTodoList.setAdapter(listAdapter);

        return view;
    }

    private void initDependencies() {
        dispatcher = Dispatcher.get(EventBus.getDefault());
        mTodoActionsCreator = TodoActionsCreator.get(dispatcher);
        todoStore = TodoStore.get(dispatcher);
    }

    private void updateUI() {
        listAdapter.setItems(todoStore.getTodoList());
    }


    @Override
    public void onResume() {
        super.onResume();
        dispatcher.register(this);
        dispatcher.register(todoStore);
    }

    @Override
    public void onPause() {
        super.onPause();
        dispatcher.unregister(this);
        dispatcher.unregister(todoStore);
    }

    private void addTodo() {
        if (validateInput()) {
            mTodoActionsCreator.create(getInputText());
        }
    }

    private void checkAll() {
        mTodoActionsCreator.toggleCompleteAll();
    }

    private void clearCompleted() {
        mTodoActionsCreator.deleteCompleted();
    }

    private void resetMainInput() {
        mTodoInput.setText("");
    }

    private void resetMainCheck() {
        if (mTodoCheckbox.isChecked()) {
            mTodoCheckbox.setChecked(false);
        }
    }

    private boolean validateInput() {
        return !TextUtils.isEmpty(getInputText());
    }

    private String getInputText() {
        return mTodoInput.getText().toString();
    }

    @Subscribe
    public void onTodoStoreChange(TodoStore.TodoStoreChangeEvent event) {
        updateUI();
    }

    @Subscribe
    public void onAddTodoEvent(TodoStore.AddTodoEvent event){
    }

    @Subscribe
    public void onDeletTodoEvent(TodoStore.DeleteTodoEvent event){
        if (todoStore.canUndo()) {
            Snackbar snackbar = Snackbar.make(mTodoLayout, "Element deleted", Snackbar.LENGTH_LONG);
            snackbar.setAction("Undo", (v) -> mTodoActionsCreator.undoDelete());
            snackbar.show();
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
