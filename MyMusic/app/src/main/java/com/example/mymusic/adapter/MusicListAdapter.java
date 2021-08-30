package com.example.mymusic.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymusic.R;
import com.example.mymusic.activity.PlayMusicActivity;
import com.example.mymusic.model.MusicInfo;
import com.example.mymusic.utils.HttpUtil;
import com.example.mymusic.utils.ToastUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.List;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.InnerHolder>
        implements HttpUtil.HttpCallbackListener {

    private Context mContext;
    private List<MusicInfo> musicList;


    public MusicListAdapter(Context mContext, List<MusicInfo> musicList) {
        this.mContext = mContext;
        this.musicList = musicList;
    }

    @NonNull
    @Override
    public MusicListAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_music, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicListAdapter.InnerHolder holder, int position) {
//        MusicInfo music = musicList.get(position);

        //填入数据，这里一直报空指针异常闪退，干不动了
//        holder.songName.setText(music.getName());
//        holder.author.setText(music.getArtistsName());
//        holder.album.setText(music.getAlbumName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(mContext, PlayMusicActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (musicList != null) {
            return musicList.size();
        }
        return 0;
    }

    @Override
    public void onFinish(int requestId, String response, String cookie) {
        if (requestId == 0) {
            JsonObject jsonObj = new JsonParser().parse(response).getAsJsonObject();
            ((Activity) mContext).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (jsonObj.get("code").getAsInt() == 200) {
                        ToastUtil.showShortToast(mContext, "搜索成功");
                    } else {
                        ToastUtil.showShortToast(mContext, jsonObj.get("msg").getAsString());
                    }
                }
            });
        }
    }

    @Override
    public void onFailure(Exception e) {

    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        private ImageView ivMusic;
        private TextView album;
        private TextView author;
        private TextView songName;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);

            songName = itemView.findViewById(R.id.tv_music_songname);
            author = itemView.findViewById(R.id.tv_music_author);
            album = itemView.findViewById(R.id.tv_music_album);
            ivMusic = itemView.findViewById(R.id.iv_music);

        }
    }
}
