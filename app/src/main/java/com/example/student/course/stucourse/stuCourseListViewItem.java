package com.example.student.course.stucourse;

import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.widget.AppCompatButton;

public class stuCourseListViewItem {
    public String coursename,teachername;
    public int cuorseImg;
    public AppCompatButton sign,live;
    public stuCourseListViewItem(String coursename,String teachername,int cuorseImg)
    {
        this.coursename=coursename;
        this.cuorseImg=cuorseImg;
        this.teachername=teachername;
    }
    public String getCoursename()
    {
        return coursename;
    }
    public String getTeachername()
    {
        return teachername;
    }
    public int getCuorseImg()
    {
        return cuorseImg;
    }

}

