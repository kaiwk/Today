package io.github.kermit95.today.news.view;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;

import java.util.concurrent.ExecutionException;

import io.github.kermit95.baserecycler.BaseViewHolder;
import io.github.kermit95.today.R;
import io.github.kermit95.today.data.remote.bean.news.Imageurl;
import io.github.kermit95.today.data.remote.bean.news.NewsDisplay;

/**
 * Created by kermit on 16/3/27.
 */
public class NewsWithImgVH extends BaseViewHolder<NewsDisplay> {

    private static final String TAG = "NewsWithImgVH";

    TextView mTvNewsTitle;
    ImageView mImgNews;
    TextView mTvNewsDesc;
    TextView mTvNewsDate;
    TextView mTvNewsFrom;

    public NewsWithImgVH(ViewGroup parent) {
        super(parent, R.layout.item_news_withimg);
        mTvNewsFrom = findView(R.id.tv_news_with_img_item_from);
        mImgNews = findView(R.id.img_news_with_img_item);
        mTvNewsDesc = findView(R.id.tv_news_with_img_item_desc);
        mTvNewsDate = findView(R.id.tv_news_with_img_item_date);
        mTvNewsTitle = findView(R.id.tv_news_with_img_item_title);
    }

    @Override
    public void setData(NewsDisplay itemData) {
        super.setData(itemData);

        //simple and crude
        if (itemData.getImgUrls() != null){
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    Imageurl imageurl = itemData.getImgUrls().get(0);
                    try {
                        GlideDrawable drawable = Glide.with(getContext())
                                .load(imageurl.getUrl())
                                .into(imageurl.getWidth(), imageurl.getHeight())
                                .get();
                        mImgNews.post(() -> mImgNews.setImageDrawable(drawable));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }

        mTvNewsTitle.setText(itemData.getTitle());
        mTvNewsDesc.setText(itemData.getDesc());
        mTvNewsDate.setText(itemData.getPubDate());
        mTvNewsFrom.setText(itemData.getSource());
    }
}
