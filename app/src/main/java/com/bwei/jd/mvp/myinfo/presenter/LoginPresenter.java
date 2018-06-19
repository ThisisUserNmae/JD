package com.bwei.jd.mvp.myinfo.presenter;

import com.bwei.jd.mvp.myinfo.model.LoginModel;
import com.bwei.jd.mvp.myinfo.view.iview.ILoginView;

public class LoginPresenter {

    private ILoginView iLoginView;

    private LoginModel loginModel;

    public LoginPresenter(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        loginModel = new LoginModel();
    }

    public void login(String url){
        loginModel.login(url, new LoginModel.ILoginModel() {
            @Override
            public void getSuccess(String json) {

                if (iLoginView !=null){

                    iLoginView.getSuccess(json);

                }


            }

            @Override
            public void getError(String error) {

                if (iLoginView!=null){

                    iLoginView.getError(error);

                }

            }
        });

    }

}
