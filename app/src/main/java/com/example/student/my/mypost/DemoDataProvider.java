package com.example.student.my.mypost;

import com.google.gson.reflect.TypeToken;
import com.xuexiang.xutil.net.JsonUtil;
import com.xuexiang.xutil.resource.ResourceUtils;

import java.util.List;

public class DemoDataProvider {

    public static List<PostInfo> getJobInfos(){
        return JsonUtil.fromJson(ResourceUtils.readStringFromAssert("job.json"), new TypeToken<List<PostInfo>>(){

        }.getType());

    }
    public static List<PostInfo> getWhereInfos(){
        return JsonUtil.fromJson(ResourceUtils.readStringFromAssert("job.json"), new TypeToken<List<PostInfo>>(){

        }.getType());

    }
    public static List<PostInfo> getCompanyInfos(){
        return JsonUtil.fromJson(ResourceUtils.readStringFromAssert("job.json"), new TypeToken<List<PostInfo>>(){

        }.getType());

    }
    public static List<PostInfo> getTimeInfos(){
        return JsonUtil.fromJson(ResourceUtils.readStringFromAssert("job.json"), new TypeToken<List<PostInfo>>(){

        }.getType());

    }

    public static List<PostInfo> getVoiceInfos(){
        return JsonUtil.fromJson(ResourceUtils.readStringFromAssert("vioce.json"), new TypeToken<List<PostInfo>>(){

        }.getType());

    }

    public static String[] menuString=new String[]{
            "虚拟实验室",
            "添加课程",
    };
}
