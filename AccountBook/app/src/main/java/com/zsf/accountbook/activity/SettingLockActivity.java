package com.zsf.accountbook.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.zsf.accountbook.R;

/**
 * Created by zsf on 2017/6/28.
 */

public class SettingLockActivity extends BaseActivity{
    private ImageView mBack;
    private TextView mTitle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_lock);

        initView();

    }

    private void initView(){
        mBack = (ImageView) findViewById(R.id.iv_back);
        mTitle = (TextView) findViewById(R.id.tv_title);
        mTitle.setText(R.string.set_lock);
    }
}
