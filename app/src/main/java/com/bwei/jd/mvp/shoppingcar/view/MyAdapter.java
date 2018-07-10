package com.bwei.jd.mvp.shoppingcar.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.jd.R;
import com.bwei.jd.app.MyApp;
import com.bwei.jd.mvp.shoppingcar.model.ShoppingCarBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class MyAdapter extends BaseExpandableListAdapter {

    private Context context;

    private List<ShoppingCarBean.DataBean> list;

    public MyAdapter(Context context, List<ShoppingCarBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getList() == null ? 0 : list.get(groupPosition).getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        ShopsViewHolder shopsViewHolder;

        if (convertView == null) {

            shopsViewHolder = new ShopsViewHolder();

            convertView = View.inflate(context, R.layout.shopping_shops, null);

            shopsViewHolder.cb_Shops = convertView.findViewById(R.id.Cb_shops);

            shopsViewHolder.shops_Name = convertView.findViewById(R.id.shops_name);

            convertView.setTag(shopsViewHolder);

        } else {

            shopsViewHolder = (ShopsViewHolder) convertView.getTag();

        }


        shopsViewHolder.shops_Name.setText(list.get(groupPosition).getSellerName());

        boolean currentProductSelected = isCurrentProductSelected(groupPosition);

        shopsViewHolder.cb_Shops.setSelected(currentProductSelected);

        shopsViewHolder.cb_Shops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onCartListChangeListener != null) {

                    onCartListChangeListener.onSellerCheckedChange(groupPosition);

                }

            }
        });

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ProductViewHolder productViewHolder;

        if (convertView == null) {

            productViewHolder = new ProductViewHolder();

            convertView = View.inflate(context, R.layout.shopping_product, null);

            productViewHolder.cb_Product = convertView.findViewById(R.id.Cb_product);
            productViewHolder.img_Product = convertView.findViewById(R.id.img_product);
            productViewHolder.myView = convertView.findViewById(R.id.product_MyView);
            productViewHolder.tv_product_price = convertView.findViewById(R.id.tv_product_price);
            productViewHolder.tv_product_title = convertView.findViewById(R.id.tv_product_title);

            convertView.setTag(productViewHolder);

        } else {

            productViewHolder = (ProductViewHolder) convertView.getTag();

        }

        List<ShoppingCarBean.DataBean.ListBean> list = this.list.get(groupPosition).getList();

        productViewHolder.tv_product_title.setText(list.get(childPosition).getTitle());
        productViewHolder.tv_product_price.setText(list.get(childPosition).getPrice() + "元");

        String[] split = list.get(childPosition).getImages().split("\\|");

        ImageLoader.getInstance().displayImage(split[0], productViewHolder.img_Product, MyApp.getOptions());

        productViewHolder.cb_Product.setSelected(list.get(childPosition).getSelected() == 1);

        productViewHolder.cb_Product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCartListChangeListener != null) {

                    onCartListChangeListener.onProductCheckedChange(groupPosition, childPosition);

                }
            }

        });
        productViewHolder.myView.setNumber(list.get(childPosition).getNum());
        productViewHolder.myView.setOnNumberClickListener(new MyView.OnNumberClickListener() {
            @Override
            public void onNumberClick(int num) {

                if (onCartListChangeListener != null) {

                    onCartListChangeListener.onProductNumberChange(groupPosition, childPosition, num);

                }

            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public boolean isCurrentProductSelected(int groupPosition) {

        ShoppingCarBean.DataBean dataBean = list.get(groupPosition);

        List<ShoppingCarBean.DataBean.ListBean> list1 = dataBean.getList();

        for (ShoppingCarBean.DataBean.ListBean listBean : list1){

            if (listBean.getSelected() == 0){

                return false;

            }

        }

        return true;
    }

    public void isCurrentAllSelected(int groupPosition, boolean b) {

        ShoppingCarBean.DataBean dataBean = list.get(groupPosition);

        List<ShoppingCarBean.DataBean.ListBean> list = dataBean.getList();

        for (int i = 0; i < list.size(); i++) {

            ShoppingCarBean.DataBean.ListBean listBean = list.get(i);

            listBean.setSelected(b ? 1 : 0);

        }
    }

    public boolean isAllCheckboxSelected() {

        for (int i = 0; i < list.size(); i++) {

            List<ShoppingCarBean.DataBean.ListBean> list = this.list.get(i).getList();

            for (int j = 0; j < list.size(); j++) {

                if (list.get(j).getSelected() == 0) {

                    return false;

                }

            }

        }

        return true;
    }

    public int sumCheckedNumber() {

        int toalnumber = 0;

        for (int i = 0; i < list.size(); i++) {

            List<ShoppingCarBean.DataBean.ListBean> list = this.list.get(i).getList();

            for (int j = 0; j < list.size(); j++) {

                if (list.get(j).getSelected() == 1) {

                    int num = list.get(j).getNum();

                    toalnumber += num;

                }

            }

        }

        return toalnumber;
    }

    public double sumCheckedPrice() {
        double toalPrice = 0;

        for (int i = 0; i < list.size(); i++) {

            List<ShoppingCarBean.DataBean.ListBean> list = this.list.get(i).getList();

            for (int j = 0; j < list.size(); j++) {

                if (list.get(j).getSelected() == 1) {

                    double price = list.get(j).getPrice();

                    int num = list.get(j).getNum();

                    toalPrice += price * num;

                }

            }

        }

        return toalPrice;
    }

    public void changeCurrentProductStatus(int groupPosition, int childPosition) {

        List<ShoppingCarBean.DataBean.ListBean> list = this.list.get(groupPosition).getList();

        ShoppingCarBean.DataBean.ListBean listBean = list.get(childPosition);

        listBean.setSelected(listBean.getSelected() == 0 ? 1 : 0);

    }

    public void changeCurrentProductNumber(int groupPosition, int childPosition, int number) {

        List<ShoppingCarBean.DataBean.ListBean> list = this.list.get(groupPosition).getList();

        ShoppingCarBean.DataBean.ListBean listBean = list.get(childPosition);

        listBean.setNum(number);

    }

    public boolean isAllProductSelected() {

        for (int i = 0; i < list.size(); i++) {

            List<ShoppingCarBean.DataBean.ListBean> list = this.list.get(i).getList();

            for (int j = 0; j < list.size(); j++) {

                if (list.get(j).getSelected() == 0) {

                    return false;

                }

            }

        }

        return true;
    }

    public void achangeAllProductStatus(boolean b) {

        for (int i = 0; i < list.size(); i++) {

            List<ShoppingCarBean.DataBean.ListBean> list = this.list.get(i).getList();

            for (int j = 0; j < list.size(); j++) {

                list.get(j).setSelected(b ? 1 : 0);

            }

        }

    }

    class ShopsViewHolder {

        CheckBox cb_Shops;

        TextView shops_Name;

    }

    class ProductViewHolder {

        CheckBox cb_Product;

        ImageView img_Product;

        TextView tv_product_title, tv_product_price;

        MyView myView ;

    }

    onCartListChangeListener onCartListChangeListener;

    public void setOnCartListChangeListener(MyAdapter.onCartListChangeListener onCartListChangeListener) {

        this.onCartListChangeListener = onCartListChangeListener;

    }

    public interface onCartListChangeListener {

        void onSellerCheckedChange(int groupPosition);

        void onProductCheckedChange(int groupPosition, int childPosition);

        void onProductNumberChange(int groupPosition, int childPosition, int number);
    }

}
