package com.zsf.accountbook.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zsf.accountbook.R;
import com.zsf.accountbook.adapter.ToolsAdapter;
import com.zsf.accountbook.model.Picture;

import java.util.ArrayList;
import java.util.List;

/**
 * @author EWorld
 * @date 2018/3/30
 * @e-mail 852333743@qq.com
 */

public class ToolsActivity extends AppCompatActivity{
    private RecyclerView mToolsList;
    private ToolsAdapter mToolsAdapter;
    private List<Picture> mPictures;
    private TextView mTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);
        initData();
        initView();

    }

    private void initData() {
        mPictures = new ArrayList<>();
        Picture picture = new Picture(R.mipmap.ic_launcher, "计算器");
        Picture picture2 = new Picture(R.mipmap.ic_launcher,"汇率计算");
        mPictures.add(picture);
        mPictures.add(picture2);
    }

    private void initView() {
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTitle = (TextView) findViewById(R.id.tv_title);
        mTitle.setText("我的工具");

        mToolsList = (RecyclerView) findViewById(R.id.tool_list);
        mToolsAdapter = new ToolsAdapter(this, mPictures);

        mToolsList.setLayoutManager(new GridLayoutManager(this, 3));
        mToolsList.setAdapter(mToolsAdapter);

    }

}
