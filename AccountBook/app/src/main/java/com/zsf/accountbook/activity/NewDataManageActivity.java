package com.zsf.accountbook.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zsf.accountbook.R;
import com.zsf.accountbook.fragment.ExpendDataManageFragment;
import com.zsf.accountbook.fragment.IncomeDataManageFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zsf
 * 2017/9/20
 * describe:数据管理界面
 */

public class NewDataManageActivity extends BaseActivity {
    private TextView mIncomeView;
    private TextView mExpendView;
    private ViewPager mViewPager;
    private List<Fragment> mFragmentList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_manage);

        initViews();
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
        title.setText("数据管理");

        mIncomeView = (TextView) findViewById(R.id.tv_income);
        mExpendView = (TextView) findViewById(R.id.tv_expend);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        IncomeDataManageFragment incomeFragment = new IncomeDataManageFragment();
        ExpendDataManageFragment expendFragment = new ExpendDataManageFragment();
        mFragmentList = new ArrayList<>();
        mFragmentList.add(incomeFragment);
        mFragmentList.add(expendFragment);



    }


    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return super.getPageTitle(position);
        }

        /**
         * 初始化指定页面
         * @param container
         * @param position
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }
    }





}
