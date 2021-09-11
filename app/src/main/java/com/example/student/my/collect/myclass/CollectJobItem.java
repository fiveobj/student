package com.example.student.my.collect.myclass;

public class CollectJobItem {
    public String money,job_name,where,time,job_level,job_way,company_name,company_level,company_peo;
    public int com_img;

    public CollectJobItem(String money,String job_name,String job_level,String job_way,String where,String time,String company_level,String company_name,String company_peo,int com_img){
        this.com_img=com_img;
        this.company_level=company_level;
        this.company_name=company_name;
        this.job_level=job_level;
        this.job_name=job_name;
        this.job_way=job_way;
        this.where=where;
        this.time=time;
        this.company_peo=company_peo;
        this.money=money;
    }

    public String getTime() {
        return time;
    }

    public int getCom_img() {
        return com_img;
    }

    public String getCompany_level() {
        return company_level;
    }

    public String getCompany_name() {
        return company_name;
    }

    public String getCompany_peo() {
        return company_peo;
    }

    public String getJob_level() {
        return job_level;
    }

    public String getJob_name() {
        return job_name;
    }

    public String getJob_way() {
        return job_way;
    }

    public String getWhere() {
        return where;
    }

    public String getMoney() {
        return money;
    }
}
