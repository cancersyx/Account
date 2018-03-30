package com.zsf.accountbook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zsf.accountbook.R;
import com.zsf.accountbook.model.Picture;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zsf
 * 2017/9/20
 * describe:
 */

public class PictureAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<Picture> mDataList;

    public PictureAdapter(String[] titles, int[] images, Context context){
        mDataList = new ArrayList<>();
        mInflater = LayoutInflater.from(context);
        for (int i = 0; i < images.length; i++) {
            Picture picture = new Picture(images[i],titles[i]);
            mDataList.add(picture);
        }
    }

    @Override
    public int getCount() {
        return null != mDataList ? mDataList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_main,null);
            holder.image = (ImageView) convertView.findViewById(R.id.iv_pic);
            holder.desc = (TextView) convertView.findViewById(R.id.tv_desc);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.image.setImageResource(mDataList.get(position).getImageId());
        holder.desc.setText(mDataList.get(position).getDesc());


        return convertView;
    }


    public class ViewHolder{
        public TextView desc;
        public ImageView image;
    }
}
