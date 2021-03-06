package com.zsf.accountbook.fragment;

import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zsf.accountbook.R;
import com.zsf.accountbook.activity.MainNewActivity;
import com.zsf.accountbook.adapter.CostListAdapter;
import com.zsf.accountbook.db.DatabaseHelper;
import com.zsf.accountbook.model.CostBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsf.
 * 收入
 */
@Deprecated
public class IncomeFragment extends Fragment {
    public static final String MONEY = "money";
    public static final String CATEGORY = "category";
    public static final String REMARK = "remark";
    public static final String DATE_PICKER = "datePicker";
    private EditText mInputMoney;
    private TextView mDate;
    private View view;
    private TextView mCategoryTxt;
    private Button mOkBtn;

    private DatabaseHelper mDatabase;
    private List<CostBean> mCostBeanList;
    private CostListAdapter mAdapter;

    private Button mIncomeButton;
    private EditText mRemarkEdt;

    private String[] arrs = {"工资","兼职","奖金","利息"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_income, container, false);
        initView();
        initData();
        initEvent();
        getData();

        return view;
    }


    private void initView() {
        mInputMoney = (EditText) view.findViewById(R.id.et_income_input_money);
        mDate = (TextView) view.findViewById(R.id.tv_time);
        mCategoryTxt = (TextView) view.findViewById(R.id.tv_category);
        mOkBtn = (Button) view.findViewById(R.id.btn_ok);
        mRemarkEdt = (EditText) view.findViewById(R.id.et_remark);

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_mark_account,null,false);
        mIncomeButton = (Button) view.findViewById(R.id.btn_income);

    }

    private void initData() {
        //默认显示系统时间
        long sysTime = System.currentTimeMillis();
        CharSequence sysTimeStr = DateFormat.format("yyyy-MM-dd", sysTime);
        mDate.setText(sysTimeStr);

        mDatabase = new DatabaseHelper(getActivity());
        mCostBeanList = new ArrayList<>();
        mAdapter = new CostListAdapter(getActivity(), mCostBeanList);

        mCategoryTxt.setText(getString(R.string.salary));
    }

    private void initEvent() {
        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog();
            }
        });

        mCategoryTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSpinnerView();
            }
        });

        mOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ("".equals(mInputMoney.getText().toString().trim()) ||
                        mInputMoney.getText().toString().trim().equals("0")){
                    Toast.makeText(getActivity(),"金额不能为0",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    CostBean costBean = new CostBean();
                    if (mCategoryTxt.getText().equals("")){
                        costBean.costCategory = mCategoryTxt.getHint().toString();
                    }else {
                        costBean.costCategory = mCategoryTxt.getText().toString();
                    }
                    costBean.costMoney = mInputMoney.getText().toString();
                    costBean.costDate = mDate.getText().toString();
                    costBean.costType = mIncomeButton.getText().toString();
                    costBean.costRemark = mRemarkEdt.getText().toString();
                    mDatabase.insertCost(costBean);
                    mCostBeanList.add(costBean);
                    mAdapter.notifyDataSetChanged();
                    startActivity(new Intent(getActivity(), MainNewActivity.class));
                    finish();
                }

            }
        });

        mInputMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String str = mInputMoney.getText().toString().trim();

                if(str.indexOf('0')==0){
                    Toast.makeText(getActivity(), "请输入正确数字", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    private void showSpinnerView() {
        new AlertDialog.Builder(getActivity())
                //设置对话框图标
                .setIcon(R.drawable.warning)
                //设置对话标题
                .setTitle(getString(R.string.choose_item))
                //设置对话框显示的view
                .setItems(arrs, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        mCategoryTxt.setText(arrs[which].toString());
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }

    private void getData(){
        Intent intent = getActivity().getIntent();
        mInputMoney.setText(intent.getStringExtra(MONEY));
        mCategoryTxt.setText(intent.getStringExtra(CATEGORY));
        mRemarkEdt.setText(intent.getStringExtra(REMARK));
    }
    private void showTimeDialog() {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), DATE_PICKER);
    }

    private void finish(){
        getActivity().onBackPressed();
    }
}
