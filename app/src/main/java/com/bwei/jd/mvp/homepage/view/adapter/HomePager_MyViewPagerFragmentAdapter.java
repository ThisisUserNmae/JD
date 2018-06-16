package com.bwei.jd.mvp.homepage.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class HomePager_MyViewPagerFragmentAdapter extends FragmentPagerAdapter{

    private List<Fragment> list;

    public HomePager_MyViewPagerFragmentAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {

        return list.size();
    }
}
