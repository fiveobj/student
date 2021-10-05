package com.example.student.course;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.TextView;


import com.example.student.R;
import com.example.student.course.rec.recourse_detail_Activity;
import com.example.student.course.rec.recomCourseAdapter;
import com.example.student.course.rec.recomCourseListViewItem;


import com.example.student.course.stucourse.EduApplication;
import com.example.student.course.stucourse.stuCourseAdapter;
import com.example.student.course.stucourse.stucourceActivity;

import com.example.student.course.stucourse.stuCourseListViewItem;
import com.example.student.customclass.OkHttpClass;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.agora.edu.launch.AgoraEduSDK;
import io.agora.edu.launch.AgoraEduSDKConfig;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link courseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class courseFragment extends android.app.Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ImageButton coureseadd;
    private ImageButton search;
    private AutoCompleteTextView autoCompleteTextView;
    private String[] normlString=new String[]{
            "Java","Java 编程思想","Java从入门到精通","Java 教程","Java 全套教程","Java 实训课程",
            "Java 实训速成课"
    };
    private ArrayAdapter<String> arrayAdapter;
    private AppCompatButton appCompatButton;
    //-------------------------------------在学课程--------------------------------------------

    private ListView stucourselistv;
    private stuCourseAdapter stuadapter;
    private List<stuCourseListViewItem> stulist=new ArrayList<stuCourseListViewItem>();

    //-------------------------------------推荐课程-------------------------------------------

    private ListView recomcourselistv;
    private recomCourseAdapter recomadapter;
    private List<recomCourseListViewItem> recomlist=new ArrayList<recomCourseListViewItem>();


    public courseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment courseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static courseFragment newInstance(String param1, String param2) {
        courseFragment fragment = new courseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_course,null);

        //------------------------------------------------加载在学课程------------------------------------------------------------

        stucourselistv=(ListView)view.findViewById(R.id.stu_course);
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClass tools=new OkHttpClass();
                String result=tools.courseList();
                Log.d("result-setclass",result);
                int sum;
                List<String> courseids= new ArrayList<>();
                try {
                    JSONObject jsonObjectdata=new JSONObject(result);
                    String data=jsonObjectdata.getString("data");
                    String courseid="";
                    JSONArray jsonArray=new JSONArray(data);
                    sum=jsonArray.length();

                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        //Log.d("11111",jsonObject.toString());
                        if(jsonObject!=null){
                            String name=jsonObject.optString("name");
                            String teacher=jsonObject.optString("teacherName");
                            courseid=jsonObject.optString("id");
                            courseids.add(courseid);
                            stulist.add(new stuCourseListViewItem(name,teacher,R.drawable.java));
                            Log.d("list",stulist.toString());
                            Log.d("name",name);
                            Log.d("tea",teacher);
                        }

                    }


                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            stuadapter=new stuCourseAdapter(getActivity(),R.layout.stucourseitem,stulist);
                            stucourselistv.setAdapter(stuadapter);
                            //动态设置高度
                            setstulistvhigh();

                            //添加点击事件
                            stucourselistv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                    Intent intent=null;
                                    intent=new Intent(getActivity(), stucourceActivity.class);

                                    intent.putExtra("id",courseids.get(position));
                                    startActivity(intent);

                                }
                            });

                        }
                    });


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }).start();






        //-------------------------------------推荐课程--------------------------------------------

        recomcourselistv=(ListView)view.findViewById(R.id.recom_course);
        recomlist=getrecomData();
        recomadapter=new recomCourseAdapter(this.getActivity(),R.layout.recomcourseitem,recomlist);
        recomcourselistv.setAdapter(recomadapter);
        //添加点击事件
        recomcourselistv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=null;
                intent=new Intent(getActivity(), recourse_detail_Activity.class);

                startActivity(intent);
            }
        });

        setreclistvhigh();
        //------------------------------------------添加课程---------------------------------------------
        coureseadd=view.findViewById(R.id.course_add);

        coureseadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addclassDialog addclassDialog=new addclassDialog(getActivity());
                addclassDialog.setSubmit(new addclassDialog.IOnCanceListener() {
                    @Override
                    public void onCancel(com.example.student.course.addclassDialog dialog) {
                       String code=addclassDialog.getSubmit();
                       Log.d("code111111",code);
                       //Toast.makeText(getActivity(),""+code,Toast.LENGTH_SHORT).show();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                OkHttpClass tools=new OkHttpClass();
                                String result=tools.addClass(code);
                                Log.d("result-addclass",result);
                                try {
                                    JSONObject jsonObjectdata=new JSONObject(result);
                                    String data=jsonObjectdata.getString("data");


                                        JSONObject jsonObject = new JSONObject(data);
                                        //Log.d("11111",jsonObject.toString());
                                        if (jsonObject != null) {
                                            String name = jsonObject.optString("name");
                                            String teacher = jsonObject.optString("teacherName");

                                            stulist.add(new stuCourseListViewItem(name, teacher, R.drawable.java));
                                            Log.d("list", stulist.toString());
                                            Log.d("name", name);
                                            Log.d("tea", teacher);
                                        }

                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(getActivity(),"添加成功",Toast.LENGTH_SHORT).show();
                                            stuadapter.notifyDataSetChanged();
                                            stucourselistv.setAdapter(stuadapter);
                                            setstulistvhigh();
                                        }
                                    });

                                    } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();


                        //stulist=getstuDataone();
                        //stuadapter=new stuCourseAdapter(getActivity(),R.layout.stucourseitem,stulist);
                        //stucourselistv.setAdapter(stuadapter);
                        //setstulistvhigh();
                    }
                });
                addclassDialog.show();
            }
        });




        //搜索课程
        search=view.findViewById(R.id.course_search);
        autoCompleteTextView=view.findViewById(R.id.course_searchauto);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),searchActivity.class);
                intent.putExtra("search",autoCompleteTextView.getText());
                startActivity(intent);
            }
        });

        //实现一个适配器对象，用来给自动输入框添加自动装入内容
        arrayAdapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line,normlString);
        //给自动完成输入框添加内容适配器
        autoCompleteTextView.setAdapter(arrayAdapter);


        return view;
    }


   @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode== AgoraEduSDK.REQUEST_CODE_RTC){
            stuadapter.start();
        }
    }

    /*public List<stuCourseListViewItem> getstuData(){
        List<stuCourseListViewItem> list=new ArrayList<stuCourseListViewItem>();

        list.add(new stuCourseListViewItem("JAVA实训","张三",R.drawable.java));
        list.add(new stuCourseListViewItem("Web实训","李四",R.drawable.webstorm));
        list.add(new stuCourseListViewItem("python实训","王五",R.drawable.python));
        return list;
    }*/

    public List<stuCourseListViewItem> getstuDataone(){
        List<stuCourseListViewItem> list=new ArrayList<stuCourseListViewItem>();

        list.add(new stuCourseListViewItem("JAVA实训","张三",R.drawable.java));
        list.add(new stuCourseListViewItem("Web实训","李四",R.drawable.webstorm));
        list.add(new stuCourseListViewItem("python实训","王五",R.drawable.python));
        list.add(new stuCourseListViewItem("C#实训","黑六",R.drawable.c3));
        return list;
    }
    public List<recomCourseListViewItem>getrecomData(){
        List<recomCourseListViewItem> list=new ArrayList<recomCourseListViewItem>();

        list.add(new recomCourseListViewItem("python进阶实训课程","本次实训主要学习数据的爬取以及数据的分析，利用requests模块去爬取数据，利用xpath模块解析数据，利用numpy创建数据结构，利用pandas模块分析数据，以及matplotlib对数据进行可视化。",R.drawable.python1));
        list.add(new recomCourseListViewItem("UI实训","授课内容上的设定，对于课程的内容上，结合学员基础情况而设定，以项目案例做驱动，提升软件操作技术和设计理论理念，学员更加有兴趣学习，增加学员对于课程的理解和认可，从而学有所获和兴趣引导，以小组最后完成项目汇报，锻炼了个人的设计参与氛围也锻炼了团队协作能力，增加了除了设计以外的沟通表达技能。",R.
                drawable.ui));
        list.add(new recomCourseListViewItem("web实训课程","该项目为纯前端典型电商项目，开发主要JavaScript基础的学习、DOM的操作、学子商城首页、学子商城404页面、学子商城收藏页、学子商城登录页、使用盒子模型和浮动，定位等知识点。",R.drawable.web));

        return list;
    }

    public void setstulistvhigh(){
        ListAdapter listAdapter=stucourselistv.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, stucourselistv);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = stucourselistv.getLayoutParams();
        params.height = totalHeight + (stucourselistv.getDividerHeight() * (listAdapter.getCount() - 1));
        stucourselistv.setLayoutParams(params);
    }


    public void setreclistvhigh(){
        ListAdapter listAdapter=recomcourselistv.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, recomcourselistv);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = recomcourselistv.getLayoutParams();
        params.height = totalHeight + (recomcourselistv.getDividerHeight() * (listAdapter.getCount() - 1));
        recomcourselistv.setLayoutParams(params);
    }
}