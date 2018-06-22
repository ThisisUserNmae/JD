package com.bwei.jd.mvp.classify.view.fragment;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * @Author JenSenLeung.
 * @Time 2018/6/16 下午 2:35.
 * @Description This is ShowAllShopsType_list_grid.
 */
public class MyGridView extends GridView {
    public MyGridView(Context context) {
        super(context);
    }

    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}
