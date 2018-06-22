package com.bwei.jd.mvp.classify.model;

import android.util.Log;

import com.bwei.jd.http.HttpConfig;
import com.bwei.jd.http.HttpUtil;
import com.bwei.jd.http.OkhtttpUtils;

public class ClasslyModel {

    private static final String TAG = "ClasslyModel---";

    public void left_listViewModel(String url, final IClasslyModel iClasslyModel) {

        HttpUtil instance = HttpUtil.getInstance();

        instance.get(url);

        instance.setHttpUtilListance(new HttpUtil.HttpUtilListance() {
            @Override
            public void getSuccess(String json) {

                if (iClasslyModel != null) {

                    iClasslyModel.getSuccess(json);

                }

            }

            @Override
            public void getError(String error) {

                if (iClasslyModel != null) {

                    iClasslyModel.getError(error);

                }

            }
        });

    }

    public void right_el(int cid, final IRightEl iRightEl) {

        OkhtttpUtils instance = OkhtttpUtils.getInstance();

        instance.doGet(HttpConfig.PRODUCTCHILD + "?cid=" + cid, new OkhtttpUtils.OkCallback() {
            @Override
            public void onFailure(Exception e) {

            }

            @Override
            public void onResponse(String json) {

                if (iRightEl!=null){

                    iRightEl.getElSuccess(json);

                }


            }
        });

    }

    public interface IClasslyModel {

        void getSuccess(String json);

        void getError(String error);


    }

    public interface IRightEl {

        void getElSuccess(String json);

        void getElError(String error);

    }

}
