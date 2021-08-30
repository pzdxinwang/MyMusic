package com.example.mymusic.interfaces;

public interface PlayerController {

    int START = 0;
    int PAUSE = 1;
    int STOP = 2;

    /**
     * 绑定UI控制器
     * 将UI控制提交给逻辑层
     * @param
     */
    void registerIPlayViewController(PlayerViewController playerViewController);

    /**
     * 取消绑定的控制器
     */
    void unregisterIPlayViewController();

    /**
     * 开始播放
     * @param url 播放音乐的地址
     */
    void start(String url, boolean isLike);

    /**
     * 暂停或继续播放
     */
    void pauseOrResume();


    /**
     * 停止播放
     */
    void stop();

    /**
     * 进度条更新
     * @param seek
     */
    void seekTo(int seek);
}
