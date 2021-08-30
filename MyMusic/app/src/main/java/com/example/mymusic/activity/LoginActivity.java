package com.example.mymusic.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
 * 登录界面的实现
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener, HttpUtil.HttpCallbackListener {
    private EditText etUserPhone, etPassword;
    private Button btnLogin;
    private AppCompatTextView tvRegister;

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    /**
     * 初始化控件
     */
    @Override
    public void initView() {
        etUserPhone = findViewById(R.id.et_user_phone);
        etPassword = findViewById(R.id.et_user_password);
        btnLogin = findViewById(R.id.btn_login);
        AppCompatTextView tvGoRegister = findViewById(R.id.tv_register);
        btnLogin.setOnClickListener(this);
        tvGoRegister.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //去注册
            case R.id.tv_register:
                startActivity(new Intent(com.example.mymusic.activity.LoginActivity.this, com.example.mymusic.activity.RegisterActivity.class));
                break;
            //去登录
            case R.id.btn_login:
                login();
                break;
            default:
                break;
        }
    }

    private void login() {
        String phone = etUserPhone.getText().toString();
        String usePassword = etPassword.getText().toString();
        //判空
        if (StringUtil.isEmpty(phone)) {
            ToastUtil.showShortToast(this, "请输入手机号码");
        }
        if (StringUtil.isEmpty(usePassword)) {
            ToastUtil.showShortToast(this, "请输入密码！");
        }else {
            JsonObject params = new JsonObject();
            params.addProperty("phone", phone);
            params.addProperty("password", usePassword);
            HttpUtil.post(Constant.LOGIN, 0, params, null, true, this);
        }


    }

    @Override
    public void onFinish(int requestId, String response, String cookie) {
        JsonObject jsonObj = new JsonParser().parse(response).getAsJsonObject();
        Log.e("code", String.valueOf(jsonObj.get("code").getAsInt()));
        //更新UI操作
        runOnUiThread(() -> {
            if (jsonObj.get("code").getAsInt() == 200) {
                ToastUtil.showShortToast(LoginActivity.this, "登陆成功");
                //登录成功之后将用户的用户名、密码、cookie保存下来
                SharePreferencesUtil.putString(LoginActivity.this, "phone", etUserPhone.getText().toString());
                SharePreferencesUtil.putString(LoginActivity.this, "password", etPassword.getText().toString());
                SharePreferencesUtil.putBoolean(LoginActivity.this, "isLogin", true);
                SharePreferencesUtil.putString(LoginActivity.this, "Cookie", cookie);
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
            if (jsonObj.get("code").getAsInt()==502){
                ToastUtil.showShortToast(this,"密码错误,当前服务器拥挤，稍后在尝试");
            }else {
                ToastUtil.showShortToast(this,jsonObj.get("msg").getAsString());
            }
        });
    }

    @Override
    public void onFailure(Exception e) {
    }
}