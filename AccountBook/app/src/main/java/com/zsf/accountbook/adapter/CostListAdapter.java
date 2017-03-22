package com.zsf.accountbook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zsf.accountbook.R;
import com.zsf.accountbook.model.CostBean;

import java.util.List;

/**
 * Created by zsf
 */

public class CostListAdapter extends BaseAdapter {
    private List<CostBean> mList;
    private Context mContext;
    private LayoutInflater mInflater;

    public CostListAdapter(Context context, final List<CostBean> list){
        mContext = context;
        mList = list;
        mInflater = LayoutInflater.from(context);

    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.list_item,null);
            holder.mTvCostCategory = (TextView) convertView.findViewById(R.id.tv_category);
            holder.mTvCostDate = (TextView) convertView.findViewById(R.id.tv_date);
            holder.mTvCostMoney = (TextView) convertView.findViewById(R.id.tv_money);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        CostBean costBean = mList.get(position);//
        holder.mTvCostCategory.setText(costBean.costCategory);
        holder.mTvCostDate.setText(costBean.costDate);
        holder.mTvCostMoney.setText(costBean.costMoney);

        return convertView;
    }


    private static class ViewHolder{
        public TextView mTvCostCategory;
        public TextView mTvCostDate;
        public TextView mTvCostMoney;
    }
}
