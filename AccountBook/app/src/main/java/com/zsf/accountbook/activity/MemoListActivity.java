package com.zsf.accountbook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zsf.accountbook.R;
import com.zsf.accountbook.adapter.MemoAdapter;
import com.zsf.accountbook.dao.MemoDao;
import com.zsf.accountbook.model.MemoTable;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zsf
 * 2017/9/20
 * describe:
 */

public class MemoListActivity extends AppCompatActivity {

    private FloatingActionButton mFloatActionBtn;
    private ListView mMemoListView;
    private List<MemoTable> mMemoDataList;
    private MemoAdapter mMemoAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_list);

        initData();
        initViews();
        initEvent();

    }

    private void initData() {
        mMemoDataList = new ArrayList<>();
        MemoDao memoDao = new MemoDao(this);
        mMemoDataList = memoDao.getScrollData(0, (int) memoDao.getCount());
    }

    private void initViews() {
        mFloatActionBtn = (FloatingActionButton) findViewById(R.id.fat_add);
        ImageView back = (ImageView) findViewById(R.id.iv_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView title = (TextView) findViewById(R.id.tv_title);
        title.setText(R.string.memo);

        mMemoListView = (ListView) findViewById(R.id.lv_memo);
        mMemoAdapter = new MemoAdapter(this, mMemoDataList);
        mMemoListView.setAdapter(mMemoAdapter);

    }

    private void initEvent() {
        mFloatActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MemoListActivity.this, AddNewMemoActivity.class), 1);
            }
        });

        // TODO: 2018/3/18 ListView修改为RecyclerView并且采用瀑布流的形式展示memo
        mMemoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView memoNumberTxt = (TextView) view.findViewById(R.id.tv_memo_number);
                String memoNumberInfo = memoNumberTxt.getText().toString();
                Intent intent = new Intent(MemoListActivity.this, MemoDetailsActivity.class);
                intent.putExtra("memo_details", memoNumberInfo);
                startActivity(intent);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                Toast.makeText(MemoListActivity.this, "dddddddd", Toast.LENGTH_SHORT).show();
                mMemoAdapter.notifyDataSetChanged();
            }
        }
    }
}
