package com.example.student.my.collect.myclass;

import android.widget.ImageButton;

public class CollectPasttimeItem {
    public String name,money,time;
    ImageButton button;
    public CollectPasttimeItem(String name,String money,String time){
        this.money=money;
        this.name=name;
        this.time=time;
    }

    public String getMoney() {
        return money;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

}
