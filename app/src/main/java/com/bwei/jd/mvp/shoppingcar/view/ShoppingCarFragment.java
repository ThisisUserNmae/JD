package com.bwei.jd.mvp.shoppingcar.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.bwei.jd.R;
import com.bwei.jd.http.HttpConfig;
import com.bwei.jd.mvp.shoppingcar.model.ShoppingCarBean;
import com.bwei.jd.mvp.shoppingcar.presenter.ShoppingPresenter;
import com.bwei.jd.mvp.shoppingcar.view.iview.IShoppingView;
import com.google.gson.Gson;

import java.util.List;

public class ShoppingCarFragment extends Fragment implements IShoppingView,View.OnClickListener{


    private static final String TAG = "ShoppingCarFragment--";

    private View view;

    private ExpandableListView shopping_expandablelistview;

    private CheckBox shopping_cb;

    private TextView shopping_all_price;

    private ShoppingPresenter shoppingPresenter;

    private MyAdapter adapter;

    private Button close_btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.shoppingfragment,null);

        Log.d(TAG, "onCreateView: 我先进入的方法");

        //初始化页面
        initViews();

        return view;
    }

    private void initViews() {

        //获取资源ID
        shopping_expandablelistview = view.findViewById(R.id.shopping_expandablelistview);

        shopping_cb = view.findViewById(R.id.shopping_all_Cb);

        shopping_all_price = view.findViewById(R.id.shopping_all_price);

        close_btn = view.findViewById(R.id.close_btn);

        //给多选框绑定一个单击事件
        shopping_cb.setOnClickListener(this);

        //实例化ShoppingPresenter 对象

        if (shoppingPresenter == null){

            shoppingPresenter = new ShoppingPresenter(this);

            shoppingPresenter.shooppingPresenter(HttpConfig.PRODUCT_URL);
        }

    }

    @Override
    public void getSuccess(String json) {

        Gson g = new Gson();

        ShoppingCarBean shoppingCarBean = g.fromJson(json, ShoppingCarBean.class);

        String code = shoppingCarBean.getCode();

        if ("0".equals(code)){

            List<ShoppingCarBean.DataBean> data = shoppingCarBean.getData();

            adapter = new MyAdapter(getActivity(),data);

            shopping_expandablelistview.setAdapter(adapter);

            //默认二级代表全部展开
            for (int i = 0; i < data.size(); i++) {

                shopping_expandablelistview.expandGroup(i);

            }

            shopping_expandablelistview.setGroupIndicator(null);

            adapter.setOnCartListChangeListener(new MyAdapter.onCartListChangeListener() {
                @Override
                public void onSellerCheckedChange(int groupPosition) {


                    Log.d(TAG, "onSellerCheckedChange: 你进入了 商家的方法");

                    boolean currentProductSelected = adapter.isCurrentProductSelected(groupPosition);

                    adapter.isCurrentAllSelected(groupPosition,!currentProductSelected);

                    adapter.notifyDataSetChanged();

                    sumNumberAndPriceAndCheckBox();

                }

                @Override
                public void onProductCheckedChange(int groupPosition, int childPosition) {

                    Log.d(TAG, "onSellerCheckedChange: 你进入了 改变的方法");

                    adapter.changeCurrentProductStatus(groupPosition,childPosition);

                    adapter.notifyDataSetChanged();

                    sumNumberAndPriceAndCheckBox();

                }

                @Override
                public void onProductNumberChange(int groupPosition, int childPosition, int number) {

                    Log.d(TAG, "onSellerCheckedChange: 你进入了 商品和改变数量的方法");

                     adapter.changeCurrentProductNumber(groupPosition,childPosition,number);

                     adapter.notifyDataSetChanged();

                     sumNumberAndPriceAndCheckBox();
                }
            });

        }

    }

    private void sumNumberAndPriceAndCheckBox() {

        boolean allCheckboxSelected = adapter.isAllCheckboxSelected();

        shopping_cb.setSelected(allCheckboxSelected);

        int toalNumber = adapter.sumCheckedNumber();

        close_btn.setText("去结算("+toalNumber+")");

        double toalPrice = adapter.sumCheckedPrice();

        shopping_all_price.setText(toalPrice+"");

    }

    @Override
    public void getError(Exception e) {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && shoppingPresenter !=null){
            Log.d(TAG, "isVisibleToUser && shoppingPresenter !=null");

            shoppingPresenter.shooppingPresenter(HttpConfig.PRODUCT_URL);

        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.shopping_all_Cb:

                boolean allProductSelected = adapter.isAllProductSelected();

                adapter.achangeAllProductStatus(!allProductSelected);

                adapter.notifyDataSetChanged();

                sumNumberAndPriceAndCheckBox();

                break;

        }

    }
}
