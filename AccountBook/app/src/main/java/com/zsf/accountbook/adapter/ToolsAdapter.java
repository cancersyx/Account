package com.zsf.accountbook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zsf.accountbook.R;
import com.zsf.accountbook.model.Picture;

import java.util.ArrayList;
import java.util.List;

/**
 * @author EWorld
 * @date 2018/3/30
 * @e-mail 852333743@qq.com
 */

public class ToolsAdapter extends RecyclerView.Adapter<ToolsAdapter.MyHolder> {

    private Context mContext;
    private List<Picture> mDataList = new ArrayList<>();

    public ToolsAdapter(Context context, List<Picture> dataList) {
        mContext = context;
        mDataList = dataList;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_main,parent,false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        holder.pic.setBackgroundResource(mDataList.get(position).getImageId());
        holder.desc.setText(mDataList.get(position).getDesc());
        holder.pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"点击的是：" + position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        public ImageView pic;
        public TextView desc;

        public MyHolder(View itemView) {
            super(itemView);
            pic = (ImageView) itemView.findViewById(R.id.iv_pic);
            desc = (TextView) itemView.findViewById(R.id.tv_desc);
        }
    }

}
