package com.example.student.customclass;

public class recomCourseListViewItem {
    private String coursename,courseintro;
    private int cuorseImg;

    public recomCourseListViewItem(String coursename,String courseintro,int cuorseImg)
    {
        this.coursename=coursename;
        this.cuorseImg=cuorseImg;
        this.courseintro=courseintro;
    }
    public String getCoursename()
    {
        return coursename;
    }
    public String getCourseintro()
    {
        return courseintro;
    }
    public int getCuorseImg()
    {
        return cuorseImg;
    }
}
