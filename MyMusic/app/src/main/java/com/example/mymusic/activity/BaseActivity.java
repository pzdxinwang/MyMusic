package com.example.mymusic.activity;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymusic.utils.NetWorkStateReceiver;


public abstract class BaseActivity extends AppCompatActivity {
    private NetWorkStateReceiver mNetWorkStateReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        initView();
        initData();
    }
    @Override
    protected void onResume() {
        if (mNetWorkStateReceiver== null) {
            mNetWorkStateReceiver= new NetWorkStateReceiver();
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mNetWorkStateReceiver, filter);
        super.onResume();
    }

    @Override
    protected void onPause() {
        unregisterReceiver(mNetWorkStateReceiver);
        super.onPause();
    }

    /**
     * 获取布局文件
     */
    public abstract int getLayout();

    /**
     * 初始化控件
     */
    public abstract void initView();

    /**
     * 初始化数据
     */
    public abstract void initData();
}
