package com.example.mymusic.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymusic.R;
import com.example.mymusic.activity.PlayMusicActivity;
import com.example.mymusic.model.MusicUrl;

import java.util.List;

public class MusicUrlAdapter extends RecyclerView.Adapter<MusicUrlAdapter.InnerHolder> {
    private Context mContext;
    private List<MusicUrl> urlList;
    private MusicUrl list;

    public MusicUrlAdapter(Context mContext, List<MusicUrl> urlList) {
        this.mContext = mContext;
        this.urlList = urlList;
    }

    @NonNull
    @Override
    public MusicUrlAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_music, parent);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicUrlAdapter.InnerHolder holder, int position) {
        list = urlList.get(position);
        Intent intent = new Intent(mContext, PlayMusicActivity.class);
//        intent.putExtra("url", list.getUrl());
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        if (urlList != null) {
            return urlList.size();
        }
        return 0;
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
