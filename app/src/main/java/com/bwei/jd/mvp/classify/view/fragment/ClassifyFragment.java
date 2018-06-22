package com.bwei.jd.mvp.classify.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bwei.jd.R;
import com.bwei.jd.http.HttpConfig;
import com.bwei.jd.mvp.classify.model.Classly_ListViewBean;
import com.bwei.jd.mvp.classify.presenter.ClasslyPressenter;
import com.bwei.jd.mvp.classify.view.adapter.Left_ListViewBaseAdapter;
import com.bwei.jd.mvp.classify.view.iview.IClasslyView;
import com.google.gson.Gson;

import java.util.List;

public class ClassifyFragment extends Fragment implements IClasslyView{

    private static final String TAG = "ClassifyFragment----";

    private View view;

    private ListView lv_classly;

    private ClasslyPressenter classlyPressenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.classifyfragment,null);

        initViews();

        initDatas();

        return view;
    }

    private void initDatas() {

        classlyPressenter = new ClasslyPressenter(this);

    }

    private void initViews() {

        Log.d(TAG, "onCreateView: 你进入了initViews  方法");

        lv_classly = view.findViewById(R.id.lv_classly);

        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();

        Classify_Right_Fragment classify_right_fragment = new Classify_Right_Fragment();

        classify_right_fragment.getData(1);

        fragmentTransaction.replace(R.id.fm_right_fragment,classify_right_fragment);

        Bundle bundle = new Bundle();
        bundle.putInt("cid",1);
        classify_right_fragment.setArguments(bundle);

        fragmentTransaction.commit();

    }

    @Override
    public void getSuccess(String json) {

        Gson g = new Gson();

        Classly_ListViewBean classly_listViewBean = g.fromJson(json, Classly_ListViewBean.class);

        final List<Classly_ListViewBean.DataBean> data = classly_listViewBean.getData();

        Left_ListViewBaseAdapter adapter = new Left_ListViewBaseAdapter(data,getActivity());

        lv_classly.setAdapter(adapter);

        lv_classly.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Classify_Right_Fragment classify_right_fragment = new Classify_Right_Fragment();

                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();

                fragmentTransaction.replace(R.id.fm_right_fragment,classify_right_fragment);

                Bundle bundle = new Bundle();

                bundle.putInt("cid",data.get(position).getCid());
                Log.d(TAG, "onItemClick: "+data.get(position).getCid());

                classify_right_fragment.setArguments(bundle);

                fragmentTransaction.commit();

            }
        });




    }

    @Override
    public void getError(String error) {



    }

    @Override
    public void getElSuccess(String json) {

    }

    @Override
    public void getElError(String error) {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser){

            classlyPressenter.left_listViewPersenter(HttpConfig.LEFT_LISTVIEW_URL);

        }else{

        }
    }
}
