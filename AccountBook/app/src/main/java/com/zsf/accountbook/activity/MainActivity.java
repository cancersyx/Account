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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final String COST_CATEGORY = "cost_category";
    public static final String COST_DATE = "cost_date";
    public static final String COST_MONEY = "cost_money";
    public static final String COST_TYPE = "cost_type";
    public static final String COST_REMARK = "cost_remark";
    public static final String ID = "id";
    public static final String MONEY = "money";
    public static final String CATEGORY = "category";
    public static final String TYPE = "type";
    public static final String REMARK = "remark";
    public static final String COST_LIST = "cost_list";

    private FlexibleListView mCostListView;
    private List<CostBean> mCostBeanList;
    private DatabaseHelper mDatabase;//数据库
    private CostListAdapter mAdapter;
    private FloatingActionButton mFab;
    private long exitTime = 0;
    private String mStr = "";
    private String mRemarkStr = "";

    private TextView mAllExpendTxt;//所有支出
    private TextView mAllIncomeTxt;//所有收入
    private TextView mBalanceTxt;//余额
    private Map<String, Integer> table = new HashMap<>();

    private float mMealsTotalMoney = 0.0f;
    private float mShoppingTotalMoney = 0.0f;
    private float mPhoneCharge = 0.0f;
    private float mOilCharge = 0.0f;
    private float mOtherMoney = 0.0f;
    private float mSalaryTotalMoney = 0.0f;
    private float mPartTimeJobTotalMoney = 0.0f;
    private float mBonus = 0.0f;
    private float mInterest = 0.0f;//利息收入

    private float mIncomeSum = 0.0f;
    private float mExpendSum = 0.0f;
    private float mBalance = 0.0f;

    private ArcPercentView mArcPercentView;//自定义弧形View

    private List<String> mIdList = new ArrayList<>();

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

        mAllIncomeTxt = (TextView) findViewById(R.id.tv_all_income);
        mAllExpendTxt = (TextView) findViewById(R.id.tv_all_expend);
        mBalanceTxt = (TextView) findViewById(R.id.tv_balance);
        mArcPercentView = (ArcPercentView) findViewById(R.id.arc_percent_view);

        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mCostListView = (FlexibleListView) findViewById(R.id.lv_main);

        mCostBeanList = new ArrayList<>();
        mDatabase = new DatabaseHelper(this);
    }


    /**
     *
     */
    private void initDate() {
        Cursor cursor = mDatabase.getAllCostData();
        if (cursor != null && cursor.getCount() > 0) {
            mIdList.removeAll(mIdList);
            while (cursor.moveToNext()) {
                CostBean costBean = new CostBean();
                costBean.costCategory = cursor.getString(cursor.getColumnIndex(COST_CATEGORY));
                costBean.costDate = cursor.getString(cursor.getColumnIndex(COST_DATE));
                costBean.costMoney = cursor.getString(cursor.getColumnIndex(COST_MONEY));
                costBean.costType = cursor.getString(cursor.getColumnIndex(COST_TYPE));
                costBean.costRemark = cursor.getString(cursor.getColumnIndex(COST_REMARK));
                mCostBeanList.add(costBean);
                mIdList.add(cursor.getString(0));
            }
            cursor.close();
        }

        generateAllValues(mCostBeanList);//处理数据

        showAllExpendAndIncome();
    }

    private void showAllExpendAndIncome() {
        mIncomeSum = mSalaryTotalMoney + mPartTimeJobTotalMoney + mBonus + mInterest;
        mAllIncomeTxt.setText(Float.toString(mIncomeSum));

        mExpendSum = mMealsTotalMoney + mShoppingTotalMoney + mPhoneCharge + mOilCharge + mOtherMoney;
        mAllExpendTxt.setText(Float.toString(mExpendSum));
        if (mIncomeSum - mExpendSum >= 0) {
            mBalance = mIncomeSum - mExpendSum;
            float temp = mBalance / mIncomeSum;
            mArcPercentView.setSweepValue(temp);
            mBalanceTxt.setText(getString(R.string.balance) + Float.toString(mBalance) + getString(R.string.yuan));
        } else {
            mBalanceTxt.setText(getString(R.string.balance) + Float.toString(mBalance) + getString(R.string.yuan));
        }
    }

    /**
     * 处理所有的类别，金额进行累加
     *
     * @param allData
     */
    private void generateAllValues(List<CostBean> allData) {
        table.clear();
        if (allData != null) {
            for (int i = 0; i < allData.size(); i++) {
                CostBean costBean = allData.get(i);
                String costCategory = costBean.costCategory;
                int costMoney = Integer.valueOf(costBean.costMoney.trim());
                if (!table.containsKey(costCategory)) {
                    table.put(costCategory, costMoney);
                } else {
                    int originMoney = table.get(costCategory);
                    table.put(costCategory, originMoney + costMoney);
                }
            }
        }

        if (!(table.get(getString(R.string.meals)) == null)) {
            mMealsTotalMoney = table.get(getString(R.string.meals));
        } else {
            mMealsTotalMoney = 0.0f;
        }

        if (!(table.get(getString(R.string.shopping)) == null)) {
            mShoppingTotalMoney = table.get(getString(R.string.shopping));
        } else {
            mShoppingTotalMoney = 0.0f;
        }

        if (!(table.get(getString(R.string.phone_charge)) == null)) {
            mPhoneCharge = table.get(getString(R.string.phone_charge));
        } else {
            mPhoneCharge = 0.0f;
        }

        if (!(table.get(getString(R.string.oil_charge)) == null)) {
            mOilCharge = table.get(getString(R.string.oil_charge));
        } else {
            mOilCharge = 0.0f;
        }

        if (!(table.get(getString(R.string.other)) == null)) {
            mOtherMoney = table.get(getString(R.string.other));
        } else {
            mOtherMoney = 0.0f;
        }

        if (!(table.get(getString(R.string.salary)) == null)) {
            mSalaryTotalMoney = table.get(getString(R.string.salary));
        } else {
            mSalaryTotalMoney = 0.0f;
        }
        if (!(table.get(getString(R.string.part_time_job)) == null)) {
            mPartTimeJobTotalMoney = table.get(getString(R.string.part_time_job));
        } else {
            mPartTimeJobTotalMoney = 0.0f;
        }

        if (!(table.get(getString(R.string.bonus)) == null)) {
            mBonus = table.get(getString(R.string.bonus));
        } else {
            mBonus = 0.0f;
        }

        if (!(table.get(getString(R.string.interest)) == null)) {
            mInterest = table.get(getString(R.string.interest));
        } else {
            mInterest = 0.0f;
        }
    }

    private void initEvent() {
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MarkAccountActivity.class));
                finish();
            }
        });

        mCostListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor localCursor = mDatabase.getAllCostData();
                if (localCursor != null && localCursor.moveToFirst()) {
                    do {
                        if (localCursor.getString(localCursor.getColumnIndex(COST_TYPE))
                                .equals(getString(R.string.expend)) && (Integer.parseInt(
                                mIdList.get(position))
                                == localCursor.getInt(localCursor.getColumnIndex(ID)))) {
                            mStr = getString(R.string.expend);
                            mRemarkStr = localCursor.getString(localCursor.getColumnIndex(COST_REMARK));
                            skipToDetails(view);
                        } else if (localCursor.getString(
                                localCursor.getColumnIndex(COST_TYPE)).equals(getString(R.string.income))
                                && (Integer.parseInt(mIdList.get(position))
                                == localCursor.getInt(localCursor.getColumnIndex(ID)))) {
                            mStr = getString(R.string.income);
                            mRemarkStr = localCursor.getString(localCursor.getColumnIndex(COST_REMARK));
                            skipToDetails(view);
                        }
                    } while (localCursor.moveToNext());
                }
                localCursor.close();
            }
        });

        mCostListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showDeleteDialog(position);
                return true;
            }
        });
    }


    private void showDeleteDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.hint);
        builder.setIcon(R.drawable.warning);
        builder.setMessage(R.string.delete_message);
        builder.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mDatabase.deleteOneData(Integer.parseInt(mIdList.get(position)));
                mCostBeanList.remove(position);
                mAdapter.notifyDataSetChanged();
                table.clear();
                generateAllValues(mCostBeanList);
                showAllExpendAndIncome();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }


    /**
     * @param view
     */
    private void skipToDetails(View view) {
        TextView moneyTxt = (TextView) view.findViewById(R.id.tv_money);
        TextView categoryTxt = (TextView) view.findViewById(R.id.tv_category);
        String moneyStr = moneyTxt.getText().toString();
        String categoryStr = categoryTxt.getText().toString();
        Intent intent = new Intent(MainActivity.this, MarkAccountActivity.class);
        intent.putExtra(MONEY, moneyStr);
        intent.putExtra(CATEGORY, categoryStr);
        intent.putExtra(TYPE, mStr);
        intent.putExtra(REMARK, mRemarkStr);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_chart:
                Intent intent = new Intent(MainActivity.this, ChartActivity.class);
                intent.putExtra(COST_LIST, (Serializable) mCostBeanList);
                startActivity(intent);
                overridePendingTransition(R.anim.operate_in, R.anim.operate_out);
                break;
            case R.id.settings:
                startActivity(new Intent(this, SettingsActivity.class));
                overridePendingTransition(R.anim.operate_in,R.anim.operate_out);
                break;
//            case R.id.more:
//                //TODO 增加更多功能的模块
//                startActivity(new Intent(this, MoreActivity.class));
//                overridePendingTransition(R.anim.operate_in,R.anim.operate_out);
//                break;
            case R.id.about_me:
                startActivity(new Intent(MainActivity.this, AboutMeActivity.class));
                overridePendingTransition(R.anim.operate_in, R.anim.operate_out);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), R.string.exit_message, Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
            return;
        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
