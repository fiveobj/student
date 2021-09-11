package com.example.student.my.collect.myclass;

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



public class CollectFairAdapter extends ArrayAdapter<CollectFairItem> {

    private int newResourceId;
    private Context context;


    public CollectFairAdapter(@NonNull Context context, int resource, @NonNull List<CollectFairItem> objects) {
        super(context, resource, objects);

        newResourceId=resource;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view= LayoutInflater.from(getContext()).inflate(newResourceId,parent,false);

        CollectFairItem collectFairItem=getItem(position);
        TextView name=view.findViewById(R.id.fair_name);
        TextView time=view.findViewById(R.id.fair_time);
        ImageView image=view.findViewById(R.id.fair_imag);
        collectFairItem.button=view.findViewById(R.id.fair_imgbtn);

        name.setText(collectFairItem.getName());
        time.setText(collectFairItem.getTime());
        image.setImageResource(collectFairItem.getFairimg());

        collectFairItem.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return view;
    }
}
