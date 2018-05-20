package com.zsf.accountbook.activity;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.zsf.accountbook.R;
import com.zsf.accountbook.dao.ExpendDao;
import com.zsf.accountbook.fragment.DatePickerFragment;
import com.zsf.accountbook.model.ExpendTable;

import java.util.Calendar;



/**
 * Created by zsf
 * 2017/9/20
 * describe:新增支出界面
 */

public class AddExpendActivity extends BaseActivity {
    public static final String DATE_PICKER = "datePicker";
    private EditText mInputMoney;
    private TextView mShowTime;
    private Spinner mChooseType;
    private EditText mInputAddress;
    private EditText mInputRemark;
    private Button mSave;

    private int mYear;
    private int mMonth;
    private int mDay;

    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_expend);

        initData();
        initViews();
        initEvent();
        //初始化定位
        initLocation();
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
        title.setText(R.string.new_add_expend);

        LinearLayout rightLayout = (LinearLayout) findViewById(R.id.ll_title_right_area);
        rightLayout.setVisibility(View.VISIBLE);
        TextView rightTxt = (TextView) findViewById(R.id.tv_right_txt);
        rightTxt.setTextColor(getResources().getColor(R.color.white));
        rightTxt.setText(R.string.reset);
        rightTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetInputValue();
            }
        });

        mInputMoney = (EditText) findViewById(R.id.et_input_money);
        mShowTime = (TextView) findViewById(R.id.tv_show_time);
        mChooseType = (Spinner) findViewById(R.id.sp_type);
        mInputAddress = (EditText) findViewById(R.id.et_input_address);
        mInputRemark = (EditText) findViewById(R.id.et_input_remark);
        mSave = (Button) findViewById(R.id.btn_save);

        updateTimeDisplay();
        // TODO: 2017/9/26 增加自动定位功能
        updateAddressDisplay();

    }

    private void initEvent() {
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String money = mInputMoney.getText().toString();
                if (!money.isEmpty()) {
                    ExpendDao expendDao = new ExpendDao(AddExpendActivity.this);
                    ExpendTable expendTable = new ExpendTable(expendDao.getMaxId() + 1, Double.parseDouble(money),
                            mShowTime.getText().toString(), mChooseType.getSelectedItem().toString(),
                            mInputAddress.getText().toString(), mInputRemark.getText().toString());
                    expendDao.add(expendTable);
                    Toast.makeText(AddExpendActivity.this, R.string.add_to_success, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddExpendActivity.this,MyExpendInfoActivity.class));
                    finish();
                } else {
                    Toast.makeText(AddExpendActivity.this, R.string.input_money_symbol, Toast.LENGTH_SHORT).show();
                }
            }
        });

        mShowTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog();
            }
        });
    }

    private void resetInputValue() {
        mInputMoney.setText("");
        mShowTime.setText("");
        updateTimeDisplay();
        mChooseType.setSelection(0);
        mInputAddress.setText("");
        mInputRemark.setText("");

    }

    private void initData(){
        Calendar calendar = Calendar.getInstance();//获取系统当前时间
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
    }

    private void updateTimeDisplay() {
        mShowTime.setText(new StringBuilder().append(mYear).append("-").append(mMonth + 1).append("-")
                .append(mDay));
    }

    private void initLocation(){
        Log.d("zsf","======================================");
        //初始化client
        locationClient = new AMapLocationClient(this.getApplicationContext());
        locationOption = getDefaultOption();
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
    }

    /**
     * 默认的定位参数
     * @since 2.8.0
     * @author hongming.wang
     *
     */
    private AMapLocationClientOption getDefaultOption(){
        Log.d("zsf", "getDefaultOption: ");
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(true);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        return mOption;
    }

    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            String city = location.getCity();
            Log.d("zsf","city = " + city);
            mInputAddress.setText(city);
            Toast.makeText(AddExpendActivity.this,"city = " + city,Toast.LENGTH_SHORT).show();

        }
    };

    /**
     * 显示最新位置
     */
    private void updateAddressDisplay(){

    }

    private void showTimeDialog() {
        DialogFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.show(getFragmentManager(), DATE_PICKER);
    }
}
