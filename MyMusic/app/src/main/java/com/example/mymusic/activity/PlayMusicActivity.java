package com.example.mymusic.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.mymusic.R;
import com.example.mymusic.cogfig.Constant;
import com.example.mymusic.interfaces.PlayerController;
import com.example.mymusic.model.MusicUrl;
import com.example.mymusic.utils.HttpUtil;
import com.example.mymusic.utils.JsonUtil;
import com.google.gson.JsonArray;

import java.util.List;

public class PlayMusicActivity extends BaseActivity implements View.OnClickListener, HttpUtil.HttpCallbackListener {


    private SeekBar playSeek;
    private ImageView ivCome;
    private ImageView ivLast;
    private ImageView ivNext;
    private ImageView ivLike;
    private ImageView ivSave;
    private ImageView ivTalk;
    private ImageView ivPlayer;
    private boolean touch = false;
    private PlayerController playerController;
    private String id;
    private List<MusicUrl> urlList;
    private String musicUrl;

    @Override
    public int getLayout() {
        return R.layout.activity_play_music;
    }

    @Override
    public void initView() {

        playSeek = findViewById(R.id.ic_play_seek);
        ivCome = findViewById(R.id.iv_play_come);
        ivLast = findViewById(R.id.iv_play_last);
        ivNext = findViewById(R.id.iv_play_next);
        ivLike = findViewById(R.id.iv_play_like);
        ivSave = findViewById(R.id.iv_play_save);
        ivTalk = findViewById(R.id.iv_play_talk);
        ivPlayer = findViewById(R.id.iv_player);

        playSeek.setOnClickListener(this);
        ivCome.setOnClickListener(this);
        ivNext.setOnClickListener(this);
        ivLast.setOnClickListener(this);
        ivLike.setOnClickListener(this);
        ivSave.setOnClickListener(this);
        ivTalk.setOnClickListener(this);


        /**
         * 进度条事件
         */
        playSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                touch = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void initData() {
        id = getIntent().getStringExtra("id");
//        Log.e("url",musicUrl);
        HttpUtil.get(Constant.SONG_URL + "?id=" + id, 0, null, this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_play_come:
                if (playerController != null) {
                    playerController.pauseOrResume();
                    startPlay();
                }
                break;
            case R.id.iv_play_next:

                break;

            case R.id.iv_play_last:

                break;

            case R.id.iv_play_like:

                break;

            case R.id.iv_play_save:

                break;

            case R.id.iv_play_talk:

                break;
        }
    }
    /**
     * 开始播放
     */
    private void startPlay() {
        //进入新的音乐
        if ( playerController!= null) {
 //           playerController.start(urlList, false);
        }
    }
    @Override
    public void onFinish(int requestId, String response, String cookie) {
        runOnUiThread(() -> {
            if (JsonUtil.getCode(response)==200){
                urlList = JsonUtil.getMusicUrl(response);
                Log.e("urllist", String.valueOf(urlList));
                MusicUrl list = urlList.get(0);
//                String url = list.getUrl();
            }


        });
    }

    @Override
    public void onFailure(Exception e) {

    }

}