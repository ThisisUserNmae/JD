package com.bwei.jd.mvp.Home.presenter;

import com.bwei.jd.mvp.Home.model.HomeModel;
import com.bwei.jd.mvp.Home.model.Home_ViewPagerBean;
import com.bwei.jd.mvp.Home.view.iview.IViewPagerView;

import java.util.List;

public class HomePresenter {

    private IViewPagerView iViewPagerView;

    private HomeModel homeModel;

    public HomePresenter(IViewPagerView iViewPagerView) {
        this.iViewPagerView = iViewPagerView;
        homeModel = new HomeModel();
    }

    public void pagerModel(){

        homeModel.pagerModel(new HomeModel.IViewPagerModel() {
            @Override
            public void getSuccess(List<Home_ViewPagerBean.DataBean> data) {

                if (iViewPagerView!=null){

                    iViewPagerView.getSuccess(data);

                }

            }

            @Override
            public void getError(String error) {

                if (iViewPagerView!=null){

                    iViewPagerView.getError(error);

                }

            }
        });
    }

    public void gridViewPresenter(String url){

        homeModel.gridViewModel(url, new HomeModel.IGridViewModel() {
            @Override
            public void getGridViewSuccess(String json) {

                if (iViewPagerView !=null){

                    iViewPagerView.getGridViewSuccess(json);

                }

            }

            @Override
            public void getGridViewError(String error) {

                if (iViewPagerView !=null){

                    iViewPagerView.getGridViewError(error);

                }

            }
        });

    }

    public void RecyclerViewPresenter(String url){

        homeModel.RecyclerViewModel(url, new HomeModel.IRecyclerViewModel() {
            @Override
            public void getRecyclerViewSuccess(String json) {

                if (iViewPagerView !=null){

                    iViewPagerView.getRecyclerViewSuccess(json);

                }

            }

            @Override
            public void getRecyclerViewError(String error) {

                if (iViewPagerView !=null){

                    iViewPagerView.getRecyclerViewError(error);

                }

            }
        });

    }

}
