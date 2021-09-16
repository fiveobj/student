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

public class MyexpProjectAdapter extends ArrayAdapter<MyexpProjectItem> {
    private int newResourceId;
    private Context context;

    public MyexpProjectAdapter(@NonNull Context context, int resource, @NonNull List<MyexpProjectItem> objects) {
        super(context, resource, objects);
        newResourceId=resource;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view= LayoutInflater.from(getContext()).inflate(newResourceId,parent,false);

        MyexpProjectItem myexpProjectItem=getItem(position);


        TextView name=view.findViewById(R.id.myexp_project_name);
        TextView where=view.findViewById(R.id.myexp_project_where);
        TextView time=view.findViewById(R.id.myexp_project_time);
        myexpProjectItem.button=view.findViewById(R.id.myexp_project_imgbtn);

        name.setText(myexpProjectItem.getName());
        where.setText(myexpProjectItem.getWhere());
        time.setText(myexpProjectItem.getTime());

        myexpProjectItem.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }
}
