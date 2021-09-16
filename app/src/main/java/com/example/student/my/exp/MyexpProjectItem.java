package com.example.student.my.exp;

import android.widget.ImageButton;

public class MyexpProjectItem {
    public String name,where,time;
    public ImageButton button;
    public MyexpProjectItem(String name,String where,String time){
        this.time=time;
        this.name=name;

        this.where=where;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getWhere() {
        return where;
    }



}
