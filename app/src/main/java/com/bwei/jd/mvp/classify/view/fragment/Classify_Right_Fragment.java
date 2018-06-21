package com.bwei.jd.mvp.classify.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.bwei.jd.R;
import com.bwei.jd.http.HttpConfig;
import com.bwei.jd.mvp.classify.model.Product_Children;
import com.bwei.jd.mvp.classify.presenter.ClasslyPressenter;
import com.bwei.jd.mvp.classify.view.adapter.BaseExpandableListView;
import com.bwei.jd.mvp.classify.view.iview.IClasslyView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Classify_Right_Fragment extends Fragment implements IClasslyView {


    private static final String TAG = "Classify_Right_Fragment";

    private ClasslyPressenter classlyPressenter;

    private List<String> product = new ArrayList<>();

    private ExpandableListView right_el;

    private List<List<String>> childName = new ArrayList<>();
    private List<List<String>> childImg = new ArrayList<>();

    private View view;
    private int cid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_classify_layout, null);

        classlyPressenter = new ClasslyPressenter(this);

        initViews();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle arguments = getArguments();

        cid = arguments.getInt("cid");

        Toast.makeText(getContext(), "" + cid, Toast.LENGTH_SHORT).show();

        classlyPressenter.right_el(cid);
    }

    private void initViews() {

        right_el = view.findViewById(R.id.right_el);

    }

    public void getData(int cid) {
        Bundle bundle = new Bundle();

        bundle.putInt("cid", cid);

        setArguments(bundle);

    }

    @Override
    public void getSuccess(String json) {


    }

    @Override
    public void getError(String error) {

    }

    @Override
    public void getElSuccess(String json) {

        Gson g = new Gson();

        Product_Children product_children = g.fromJson(json, Product_Children.class);

        List<Product_Children.DataBean> data = product_children.getData();

        for (int i = 0; i < data.size(); i++) {

            Log.d(TAG, "getElSuccess: 您进循环了");

            String productName = data.get(i).getName();

            product.add(productName);

            List<Product_Children.DataBean.ListBean> list = data.get(i).getList();

            List<String> list_name = new ArrayList<>();
            List<String> list_img = new ArrayList<>();

            for (int j = 0; j < list.size(); j++) {

                list_name.add(list.get(j).getName());
                list_img.add(list.get(j).getIcon());

                Log.d(TAG, "getElSuccess: " + list_name.size());

            }

            childName.add(list_name);
            childImg.add(list_img);

        }

        BaseExpandableListAdapter adapter = new BaseExpandableListView(getActivity(), product, childName, childImg);

        right_el.setAdapter(adapter);

    }

    @Override
    public void getElError(String error) {
        Log.d(TAG, "getElError: " + error);

    }

}
