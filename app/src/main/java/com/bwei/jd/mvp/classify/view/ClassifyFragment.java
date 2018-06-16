package com.bwei.jd.mvp.classify.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.bwei.jd.R;
import com.bwei.jd.http.HttpConfig;
import com.google.gson.Gson;

import java.util.List;

public class ClassifyFragment extends Fragment {

    private static final String TAG = "ClassifyFragment----";

    private View view;

    private ListView lv_classly;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.classifyfragment,null);


        initViews();

        initDatas();

        return view;
    }

    private void initDatas() {


    }

    private void initViews() {

        Log.d(TAG, "onCreateView: 你进入了initViews  方法");

        lv_classly = view.findViewById(R.id.lv_classly);

    }

}
