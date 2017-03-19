package com.zsf.accountbook.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.zsf.accountbook.R;
import com.zsf.accountbook.model.CostBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by zsf on 2017/3/14.
 */

public class IncomeChartFragment extends Fragment implements OnChartValueSelectedListener {
    private PieChart mPieChart;
    private View view;

    private Map<String, Integer> table = new TreeMap<>();
    private List<CostBean> allData;
    private List<Object> mKeys = new ArrayList<>();
    private Object key;

    private float mSalaryTotalMoney = 0.0f;
    private float mPartTimeJobTotalMoney = 0.0f;
    private float mBonus = 0.0f;
    //    private float mOtherMoney = 0.0f;
    private float mInterest = 0.0f;//利息收入

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.income_fragment_chart, container, false);

        initView();
        initEvent();

        allData = (List<CostBean>) getActivity().getIntent().getSerializableExtra("cost_list");
        generateValues(allData);

        getCategoryNumber();

        setData();
        return view;
    }

    private void initView() {
        mPieChart = (PieChart) view.findViewById(R.id.pie_chart);
        mPieChart.setUsePercentValues(true);//使用百分比
        mPieChart.getDescription().setEnabled(false);//组件是否被draw出来,右下角的标签显示
        mPieChart.setExtraOffsets(5, 10, 5, 5);//设置间距
        mPieChart.setDragDecelerationFrictionCoef(0.95f);
//        mPieChart.setCenterTextTypeface(mTfLight);//设置饼状图中间文字字体
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
        mPieChart.setHighlightPerTapEnabled(true);//显示点击高亮
    }

    private void initEvent() {
        // add a selection listener
        mPieChart.setOnChartValueSelectedListener(this);

    }

    /**
     * 获得项目数量，即table中key的数量
     */
    private void getCategoryNumber() {
        Set entries = table.entrySet();
        if (entries != null) {
            Iterator iterator = entries.iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                key = entry.getKey();
                mKeys.add(key);
            }
        }
    }

    private void setData() {
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < allData.size(); i++) {
            if (!(table.get(getString(R.string.salary)) == null))
                mSalaryTotalMoney = table.get(getString(R.string.salary));
            if (!(table.get(getString(R.string.part_time_job)) == null))
                mPartTimeJobTotalMoney = table.get(getString(R.string.part_time_job));
            if (!(table.get(getString(R.string.bonus)) == null))
                mBonus = table.get(getString(R.string.bonus));
            if (!(table.get(getString(R.string.interest)) == null))
                mInterest = table.get(getString(R.string.interest));
        }

        if (!(mSalaryTotalMoney == 0))
            entries.add(new PieEntry(mSalaryTotalMoney, getString(R.string.salary)));
        if (!(mPartTimeJobTotalMoney == 0))
            entries.add(new PieEntry(mPartTimeJobTotalMoney, getString(R.string.part_time_job)));
        if (!(mBonus == 0))
            entries.add(new PieEntry(mBonus, getString(R.string.bonus)));
        if (!(mInterest == 0))
            entries.add(new PieEntry(mInterest, getString(R.string.interest)));

        PieDataSet dataSet = new PieDataSet(entries, getString(R.string.choose_result));
        dataSet.setSliceSpace(3f);//片之间的距离
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());//值显示形式
        data.setValueTextSize(22f);//文字大小
        data.setValueTextColor(Color.WHITE);//文字颜色
        mPieChart.setData(data);

        // undo all highlights
        mPieChart.highlightValues(null);

        mPieChart.invalidate();

        mPieChart.setCenterText(getString(R.string.total) +
                showHoleCenterText() + getString(R.string.yuan));//设置中间文字内容

    }

    /**
     * 处理数据
     * 将相同项目下的钱相加
     *
     * @param allData
     */
    private void generateValues(List<CostBean> allData) {
        if (allData != null) {
            for (int i = 0; i < allData.size(); i++) {
                CostBean costBean = allData.get(i);
                String costCategory = costBean.costCategory;
                int costMoney = Integer.parseInt(costBean.costMoney);
                if (!table.containsKey(costCategory)) {
                    table.put(costCategory, costMoney);
                } else {
                    int originMoney = table.get(costCategory);
                    table.put(costCategory, originMoney + costMoney);
                }


            }
        }

    }


    private String showHoleCenterText() {
        float sum = mSalaryTotalMoney + mPartTimeJobTotalMoney + mBonus + mInterest;
        return Float.toString(sum);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        if (e == null)
            return;
    }

    @Override
    public void onNothingSelected() {

    }
}
