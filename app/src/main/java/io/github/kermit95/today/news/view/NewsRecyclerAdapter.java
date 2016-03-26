package io.github.kermit95.today.news.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import java.util.List;

import io.github.kermit95.baserecycler.BaseRecyclerAdapter;
import io.github.kermit95.baserecycler.BaseViewHolder;
import io.github.kermit95.today.data.remote.bean.news.NewsDisplay;

/**
 * Created by kermit on 16/3/26.
 */
public class NewsRecyclerAdapter extends BaseRecyclerAdapter<NewsDisplay> {

    private static final String TAG = "NewsRecyclerAdapter";

    public NewsRecyclerAdapter(@NonNull Context context) {
        super(context);
    }

    public NewsRecyclerAdapter(@NonNull Context context, @NonNull NewsDisplay[] dataArray) {
        super(context, dataArray);
    }

    public NewsRecyclerAdapter(@NonNull Context context, @NonNull List<NewsDisplay> dataList) {
        super(context, dataList);
    }

    @NonNull
    @Override
    protected BaseViewHolder OnCreateViewHolder(ViewGroup viewGroup, int i) {
        BaseViewHolder baseViewHolder;
        switch (i){
            case NewsDisplay.WITH_IMG: baseViewHolder = new NewsWithImgVH(viewGroup); break;
            case NewsDisplay.NO_IMG: baseViewHolder = new NewsNoImgVH(viewGroup); break;
            default: throw new NullPointerException("Please check your itemview type or viewholder!");
        }
        return baseViewHolder;
    }



}
