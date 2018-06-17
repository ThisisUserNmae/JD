package com.bwei.jd.mvp.classify.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bwei.jd.R;
import com.bwei.jd.mvp.classify.model.Classly_ListViewBean;

import java.util.List;

public class Left_ListViewBaseAdapter extends BaseAdapter {

    private List<Classly_ListViewBean.DataBean> list;

    private Context context;

    public Left_ListViewBaseAdapter(List<Classly_ListViewBean.DataBean> list, Context context) {
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

            convertView = View.inflate(context, R.layout.classly_left_listview_item_layout,null);

            viewHolder.classly_left_listview_name = convertView.findViewById(R.id.classly_left_listview_name);

            convertView.setTag(viewHolder);

        }else{

            viewHolder = (ViewHolder) convertView.getTag();

        }

        viewHolder.classly_left_listview_name.setText(list.get(position).getName());

        return convertView;
    }

    class ViewHolder{

        TextView classly_left_listview_name;

    }
}
