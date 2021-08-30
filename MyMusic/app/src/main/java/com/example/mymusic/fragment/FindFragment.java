package com.example.mymusic.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.mymusic.R;
import com.example.mymusic.activity.MainActivity;
import com.example.mymusic.activity.MusicListActivity;
import com.example.mymusic.cogfig.Constant;
import com.example.mymusic.utils.HttpUtil;
import com.example.mymusic.utils.SharePreferencesUtil;
import com.example.mymusic.utils.ToastUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;

/**
 * 开始显示的那个发现页面
 */
public class FindFragment extends BaseFragment implements View.OnClickListener, HttpUtil.HttpCallbackListener {

    private EditText etSearch;
    private ImageView ivSearch;
    private String keyword;

    @Override
    public int getLayout() {
        return R.layout.fragment_find;
    }

    @Override
    public void initView() {
        etSearch = getView().findViewById(R.id.et_search);
        ivSearch = getView().findViewById(R.id.iv_search);
        ivSearch.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view) {
        keyword = etSearch.getText().toString();
        HttpUtil.get(Constant.SONG_SEARCH + "?keywords=" + keyword, 0, null, this);
    }

    @Override
    public void onFinish(int requestId, String response, String cookie) {
        JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
        if (jsonObject.get("code").getAsInt() == 200) {
            Intent intent = new Intent(getContext(),MusicListActivity.class);
            intent.putExtra("Tag","搜索");
            intent.putExtra("keywords",keyword);
            getActivity().startActivity(intent);
        }

    }

    @Override
    public void onFailure(Exception e) {

    }
}