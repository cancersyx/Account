package com.zsf.accountbook.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.zsf.accountbook.R;
import com.zsf.accountbook.fragment.ExpendFragment;
import com.zsf.accountbook.fragment.IncomeFragment;

/**
 * Created by zsf on 2017/3/3.
 *
 */

public class MarkAccountActivity extends Activity implements View.OnClickListener {

    private Button incomeBtn;
    private Button expendBtn;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_account);

        incomeBtn = (Button) findViewById(R.id.btn_income);
        expendBtn = (Button) findViewById(R.id.btn_expend);
        linearLayout = (LinearLayout) findViewById(R.id.ll_fragment_container);

        incomeBtn.setOnClickListener(this);
        expendBtn.setOnClickListener(this);

        initExpendFragment();//先展示的是支出片段
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_income:
                expendBtn.setBackground(getResources().getDrawable(R.drawable.button_normal_bg));
                initIncomeFragment();
                break;
            case R.id.btn_expend:
                incomeBtn.setBackground(getResources().getDrawable(R.drawable.button_normal_bg));
                initExpendFragment();
                break;
            default:
                break;
        }
    }

    private void initIncomeFragment() {
        incomeBtn.setBackground(getResources().getDrawable(R.drawable.stroke_blue));
        IncomeFragment incomeFragment = new IncomeFragment();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.ll_fragment_container,incomeFragment);
        transaction.commit();
    }

    private void initExpendFragment() {
        expendBtn.setBackground(getResources().getDrawable(R.drawable.stroke_blue));
        ExpendFragment expendFragment = new ExpendFragment();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.ll_fragment_container,expendFragment);
        transaction.commit();
    }

}
