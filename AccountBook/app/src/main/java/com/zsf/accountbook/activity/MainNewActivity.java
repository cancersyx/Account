package com.zsf.accountbook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.zsf.accountbook.R;
import com.zsf.accountbook.adapter.GuideViewPagerAdapter;
import com.zsf.accountbook.adapter.PictureAdapter;
import com.zsf.accountbook.widget.CustomDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * @author EWorld
 * @date 2018/3/18
 * @e-mail 852333743@qq.com
 */

public class MainNewActivity extends AppCompatActivity {

    private GridView mGridView;
    private String[] mTitles;
    private int[] mImages;
    private ViewPager mViewPager;
    private int vpSize;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                mViewPager.setCurrentItem((mViewPager.getCurrentItem() + 1) % vpSize );
                sendEmptyMessageDelayed(0, 2000);
            }
        }
    };


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
                R.drawable.icon_exit,R.drawable.icon_tool,R.drawable.icon_manager_money};
    }

    private void initViews() {
        initBanner();
        mGridView = (GridView) findViewById(R.id.gv_main);

        PictureAdapter adapter = new PictureAdapter(mTitles, mImages, this);
        mGridView.setAdapter(adapter);

    }

    private void initBanner() {
        mViewPager = (ViewPager) findViewById(R.id.banner_view_pager);
        int[] images = {R.drawable.banner_2,R.drawable.img1,R.drawable.banner_2};
        List<View> viewList = new ArrayList<>();

        for (int i = 0; i < images.length; i++) {
            View view = getLayoutInflater().inflate(R.layout.cell_banner,null);
            ImageView bannerItem = (ImageView) view.findViewById(R.id.banner_item_view);
            bannerItem.setBackground(getResources().getDrawable(images[i]));
            viewList.add(view);
        }
        mViewPager.setAdapter(new GuideViewPagerAdapter(viewList));
        vpSize=viewList.size();
        mViewPager.setCurrentItem(0);
        mHandler.sendEmptyMessageDelayed(0, 5000);

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
