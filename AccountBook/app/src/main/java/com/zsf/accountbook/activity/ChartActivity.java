package com.zsf.accountbook.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.zsf.accountbook.R;

/**
 * Created by zsf.
 */

public class ChartActivity extends Activity {
    private Button mExpendChartBtn;
    private Button mIncomeChartBtn;
    private FrameLayout mContainer;
    private FrameLayout mContainer2;//支出片段容器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart);

        initView();
        initEvent();
        mExpendChartBtn.setBackground(getResources().getDrawable(R.drawable.stroke_blue));




    }

    private void initView() {
        mExpendChartBtn = (Button) findViewById(R.id.btn_expend);
        mIncomeChartBtn = (Button) findViewById(R.id.btn_income);
        mContainer = (FrameLayout) findViewById(R.id.fl_container);
        mContainer2 = (FrameLayout) findViewById(R.id.fl_containe2);

    }

    private void initEvent() {
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
