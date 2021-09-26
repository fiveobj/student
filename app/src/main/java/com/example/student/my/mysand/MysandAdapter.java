package com.example.student.my.mysand;

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

public class MysandAdapter extends ArrayAdapter<MysandItem> {

    private int newResourceId;
    private Context context;

    public MysandAdapter(@NonNull Context context, int resource, @NonNull List<MysandItem> objects) {
        super(context, resource, objects);

        newResourceId=resource;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view= LayoutInflater.from(getContext()).inflate(newResourceId,parent,false);

        MysandItem mysandItem=getItem(position);
        TextView name=view.findViewById(R.id.mysand_job_name);
        TextView detail=view.findViewById(R.id.mysand_job_detail);
        TextView time=view.findViewById(R.id.mysand_job_time);
        TextView state=view.findViewById(R.id.mysand_job_state);
        TextView money=view.findViewById(R.id.mysand_job_money);
        TextView where=view.findViewById(R.id.mysand_job_where);
        ImageView img=view.findViewById(R.id.mysand_imv);

        name.setText(mysandItem.getName());
        detail.setText(mysandItem.getDetail());
        state.setText(mysandItem.getState());
        money.setText(mysandItem.getMoney());
        where.setText(mysandItem.getWhere());
        time.setText(mysandItem.getTime());
        img.setImageResource(mysandItem.getImag());



        return  view;
    }
}
