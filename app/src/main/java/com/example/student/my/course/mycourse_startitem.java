package com.example.student.my.course;

import android.widget.ImageButton;

public class mycourse_startitem {
    public String name,time,teacher;
    public int startimg;
    public ImageButton button;
    public mycourse_startitem(String name,String time,String teacher,int startimg){
        this.name=name;
        this.time=time;
        this.teacher=teacher;
        this.startimg=startimg;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public int getStartimg() {
        return startimg;
    }
}
