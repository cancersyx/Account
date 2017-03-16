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

public class SettingsActivity extends Activity {

    private ImageView mBack;
    private TextView mTitle;
    private TextView mChangeThemeColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initView();
        initEvent();
    }


    private void initView() {
        mBack = (ImageView) findViewById(R.id.iv_back);
        mTitle = (TextView) findViewById(R.id.tv_title);
        mTitle.setText("设置");
        mChangeThemeColor = (TextView) findViewById(R.id.tv_change_theme_color);

    }

    private void initEvent() {
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mChangeThemeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
