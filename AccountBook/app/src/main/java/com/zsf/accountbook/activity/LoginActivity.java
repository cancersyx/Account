package com.zsf.accountbook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zsf.accountbook.R;
import com.zsf.accountbook.dao.PasswordDao;


/**
 * Created by zsf
 * 2017/9/20
 * describe:登录
 */

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText inputPassword = (EditText) findViewById(R.id.et_input_password);
        Button loginBtn = (Button) findViewById(R.id.btn_login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,MainNewActivity.class);
                PasswordDao passwordDao = new PasswordDao(LoginActivity.this);
                if (passwordDao.getCount() == 0 || passwordDao.find().getPassword().isEmpty()){
                    if (inputPassword.getText().toString().isEmpty()){
                        startActivity(intent);
                    }else {
                        Toast.makeText(LoginActivity.this,"请不要输入任何密码登录系统", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    //判断输入密码是否与数据库密码一致
                    if (passwordDao.find().getPassword().equals(inputPassword.getText().toString())){
                        startActivity(intent);
                    }else {
                        Toast.makeText(LoginActivity.this,"请输入正确的密码", Toast.LENGTH_SHORT).show();
                    }
                }
                inputPassword.setText("");
            }
        });

        Button cancelBtn = (Button) findViewById(R.id.btn_cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
