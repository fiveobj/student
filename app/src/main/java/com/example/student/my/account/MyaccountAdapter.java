package com.example.student.my.account;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.student.R;

import java.util.List;

public class MyaccountAdapter extends ArrayAdapter<MyaccountItem> {
    private int newResourceId;
    private Context context;
    public MyaccountAdapter(@NonNull Context context, int resource, @NonNull List<MyaccountItem> objects) {
        super(context, resource, objects);
        newResourceId=resource;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view= LayoutInflater.from(getContext()).inflate(newResourceId,parent,false);
        MyaccountItem myaccountItem=getItem(position);

        TextView name=view.findViewById(R.id.myaccount_item_name);
        TextView time=view.findViewById(R.id.myaccount_item_time);
        TextView money=view.findViewById(R.id.myaccount_item_money);
        ImageView im=view.findViewById(R.id.myaccount_item_img);

        name.setText(myaccountItem.getName_tv());
        time.setText(myaccountItem.getTime_tv());
        money.setText(myaccountItem.getMoney_tv());
        im.setImageResource(myaccountItem.getIm());


        return view;
    }
}
