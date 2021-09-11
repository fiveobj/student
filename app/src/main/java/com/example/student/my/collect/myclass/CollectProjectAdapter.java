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

public class CollectProjectAdapter extends ArrayAdapter<CollectProjectItem> {
    private int newResourceId;
    private Context context;

    public CollectProjectAdapter(@NonNull Context context, int resource, @NonNull List<CollectProjectItem> objects) {
        super(context, resource, objects);
        newResourceId=resource;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view= LayoutInflater.from(getContext()).inflate(newResourceId,parent,false);

        CollectProjectItem collectProjectItem=getItem(position);

        TextView name=view.findViewById(R.id.project_name);
        TextView where=view.findViewById(R.id.project_where);
        TextView state=view.findViewById(R.id.project_state);
        TextView time=view.findViewById(R.id.project_time);
        collectProjectItem.button=view.findViewById(R.id.project_imgbtn);

        name.setText(collectProjectItem.getName());
        where.setText(collectProjectItem.getWhere());
        state.setText(collectProjectItem.getState());
        time.setText(collectProjectItem.getTime());

        collectProjectItem.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }
}
