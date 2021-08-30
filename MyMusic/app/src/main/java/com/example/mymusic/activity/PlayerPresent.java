package com.example.mymusic.activity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;

import com.example.mymusic.interfaces.PlayerController;
import com.example.mymusic.interfaces.PlayerViewController;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class PlayerPresent extends Binder implements PlayerController {

    private PlayerViewController mPlayerViewController;
    private MediaPlayer mediaPlayer;
    private Timer timer;
    private SeekTimeTask timeTask;
    private int currentPosition;
    private static int currentState = STOP;

    @Override
    public void registerIPlayViewController(PlayerViewController playerViewController) {
        mPlayerViewController = playerViewController;
    }

    @Override
    public void unregisterIPlayViewController() {
        mPlayerViewController = null;
    }

    @Override
    public void start(String url, boolean isLike) {
        initMediaPlayer();
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            mediaPlayer.start();
            startTimer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void pauseOrResume() {
        if (currentState == START) {
            mediaPlayer.pause();
            stopTimer();
        } else if (currentState == PAUSE) {
            mediaPlayer.start();
            startTimer();
        }
    }

    @Override
    public void stop() {
        mediaPlayer.stop();
        initMediaPlayer();
    }

    @Override
    public void seekTo(int seek) {
        if (mediaPlayer != null) {
            int tagSeek = (int) (seek * 1.0 / 100 * mediaPlayer.getDuration());
            mediaPlayer.seekTo(tagSeek);
        }
    }

    /**
     * 初始化播放器
     */
    private void initMediaPlayer() {
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        }
    }

    /**
     * 定义TImeTask，实时获取进度
     */
    class SeekTimeTask extends TimerTask {
        @Override
        public void run() {
            currentPosition = (int) (100.1 * mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration());
            mPlayerViewController.onSeekChange(currentPosition);
        }
    }

    public void startTimer() {
        if (timer == null) {
            timer = new Timer();
        }
        if (timeTask == null) {
            timeTask = new SeekTimeTask();
        }
        timer.schedule(timeTask, 0, 50);
    }

    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (timeTask != null) {
            timeTask.cancel();
            timeTask = null;
        }
    }
}
