package com.example.student;

public class LoginMessage {
    private String rspCode,rspMsg,data;
    public LoginMessage(String rspCode,String rspMsg,String data){
        this.rspCode=rspCode;
        this.rspMsg=rspMsg;
        this.data=data;
    }
    public String getRspCode(){
        return rspCode;
    }
    public String getRspMsg(){
        return rspMsg;
    }
    public String getData()
    {
        return data;
    }
}
