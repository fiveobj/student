package com.example.student.my.exp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.student.R;

import java.util.List;

public class MyexpPasttimrAdapter extends ArrayAdapter<MyexpPasttimeItme> {

    private int newResourceId;
    private Context context;


    public MyexpPasttimrAdapter(@NonNull Context context, int resource, @NonNull List<MyexpPasttimeItme> objects) {
        super(context, resource, objects);
        newResourceId=resource;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view= LayoutInflater.from(getContext()).inflate(newResourceId,parent,false);

        MyexpPasttimeItme myexpPasttimeItme=getItem(position);
        TextView name=view.findViewById(R.id.myexp_pasttime_name);
        TextView money=view.findViewById(R.id.myexp_pasttime_money);
        TextView time=view.findViewById(R.id.myexp_pasttime_time);
        myexpPasttimeItme.button=view.findViewById(R.id.myexp_pasttime_umgbtn);

        name.setText(myexpPasttimeItme.getName());
        money.setText(myexpPasttimeItme.getMoney());
        time.setText(myexpPasttimeItme.getTime());

        myexpPasttimeItme.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }
}
