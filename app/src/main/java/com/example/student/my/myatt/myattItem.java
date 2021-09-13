package com.example.student.my.myatt;

import android.widget.ImageButton;

public class myattItem {
    public String myattname;
    public int myattimg;
    public ImageButton btn;
    public myattItem(String myattname,int myattimg){
        this.myattimg=myattimg;
        this.myattname=myattname;
    }

    public int getMyattimg() {
        return myattimg;
    }

    public String getMyattname() {
        return myattname;
    }
}
