package com.zsf.accountbook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zsf.accountbook.R;
import com.zsf.accountbook.model.ExpendTable;

import java.util.List;



/**
 * Created by zsf
 * 2017/9/21
 * describe:
 */

public class MyExpendAdapter extends BaseAdapter {
    private List<ExpendTable> mDataList;
    private LayoutInflater mInflater;

    public MyExpendAdapter(Context context, List<ExpendTable> dataList){
        this.mDataList = dataList;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDataList.size();
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
            convertView = mInflater.inflate(R.layout.item_my_income_info,null);
            holder.number = (TextView) convertView.findViewById(R.id.tv_number);
            holder.type = (TextView) convertView.findViewById(R.id.tv_type);
            holder.money = (TextView) convertView.findViewById(R.id.tv_money);
            holder.date = (TextView) convertView.findViewById(R.id.tv_date);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.number.setText(mDataList.get(position).getId() + "");
        holder.type.setText(mDataList.get(position).getType());
        holder.money.setText(String.valueOf(mDataList.get(position).getMoney()) + "元");
        holder.date.setText(mDataList.get(position).getTime());


        return convertView;
    }


    public final class ViewHolder{
        public TextView number;
        public TextView type;
        public TextView money;
        public TextView date;
    }
}
