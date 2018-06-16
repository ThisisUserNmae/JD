package com.bwei.jd.mvp.Home.view;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.bwei.jd.R;
import com.bwei.jd.mvp.classify.view.ClassifyFragment;
import com.bwei.jd.mvp.homepage.view.adapter.HomePager_MyViewPagerFragmentAdapter;
import com.bwei.jd.mvp.myinfo.view.MyInfoFragment;
import com.bwei.jd.mvp.shoppingcar.view.ShoppingCarFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private ViewPager pager;

    private RadioGroup group;

    private List<Fragment> listFragment= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();

        initDatas();

    }

    private void initDatas() {

        listFragment.add(new HomeFragment());
        listFragment.add(new ClassifyFragment());
        listFragment.add(new ShoppingCarFragment());
        listFragment.add(new MyInfoFragment());

        HomePager_MyViewPagerFragmentAdapter adapter = new HomePager_MyViewPagerFragmentAdapter(getSupportFragmentManager(),listFragment);

        pager.setAdapter(adapter);

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){

                    case R.id.btn1:
                        pager.setCurrentItem(0);
                        break;

                    case R.id.btn2:
                        pager.setCurrentItem(1);
                        break;

                    case R.id.btn3:
                        pager.setCurrentItem(2);
                        break;

                    case R.id.btn4:
                        pager.setCurrentItem(3);
                        break;

                }

            }
        });

    }

    private void initViews() {

        pager = findViewById(R.id.pager_homePage);

        group = findViewById(R.id.group_homePage);


    }
}
