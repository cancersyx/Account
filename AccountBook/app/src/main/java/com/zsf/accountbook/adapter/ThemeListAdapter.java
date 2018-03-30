package com.zsf.accountbook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.zsf.accountbook.R;

import java.util.List;

/**
 * @author EWorld
 * @date 2018/3/20
 * @e-mail 852333743@qq.com
 */

public class ThemeListAdapter extends RecyclerView.Adapter<ThemeListAdapter.MyViewHolder> {

    private List<Integer> mThemeColorList;
    private Context mContext;


    public ThemeListAdapter(Context context,List<Integer> themeColorList){
        this.mContext = context;
        this.mThemeColorList = themeColorList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(mContext).inflate(R.layout.item_theme_color,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mImageView.setBackgroundColor(mThemeColorList.get(position));
        holder.mRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"ddddd",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mThemeColorList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageView;
        RadioButton mRadioButton;

        public MyViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_module_theme_color);
            mRadioButton = (RadioButton) itemView.findViewById(R.id.radio);
        }
    }
}
