package com.example.student.my.mysand;

public class MysandItem {
    public String name,state,time,money,detail,where;
    public int imag;

    public MysandItem(String name,String state,String time,String money,String detail,String where,int imag){
        this.detail=detail;
        this.imag=imag;
        this.money=money;
        this.name=name;
        this.state=state;
        this.time=time;
        this.where=where;

    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public String getWhere() {
        return where;
    }

    public String getMoney() {
        return money;
    }

    public int getImag() {
        return imag;
    }

    public String getDetail() {
        return detail;
    }
}
