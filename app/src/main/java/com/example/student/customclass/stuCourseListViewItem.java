package com.example.student.customclass;

public class stuCourseListViewItem {
    private String coursename,teachername;
    private int cuorseImg;

    public stuCourseListViewItem(String coursename,String teachername,int cuorseImg)
    {
        this.coursename=coursename;
        this.cuorseImg=cuorseImg;
        this.teachername=teachername;
    }
    public String getCoursename()
    {
        return coursename;
    }
    public String getTeachername()
    {
        return teachername;
    }
    public int getCuorseImg()
    {
        return cuorseImg;
    }

}

