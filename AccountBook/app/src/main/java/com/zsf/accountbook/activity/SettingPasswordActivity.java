package com.zsf.accountbook.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zsf.accountbook.R;
import com.zsf.accountbook.dao.PasswordDao;
import com.zsf.accountbook.model.PasswordTable;


/**
 * Created by zsf
 * 2017/9/21
 * describe:设置密码界面
 */

public class SettingPasswordActivity extends BaseActivity {
    private EditText mInputPassword;
    private Button mSure;
    private ImageView mCrossMark;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_password);
        initViews();
        initEvent();
        updateView();
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
        title.setText("设置密码");

        mInputPassword = (EditText) findViewById(R.id.et_input_password);
        mSure = (Button) findViewById(R.id.btn_sure);
        mCrossMark = (ImageView) findViewById(R.id.iv_cross_mark);


    }

    private void initEvent() {
        mSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PasswordDao passwordDao = new PasswordDao(SettingPasswordActivity.this);
                PasswordTable passwordTable = new PasswordTable(mInputPassword.getText().toString());
                if (passwordDao.getCount() == 0){
                    passwordDao.add(passwordTable);
                }else {
                    passwordDao.update(passwordTable);
                }
                Toast.makeText(SettingPasswordActivity.this,"密码设置成功!", Toast.LENGTH_SHORT).show();
            }
        });

        mCrossMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInputPassword.setText("");
                mInputPassword.setHint("请输入密码(不得超于6位)");
                updateView();
            }
        });

    }

    private void updateView(){
        if (!mInputPassword.getText().toString().trim().isEmpty()){
            mCrossMark.setVisibility(View.VISIBLE);
        }else {
            mCrossMark.setVisibility(View.GONE);
        }
    }



}
