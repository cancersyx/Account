package com.zsf.accountbook.activity;

import android.content.Intent;
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
 * 理财常识
 */

public class ManagerMoneyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_money);

        initView();


    }

    private void initView() {
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView title = (TextView) findViewById(R.id.tv_title);
        title.setText("理财常识");


        findViewById(R.id.btn_quanxi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerMoneyActivity.this,ManagerMoneyDetailsActivity.class);
                intent.putExtra("book_name","消费者权益保护");
                startActivity(intent);
            }
        });


    }
}
