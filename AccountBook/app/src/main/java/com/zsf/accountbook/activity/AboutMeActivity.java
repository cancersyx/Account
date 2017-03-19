package com.zsf.accountbook.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zsf.accountbook.R;

/**
 * Created by zsf on 2017/3/16.
 */

public class AboutMeActivity extends Activity {

    private ImageView mBack;
    private TextView mTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);
        initView();
        initEvent();
    }

    private void initView() {
        mBack = (ImageView) findViewById(R.id.iv_back);
        mTitle = (TextView) findViewById(R.id.tv_title);
        mTitle.setText(R.string.about_me);
    }

    private void initEvent() {
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
