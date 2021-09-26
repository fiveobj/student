package com.example.student.my.mypost;

import com.xuexiang.xui.widget.picker.wheelview.interfaces.IPickerViewItem;

import java.util.List;

public class PostInfo implements IPickerViewItem {

    private String name;
    public List<String> job;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getJob() {
        return job;
    }

    public void setJob(List<String> job) {
        this.job = job;
    }

    @Override
    public String getPickerViewText() {
        return this.name;
    }

}
