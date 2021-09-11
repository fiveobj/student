package com.example.student.my.collect.myclass;

import android.widget.ImageButton;

public class CollectProjectItem {
    public String name,where,state,time;
    public ImageButton button;
    public CollectProjectItem(String name,String where,String state,String time){
        this.time=time;
        this.name=name;
        this.state=state;
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

    public String getState() {
        return state;
    }
}

