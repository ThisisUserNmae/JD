package com.bwei.jd.mvp.classify.presenter;

import com.bwei.jd.mvp.classify.model.ClasslyModel;
import com.bwei.jd.mvp.classify.view.iview.IClasslyView;

public class ClasslyPressenter {

    private IClasslyView iClasslyView;

    private ClasslyModel classlyModel;

    public ClasslyPressenter(IClasslyView iClasslyView) {
        this.iClasslyView = iClasslyView;
        classlyModel = new ClasslyModel();
    }

    public void left_listViewPersenter(String url){

        classlyModel.left_listViewModel(url, new ClasslyModel.IClasslyModel() {
            @Override
            public void getSuccess(String json) {

                if (iClasslyView!=null){

                    iClasslyView.getSuccess(json);

                }

            }

            @Override
            public void getError(String error) {

                if (iClasslyView!=null){

                    iClasslyView.getError(error);

                }

            }
        });

    }

}
