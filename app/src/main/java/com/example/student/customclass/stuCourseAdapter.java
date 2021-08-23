package com.example.student.customclass;

import android.content.Context;
import android.content.Intent;
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

import com.example.student.course.stucourceActivity;
import com.example.student.R;
import com.example.student.course.courseFragment;
import com.example.student.course.liveFragment;

import java.util.List;

public class stuCourseAdapter extends ArrayAdapter<stuCourseListViewItem> {
    private int newResourceId;
    private Context context;
    public stuCourseAdapter(@NonNull Context context, int resource, @NonNull List<stuCourseListViewItem> itemList) {
        super(context, resource, itemList);
        newResourceId=resource;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        stuCourseListViewItem stuCourseListViewItem=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(newResourceId,parent,false);
        TextView coursename=view.findViewById(R.id.stu_course_name);
        TextView teachername=view.findViewById(R.id.stu_course_teachname);
        ImageView courseimg=view.findViewById(R.id.stu_course_ImgV);
        stuCourseListViewItem.live=view.findViewById(R.id.live);

        coursename.setText(stuCourseListViewItem.getCoursename());
        teachername.setText(stuCourseListViewItem.getTeachername());
        courseimg.setImageResource(stuCourseListViewItem.getCuorseImg());
        //实现直播按钮点击页面跳转效果
        stuCourseListViewItem.live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,stucourceActivity.class);
                intent.putExtra("id",4);
                context.startActivity(intent);
            }
        });
        return view;
    }
}
