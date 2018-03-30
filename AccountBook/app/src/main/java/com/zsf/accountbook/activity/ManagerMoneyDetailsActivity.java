package com.zsf.accountbook.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.zsf.accountbook.R;

/**
 * @author EWorld
 * @date 2018/3/30
 * @e-mail 852333743@qq.com
 */

public class ManagerMoneyDetailsActivity extends AppCompatActivity {

    private TextView mTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_money_details);
        initView();
        initData();
    }

    private void initData() {
        String title  = getIntent().getStringExtra("book_name").isEmpty() ? "" : getIntent().getStringExtra("book_name");
        mTitle.setText(title);

    }

    private void initView() {
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTitle = (TextView) findViewById(R.id.tv_title);
    }
}
