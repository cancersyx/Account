package com.zsf.accountbook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.zsf.accountbook.R;
import com.zsf.accountbook.dao.ExpendDao;
import com.zsf.accountbook.dao.IncomeDao;
import com.zsf.accountbook.model.ExpendTable;
import com.zsf.accountbook.model.IncomeTable;


/**
 * Created by zsf
 * 2017/9/21
 * describe:
 */
@Deprecated
public class InfoDetailsActivity extends BaseActivity{
    private TextView mDeleteTxt;
    private TextView mTitle;
    private EditText mMoney;
    private EditText mTime;
    private Spinner mChooseType;
    private EditText mHandlerOrAddress;
    private EditText mRemark;
    private Button mModify;
    private TextView mHandlerOrAddressTxt;

    private IncomeDao mIncomeDAO;
    private ExpendDao mExpendDAO;

    private String mNumber;
    private String mManageType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_details);

        initViews();
        initEvent();
        initData();
    }

    private void initViews() {
        ImageView back = (ImageView) findViewById(R.id.iv_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mTitle= (TextView) findViewById(R.id.tv_title);
        LinearLayout rightArea = (LinearLayout) findViewById(R.id.ll_title_right_area);
        rightArea.setVisibility(View.VISIBLE);
        mDeleteTxt = (TextView) findViewById(R.id.tv_right_txt);
        mDeleteTxt.setText(R.string.delete);

        mIncomeDAO = new IncomeDao(this);
        mExpendDAO = new ExpendDao(this);

        mMoney = (EditText) findViewById(R.id.et_input_money);
        mTime = (EditText) findViewById(R.id.et_input_time);
        mChooseType = (Spinner) findViewById(R.id.sp_type);
        mHandlerOrAddress = (EditText) findViewById(R.id.et_input_handler_or_address);
        mRemark = (EditText) findViewById(R.id.et_input_remark);
        mModify = (Button) findViewById(R.id.btn_save);
        mHandlerOrAddressTxt = (TextView) findViewById(R.id.tv_handler_or_address);


    }

    private void initEvent() {
        mDeleteTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mManageType.equals("income_info_type")){
                    IncomeTable incomeTable = new IncomeTable();
                    incomeTable.setId(Integer.parseInt(mNumber));
                    incomeTable.setMoney(Double.parseDouble(mMoney.getText().toString()));
                    incomeTable.setTime(mTime.getText().toString());
                    incomeTable.setType(mChooseType.getSelectedItem().toString());
                    incomeTable.setHandler(mHandlerOrAddress.getText().toString());
                    incomeTable.setRemark(mRemark.getText().toString());
                    mIncomeDAO.update(incomeTable);
                }else if (mManageType.equals("expend_info_type")){
                    ExpendTable expendTable = new ExpendTable();
                    expendTable.setId(Integer.parseInt(mNumber));
                    expendTable.setMoney(Double.parseDouble(mMoney.getText().toString()));
                    expendTable.setTime(mTime.getText().toString());
                    expendTable.setType(mChooseType.getSelectedItem().toString());
                    expendTable.setAddress(mHandlerOrAddress.getText().toString());
                    expendTable.setRemark(mRemark.getText().toString());
                    mExpendDAO.update(expendTable);
                }
                Toast.makeText(InfoDetailsActivity.this, R.string.modify_success, Toast.LENGTH_SHORT).show();
            }
        });

        mDeleteTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mManageType.equals("income_info_type")){
                    mIncomeDAO.delete(Integer.parseInt(mNumber));
                }else if (mManageType.equals("expend_info_type")){
                    mExpendDAO.delete(Integer.parseInt(mNumber));
                }

                Toast.makeText(InfoDetailsActivity.this, R.string.delete_success, Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String[] infos = bundle.getStringArray("details_info");
        mNumber = infos[0];
        mManageType = infos[1];
        if (mManageType.equals("income_info_type")){
            mTitle.setText("收入管理");
            IncomeTable incomeTable = mIncomeDAO.find(Integer.parseInt(mNumber));
            mMoney.setText(String.valueOf(incomeTable.getMoney()));
            mTime.setText(incomeTable.getTime());
            mChooseType.setPrompt(incomeTable.getType());
            mHandlerOrAddressTxt.setText("付款方:");
            mHandlerOrAddress.setText(incomeTable.getHandler());
            mRemark.setText(incomeTable.getRemark());

        }else if (mManageType.equals("expend_info_type")){
            mTitle.setText("支出管理");
            ExpendTable expendTable = mExpendDAO.find(Integer.parseInt(mNumber));
            mMoney.setText(String.valueOf(expendTable.getMoney()));
            mTime.setText(expendTable.getTime());
            mChooseType.setPrompt(expendTable.getType());
            mHandlerOrAddressTxt.setText("地点:");
            mHandlerOrAddress.setText(expendTable.getAddress());
            mRemark.setText(expendTable.getRemark());


        }
    }
}
