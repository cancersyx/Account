package com.zsf.accountbook.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.zsf.accountbook.R;

/**
 * Created by zsf on 2017/3/3.
 * 收入
 */

public class IncomeFragment extends Fragment {
    private EditText mInputMoney;
    private TextView mDate;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_income, container, false);
        initView();
        return view;
    }

    private void initView() {
        mInputMoney = (EditText) view.findViewById(R.id.et_input_money);
        mDate = (TextView) view.findViewById(R.id.tv_date);
    }
}
