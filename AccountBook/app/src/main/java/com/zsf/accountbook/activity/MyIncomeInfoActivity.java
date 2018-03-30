package com.zsf.accountbook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.zsf.accountbook.R;
import com.zsf.accountbook.adapter.MyIncomeAdapter;
import com.zsf.accountbook.constans.AppConstants;
import com.zsf.accountbook.dao.IncomeDao;
import com.zsf.accountbook.model.IncomeTable;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by zsf
 * 2017/9/20
 * describe:
 */

public class MyIncomeInfoActivity extends BaseActivity{

    private ListView mIncomeInfoView;
    private ImageView mNoDataView;
    private List<IncomeTable> mIncomeList;
    private String mManageType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_income_info);
        initData();
        initViews();
        initEvent();
    }

    private void initData() {
        mIncomeList = new ArrayList<>();
        IncomeDao incomeDao = new IncomeDao(this);
        mIncomeList = incomeDao.getScrollData(0, (int) incomeDao.getCount());
        mManageType = "income_info_type";

    }

    private void initViews() {
        ImageView back = (ImageView) findViewById(R.id.iv_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView title = (TextView) findViewById(R.id.tv_title);
        title.setText("我的收入");

        mIncomeInfoView = (ListView) findViewById(R.id.lv_my_income_info);
        mNoDataView = (ImageView) findViewById(R.id.iv_no_data);

        LinearLayout rightLayout = (LinearLayout) findViewById(R.id.ll_title_right_area);
        rightLayout.setVisibility(View.VISIBLE);
        TextView rightTxt = (TextView) findViewById(R.id.tv_right_txt);
        rightTxt.setTextColor(getResources().getColor(R.color.white));
        rightTxt.setText("饼形图");
        rightTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2017/9/25 跳转到我的收入的饼形图界面
                Intent intent = new Intent(MyIncomeInfoActivity.this,CharNewActivity.class);
                intent.putExtra("title","我的收入");
                intent.putExtra("flag", "income_flag");
                startActivity(intent);

            }
        });

        MyIncomeAdapter adapter = new MyIncomeAdapter(this,mIncomeList);
        mIncomeInfoView.setAdapter(adapter);

        showNoData();
    }

    private void showNoData() {
        if (mIncomeList.size()==0){
            mNoDataView.setVisibility(View.VISIBLE);
        }
    }

    private void initEvent() {
        mIncomeInfoView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView number = (TextView) view.findViewById(R.id.tv_number);
                String numberInfo = number.getText().toString();
                Intent intent = new Intent(MyIncomeInfoActivity.this,InfoDetailsActivity.class);
                intent.putExtra("details_info",new String[]{numberInfo,mManageType});
                startActivity(intent);
            }
        });

    }
}
