package com.zsf.accountbook.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.zsf.accountbook.R;

/**
 * Created by zsf.
 */

public class ChartActivity extends BaseActivity {
    private Button mExpendChartBtn;
    private Button mIncomeChartBtn;
    private FrameLayout mContainer;
    private FrameLayout mContainer2;//支出片段容器
    private ImageView mBack;
    private TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart);

        initView();
        initEvent();
        mExpendChartBtn.setBackground(getResources().getDrawable(R.drawable.stroke_blue));




    }

    private void initView() {
        mBack = (ImageView) findViewById(R.id.iv_back);
        mTitle = (TextView) findViewById(R.id.tv_title);
        mTitle.setText(R.string.statistics);
        mExpendChartBtn = (Button) findViewById(R.id.btn_expend);
        mIncomeChartBtn = (Button) findViewById(R.id.btn_income);
        mContainer = (FrameLayout) findViewById(R.id.fl_container);
        mContainer2 = (FrameLayout) findViewById(R.id.fl_containe2);

    }

    private void initEvent() {
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mExpendChartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExpendChartBtn.setBackground(getResources().getDrawable(R.drawable.stroke_blue));
                mIncomeChartBtn.setBackground(getResources().getDrawable(R.drawable.button_normal_bg));
                mContainer2.setVisibility(View.VISIBLE);
                mContainer.setVisibility(View.GONE);
            }
        });

        mIncomeChartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIncomeChartBtn.setBackground(getResources().getDrawable(R.drawable.stroke_blue));
                mExpendChartBtn.setBackground(getResources().getDrawable(R.drawable.button_normal_bg));
                mContainer.setVisibility(View.VISIBLE);
                mContainer2.setVisibility(View.GONE);
            }
        });

    }


}
