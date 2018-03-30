package com.zsf.accountbook.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.zsf.accountbook.R;

/**
 * @author EWorld
 * @date 2018/3/30
 * @e-mail 852333743@qq.com
 * 图表界面
 */

public class CharNewActivity extends AppCompatActivity {

    private TextView mTitle;
    private PieChart mPieChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char_new);

        initView();
        initData();

    }

    private void initData() {
        String title = getIntent() == null ? "" : getIntent().getStringExtra("title");
        mTitle.setText(title);
        String flag = getIntent() == null ? "" : getIntent().getStringExtra("flag");
        if (flag.equals("expend_flag")) {
            showExpendPie();
        } else if (flag.equals("income_flag")) {
            showIncomePie();
        }


    }

    private void initView() {
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTitle = (TextView) findViewById(R.id.tv_title);

        initPieChart();


    }

    private void initPieChart() {
        mPieChart = (PieChart) findViewById(R.id.pie_chart);
        mPieChart.setUsePercentValues(true);//使用百分比
        mPieChart.getDescription().setEnabled(false);//组件是否被draw出来,右下角的标签显示
        mPieChart.setExtraOffsets(5, 10, 5, 5);//设置间距
        mPieChart.setDragDecelerationFrictionCoef(0.95f);
//        mPieChart.setCenterTextTypeface(mTfLight);//设置饼状图中间文字字体
//        mPieChart.setCenterText(generateCenterSpannableText());//设置中间文字内容
        mPieChart.setDrawHoleEnabled(true);//显示Chart空心
        mPieChart.setHoleColor(Color.WHITE);//圈中心空心颜色

        mPieChart.setTransparentCircleColor(Color.WHITE);//设置圆透明颜色
        mPieChart.setTransparentCircleAlpha(110);//透明度

        mPieChart.setHoleRadius(58f);
        mPieChart.setTransparentCircleRadius(61f);

        mPieChart.setDrawCenterText(true);//是否绘制中心区域文字

        mPieChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mPieChart.setRotationEnabled(true);
        mPieChart.setHighlightPerTapEnabled(true);
    }

    private void showIncomePie() {
        Log.d("zsf", "showIncomePie()");
        // TODO: 2018/3/30 显示收入饼形图

    }

    private void showExpendPie() {
        Log.d("zsf", "showExpendPie()");
        // TODO: 2018/3/30 显示支出饼形图
    }
}
