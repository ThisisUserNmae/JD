package com.bwei.jd.mvp.myinfo.view;

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

public class LoginActivity extends AppCompatActivity implements ILoginView,View.OnClickListener {

    private static final String TAG = "LoginActivity--";

    private EditText ed_mobile,ed_password;

    private Button btn_login;

    private ImageView qq_img,weixin_img,my_img;

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

                break;

            case R.id.weixin_img:

                break;

            case R.id.my_img:

                break;

        }

    }
}
