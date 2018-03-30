package com.zsf.accountbook.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zsf.accountbook.R;
import com.zsf.accountbook.adapter.ThemeListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author EWorld
 * @date 2018/3/20
 * @e-mail 852333743@qq.com
 */

public class ShowThemeListActivity extends AppCompatActivity {

    private RecyclerView mThemeRecyclerView;
    private ThemeListAdapter mAdapter;
    private List<Integer> mDataList;
    private TextView mTitle;
    private ImageView mBack;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_thmee_list);
        initData();
        initView();
        initEvent();
    }

    private void initEvent() {

    }

    private void initData() {
        mDataList = new ArrayList<>();
        mDataList.add(getResources().getColor(R.color.colorPrimary));
        mDataList.add(getResources().getColor(R.color.green));
        Log.d("zsf", "mDataList = " + mDataList.size());
    }

    private void initView() {
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTitle = (TextView) findViewById(R.id.tv_title);
        mTitle.setText("设置主题色");

        mThemeRecyclerView = (RecyclerView) findViewById(R.id.theme_recycler_view);
        mAdapter = new ThemeListAdapter(this, mDataList);

        mThemeRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mThemeRecyclerView.setAdapter(mAdapter);
    }


}
