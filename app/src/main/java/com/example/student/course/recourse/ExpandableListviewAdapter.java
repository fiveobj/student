package com.example.student.course.recourse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.student.R;

public class ExpandableListviewAdapter extends BaseExpandableListAdapter {
    //定义数据
    private String[] parents;
    private String[][] child;
    private int[][] childimv;
    private Context context;

    public ExpandableListviewAdapter(Context context,String[] parent,String[][] child,int[][] childimv){
        this.context=context;
        this.parents=parent;
        this.child=child;
        this.childimv=childimv;
    }
    @Override
    public int getGroupCount() {
        return parents.length;
    }

    @Override
    public int getChildrenCount(int i) {
        return child[i].length;
    }

    @Override
    public Object getGroup(int i) {
        return parents[i];
    }

    @Override
    public Object getChild(int i, int j) {
        return child[i][j];
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int j) {
        return j;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    /**
     *
     * 获取显示指定组的视图对象
     *
     * @param groupPosition 组位置
     * @param isExpanded 该组是展开状态还是伸缩状态，true=展开
     * @param convertView 重用已有的视图对象
     * @param parent 返回的视图对象始终依附于的视图组
     */

    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ParentViewHolder parentViewHolder;
        if(convertView==null)
        {
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.stuc_rec_elist_parentitem,parent,false);
            parentViewHolder=new ParentViewHolder();
            parentViewHolder.parentimg=convertView.findViewById(R.id.stuc_rec_parentitemIv);
            parentViewHolder.parenttv=convertView.findViewById(R.id.stuc_rec_parentitemtv);
            parentViewHolder.parent_imagemore=convertView.findViewById(R.id.stuc_rec_parentitemmore);
            convertView.setTag(parentViewHolder);
        }else{
            parentViewHolder=(ParentViewHolder)convertView.getTag();
        }
        parentViewHolder.parenttv.setText(parents[groupPosition]);
        if(isExpanded){
            parentViewHolder.parent_imagemore.setImageDrawable(ContextCompat.getDrawable(context,R.mipmap.stuc_rec_parentitemmore));
        }else {
            parentViewHolder.parent_imagemore.setImageDrawable(ContextCompat.getDrawable(context,R.mipmap.stuc_rec_parentitemmore1));
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildeViewHolder childeViewHolder;
        if(convertView==null){
            convertView=LayoutInflater.from(parent.getContext()).inflate(R.layout.stuc_rec_elist_childitem,parent,false);
            childeViewHolder=new ChildeViewHolder();
            childeViewHolder.childimg=(ImageView)convertView.findViewById(R.id.stuc_rec_childitemIv);
            childeViewHolder.childtv=(TextView)convertView.findViewById(R.id.stuc_rec_childitemtv);
            convertView.setTag(childeViewHolder);
        }else{
            childeViewHolder=(ChildeViewHolder)convertView.getTag();
        }
        childeViewHolder.childtv.setText(child[groupPosition][childPosition]);
        childeViewHolder.childimg.setImageResource(childimv[groupPosition][childPosition]);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class ParentViewHolder{
        ImageView parentimg;
        TextView parenttv;
        ImageView parent_imagemore;
    }

    static class ChildeViewHolder{
        ImageView childimg;
        TextView childtv;
    }
}
