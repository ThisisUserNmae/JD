package com.bwei.jd.http;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtil {
    private MyHandler myHandler = new MyHandler();
    private static final String TAG = "HttpUtil-----";
    private HttpUtilListance httpUtilListance;
    //单例模式
    private static HttpUtil httpUtil = new HttpUtil();

    public HttpUtil() {
    }

    public static HttpUtil getInstance() {
        if (httpUtil == null) {

            synchronized (HttpUtil.class){
                if (httpUtil == null) {

                    return httpUtil = new HttpUtil();
                }

            }

        }
        return httpUtil;
    }

    //进行网络请求的方法
    public void get(final String url) {
        new Thread() {
            @Override
            public void run() {
                try {
                    URL u = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) u.openConnection();
                    connection.setReadTimeout(3000);
                    int code = connection.getResponseCode();
                    if (code == 200) {
                        InputStream inputStream = connection.getInputStream();
                        String josn = inputStream2String(inputStream);
                        Message message = myHandler.obtainMessage();
                        message.what = 0;
                        message.obj = josn;
                        myHandler.sendMessage(message);

                    } else {
                        Message message = myHandler.obtainMessage();
                        message.what = 1;
                        message.obj = "错误!";
                        myHandler.sendMessage(message);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Message message = myHandler.obtainMessage();
                    message.what = 0;
                    message.obj = e.getMessage();
                    myHandler.sendMessage(message);
                }
            }
        }.start();
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:

                    String json = (String) msg.obj;
                    Log.d(TAG, "handleMessage: " + json);
                    httpUtilListance.getSuccess(json);
                    break;
                case 1:
                    String error = (String) msg.obj;
                    Log.d(TAG, "handleMessage: " + error);
                    httpUtilListance.getError(error);
                    break;
            }
        }
    }

    //字节---转换字符 防止乱码
    public static String inputStream2String(InputStream inputStream) throws IOException {
        InputStreamReader streamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(streamReader);
        String temp = null;
        StringBuffer buffer = new StringBuffer();
        while ((temp = bufferedReader.readLine()) != null) {
            buffer.append(temp);
        }
        return buffer.toString();
    }

    public interface HttpUtilListance {
        void getSuccess(String json);

        void getError(String error);
    }

    public void setHttpUtilListance(HttpUtilListance httpUtilListance) {
        this.httpUtilListance = httpUtilListance;
    }
}
