package io.github.kermit95.today.main.view;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.github.kermit95.today.R;
import io.github.kermit95.today.data.local.bean.LeftMenuItem;

/**
 * Created by kermit on 16/3/13.
 */
public class LeftMenuAdapter extends BaseAdapter{

    private static final int[] picIds =
            {R.drawable.schedule, R.drawable.weather, R.drawable.news};
    private static final int[] itemsName =
            {R.string.today_schedule, R.string.today_weather, R.string.today_news};

    private List<LeftMenuItem> mItemList;
    private LayoutInflater mInflater;


    public LeftMenuAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mItemList = new ArrayList<>();
        initData(context);
    }

    private void initData(Context context) {
        Resources resources = context.getResources();
        for(int i = 0; i < 3; ++i){
            LeftMenuItem item = new LeftMenuItem(resources.getString(itemsName[i]), picIds[i]);
            mItemList.add(item);
        }
    }

    @Override
    public int getCount() {
        return mItemList == null ? 0 : mItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return mItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        LeftMenuItem item = mItemList.get(position);
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_left_menu, parent, false);
            viewHolder.mImageView = (ImageView) convertView.findViewById(R.id.img_left_menu_item);
            viewHolder.mTextView = (TextView) convertView.findViewById(R.id.tv_left_menu_item);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mImageView.setImageResource(item.getPicId());
        viewHolder.mTextView.setText(item.getItem());

        return convertView;
    }

    final class ViewHolder{
        public ImageView mImageView;
        public TextView mTextView;
    }
}
