package com.zsf.accountbook.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.zsf.accountbook.R;
import com.zsf.accountbook.dao.IncomeDao;
import com.zsf.accountbook.fragment.DatePickerFragment;
import com.zsf.accountbook.model.IncomeTable;

import java.util.Calendar;



/**
 * Created by zsf
 * 2017/9/20
 * describe:新增收入界面
 */

public class AddIncomeActivity extends BaseActivity {
    protected static final int DATE_DIALOG_ID = 0;
    public static final String DATE_PICKER = "datePicker";

    private EditText mInputMoney;
    private TextView mShowTime;
    private Spinner mChooseType;
    private EditText mInputHandler;
    private EditText mInputRemark;
    private Button mSave;
    private TextView mReset;

    private int mYear;
    private int mMonth;
    private int mDay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);
        initData();
        initViews();
        initEvents();
    }

    private void initData() {
        Calendar calendar = Calendar.getInstance();//获取系统当前时间
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
    }

    private void updateTimeDisplay() {
        mShowTime.setText(new StringBuilder().append(mYear).append("-").append(mMonth + 1).append("-")
                .append(mDay));
    }

    private void initViews() {
        ImageView back = (ImageView) findViewById(R.id.iv_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        LinearLayout rightLayout = (LinearLayout) findViewById(R.id.ll_title_right_area);
        rightLayout.setVisibility(View.VISIBLE);
        mReset = (TextView) findViewById(R.id.tv_right_txt);
        mReset.setTextColor(getResources().getColor(R.color.white));
        mReset.setText(R.string.reset);
        TextView title = (TextView) findViewById(R.id.tv_title);
        title.setText(R.string.new_add_income);

        mInputMoney = (EditText) findViewById(R.id.et_input_money);
        mShowTime = (TextView) findViewById(R.id.tv_show_time);
        // TODO: 2017/9/21 后面修改为可以自动增加类别
        mChooseType = (Spinner) findViewById(R.id.sp_type);
        mInputHandler = (EditText) findViewById(R.id.et_input_handler);
        mInputRemark = (EditText) findViewById(R.id.et_input_remark);
        mSave = (Button) findViewById(R.id.btn_save);

        updateTimeDisplay();


    }

    private void initEvents() {
        mShowTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog(DATE_DIALOG_ID);
            }
        });

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String money = mInputMoney.getText().toString();
                if (!money.isEmpty()) {
                    IncomeDao incomeDao = new IncomeDao(AddIncomeActivity.this);
                    IncomeTable incomeTable = new IncomeTable(incomeDao.getMaxId() + 1, Double.parseDouble(money),
                            mShowTime.getText().toString(), mChooseType.getSelectedItem().toString(),
                            mInputHandler.getText().toString(), mInputRemark.getText().toString());
                    incomeDao.add(incomeTable);
                    Toast.makeText(AddIncomeActivity.this, R.string.add_to_success, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddIncomeActivity.this,MyIncomeInfoActivity.class));
                    finish();
                } else {
                    Toast.makeText(AddIncomeActivity.this, R.string.input_money_symbol, Toast.LENGTH_SHORT).show();
                }
            }
        });

        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInputMoney.setText("");
                mInputMoney.setHint("0.00");
                mShowTime.setText("");
                updateTimeDisplay();
                mInputHandler.setText("");
                mInputRemark.setText("");
                mChooseType.setSelection(0);
            }
        });

        mShowTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog();
            }
        });

    }

    private void showTimeDialog() {
        DialogFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.show(getFragmentManager(), DATE_PICKER);
    }

    private void showTimeDialog(int dateDialogId) {
        onCreateDialog(dateDialogId);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener, mYear, mMonth, mDay);
        }
        return null;
    }


    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            updateTimeDisplay();
        }
    };
}
