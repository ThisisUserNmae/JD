package com.bwei.jd.mvp.Home.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.jd.R;
import com.bwei.jd.app.MyApp;
import com.bwei.jd.mvp.Home.model.Home_ViewPagerBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class Home_GridViewAdapter extends BaseAdapter{

    private List<Home_ViewPagerBean.TuijianBean.ListBean> list;

    private Context context;

    public Home_GridViewAdapter(List<Home_ViewPagerBean.TuijianBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        ViewHolder viewHolder;

        if (convertView == null){

            viewHolder = new ViewHolder();

            convertView = View.inflate(context, R.layout.home_gridview_item_layout,null);

            viewHolder.grid_item_img = convertView.findViewById(R.id.grid_item_img);
            viewHolder.grid_item_title = convertView.findViewById(R.id.grid_item_title);

            convertView.setTag(viewHolder);

        }else{

            viewHolder = (ViewHolder) convertView.getTag();

        }

        viewHolder.grid_item_title.setText(list.get(position).getTitle());

        String images = list.get(position).getImages();

        String[] split = images.split("\\|");

        ImageLoader.getInstance().displayImage(split[0],viewHolder.grid_item_img, MyApp.getOptions());

        return convertView;
    }

    class ViewHolder{

        ImageView grid_item_img;

        TextView grid_item_title;

    }
}
