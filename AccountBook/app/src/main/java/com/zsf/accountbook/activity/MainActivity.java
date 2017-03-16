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
import com.zsf.accountbook.view.FlexibleListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
        builder.setIcon(R.drawable.hint);
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
        int id = item.getItemId();
        if (id == R.id.action_chart) {
            Intent intent = new Intent(MainActivity.this, ChartActivity.class);
            intent.putExtra("cost_list", (Serializable) mCostBeanList);
            startActivity(intent);
            return true;
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
