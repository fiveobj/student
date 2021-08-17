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

public class recomCourseAdapter extends ArrayAdapter<recomCourseListViewItem> {
    private int newResourceId;

    public recomCourseAdapter(@NonNull Context context, int resource, @NonNull List<recomCourseListViewItem> itemList) {
        super(context, resource, itemList);
        newResourceId=resource;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        recomCourseListViewItem recomCourseListViewItem=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(newResourceId,parent,false);

        TextView coursename=view.findViewById(R.id.recom_course_name);
        TextView courseintro=view.findViewById(R.id.recom_course_intro);
        ImageView courseimg=view.findViewById(R.id.recom_course_ImgV);

        coursename.setText(recomCourseListViewItem.getCoursename());
        courseintro.setText(recomCourseListViewItem.getCourseintro());
        courseimg.setImageResource(recomCourseListViewItem.getCuorseImg());

        return view;
    }
}
