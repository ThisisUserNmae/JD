package com.bwei.jd.mvp.homepage.view.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bwei.jd.R;
import com.bwei.jd.mvp.Home.view.HomeActivity;

import java.util.Timer;
import java.util.TimerTask;

public class HomePageActivity extends AppCompatActivity {

    private Timer timer;

    private MyHandler h = new MyHandler();

    int i = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatas();

    }

    private void initDatas() {

        timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {

            h.sendEmptyMessage(0);

            }
        },0,1000);

    }

    class MyHandler extends Handler{

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            i--;

            if (i==0){
                timer.cancel();
                Intent it = new Intent(HomePageActivity.this, HomeActivity.class);
                startActivity(it);
                finish();

            }
        }
    }

    @Override
    protected void onDestroy() {

        h.removeCallbacksAndMessages(null);

        super.onDestroy();
    }
}
