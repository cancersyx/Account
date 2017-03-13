package com.zsf.accountbook.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.zsf.accountbook.R;
import com.zsf.accountbook.fragment.ExpendFragment;
import com.zsf.accountbook.fragment.IncomeFragment;

/**
 * Created by zsf.
 *
 */

public class MarkAccountActivity extends Activity implements View.OnClickListener {

    private Button incomeBtn;
    private Button mExpendButton;
    private LinearLayout linearLayout;

    private EditText mInputMoneyOfExpand;
    private TextView mTimeOfExpand;
    private Spinner mCategoryOfExpand;

    private EditText mInputMoneyOfIncome;
    private TextView mTimeOfIncome;
    private Spinner mCategoryOfIncome;

    private View mViewExpendFragment;//支出Fragment
    private View mViewIncomeFragment;//收入Fragment

    private EditText mExpandRemarkEdt;//支出片段的备注
    private EditText mIncomeRemarkEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_account);

        initView();
        initData();
        initEvent();
        incomeBtn.setOnClickListener(this);
        mExpendButton.setOnClickListener(this);
        initExpendFragment();//先展示的是支出片段

        getData();
    }

    private void initView() {
        incomeBtn = (Button) findViewById(R.id.btn_income);
        mExpendButton = (Button) findViewById(R.id.btn_expend);
        linearLayout = (LinearLayout) findViewById(R.id.ll_fragment_container);

        mViewExpendFragment = LayoutInflater.from(MarkAccountActivity.this).inflate(R.layout.fragment_expend,linearLayout,false);
        mViewIncomeFragment = LayoutInflater.from(MarkAccountActivity.this).inflate(R.layout.fragment_income,linearLayout,false);

        mInputMoneyOfExpand = (EditText) mViewExpendFragment.findViewById(R.id.et_input_money);
        mTimeOfExpand = (TextView) mViewExpendFragment.findViewById(R.id.tv_time);
        mCategoryOfExpand = (Spinner) mViewExpendFragment.findViewById(R.id.sp_category);
        mExpandRemarkEdt = (EditText) mViewExpendFragment.findViewById(R.id.et_remark);

        mInputMoneyOfIncome = (EditText) mViewIncomeFragment.findViewById(R.id.et_income_input_money);
        mTimeOfIncome = (TextView) mViewIncomeFragment.findViewById(R.id.tv_time);
        mCategoryOfIncome = (Spinner) mViewIncomeFragment.findViewById(R.id.sp_category);
        mIncomeRemarkEdt = (EditText) mViewIncomeFragment.findViewById(R.id.et_remark);

    }

    private void initData(){

    }

    private void initEvent(){

    }
    private void getData() {
        if ((getIntent() != null) && (getIntent().getStringExtra("type") != null)
                && (getIntent().getStringExtra("type").equals("支出"))){
            initExpendFragment();

        }else if ((getIntent() != null) && (getIntent().getStringExtra("type") != null)
                && (getIntent().getStringExtra("type").equals("收入"))){
            initIncomeFragment();
            mExpendButton.setBackground(getResources().getDrawable(R.drawable.button_normal_bg));
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_income:
                mExpendButton.setBackground(getResources().getDrawable(R.drawable.button_normal_bg));
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
        Log.d("zsf","到这里没有");
        mExpendButton.setBackground(getResources().getDrawable(R.drawable.stroke_blue));
        ExpendFragment expendFragment = new ExpendFragment();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.ll_fragment_container,expendFragment);
        transaction.commit();
    }

}
