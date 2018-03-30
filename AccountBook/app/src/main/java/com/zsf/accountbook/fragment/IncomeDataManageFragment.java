package com.zsf.accountbook.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zsf.accountbook.R;


/**
 * Created by zsf
 * 2017/9/25
 * describe:
 */

public class IncomeDataManageFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_income_data_manage, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        ImageView back = (ImageView) view.findViewById(R.id.iv_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        TextView title = (TextView) view.findViewById(R.id.tv_title);
        title.setText("收入");
    }
}
