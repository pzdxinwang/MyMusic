package com.example.mymusic.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mymusic.R;
import com.example.mymusic.cogfig.Constant;
import com.example.mymusic.model.MusicUrl;
import com.example.mymusic.utils.HttpUtil;
import com.example.mymusic.utils.JsonUtil;
import com.example.mymusic.utils.ToastUtil;

import java.util.List;

public class MusicUrlActivity extends BaseActivity implements HttpUtil.HttpCallbackListener {


    private String id;
    private List<MusicUrl> urlList;


    @Override
    public int getLayout() {
        return R.layout.activity_music_url;
    }

    @Override
    public void initView() {

        id = getIntent().getStringExtra("id");
        Log.e("id",id);
    }

    @Override
    public void initData() {
        HttpUtil.get(Constant.SONG_URL + "?id=" + id, 0, null, this);
    }

    @Override
    public void onFinish(int requestId, String response, String cookie) {
        runOnUiThread(() -> {
            //当请求成功时
            if (JsonUtil.getCode(response) == 200) {

            } else {
                ToastUtil.showShortToast(this, "参数错误");
            }
        });
    }

    @Override
    public void onFailure(Exception e) {

    }
}