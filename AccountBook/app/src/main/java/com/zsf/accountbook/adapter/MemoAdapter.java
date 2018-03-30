package com.zsf.accountbook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zsf.accountbook.R;
import com.zsf.accountbook.model.MemoTable;

import java.util.List;


/**
 * Created by zsf
 * 2017/9/21
 * describe:
 */

public class MemoAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<MemoTable> mDataList;

    public MemoAdapter(Context context, List<MemoTable> dataList) {
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
            convertView = mInflater.inflate(R.layout.item_memo,null);
            holder.memoNumber = (TextView) convertView.findViewById(R.id.tv_memo_number);
            holder.memoContent = (TextView) convertView.findViewById(R.id.tv_memo_content);
            holder.memoTime = (TextView) convertView.findViewById(R.id.tv_memo_create_time);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.memoNumber.setText(mDataList.get(position).getId() + "");
        holder.memoContent.setText(mDataList.get(position).getMemoContent());
        holder.memoTime.setText(mDataList.get(position).getTime());

        return convertView;
    }

    public class ViewHolder {
        public TextView memoNumber;
        public TextView memoContent;
        public TextView memoTime;
    }
}
