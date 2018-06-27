package com.bwei.jd.mvp.shoppingcar.presenter;

import com.bwei.jd.mvp.shoppingcar.model.ShoppingModel;
import com.bwei.jd.mvp.shoppingcar.view.iview.IShoppingView;

public class ShoppingPresenter {

    private IShoppingView iShoppingView;

    private ShoppingModel shoppingModel;

    public ShoppingPresenter(IShoppingView iShoppingView) {
        this.iShoppingView = iShoppingView;
        shoppingModel = new ShoppingModel();
    }

    public void shooppingPresenter(String url){

        shoppingModel.shoppingMoodel(url, new ShoppingModel.IShoppingModel() {
            @Override
            public void getSuccess(String json) {

                if (iShoppingView!=null){

                    iShoppingView.getSuccess(json);

                }

            }

            @Override
            public void getErroor(Exception e) {

                if (iShoppingView!=null){

                    iShoppingView.getError(e);

                }

            }
        });

    }

}
