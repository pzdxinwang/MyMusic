package com.example.mymusic.utils;

import android.util.Log;

import com.example.mymusic.model.Music;
import com.example.mymusic.model.MusicInfo;
import com.example.mymusic.model.MusicPresent;
import com.example.mymusic.model.MusicUrl;
import com.example.mymusic.model.TopList;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * 这个工具是对返回的json数据进行处理，写到一半了才创建的
 * 所以有些地方没有用到这个工具包，有时间了再回去优化
 */
public class JsonUtil {

    public static int getCode(String response) {
        int code = 0;
        try {
            JsonObject jsonObj = new JsonParser().parse(response).getAsJsonObject();
            code = jsonObj.get("code").getAsInt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return code;
    }
    public static String getErrorMsg(String response) {
        String errorMsg = null;
        try {
            JsonObject jsonObj = new JsonParser().parse(response).getAsJsonObject();
            errorMsg = jsonObj.get("msg").getAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return errorMsg;
    }


    public static List<MusicPresent> getMusic(String response) {
        List<MusicPresent> music = null;
        try {
            JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
            if (jsonObject.get("songs").getClass() == JsonArray.class) {
                JsonArray songs = jsonObject.getAsJsonArray("songs");
                Log.e("song", String.valueOf(songs));
                music = new Gson().fromJson(songs, new TypeToken<List<Music>>() {
                }.getType());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e("music", String.valueOf(music));
        return music;
    }

    public static List<MusicInfo> getMusic2(String response) {
        List<MusicInfo> music = null;
        try {
            JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
            JsonObject playlist = jsonObject.getAsJsonObject("playlist");
            if (playlist.get("tracks").getClass() == JsonArray.class) {
                JsonArray tracks = playlist.getAsJsonArray("tracks");
                Log.e("tracks", String.valueOf(tracks));
                music = new Gson().fromJson(tracks, new TypeToken<List<MusicPresent>>() {
                }.getType());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e("music", String.valueOf(music));
        return music;
    }
    public static List<TopList> getTopList(String response) {
        List<TopList> topList = null;
        try {
            JsonObject jsonObj = new JsonParser().parse(response).getAsJsonObject();
            if (jsonObj.get("list").getClass() == JsonArray.class) {
                JsonArray list = jsonObj.getAsJsonArray("list");
                topList = new Gson().fromJson(list, new TypeToken<List<TopList>>() {
                }.getType());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e("topList", String.valueOf(topList));
        return topList;
    }

    public static List<MusicUrl> getMusicUrl(String response) {
        List<MusicUrl> musicUrls = null;
        try {
            JsonObject jsonObj = new JsonParser().parse(response).getAsJsonObject();
            JsonArray data = jsonObj.getAsJsonArray("data");

            musicUrls = new Gson().fromJson(data, new TypeToken<List<MusicUrl>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return musicUrls;
    }
}