package com.bwei.jd.mvp.classify.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.jd.R;
import com.bwei.jd.app.MyApp;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class El_Children_GridView extends BaseAdapter{

    private Context context;

    private List<String> childName;

    private List<String> childImg;

    public El_Children_GridView(Context context, List<String> childName, List<String> childImg) {
        this.context = context;
        this.childName = childName;
        this.childImg = childImg;
    }

    @Override
    public int getCount() {
        return childImg == null ? 0: childImg.size();
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

            convertView = View.inflate(context, R.layout.el_children_gridview,null);

            viewHolder.img_gridview_classify = convertView.findViewById(R.id.img_grid_view_classify);
            viewHolder.tv_gridview_classify = convertView.findViewById(R.id.tv_grid_item_classify);

            convertView.setTag(viewHolder);

        }else{

            viewHolder = (ViewHolder) convertView.getTag();

        }

        viewHolder.tv_gridview_classify.setText(childName.get(position));

        ImageLoader.getInstance().displayImage(childImg.get(position),viewHolder.img_gridview_classify, MyApp.getOptions());

        return convertView;
    }

    class ViewHolder{

        ImageView img_gridview_classify;

        TextView tv_gridview_classify;

    }
}
