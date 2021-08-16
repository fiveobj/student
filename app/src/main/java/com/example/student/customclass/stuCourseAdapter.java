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

public class stuCourseAdapter extends ArrayAdapter<stuCourseListViewItem> {
    private int newResourceId;
    public stuCourseAdapter(@NonNull Context context, int resource, @NonNull List<stuCourseListViewItem> itemList) {
        super(context, resource, itemList);
        newResourceId=resource;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        stuCourseListViewItem stuCourseListViewItem=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(newResourceId,parent,false);
        TextView coursename=view.findViewById(R.id.stu_course_name);
        TextView teachername=view.findViewById(R.id.stu_course_teachname);
        ImageView courseimg=view.findViewById(R.id.stu_course_ImgV);

        coursename.setText(stuCourseListViewItem.getCoursename());
        teachername.setText(stuCourseListViewItem.getTeachername());
        courseimg.setImageResource(stuCourseListViewItem.getCuorseImg());
        return view;
    }
}
