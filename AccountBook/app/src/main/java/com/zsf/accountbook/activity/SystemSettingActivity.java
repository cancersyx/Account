package com.zsf.accountbook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zsf.accountbook.R;


/**
 * Created by zsf
 * 2017/9/20
 * describe:
 */

public class SystemSettingActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_setting);

        initViews();
        initEvents();
    }

    private void initEvents() {
        findViewById(R.id.rl_setting_num_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SystemSettingActivity.this,SettingPasswordActivity.class));
            }
        });

        findViewById(R.id.rl_setting_lock_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SystemSettingActivity.this,SettingLockActivity.class));
            }
        });

        findViewById(R.id.rl_about_me).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SystemSettingActivity.this,AboutMeActivity.class));
            }
        });

        //换肤
        findViewById(R.id.rl_setting_theme).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SystemSettingActivity.this, ShowThemeListActivity.class));
            }
        });

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
        title.setText("系统设置");


    }


}
