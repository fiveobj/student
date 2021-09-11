package com.example.student.my.collect.myclass;

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

public class CollectPasttimeAdapter extends ArrayAdapter<CollectPasttimeItem> {

    private int newResourceId;
    private Context context;

    public CollectPasttimeAdapter(@NonNull Context context, int resource, @NonNull List<CollectPasttimeItem> objects) {
        super(context, resource, objects);
        newResourceId=resource;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view= LayoutInflater.from(getContext()).inflate(newResourceId,parent,false);

        CollectPasttimeItem collectPasttimeItem=getItem(position);
        TextView name=view.findViewById(R.id.pasttime_name);
        TextView money=view.findViewById(R.id.pasttime_money);
        TextView time=view.findViewById(R.id.pasttime_time);
        collectPasttimeItem.button=view.findViewById(R.id.pasttime_umgbtn);

        name.setText(collectPasttimeItem.getName());
        money.setText(collectPasttimeItem.getMoney());
        time.setText(collectPasttimeItem.getTime());

        collectPasttimeItem.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return view;
    }
}
