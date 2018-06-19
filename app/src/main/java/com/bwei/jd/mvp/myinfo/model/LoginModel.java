package com.bwei.jd.mvp.myinfo.model;

import com.bwei.jd.http.HttpUtil;

public class LoginModel {

    public void login(String url, final ILoginModel iLoginModel) {

        HttpUtil instance = HttpUtil.getInstance();

        instance.get(url);

        instance.setHttpUtilListance(new HttpUtil.HttpUtilListance() {
            @Override
            public void getSuccess(String json) {

                if (iLoginModel != null) {

                    iLoginModel.getSuccess(json);

                }

            }

            @Override
            public void getError(String error) {

                if (iLoginModel != null) {

                    iLoginModel.getError(error);

                }

            }
        });

    }

    public interface ILoginModel {

        void getSuccess(String json);

        void getError(String error);

    }

}
