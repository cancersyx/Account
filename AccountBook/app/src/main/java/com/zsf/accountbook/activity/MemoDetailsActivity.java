package com.zsf.accountbook.activity;

import android.content.Intent;
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
 * describe:
 */

public class MemoDetailsActivity extends BaseActivity{
    private EditText mMemoDetails;
    private Button mModifyMemo;
    private TextView mClearMemo;
    private Button mDeleteMemo;
    private TextView mTimeInfo;

    private String mMemoNumberInfo;
    private MemoDao mMemoDao;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_details);

        initViews();
        initEvent();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        mMemoNumberInfo = bundle.getString("memo_details");
        mMemoDao = new MemoDao(this);
        MemoTable memoTable = mMemoDao.find(Integer.parseInt(mMemoNumberInfo));
        mMemoDetails.setText(memoTable.getMemoContent());
        mTimeInfo.setText(memoTable.getTime());
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
        title.setText(R.string.memo_details);

        LinearLayout rightArea = (LinearLayout) findViewById(R.id.ll_title_right_area);
        rightArea.setVisibility(View.VISIBLE);
        mClearMemo = (TextView) findViewById(R.id.tv_right_txt);
        mClearMemo.setText(R.string.clear);

        mMemoDetails = (EditText) findViewById(R.id.et_memo_details);
        mModifyMemo = (Button) findViewById(R.id.btn_modify);
        mDeleteMemo = (Button) findViewById(R.id.btn_delete_memo);
        mTimeInfo = (TextView) findViewById(R.id.tv_time_info);
    }

    private void initEvent() {
        mClearMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMemoDetails.setText("");
            }
        });

        mModifyMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemoTable memoTable = new MemoTable();
                memoTable.setId(Integer.parseInt(mMemoNumberInfo));
                memoTable.setMemoContent(mMemoDetails.getText().toString());
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                StringBuilder sb = new StringBuilder().append(year).append("年").append(month).append("月")
                        .append(day).append("日");
                memoTable.setTime(sb.toString());
                mMemoDao.update(memoTable);
                Toast.makeText(MemoDetailsActivity.this,R.string.modify_success, Toast.LENGTH_SHORT).show();
            }
        });

        mDeleteMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMemoDao.delete(Integer.parseInt(mMemoNumberInfo));
                Toast.makeText(MemoDetailsActivity.this,R.string.delete_success, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
