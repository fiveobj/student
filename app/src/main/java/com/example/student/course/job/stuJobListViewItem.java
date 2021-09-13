package com.example.student.course.job;

import android.widget.ImageView;

public class stuJobListViewItem {
    private int jobimv,stateimv;
    private String name,start,end,goal,state,pnum;
    public stuJobListViewItem(int jobimv,int stateimv,String name,String end,String start, String goal,String state, String pnum){
        this.end=end;
        this.goal=goal;
        this.jobimv=jobimv;
        this.name=name;
        this.pnum=pnum;
        this.start=start;
        this.state=state;
        this.stateimv=stateimv;
    }

    public int getJobimv() {
        return jobimv;
    }

    public int getStateimv() {
        return stateimv;
    }

    public String getEnd() {
        return end;
    }

    public String getGoal() {
        return goal;
    }

    public String getName() {
        return name;
    }

    public String getPnum() {
        return pnum;
    }

    public String getStart() {
        return start;
    }

    public String getState() {
        return state;
    }

}
