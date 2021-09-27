package com.example.student.contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.student.R;

import java.util.ArrayList;
import java.util.List;

public class CharAdapter extends BaseAdapter {

    private List<String>  data=new ArrayList<>();
    private Context context;
    private ViewHolder viewHolder;

    public void addDataToAdapter(String s){
        data.add(s);
    }

    public CharAdapter(Context context){
        this.context=context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.charitemlayout, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String datas=data.get(position);
        viewHolder.havesandtv.setText(datas);
        viewHolder.right.setVisibility(View.VISIBLE);


        return convertView;
    }

    public static class ViewHolder{

        public View rootView;
        public TextView havesandtv;
        public LinearLayout right;

        public ViewHolder(View rootView){
            this.rootView=rootView;
            this.havesandtv=(TextView)rootView.findViewById(R.id.text_right);
            this.right=(LinearLayout)rootView.findViewById(R.id.right);
        }
    }
}
