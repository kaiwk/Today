package io.github.kermit95.today.news.view;

import android.view.ViewGroup;
import android.widget.TextView;

import io.github.kermit95.baserecycler.BaseViewHolder;
import io.github.kermit95.today.R;
import io.github.kermit95.today.data.remote.bean.news.NewsDisplay;

/**
 * Created by kermit on 16/3/27.
 */
public class NewsNoImgVH extends BaseViewHolder<NewsDisplay> {

    private static final String TAG = "NewsNoImgVH";

    TextView mTvNewsTitle;
    TextView mTvNewsDesc;
    TextView mTvNewsDate;
    TextView mTvNewsFrom;

    public NewsNoImgVH(ViewGroup parent) {
        super(parent, R.layout.item_news_noimg);
        mTvNewsFrom = findView(R.id.tv_news_item_from);
        mTvNewsDesc = findView(R.id.tv_news_item_desc);
        mTvNewsDate = findView(R.id.tv_news_item_date);
        mTvNewsTitle = findView(R.id.tv_news_item_title);
    }

    @Override
    public void setData(NewsDisplay itemData) {
        super.setData(itemData);
        mTvNewsTitle.setText(itemData.getTitle());
        mTvNewsDesc.setText(itemData.getDesc());
        mTvNewsDate.setText(itemData.getPubDate());
        mTvNewsFrom.setText(itemData.getSource());
    }
}
