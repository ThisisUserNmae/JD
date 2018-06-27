package com.bwei.jd.mvp.shoppingcar.model;

import android.widget.ExpandableListView;

import com.bwei.jd.http.OkhtttpUtils;

public class ShoppingModel {

    public void shoppingMoodel(String url, final IShoppingModel iShoppingModel){

        OkhtttpUtils instance = OkhtttpUtils.getInstance();

        instance.doGet(url, new OkhtttpUtils.OkCallback() {
            @Override
            public void onFailure(Exception e) {

                if (iShoppingModel!=null){

                    iShoppingModel.getErroor(e);

                }

            }

            @Override
            public void onResponse(String json) {

                if (iShoppingModel!=null){

                    iShoppingModel.getSuccess(json);

                }

            }
        });

    }

    public interface IShoppingModel{

        void getSuccess(String json);

        void getErroor(Exception e);

    }

}
