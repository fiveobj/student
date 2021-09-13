package com.example.student.my.myatt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.student.R;

import java.util.List;

public class myattAdapter extends ArrayAdapter<myattItem> {

    private int newResourceId;
    private Context context;
    private List<myattItem> list;

    public myattAdapter(@NonNull Context context, int resource, @NonNull List<myattItem> objects) {
        super(context, resource, objects);
        newResourceId=resource;
        this.context=context;
        this.list=objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view= LayoutInflater.from(getContext()).inflate(newResourceId,parent,false);

        myattItem myattItem=getItem(position);
        TextView myattname=view.findViewById(R.id.myatt_name);
        ImageView myattimg=view.findViewById(R.id.myatt_image);
        ImageButton btn=view.findViewById(R.id.myatt_btn);
        myattItem.btn=view.findViewById(R.id.myatt_btn);

        myattimg.setImageResource(myattItem.getMyattimg());
        myattname.setText(myattItem.getMyattname());

        myattItem.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attDialog attDialog=new attDialog(context);
                String str="确定取消对【"+myattItem.getMyattname()+"】的关注？";
                attDialog.setCancel_tatiltv(str);
                attDialog.setCancel(new attDialog.IOnCancelListener() {
                    @Override
                    public void onCancel(com.example.student.my.myatt.attDialog dialog) {
                        Toast.makeText(context,"取消成功",Toast.LENGTH_SHORT).show();
                        list.remove(position);
                        notifyDataSetChanged();
                    }
                });
                attDialog.setConfirm(new attDialog.IOnConfirmListener() {
                    @Override
                    public void onCancel(com.example.student.my.myatt.attDialog dialog) {
                        //Toast.makeText(myattActivity.this,"取消成功",Toast.LENGTH_SHORT).show();
                    }
                });
                attDialog.show();
            }
        });

        return view;
    }
}
