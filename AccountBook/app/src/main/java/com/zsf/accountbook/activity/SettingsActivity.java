package com.zsf.accountbook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.zsf.accountbook.R;

/**
 * Created by zsf on 2017/3/16.
 */

@Deprecated
public class SettingsActivity extends BaseActivity {

    private ImageView mBack;
    private TextView mTitle;
    private TextView mChangeThemeColor;
    private Switch mPasswordLockSwitch;
    private Switch mHintTimeSwitch;
    private TextView mChangeLock;
    private LinearLayout mLockLayout;
    private LinearLayout mHintLayout;

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
        mTitle.setText(R.string.setting);
        mChangeThemeColor = (TextView) findViewById(R.id.tv_change_theme_color);
        mPasswordLockSwitch = (Switch) findViewById(R.id.switch_password_lock);
        mHintTimeSwitch = (Switch) findViewById(R.id.switch_hint);
        mChangeLock = (TextView) findViewById(R.id.tv_change_lock);
        mLockLayout = (LinearLayout) findViewById(R.id.ll_lock);
        mHintLayout = (LinearLayout) findViewById(R.id.ll_hint);

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

        mPasswordLockSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    mPasswordLockSwitch.setChecked(true);
                    mLockLayout.setVisibility(View.VISIBLE);
                } else {
                    mPasswordLockSwitch.setChecked(false);
                    mLockLayout.setVisibility(View.GONE);
                }
            }
        });

        mHintTimeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    mHintTimeSwitch.setChecked(true);
                    mHintLayout.setVisibility(View.VISIBLE);
                } else {
                    mHintTimeSwitch.setChecked(false);
                    mHintLayout.setVisibility(View.GONE);
                }
            }
        });

        mLockLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingsActivity.this,SettingLockActivity.class));
            }
        });

    }

}
