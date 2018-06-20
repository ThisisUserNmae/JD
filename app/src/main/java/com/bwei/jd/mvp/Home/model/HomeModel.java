package com.bwei.jd.mvp.Home.model;

import android.util.Log;

import com.bwei.jd.http.HttpConfig;
import com.bwei.jd.http.HttpUtil;
import com.google.gson.Gson;

import java.util.List;

public class HomeModel {

    private static final String TAG = "HomeModel----";


    public void pagerModel(final IViewPagerModel iViewPagerModel){



        HttpUtil instance = HttpUtil.getInstance();

        instance.get(HttpConfig.PAGER_URL);

        instance.setHttpUtilListance(new HttpUtil.HttpUtilListance() {
            @Override
            public void getSuccess(String json) {

                Log.d(TAG, "getSuccess: "+json);

                Gson g = new Gson();

                Home_ViewPagerBean home_viewPagerBean = g.fromJson(json,Home_ViewPagerBean.class);

                List<Home_ViewPagerBean.DataBean> data = home_viewPagerBean.getData();

                if (iViewPagerModel!=null){

                    iViewPagerModel.getSuccess(data);

                }

            }

            @Override
            public void getError(String error) {

                Log.d(TAG, "getSuccess: "+error);

                if (iViewPagerModel!=null){

                    iViewPagerModel.getError(error);

                }

            }
        });

    }


    public void gridViewModel(String url, final IGridViewModel iGridViewModel){

        HttpUtil instance = HttpUtil.getInstance();

        instance.get(url);

        instance.setHttpUtilListance(new HttpUtil.HttpUtilListance() {
            @Override
            public void getSuccess(String json) {

                if (iGridViewModel!=null){

                    iGridViewModel.getGridViewSuccess(json);

                }

            }

            @Override
            public void getError(String error) {

                if (iGridViewModel!=null){

                    iGridViewModel.getGridViewError(error);

                }

            }
        });

    }

    public void RecyclerViewModel(String url, final IRecyclerViewModel iRecyclerViewModel){

        HttpUtil instance = HttpUtil.getInstance();

        instance.get(url);

        instance.setHttpUtilListance(new HttpUtil.HttpUtilListance() {
            @Override
            public void getSuccess(String json) {

                if (iRecyclerViewModel!=null){

                    iRecyclerViewModel.getRecyclerViewSuccess(json);

                }

            }

            @Override
            public void getError(String error) {

                if (iRecyclerViewModel!=null){

                    iRecyclerViewModel.getRecyclerViewError(error);

                }

            }
        });

    }

    public interface IViewPagerModel{

        void getSuccess(List<Home_ViewPagerBean.DataBean> data);

        void getError(String error);

    }

    public interface IGridViewModel{

        void getGridViewSuccess(String json);

        void getGridViewError(String error);

    }

    public interface IRecyclerViewModel{

        void getRecyclerViewSuccess(String json);

        void getRecyclerViewError(String error);

    }

}
