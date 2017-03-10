package com.zsf.accountbook.fragment;

import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.zsf.accountbook.R;
import com.zsf.accountbook.activity.MainActivity;
import com.zsf.accountbook.adapter.CostListAdapter;
import com.zsf.accountbook.db.DatabaseHelper;
import com.zsf.accountbook.model.CostBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsf on 2017/3/3.
 * 支出
 */

public class ExpendFragment extends Fragment {
    private EditText mInputMoney;
    private TextView mTime;
    private Spinner mCategory;
    private View view;
    private Button mOkBtn;

    private DatabaseHelper mDatabase;
    private List<CostBean> mCostBeanList;
    private CostListAdapter mAdapter;

    private String mChooseSpinnerStr;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_expend, container, false);
        initView();
        initData();
        initEvent();

        return view;
    }


    private void initView() {
        mInputMoney = (EditText) view.findViewById(R.id.et_input_money);
        mTime = (TextView) view.findViewById(R.id.tv_time);
        mCategory = (Spinner) view.findViewById(R.id.sp_category);
        mOkBtn = (Button) view.findViewById(R.id.btn_ok);
    }

    private void initData() {
        //默认显示系统时间
        long sysTime = System.currentTimeMillis();
        CharSequence sysTimeStr = DateFormat.format("HH:mm", sysTime);
        mTime.setText(sysTimeStr);

        mDatabase = new DatabaseHelper(getActivity());
        mCostBeanList = new ArrayList<>();
        mAdapter = new CostListAdapter(getActivity(), mCostBeanList);
    }

    private void initEvent() {
        mTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "点击了时间", Toast.LENGTH_SHORT).show();
                showTimeDialog();
            }
        });

        mOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CostBean costBean = new CostBean();
                costBean.costTitle = (String) mCategory.getSelectedItem();
                costBean.costMoney = mInputMoney.getText().toString();
                costBean.costDate = mTime.getText().toString();
                mDatabase.insertCost(costBean);
                mCostBeanList.add(costBean);
                mAdapter.notifyDataSetChanged();
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });
    }

    private void showTimeDialog() {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");


    }

}
