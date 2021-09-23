package com.example.student.my.course;

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

public class mycourse_startAdapter extends ArrayAdapter<mycourse_startitem> {

    private int newResourceId;
    private Context context;


    public mycourse_startAdapter(@NonNull Context context, int resource, @NonNull List<mycourse_startitem> objects) {
        super(context, resource, objects);

        newResourceId=resource;
        this.context=context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view= LayoutInflater.from(getContext()).inflate(newResourceId,parent,false);


        mycourse_startitem item=getItem(position);
        TextView name=view.findViewById(R.id.mycourse_start_name);
        TextView time=view.findViewById(R.id.mycourse_start_time);
        TextView tea=view.findViewById(R.id.mycourse_start_teacher);
        ImageView imageView=view.findViewById(R.id.mycourse_start_imag);
        item.button=view.findViewById(R.id.mycourse_start_imgbtn);

        name.setText(item.getName());
        time.setText(item.getTime());
        tea.setText(item.getTeacher());
        imageView.setImageResource(item.getStartimg());

        item.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return view;
    }
}
