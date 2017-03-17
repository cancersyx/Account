package com.zsf.accountbook.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.zsf.accountbook.R;
import com.zsf.accountbook.adapter.CostListAdapter;
import com.zsf.accountbook.db.DatabaseHelper;
import com.zsf.accountbook.model.CostBean;
import com.zsf.accountbook.view.ArcPercentView;
import com.zsf.accountbook.view.FlexibleListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private FlexibleListView mCostListView;
    private List<CostBean> mCostBeanList;
    private DatabaseHelper mDatabase;
    private CostListAdapter mAdapter;
    private TextView mDateTxt;
    private FloatingActionButton mFab;
    private long exitTime = 0;
    private String mStr = "";
    private String mRemarkStr = "";

    private TextView mAllExpendTxt;//所有支出
    private TextView mAllIncomeTxt;//所有收入
    private TextView mBalanceTxt;//余额
    private Map<String,Integer> table = new HashMap<>();

    private float mMealsTotalMoney = 0.0f;
    private float mShoppingTotalMoney =0.0f;
    private float mPhoneCharge = 0.0f;
    private float mOilCharge = 0.0f;
    private float mOtherMoney = 0.0f;
    private float mSalaryTotalMoney = 0.0f;
    private float mPartTimeJobTotalMoney =0.0f;
    private float mBonus = 0.0f;
    private float mInterest = 0.0f;//利息收入

    private float mIncomeSum = 0.0f;
    private float mExpendSum = 0.0f;
    private float mBalance = 0.0f;

    private ArcPercentView mArcPercentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initDate();
        initEvent();

        mAdapter = new CostListAdapter(this, mCostBeanList);
        mCostListView.setAdapter(mAdapter);

    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mCostListView = (FlexibleListView) findViewById(R.id.lv_main);
        mDateTxt = (TextView) findViewById(R.id.tv_date);
        mFab = (FloatingActionButton) findViewById(R.id.fab);

        mAllIncomeTxt = (TextView) findViewById(R.id.tv_all_income);
        mAllExpendTxt = (TextView) findViewById(R.id.tv_all_expend);
        mBalanceTxt = (TextView) findViewById(R.id.tv_balance);

        mArcPercentView = (ArcPercentView) findViewById(R.id.arc_percent_view);

        mCostBeanList = new ArrayList<>();
        mDatabase = new DatabaseHelper(this);
    }

    private void initDate() {
        Cursor cursor = mDatabase.getAllCostData();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                CostBean costBean = new CostBean();
                costBean.costCategory = cursor.getString(cursor.getColumnIndex("cost_category"));
                costBean.costDate = cursor.getString(cursor.getColumnIndex("cost_date"));
                costBean.costMoney = cursor.getString(cursor.getColumnIndex("cost_money"));
                costBean.costType = cursor.getString(cursor.getColumnIndex("cost_type"));
                costBean.costRemark = cursor.getString(cursor.getColumnIndex("cost_remark"));
                mCostBeanList.add(costBean);
            }
            cursor.close();
        }

        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        mDateTxt.setText((month + 1) + "月" + day + "日");


        generateAllValues(mCostBeanList);//处理所有的支出

        mIncomeSum = mSalaryTotalMoney + mPartTimeJobTotalMoney + mBonus + mInterest;
        mAllIncomeTxt.setText(Float.toString(mIncomeSum));
        mExpendSum = mMealsTotalMoney + mShoppingTotalMoney + mPhoneCharge + mOilCharge + mOtherMoney;
        mAllExpendTxt.setText(Float.toString(mExpendSum));
        if (mIncomeSum - mExpendSum >= 0){
            mBalance = mIncomeSum - mExpendSum;
            float temp = mBalance / mIncomeSum;
            mArcPercentView.setSweepValue(temp);
//            mArcPercentView.setShowTxt("余额:"+ Float.toString(mBalance) + "元");
            mBalanceTxt.setText("余额：\n" + Float.toString(mBalance) + "元");
        }else {
//            mArcPercentView.setShowTxt("余额：" + Float.toString(mBalance));
            mBalanceTxt.setText("余额：\n" + Float.toString(mBalance) + "元");
        }


    }

    /**
     * 处理所有的支出，进行累加
     * @param allData
     */
    private void generateAllValues(List<CostBean> allData) {
        if (allData != null){
            for (int i = 0; i < allData.size(); i++) {
                CostBean costBean = allData.get(i);
                String costCategory = costBean.costCategory;
                int costMoney = Integer.parseInt(costBean.costMoney);
                if (!table.containsKey(costCategory)){
                    table.put(costCategory,costMoney);
                }else {
                    int originMoney = table.get(costCategory);
                    table.put(costCategory,originMoney + costMoney);
                }
            }
        }


        for (int i = 0; i < allData.size(); i++) {
            if (!(table.get("早午晚餐") == null))
                mMealsTotalMoney = table.get("早午晚餐");
            if (!(table.get("购物") == null))
                mShoppingTotalMoney = table.get("购物");
            if (!(table.get("话费") == null))
                mPhoneCharge = table.get("话费");
            if (!(table.get("油费") == null))
                mOilCharge = table.get("油费");
            if (!(table.get("其他") == null))
                mOtherMoney = table.get("其他");
            if (!(table.get("工资") == null))
                mSalaryTotalMoney = table.get("工资");
            if (!(table.get("兼职") == null))
                mPartTimeJobTotalMoney = table.get("兼职");
            if (!(table.get("奖金") == null))
                mBonus = table.get("奖金");
            if (!(table.get("利息") == null))
                mInterest = table.get("利息");
        }
    }

    private void initEvent() {
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MarkAccountActivity.class));
            }
        });

        mCostListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                position = position + 1;
                Cursor localCursor = mDatabase.getAllCostData();
                if (localCursor != null && localCursor.moveToFirst()){
                    do {
                        if (localCursor.getString(
                                localCursor.getColumnIndex("cost_type")).equals("支出") && (position
                                == localCursor.getInt(localCursor.getColumnIndex("id")))){
                            mStr = "支出";
                            mRemarkStr = localCursor.getString(localCursor.getColumnIndex("cost_remark"));
                            skipToDetails(view);
                        }else if (localCursor.getString(
                                localCursor.getColumnIndex("cost_type")).equals("收入") && (position
                                == localCursor.getInt(localCursor.getColumnIndex("id")))){
                            mStr = "收入";
                            mRemarkStr = localCursor.getString(localCursor.getColumnIndex("cost_remark"));
                            skipToDetails(view);
                        }
                    } while (localCursor.moveToNext());
                }
                Toast.makeText(getApplicationContext(), "点击的是：" + position, Toast.LENGTH_SHORT).show();
                localCursor.close();
            }
        });

        mCostListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showDeleteDialog(id);
                return true;
            }
        });
    }

    private void showDeleteDialog(final long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setIcon(R.drawable.warning);
        builder.setMessage("您确定要删除该条账单吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mDatabase.deleteOneData(id);
                mAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }




    /**
     *
     * @param view
     */
    private void skipToDetails(View view) {
        TextView moneyTxt = (TextView) view.findViewById(R.id.tv_money);
        TextView categoryTxt = (TextView) view.findViewById(R.id.tv_category);
        String moneyStr = moneyTxt.getText().toString();
        String categoryStr = categoryTxt.getText().toString();
        Intent intent = new Intent(MainActivity.this, MarkAccountActivity.class);
        intent.putExtra("money", moneyStr);
        intent.putExtra("category", categoryStr);
        intent.putExtra("type", mStr);
        intent.putExtra("remark",mRemarkStr);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_chart:
                Intent intent = new Intent(MainActivity.this, ChartActivity.class);
                intent.putExtra("cost_list", (Serializable) mCostBeanList);
                startActivity(intent);
                break;
            case R.id.settings:
                startActivity(new Intent(this,SettingsActivity.class));
                break;
            case R.id.more:
                //TODO 增加更多功能的模块
                startActivity(new Intent(this,MoreActivity.class));
                break;
            case R.id.about_me:
                startActivity(new Intent(MainActivity.this,AboutMeActivity.class));
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
            return;
        } else {
            finish();
            System.exit(0);
        }
    }
}
