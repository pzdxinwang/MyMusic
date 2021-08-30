package com.example.mymusic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.mymusic.R;
import com.example.mymusic.cogfig.Constant;
import com.example.mymusic.utils.HttpUtil;
import com.example.mymusic.utils.SharePreferencesUtil;
import com.example.mymusic.utils.StringUtil;
import com.example.mymusic.utils.ToastUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 注册界面的实现
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, HttpUtil.HttpCallbackListener {
    private EditText etUsername;
    private EditText etPassword;
    private EditText etPasswordAgain;
    private EditText etCaptcha;
    private EditText etUserTelephone;
    private Button btnSendCode;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        etUsername = findViewById(R.id.et_user_phone);
        etPassword = findViewById(R.id.et_userpassword);
        etPasswordAgain = findViewById(R.id.et_userpassword_again);
        etCaptcha = findViewById(R.id.et_captcha);
        etUserTelephone = findViewById(R.id.et_user_password);

        btnSendCode = findViewById(R.id.btn_send_captcha);
        btnRegister = findViewById(R.id.btn_register);
        AppCompatTextView tvGoLogin = findViewById(R.id.tv_goLogin);
        btnRegister.setOnClickListener(this);
        tvGoLogin.setOnClickListener(this);
        btnSendCode.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_goLogin:
                //转到登录界面
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.btn_register:
                //注册
                register();
                break;
            case R.id.btn_send_captcha:
                //发送验证码
                sendCode();
//                //设置成不可点击
//                btnSendCode.setClickable(false);
                break;
            default:
                break;
        }
    }

    /**
     * 注册时发送验证码
     */
    private void sendCode() {
        String userTelephone = etUserTelephone.getText().toString();
        JsonObject params = new JsonObject();
        params.addProperty("phone", userTelephone);
        if (judgePhoneNums(userTelephone)) {
            HttpUtil.get(Constant.SEND_CODE + "?phone=" + userTelephone, 0, null, this);
            ToastUtil.showShortToast(this, "正在发送验证码");
        } else ToastUtil.showShortToast(this, "验证码发送失败");



    }

    /***
     * 判断手机号码是否正确
     * @param phoneNums
     * @return
     */
    private boolean judgePhoneNums(String phoneNums) {
        if (isMatchLength(phoneNums, 11)) {
            return true;
        }

        ToastUtil.showShortToast(this, "手机号码不正确");
        return false;
    }


    /**
     * 判断输入的号码长度是否正确
     *
     * @param str
     * @param length
     * @return
     */
    public static boolean isMatchLength(String str, int length) {
        if (str.isEmpty()) {
            return false;
        } else {
            return str.length() == length ? true : false;
        }
    }

    /**
     * 注册
     */
    private void register() {
        //获取用户输入的信息
        String userTelephone = etUserTelephone.getText().toString();
        String userName = etUsername.getText().toString();
        String Password = etPassword.getText().toString();
        String rePassword = etPasswordAgain.getText().toString();
        String captcha = etCaptcha.getText().toString();
        //判断是否为空
        if (StringUtil.isEmpty(userName)) {
            ToastUtil.showShortToast(this, "昵称不能为空！");
            return;
        }
        if (StringUtil.isEmpty(Password)) {
            ToastUtil.showShortToast(this, "密码不能为空！");
            return;
        }
        if (StringUtil.isEmpty(captcha)) {
            ToastUtil.showLongToast(this, "验证码不能为空");
            //检查验证码是否正确
            checkCaptcha();
        }

        if (StringUtil.isEmpty(rePassword)) {
            ToastUtil.showShortToast(this, "请再次确认密码！");
            return;
        }
        if (!Password.equals(rePassword)) {
            ToastUtil.showShortToast(this, "两次密码输入不一致，请重新输入");
            return;
        }
        /*
        addProperty是向params(参数集合)里添加参数，从而上传到url中
         */
        JsonObject params = new JsonObject();
        params.addProperty("phone", userTelephone);
        params.addProperty("password", Password);
        params.addProperty("captcha", captcha);
        params.addProperty("nickname", userName);
//        HttpUtil.get(Constant.REGISTER + "?phone=" + userTelephone + "&password=" + Password +
//                "&captcha=" + captcha + "&nickname=" + userName, 0, null, this);
        HttpUtil.post(Constant.REGISTER, 0, params, null, false, this);
    }

    /**
     * 验证验证码是否正确
     */
    private void checkCaptcha() {
        String checkCaptcha = etCaptcha.getText().toString();
        String phone = etUserTelephone.getText().toString();
        JsonObject params = new JsonObject();
        params.addProperty("phone", phone);
        params.addProperty("captcha", checkCaptcha);
        HttpUtil.post(Constant.VERIFY_CODE, 0, params, null, false, this);
    }

    /**
     * 网络请求成功时调用，这里有多种情况要处理
     *
     * @param requestId
     * @param response
     * @param cookie
     */
    @Override
    public void onFinish(int requestId, String response, String cookie) {
        try {
            JsonObject jsonObj = new JsonParser().parse(response).getAsJsonObject();
            runOnUiThread(() -> {
                Log.e("code",jsonObj.get("code").getAsString());
                //查看API接口获取验证码返回的code是200,我很迷，发送验证码和注册成功的一样
                if(jsonObj.get("code").getAsInt()==200){
                    ToastUtil.showShortToast(this,"验证码发送成功");
                }
                //昵称一样时
                if (jsonObj.get("code").getAsInt() == 505) {
                    ToastUtil.showShortToast(this, "该昵称被占用，请更换");
                }
                if ((jsonObj.get("code").getAsInt()) == 503) {
                    ToastUtil.showShortToast(this, "验证码错误");
                }
                if (jsonObj.get("code").getAsInt() == 400) {
                    ToastUtil.showShortToast(this, "发送验证码超过限制");
                }
                //这个是注册成功时
                if (jsonObj.get("code").getAsInt() == 200) {
                    ToastUtil.showShortToast(this, "注册成功");
                    SharePreferencesUtil.putString(com.example.mymusic.activity.RegisterActivity.this, "phone", etUserTelephone.getText().toString());
                    SharePreferencesUtil.putString(com.example.mymusic.activity.RegisterActivity.this, "password", etPassword.getText().toString());
//                    startActivity(new Intent(com.example.mymusic.activity.RegisterActivity.this, LoginActivity.class));
                    finish();
                } else {
                    ToastUtil.showShortToast(com.example.mymusic.activity.RegisterActivity.this, jsonObj.get("code").getAsString());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //当请求不成功时
    @Override
    public void onFailure(Exception e) {
        Log.e("coder", e.getMessage());
    }
}