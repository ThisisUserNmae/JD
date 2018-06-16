package com.bwei.jd.mvp.Home.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.bwei.jd.R;
import com.bwei.jd.app.MyApp;
import com.bwei.jd.http.HttpConfig;
import com.bwei.jd.mvp.Home.model.Home_ViewPagerBean;
import com.bwei.jd.mvp.Home.presenter.HomePresenter;
import com.bwei.jd.mvp.Home.view.adapter.HomePagerAdapter;
import com.bwei.jd.mvp.Home.view.adapter.Home_GridViewAdapter;
import com.bwei.jd.mvp.Home.view.iview.IViewPagerView;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements IViewPagerView{

    private View view;

    private ImageView img;

    private ViewPager pager_home;

    private List<ImageView> list = new ArrayList<>();

    private HomePresenter homePresenter;

    private MyHandler h = new MyHandler();

    private MyGridView  home_gv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.homefragment,null);

        homePresenter =new HomePresenter(this);

        initViews();

        initDatas();

        return view;
    }

    private void initDatas() {

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    // 申请CAMERA权限
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 1);
                }

                // 打开扫描界面扫描条形码或二维码
                Intent openCameraIntent = new Intent(getActivity(), CaptureActivity.class);

                startActivityForResult(openCameraIntent, 0);

            }
        });



    }

    private void initViews() {

        img = view.findViewById(R.id.img_home);

        pager_home = view.findViewById(R.id.pager_home);

        home_gv = view.findViewById(R.id.home_gv);

        homePresenter.pagerModel();
    }

    @Override
    public void getSuccess(List<Home_ViewPagerBean.DataBean> data) {

        for (int i = 0; i < data.size(); i++) {

            String icon = data.get(i).getIcon();

            ImageView img = new ImageView(getActivity());

            img.setScaleType(ImageView.ScaleType.CENTER_CROP);

            ImageLoader.getInstance().displayImage(icon,img, MyApp.getOptions());

            list.add(img);



        }

        homePresenter.gridViewPresenter(HttpConfig.PAGER_URL);

        HomePagerAdapter adapter = new HomePagerAdapter(list);

        pager_home.setAdapter(adapter);



        h.sendEmptyMessageDelayed(0,2000);

    }

    @Override
    public void getError(String error) {



    }

    @Override
    public void getGridViewSuccess(String json) {

        Gson g = new Gson();

        Home_ViewPagerBean home_viewPagerBean = g.fromJson(json, Home_ViewPagerBean.class);

        List<Home_ViewPagerBean.TuijianBean.ListBean> list = home_viewPagerBean.getTuijian().getList();

        Home_GridViewAdapter adapter = new Home_GridViewAdapter(list,getActivity());

        home_gv.setAdapter(adapter);

    }

    @Override
    public void getGridViewError(String error) {



    }

    class MyHandler extends Handler{

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            int num = pager_home.getCurrentItem()+1;

            pager_home.setCurrentItem(num);

            h.sendEmptyMessageDelayed(0,2000);

        }
    }

}
