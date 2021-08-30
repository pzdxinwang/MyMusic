package com.example.mymusic.cogfig;

/**
 * 常量类，主要存放的是网址，将网址转换成字符串常量方便使用
 */
public class Constant {
    //主网页
    public static final String URL = "http://49.234.117.142:3000";

    //登录
    public static final String LOGIN = "/login/cellphone";

    //手机号注册
    public static final String REGISTER = "/register/cellphone";

    //发送验证码
    public static final String SEND_CODE = "/captcha/sent";

    //验证验证码
    public static final String VERIFY_CODE = "/captcha/verify";

    //所有榜单
    public static final String TOP_LIST = "/toplist";

    //精品歌单
    public static final String TOP_PLAYLIST = "/top/playlist";

    //获取歌单详情
    public static final String PLAYLIST_DETAILS = "/playlist/detail";

    public static final String SONG_DETAILS = "/song/detail";
    //搜索
    public static final String SONG_SEARCH = "/search";

    //获取音乐url
    public static final String SONG_URL = "/song/url";

    //退出登录
    public static final String LOGOUT = "/logout";
}
