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

import java.util.List;
import com.example.student.R;
public class CollectJobAdapter extends ArrayAdapter<CollectJobItem> {

    private int newResourceId;
    private Context context;

    public CollectJobAdapter(@NonNull Context context, int resource, @NonNull List<CollectJobItem> objects) {
        super(context, resource, objects);
        newResourceId=resource;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view= LayoutInflater.from(getContext()).inflate(newResourceId,parent,false);

        CollectJobItem collectJobItem=getItem(position);
        TextView job_name=view.findViewById(R.id.job_name);
        TextView where=view.findViewById(R.id.job_where);
        TextView time=view.findViewById(R.id.job_time);
        TextView job_level=view.findViewById(R.id.job_level);
        TextView job_way=view.findViewById(R.id.job_way);
        TextView company_name=view.findViewById(R.id.job_compeny_name);
        TextView company_level=view.findViewById(R.id.job_compeny_level);
        TextView company_peo=view.findViewById(R.id.job_compeny_peo);
        TextView money=view.findViewById(R.id.job_money);
        ImageView image=view.findViewById(R.id.collect_job_imv);

        job_level.setText(collectJobItem.getJob_level());
        where.setText(collectJobItem.getWhere());
        job_name.setText(collectJobItem.getJob_name());
        job_way.setText(collectJobItem.getJob_way());
        time.setText(collectJobItem.getTime());
        company_level.setText(collectJobItem.getCompany_level());
        company_name.setText(collectJobItem.getCompany_name());
        company_peo.setText(collectJobItem.getCompany_peo());
        money.setText(collectJobItem.getMoney());
        image.setImageResource(collectJobItem.getCom_img());

        return view;
    }
}
