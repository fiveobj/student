package com.example.student.course.job;

import android.widget.ImageView;

import com.example.student.R;

public class stuJobListViewItem {
    private int jobimv,stateimv;
    private String name,start,end,goal,state,pnum;
    public stuJobListViewItem(int jobimv,String name,String end,String start, String goal,String state, String pnum){
        this.end=end;
        this.goal=goal;
        this.jobimv=jobimv;
        this.name=name;
        this.pnum=pnum;
        if (state.equals("-1")){
            this.state="未开始";
            this.stateimv= R.mipmap.stuc_job_over;
        }else if(state.equals("0")){
            this.state="进行中";
            this.stateimv=R.mipmap.stuc_job_ing;
        }else {
            this.state="已结束";
            this.stateimv=R.mipmap.stuc_job_end;
        }

        this.start=start;

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
