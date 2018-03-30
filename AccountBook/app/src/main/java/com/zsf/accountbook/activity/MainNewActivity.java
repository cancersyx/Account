package com.zsf.accountbook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.zsf.accountbook.R;
import com.zsf.accountbook.adapter.PictureAdapter;
import com.zsf.accountbook.widget.CustomDialog;

/**
 * @author EWorld
 * @date 2018/3/18
 * @e-mail 852333743@qq.com
 */

public class MainNewActivity extends AppCompatActivity {

    private GridView mGridView;
    private String[] mTitles;
    private int[] mImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);
        initData();
        initViews();
        initEvent();
    }

    private void initData() {
        mTitles = new String[]{"新增支出", "新增收入", "我的支出", "我的收入",
                "数据管理", "系统设置", "便签", "帮助", "退出","小工具","理财常识"};
        mImages = new int[]{R.drawable.icon_add_expend, R.drawable.icon_add_income,
                R.drawable.icon_my_expend, R.drawable.icon_my_income, R.drawable.icon_data_manage,
                R.drawable.icon_system_setting, R.drawable.icon_memo, R.drawable.icon_help,
                R.drawable.icon_exit,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
    }

    private void initViews() {
        mGridView = (GridView) findViewById(R.id.gv_main);

        PictureAdapter adapter = new PictureAdapter(mTitles, mImages, this);
        mGridView.setAdapter(adapter);

    }

    private void initEvent() {
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch (position) {
                    case 0:
                        intent = new Intent(MainNewActivity.this, AddExpendActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.operate_in,R.anim.operate_out);
                        break;
                    case 1:
                        intent = new Intent(MainNewActivity.this, AddIncomeActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(MainNewActivity.this, MyExpendInfoActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(MainNewActivity.this, MyIncomeInfoActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(MainNewActivity.this, NewDataManageActivity.class);
                        startActivity(intent);
                        break;
                    case 5:
                        intent = new Intent(MainNewActivity.this, SystemSettingActivity.class);
                        startActivity(intent);
                        break;
                    case 6:
                        intent = new Intent(MainNewActivity.this, MemoListActivity.class);
                        startActivity(intent);
                        break;
                    case 7:
                        intent = new Intent(MainNewActivity.this, HelpActivity.class);
                        startActivity(intent);
                        break;
                    case 8:
                        CustomDialog.showDialog(MainNewActivity.this);
                        break;
                    case 9:
                        intent = new Intent(MainNewActivity.this,ToolsActivity.class);
                        startActivity(intent);
                        break;

                    case 10:
                        intent= new Intent(MainNewActivity.this,ManagerMoneyActivity.class);
                        startActivity(intent);
                        break;

                }
            }
        });
    }

}
