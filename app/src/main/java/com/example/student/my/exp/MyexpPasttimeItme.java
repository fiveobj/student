package com.example.student.my.exp;

import android.widget.ImageButton;

public class MyexpPasttimeItme {

    public String name,money,time;
    ImageButton button;
    public MyexpPasttimeItme(String name,String money,String time){
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
