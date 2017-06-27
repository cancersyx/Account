package com.zsf.accountbook.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zsf.accountbook.R;
import com.zsf.accountbook.fragment.ExpendFragment;
import com.zsf.accountbook.fragment.IncomeFragment;

/**
 * Created by zsf.
 *
 */

public class MarkAccountActivity extends BaseActivity implements View.OnClickListener {

    public static final String TYPE = "type";
    private Button incomeBtn;
    private Button mExpendButton;
    private LinearLayout linearLayout;
    private ImageView mBack;
    private TextView mTitle;

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
        mBack = (ImageView) findViewById(R.id.iv_back);
        mTitle = (TextView) findViewById(R.id.tv_title);
        incomeBtn = (Button) findViewById(R.id.btn_income);
        mExpendButton = (Button) findViewById(R.id.btn_expend);
        linearLayout = (LinearLayout) findViewById(R.id.ll_fragment_container);

    }

    private void initData(){

    }

    private void initEvent(){
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MarkAccountActivity.this,MainActivity.class));
            }
        });
    }
    private void getData() {
        if ((getIntent() != null) && (getIntent().getStringExtra(TYPE) != null)
                && (getIntent().getStringExtra(TYPE).equals(getString(R.string.expend)))){
            initExpendFragment();

        }else if ((getIntent() != null) && (getIntent().getStringExtra(TYPE) != null)
                && (getIntent().getStringExtra(TYPE).equals(getString(R.string.income)))){
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
                overridePendingTransition(R.anim.operate_in,R.anim.operate_out);
                break;
            case R.id.btn_expend:
                incomeBtn.setBackground(getResources().getDrawable(R.drawable.button_normal_bg));
                initExpendFragment();
                overridePendingTransition(R.anim.operate_in,R.anim.operate_out);
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
        mExpendButton.setBackground(getResources().getDrawable(R.drawable.stroke_blue));
        ExpendFragment expendFragment = new ExpendFragment();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.ll_fragment_container,expendFragment);
        transaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
