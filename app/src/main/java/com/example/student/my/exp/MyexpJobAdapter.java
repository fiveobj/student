package com.example.student.my.exp;

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

public class MyexpJobAdapter extends ArrayAdapter<MyexpJobItem> {

    private int newResourceId;
    private Context context;


    public MyexpJobAdapter(@NonNull Context context, int resource, @NonNull List<MyexpJobItem> objects) {
        super(context, resource, objects);

        newResourceId=resource;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view= LayoutInflater.from(getContext()).inflate(newResourceId,parent,false);

        MyexpJobItem myexpJobItem=getItem(position);

        TextView name=view.findViewById(R.id.myexp_job_name);
        TextView time=view.findViewById(R.id.myexp_job_time);
        TextView type=view.findViewById(R.id.myexp_job_type);
        ImageView image=view.findViewById(R.id.myexp_job_img);
        myexpJobItem.button=view.findViewById(R.id.myexp_job_detailbtn);

        name.setText(myexpJobItem.getName());
        time.setText(myexpJobItem.getTime());
        type.setText(myexpJobItem.getType());
        image.setImageResource(myexpJobItem.getImg());

        myexpJobItem.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }
}
