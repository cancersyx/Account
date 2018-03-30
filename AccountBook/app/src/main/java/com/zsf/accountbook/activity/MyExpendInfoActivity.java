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
import com.zsf.accountbook.adapter.MyExpendAdapter;
import com.zsf.accountbook.constans.AppConstants;
import com.zsf.accountbook.dao.ExpendDao;
import com.zsf.accountbook.model.ExpendTable;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by zsf
 * 2017/9/20
 * describe:我的支出
 */

public class MyExpendInfoActivity extends BaseActivity{
    private ListView mExpendInfoView;
    private ImageView mNoDataView;
    private List<ExpendTable> mExpendList;
    private String mManageType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_expend_info);

        initData();
        initViews();
        initEvent();
    }

    private void initData() {
        mExpendList = new ArrayList<>();
        ExpendDao expendDao = new ExpendDao(this);
        mExpendList = expendDao.getScrollData(0, (int) expendDao.getCount());
        mManageType = "expend_info_type";

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
        title.setText("我的支出");

        LinearLayout rightLayout = (LinearLayout) findViewById(R.id.ll_title_right_area);
        rightLayout.setVisibility(View.VISIBLE);
        TextView rightTxt = (TextView) findViewById(R.id.tv_right_txt);
        rightTxt.setTextColor(getResources().getColor(R.color.white));
        rightTxt.setText("饼形图");
        rightTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2017/9/25 跳转到我的支出的饼形图界面
                Intent intent = new Intent(MyExpendInfoActivity.this,CharNewActivity.class);
                intent.putExtra("title","我的支出");
                intent.putExtra("flag", "expend_flag");
                startActivity(intent);

            }
        });

        mExpendInfoView = (ListView) findViewById(R.id.lv_my_expend_info);
        mNoDataView = (ImageView) findViewById(R.id.iv_no_data);

        MyExpendAdapter adapter = new MyExpendAdapter(this,mExpendList);
        mExpendInfoView.setAdapter(adapter);

        showNoData();
    }

    private void showNoData() {
        if (mExpendList.size()==0){
            mNoDataView.setVisibility(View.VISIBLE);
        }
    }

    private void initEvent(){
        mExpendInfoView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView number = (TextView) view.findViewById(R.id.tv_number);
                String numberInfo = number.getText().toString();
                Intent intent = new Intent(MyExpendInfoActivity.this,InfoDetailsActivity.class);
                intent.putExtra("details_info",new String[]{numberInfo,mManageType});
                startActivity(intent);
            }
        });
    }
}
