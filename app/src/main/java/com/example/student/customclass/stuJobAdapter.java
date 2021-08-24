package com.example.student.customclass;

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

public class stuJobAdapter extends ArrayAdapter<stuJobListViewItem> {
    private int newResourceId;
    private Context context;
    public stuJobAdapter(@NonNull Context context, int resource, @NonNull List<stuJobListViewItem> objects) {
        super(context, resource, objects);
        newResourceId=resource;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view= LayoutInflater.from(getContext()).inflate(newResourceId,parent,false);
        stuJobListViewItem stuJobListViewItem=getItem(position);
        ImageView jobimv=view.findViewById(R.id.job_imv);
        ImageView stateimv=view.findViewById(R.id.job_stateimv);
        TextView name=view.findViewById(R.id.job_name);
        TextView start=view.findViewById(R.id.job_starttime);
        TextView end=view.findViewById(R.id.job_endtime);
        TextView goal=view.findViewById(R.id.job_goal);
        TextView state=view.findViewById(R.id.job_state);
        TextView pnum=view.findViewById(R.id.job_pnum);


        jobimv.setImageResource(stuJobListViewItem.getJobimv());
        stateimv.setImageResource(stuJobListViewItem.getStateimv());
        name.setText(stuJobListViewItem.getName());
        start.setText(stuJobListViewItem.getStart());
        state.setText(stuJobListViewItem.getState());
        goal.setText(stuJobListViewItem.getGoal());
        pnum.setText(stuJobListViewItem.getPnum());
        end.setText(stuJobListViewItem.getEnd());



        return view;

    }
}
