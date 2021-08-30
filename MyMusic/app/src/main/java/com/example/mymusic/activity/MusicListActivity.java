package com.example.mymusic.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymusic.R;
import com.example.mymusic.adapter.MusicListAdapter;
import com.example.mymusic.cogfig.Constant;
import com.example.mymusic.model.MusicInfo;
import com.example.mymusic.utils.HttpUtil;
import com.example.mymusic.utils.JsonUtil;
import com.example.mymusic.utils.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class MusicListActivity extends BaseActivity implements
        HttpUtil.HttpCallbackListener, OnRefreshListener, OnLoadMoreListener {
    private List<MusicInfo> musicList;
    private MusicListAdapter musicListAdapter;
    private SmartRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private ContentLoadingProgressBar loadingProgressBar;
    private String name;
    private String id;
    private TextView info;


    @Override
    public int getLayout() {
        return R.layout.activity_music_list;
    }

    @Override
    public void initView() {

        refreshLayout = findViewById(R.id.refresh);
        recyclerView = findViewById(R.id.recycler_view);
        loadingProgressBar = findViewById(R.id.loading_view);
        info = findViewById(R.id.tv_info);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        id = intent.getStringExtra("id");

        info.setText(name);

        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadMoreListener(this);

        //准备数据
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        musicList = new ArrayList<>();
        musicListAdapter = new MusicListAdapter(this,musicList);

        //将adapter设置进recyclerview
        recyclerView.setAdapter(musicListAdapter);
    }

    @Override
    public void initData() {

        HttpUtil.get(Constant.PLAYLIST_DETAILS+"?id="+id, 0, null, this);
        loadingProgressBar.show();
        loadingProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFinish(int requestId, String response, String cookie) {
        runOnUiThread(() -> {
            //当请求成功时
            if (JsonUtil.getCode(response) == 200) {
                musicList.addAll(JsonUtil.getMusic2(response));
                musicListAdapter.notifyDataSetChanged();

            }
            else {
                ToastUtil.showShortToast(this,JsonUtil.getErrorMsg(response));
            }
        });
    }

    @Override
    public void onFailure(Exception e) {
        e.printStackTrace();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }
}