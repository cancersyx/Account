package com.zsf.accountbook.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zsf.accountbook.R;
import com.zsf.accountbook.dao.MemoDao;
import com.zsf.accountbook.model.MemoTable;

import java.util.Calendar;


/**
 * Created by zsf
 * 2017/9/21
 * describe:新增便签界面
 */

public class AddNewMemoActivity extends BaseActivity {
    private EditText mInputNewMemo;
    private Button mSaveNewMemo;
    private TextView mClearContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_memo);

        initViews();
        initEvent();
    }

    private void initViews() {
        ImageView back = (ImageView) findViewById(R.id.iv_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView title = (TextView) findViewById(R.id.tv_title);
        title.setText(R.string.new_add_memo);

        mInputNewMemo = (EditText) findViewById(R.id.et_input_new_memo);
        mSaveNewMemo = (Button) findViewById(R.id.btn_save_memo);
        LinearLayout rightArea = (LinearLayout) findViewById(R.id.ll_title_right_area);
        rightArea.setVisibility(View.VISIBLE);
        mClearContent = (TextView) findViewById(R.id.tv_right_txt);
        mClearContent.setText(R.string.clear);

    }

    private void initEvent() {
        mSaveNewMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String memoStr = mInputNewMemo.getText().toString();
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                StringBuilder timeBuilder = new StringBuilder().append(year)
                        .append("年").append(month + 1).append("月").append(day).append("日");
                if (!memoStr.isEmpty()) {
                    MemoDao memoDao = new MemoDao(AddNewMemoActivity.this);
                    MemoTable memoTable = new MemoTable(memoDao.getMaxId() + 1, memoStr,timeBuilder.toString());
                    memoDao.add(memoTable);
                    Toast.makeText(AddNewMemoActivity.this, R.string.new_add_memo_success, Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(AddNewMemoActivity.this, R.string.input_memo_content, Toast.LENGTH_SHORT).show();
                }
            }
        });

        mClearContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInputNewMemo.setText("");
            }
        });
    }
}
