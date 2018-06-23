package com.bwei.jd.mvp.myinfo.view;

import android.Manifest;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bwei.jd.R;
import com.bwei.jd.http.HttpConfig;
import com.bwei.jd.mvp.myinfo.model.bean.LoginBean;
import com.bwei.jd.mvp.myinfo.presenter.LoginPresenter;
import com.bwei.jd.mvp.myinfo.view.iview.ILoginView;
import com.google.gson.Gson;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

public class LoginActivity extends AppCompatActivity implements ILoginView,View.OnClickListener {

    private static final String TAG = "LoginActivity--";

    private EditText ed_mobile,ed_password;

    private Button btn_login;

    private ImageView qq_img,weixin_img,my_img;

    private UMShareAPI mShareAPI;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();

        initDatas();

    }

    private void initDatas() {


    }

    private void initViews() {

        ed_mobile = findViewById(R.id.ed_mobile);

        ed_password = findViewById(R.id.ed_password);

        btn_login = findViewById(R.id.btn_login);

        qq_img = findViewById(R.id.qq_img);

        weixin_img = findViewById(R.id.weixin_img);

        my_img = findViewById(R.id.my_img);

        loginPresenter = new LoginPresenter(this);

        btn_login.setOnClickListener(this);

        mShareAPI =  UMShareAPI.get(LoginActivity.this);

        qq_img.setOnClickListener(this);

        weixin_img.setOnClickListener(this);

        my_img.setOnClickListener(this);
    }

    @Override
    public void getSuccess(final String json) {


        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Gson g = new Gson();

                Log.d(TAG, "getSuccess: getSuccess 你进来了"+json);

                LoginBean loginBean = g.fromJson(json, LoginBean.class);

                String code = loginBean.getCode();

                if (code.equals("0")){

                    finish();

                }

            }
        });

    }

    @Override
    public void getError(String error) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(LoginActivity.this,"登陆失败",Toast.LENGTH_SHORT);

            }
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_login:

                Log.d(TAG, "onClick: 你进来了");

                String mobile = ed_mobile.getText().toString().trim();
                String password = ed_password.getText().toString().trim();

                loginPresenter.login(HttpConfig.LOGIN_URL+"?mobile="+mobile+"&password="+password);

                break;

            case R.id.qq_img:

                if(Build.VERSION.SDK_INT>=23){
                    String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS,Manifest.permission.WRITE_APN_SETTINGS};
                    ActivityCompat.requestPermissions(LoginActivity.this,mPermissionList,123);
                }

                mShareAPI.getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, authListener);

                break;

            case R.id.weixin_img:

                break;

            case R.id.my_img:

                break;

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {



    }

    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            Toast.makeText(LoginActivity.this, "成功了", Toast.LENGTH_LONG).show();

        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(LoginActivity.this, "失败：" + t.getMessage(),
                    Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(LoginActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

}
