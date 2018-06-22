package com.bwei.jd.mvp.classify.view.iview;

public interface IClasslyView {

    void getSuccess(String json);

    void getError(String error);

    void getElSuccess(String json);

    void getElError(String error);

}
