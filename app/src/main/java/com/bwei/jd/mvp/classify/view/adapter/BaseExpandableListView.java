package com.bwei.jd.mvp.classify.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.bwei.jd.R;
import com.bwei.jd.mvp.classify.view.fragment.MyGridView;

import java.util.List;

public class BaseExpandableListView extends BaseExpandableListAdapter {

    private Context context;

    private List<String> productName;

    private List<List<String>> child_Name;

    private List<List<String>> child_Img;

    public BaseExpandableListView(Context context, List<String> productName, List<List<String>> child_name, List<List<String>> child_img) {
        this.context = context;
        this.productName = productName;
        child_Name = child_name;
        child_Img = child_img;
    }

    @Override
    public int getGroupCount() {

        return productName == null ? 0 : productName.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
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
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        GroupHolder groupHolder;

        if (convertView == null) {

            groupHolder = new GroupHolder();

            convertView = View.inflate(context, R.layout.el_producttitle, null);

            groupHolder.el_title = convertView.findViewById(R.id.el_title);

            convertView.setTag(groupHolder);

        } else {

            groupHolder = (GroupHolder) convertView.getTag();

        }

        groupHolder.el_title.setText(productName.get(groupPosition));

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        MyGridView gridView = new MyGridView(context);

        El_Children_GridView el_children_gridView = new El_Children_GridView(context, child_Name.get(groupPosition), child_Img.get(groupPosition));

        gridView.setNumColumns(3);

        gridView.setGravity(Gravity.CENTER);// 位置居中
        gridView.setAdapter(el_children_gridView);

        return gridView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {

        return true;
    }

    class GroupHolder {

        TextView el_title;

    }
}
