package io.github.kermit95.today.todo.view;

import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.kermit95.today.R;
import io.github.kermit95.today.data.local.model.TodoModel;
import io.github.kermit95.today.data.local.bean.Todo;
import io.github.kermit95.today.todo.TodoActionsCreator;
import io.github.kermit95.today.todo.TodoStore;

public class TodoRecyclerAdapter extends RecyclerView.Adapter<TodoRecyclerAdapter.ViewHolder> {

    private static TodoActionsCreator sTodoActionsCreator;
    private List<Todo> todos;

    public TodoRecyclerAdapter(TodoActionsCreator todoActionsCreator) {
        this.todos = TodoModel.getInstance().readList(TodoStore.TODOLIST_FILENAME);
        TodoRecyclerAdapter.sTodoActionsCreator = todoActionsCreator;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.bindView(todos.get(i));
    }

    @Override
    public int getItemCount() {
        return todos == null ? 0 : todos.size();
    }

    public void setItems(List<Todo> todos) {
        this.todos = todos;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.checkbox_itemtodo_isdone)
        CheckBox mCheckboxIsdone;
        @Bind(R.id.tv_itemtodo_todo)
        TextView mTvTodo;
        @Bind(R.id.btn_itemtodo_delete)
        Button mBtnDelete;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);

        }

        public void bindView(final Todo todo) {
            if (todo.isComplete()) {
                SpannableString spanString = new SpannableString(todo.getText());
                spanString.setSpan(new StrikethroughSpan(), 0, spanString.length(), 0);
                 mTvTodo.setText(spanString);
            } else {
                 mTvTodo.setText(todo.getText());
            }


            mCheckboxIsdone.setChecked(todo.isComplete());
            mCheckboxIsdone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sTodoActionsCreator.toggleComplete(todo);
                }
            });

            mBtnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sTodoActionsCreator.delete(todo.getId());
                }
            });
        }
    }
}
