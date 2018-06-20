package com.bwei.jd.mvp.Home.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.jd.R;
import com.bwei.jd.app.MyApp;
import com.bwei.jd.mvp.Home.model.Home_ViewPagerBean;
import com.bwei.jd.mvp.Home.model.RecyclerViewBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class Home_MiaoSha_RecyclerView02 extends RecyclerView.Adapter<Home_MiaoSha_RecyclerView02.MyViewHolder> {

    private List<RecyclerViewBean.DataBean> list;

    public Home_MiaoSha_RecyclerView02(List<RecyclerViewBean.DataBean> list) {

        this.list = list;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemType = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview02, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(itemType);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        String icon = list.get(position).getIcon();

        ImageLoader.getInstance().displayImage(icon, holder.item_img, MyApp.getOptions());

        holder.item_title.setText(list.get(position).getName());

    }

    @Override
    public int getItemCount() {

        return list == null ? 0 : list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView item_img;
        private final TextView item_title;

        public MyViewHolder(View itemView) {
            super(itemView);

            item_img = itemView.findViewById(R.id.item_img);

            item_title = itemView.findViewById(R.id.item_title);

        }
    }
}
