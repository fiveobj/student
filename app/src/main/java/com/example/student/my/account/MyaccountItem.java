package com.example.student.my.account;

import android.widget.TextView;

import com.example.student.customclass.RadiusImageView;

public class MyaccountItem {

    private RadiusImageView image;
    private int im;
    private TextView name,time,money;
    private String name_tv,time_tv,money_tv;

    public MyaccountItem(String name_tv,String time_tv,String money_tv,int image)
    {
        this.money_tv=money_tv;
        this.name_tv=name_tv;
        this.time_tv=time_tv;
        this.im=image;
    }

    public String getMoney_tv() {
        return money_tv;
    }

    public String getName_tv() {
        return name_tv;
    }

    public String getTime_tv() {
        return time_tv;
    }

    public int getIm() {
        return im;
    }
}
