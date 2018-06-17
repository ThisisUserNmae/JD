package com.bwei.jd.mvp.classify.model;

import com.bwei.jd.http.HttpUtil;

public class ClasslyModel {


    public void left_listViewModel(String url, final IClasslyModel iClasslyModel){

        HttpUtil instance = HttpUtil.getInstance();

        instance.get(url);

        instance.setHttpUtilListance(new HttpUtil.HttpUtilListance() {
            @Override
            public void getSuccess(String json) {


                if (iClasslyModel!=null){

                    iClasslyModel.getSuccess(json);

                }

            }

            @Override
            public void getError(String error) {

                if (iClasslyModel!=null){

                    iClasslyModel.getError(error);

                }

            }
        });

    }

    public interface IClasslyModel{

        void getSuccess(String json);
        void getError(String error);


    }

}
