package com.example.mymusic.fragment;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymusic.R;
import com.example.mymusic.adapter.TopListAdapter;
import com.example.mymusic.cogfig.Constant;
import com.example.mymusic.model.TopList;
import com.example.mymusic.utils.HttpUtil;
import com.example.mymusic.utils.JsonUtil;
import com.example.mymusic.utils.ToastUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;


public class TopListFragment extends BaseFragment implements HttpUtil.HttpCallbackListener,
        OnLoadMoreListener, OnRefreshListener {
    private List<TopList> topLists;
    private TopListAdapter topListAdapter;
    private SmartRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private ContentLoadingProgressBar loadingProgressBar;

    @Override
    public int getLayout() {
        return R.layout.fragment_top_list;
    }

    @Override
    public void initView() {
        refreshLayout = getActivity().findViewById(R.id.refresh);
        recyclerView = getActivity().findViewById(R.id.recycler_view);
        loadingProgressBar = getActivity().findViewById(R.id.loading_view);
        //准备数据
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        topLists = new ArrayList<>();
        topListAdapter = new TopListAdapter(mContext, topLists);

        //将adapter设置进recyclerview
        recyclerView.setAdapter(topListAdapter);
    }

    @Override
    public void initData() {
        HttpUtil.get(Constant.TOP_LIST, 0, null, this);
    }

    @Override
    public void onFinish(int requestId, String response, String cookie) {
        ((Activity) mContext).runOnUiThread(() -> {
            if (JsonUtil.getCode(response) == 200) {
                topLists.addAll(JsonUtil.getTopList(response));
                topListAdapter.notifyDataSetChanged();
                refreshLayout.finishLoadMore();
                refreshLayout.finishRefresh();
            } else {
                ToastUtil.showShortToast(mContext, String.valueOf(JsonUtil.getCode(response)));
            }
        });
    }

    @Override
    public void onFailure(Exception e) {

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }
}