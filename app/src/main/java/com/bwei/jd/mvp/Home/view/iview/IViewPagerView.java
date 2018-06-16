package com.bwei.jd.mvp.Home.view.iview;

import com.bwei.jd.mvp.Home.model.Home_ViewPagerBean;

import java.util.List;

public interface IViewPagerView {

    void getSuccess(List<Home_ViewPagerBean.DataBean> data);

    void getError(String error);

    void getGridViewSuccess(String json);

    void getGridViewError(String error);
}
