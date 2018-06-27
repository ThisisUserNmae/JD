package com.bwei.jd.mvp.Home.view.adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class HomePagerAdapter extends PagerAdapter{

    private List<ImageView> list;

    public HomePagerAdapter(List<ImageView> list) {

        this.list = list;
    }


    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        ImageView img = list.get(position%list.size());

        container.addView(img);

        return img;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((ImageView) object);

    }
}
