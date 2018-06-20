package com.bwei.jd.mvp.Home.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bwei.jd.R;
import com.bwei.jd.app.MyApp;
import com.bwei.jd.mvp.Home.model.Home_ViewPagerBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class Home_MiaoSha_RecyclerView extends RecyclerView.Adapter<Home_MiaoSha_RecyclerView.MyViewHolder> {

    private List<Home_ViewPagerBean.MiaoshaBean.ListBeanX> list;

    public Home_MiaoSha_RecyclerView(List<Home_ViewPagerBean.MiaoshaBean.ListBeanX> list) {

        this.list = list;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemType = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(itemType);


        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        String[] split = list.get(position).getImages().split("\\|");

        ImageLoader.getInstance().displayImage(split[0], holder.item_img, MyApp.getOptions());

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView item_img;

        public MyViewHolder(View itemView) {
            super(itemView);

            item_img = itemView.findViewById(R.id.item_img);

        }
    }
}
