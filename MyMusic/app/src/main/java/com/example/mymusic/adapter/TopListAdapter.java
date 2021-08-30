package com.example.mymusic.adapter;

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

import com.bumptech.glide.Glide;
import com.example.mymusic.R;
import com.example.mymusic.activity.MusicListActivity;
import com.example.mymusic.activity.MusicUrlActivity;
import com.example.mymusic.activity.PlayMusicActivity;
import com.example.mymusic.model.TopList;
import com.example.mymusic.utils.HttpUtil;

import java.util.List;

public class TopListAdapter extends RecyclerView.Adapter<TopListAdapter.InnerHolder> implements HttpUtil.HttpCallbackListener {
    private Context mContext;
    private List<TopList> topLists;
    private ImageView pic;

    public TopListAdapter(Context mContext, List<TopList> topLists) {
        this.mContext = mContext;
        this.topLists = topLists;
    }

    @NonNull
    @Override
    public TopListAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_toplist, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopListAdapter.InnerHolder holder, int position) {

        TopList playList = topLists.get(position);

        Glide.with(mContext).load(playList.getCoverImgUrl())
                .thumbnail(0.1f)
                .into(pic);
        holder.name.setText(playList.getName());
        holder.updateFrequency.setText(playList.getUpdateFrequency());
        holder.description.setText(playList.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MusicListActivity.class);
                //将id传过去才能获取url
                Log.e("id",playList.getId());
                intent.putExtra("id", playList.getId());
                intent.putExtra("name",playList.getName());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (topLists != null) {
            return topLists.size();
        }
        return 0;
    }

    @Override
    public void onFinish(int requestId, String response, String cookie) {

    }

    @Override
    public void onFailure(Exception e) {

    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        private TextView updateFrequency;
        private TextView name;
        private TextView description;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            updateFrequency = itemView.findViewById(R.id.tv_toplist_updateFrequency);
            name = itemView.findViewById(R.id.tv_toplist_songname);
            description = itemView.findViewById(R.id.tv_toplist_songdescription);
            pic = itemView.findViewById(R.id.iv_toplist_songpic);

        }
    }
}
