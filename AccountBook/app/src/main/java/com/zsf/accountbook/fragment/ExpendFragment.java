package com.zsf.accountbook.fragment;

import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.zsf.accountbook.R;

/**
 * Created by zsf on 2017/3/3.
 * 支出
 */

public class ExpendFragment extends Fragment {
    private EditText mInputMoney;
    private TextView mTime;
    private Spinner mCategory;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_expend, container, false);
        initView();
        initEvent();
        return view;
    }

    private void initView() {
        mInputMoney = (EditText) view.findViewById(R.id.et_input_money);
        mTime = (TextView) view.findViewById(R.id.tv_time);
        mCategory = (Spinner) view.findViewById(R.id.sp_category);
    }

    private void initEvent() {
        mTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"点击了时间",Toast.LENGTH_SHORT).show();
                showTimeDialog();
            }
        });
    }

    private void showTimeDialog() {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");

    }

}
