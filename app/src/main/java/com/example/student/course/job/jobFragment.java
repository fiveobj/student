package com.example.student.course.job;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.student.R;
import com.example.student.course.stucourse.stuCourseListViewItem;
import com.example.student.customclass.OkHttpClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link jobFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class jobFragment extends android.app.Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String id;

    //---------------------------平时作业----------------------------
    private ListView list1,list2;
    private stuJobAdapter adapter1,adapter2;
    private List<stuJobListViewItem> stulist1=new ArrayList<stuJobListViewItem>();
    private List<stuJobListViewItem> stulist2=new ArrayList<stuJobListViewItem>();
    private String[] pnum1={"15人已参与"};
    private String[] name1={"上交实验一报告"};
    private String[] start1={"开始时间：2021年7月3日"};
    private String[] end1={"结束时间：2021年8月3日"};
    private String[] state1={"进行中"};
    private String[] goal1={"得分/总分： 0/10.0"};
    private int[] logo1={R.mipmap.logo};
    private int[] stateimg1={R.mipmap.stuc_job_ing};


    private String[] pnum2={"本次已有15人已参与"};
    private String[] name2={"实习日志"};
    private String[] start2={"时长：2021年7月3日-2021年8月3日"};
    private String[] end2={"截止时间：每天24：00"};
    private String[] state2={"进行中"};
    private String[] goal2={"提交情况： 2/31"};
    private int[] logo2={R.mipmap.logo};
    private int[] stateimg2={R.mipmap.stuc_job_ing};
    public jobFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment jobFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static jobFragment newInstance(String param1, String param2) {
        jobFragment fragment = new jobFragment();
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
        View view=inflater.inflate(R.layout.fragment_job, null);

        list1=(ListView)view.findViewById(R.id.stu_job1);
        list2=(ListView)view.findViewById(R.id.stu_job2);

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClass tools=new OkHttpClass();
                String result=tools.addjob(id);
                Log.d("result-addjob",result);
                List<String> jobids= new ArrayList<>();
                try {
                    JSONObject jsonObjectdata=new JSONObject(result);
                    String data=jsonObjectdata.getString("data");
                    String jobid="";
                    String fullmack="";
                    String detail="";
                    String dealtime="";
                    JSONArray jsonArray=new JSONArray(data);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        //Log.d("11111",jsonObject.toString());
                        if(jsonObject!=null){
                            String name=jsonObject.optString("title");
                            String starttime=jsonObject.optString("startTime");
                            dealtime=jsonObject.optString("deadline");
                            fullmack=jsonObject.optString("fullMark");
                            detail=jsonObject.optString("detail");
                            String stunum=jsonObject.optString("handInNum");
                            String state=jsonObject.optString("status");
                            String score=jsonObject.optString("score");

                            jobid=jsonObject.optString("id");
                            jobids.add(jobid);
                            stulist1.add(new stuJobListViewItem(R.mipmap.logo,name,"结束时间： "+dealtime,"开始时间： "+starttime,"得分/总分： "+score+"/"+fullmack,state,"已有"+stunum+"人参与"));


                            Log.d("name",name);

                        }

                    }

                    String finalFullmack = fullmack;
                    String finalDetail = detail;
                    String finalDealtime = dealtime;
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter1=new stuJobAdapter(getActivity(),R.layout.stu_jobitem,stulist1);
                            list1.setAdapter(adapter1);
                            setJoblistVihg();
                            list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent=null;
                                    intent=new Intent(getActivity(), jobsubmitActivity.class);
                                    intent.putExtra("jobid",jobids.get(position));
                                    intent.putExtra("full", finalFullmack);
                                    intent.putExtra("detail", finalDetail);
                                    intent.putExtra("deadline", finalDealtime);
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
        //stulist1=getData(logo1,stateimg1,pnum1,name1,start1,state1,end1,goal1);
        stulist2=getData(logo2,stateimg2,pnum2,name2,start2,state2,end2,goal2);

        //adapter1=new stuJobAdapter(this.getActivity(),R.layout.stu_jobitem,stulist1);
        adapter2=new stuJobAdapter(this.getActivity(),R.layout.stu_jobitem,stulist2);

        //list1.setAdapter(adapter1);
        list2.setAdapter(adapter2);



        list2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=null;
                switch (position){
                    case 0:
                        intent=new Intent(getActivity(), ajobsubmintActivity.class);
                        break;
                    default:
                        break;
                }
                startActivity(intent);
            }
        });
        return view;
    }

    public List<stuJobListViewItem> getData(int[] logo,int[] stateimg,String[] pnum, String[] name,String[] start,String[] state,String[] end,String[] goal){
        List<stuJobListViewItem> list=new ArrayList<stuJobListViewItem>();

        for(int i=0;i<name.length;i++)
        {
            list.add(new stuJobListViewItem(logo[i],name[i],end[i],start[i],goal[i],state[i],pnum[i]));
        }


        return list;
    }

    public void getcourseId(String id) {
        this.id = id;
        if (this.id != null) {
            Log.d("id", id);
        }

    }
        public void setJoblistVihg(){
            ListAdapter listAdapter=list1.getAdapter();
            if (listAdapter == null) {
                return;
            }
            int totalHeight = 0;
            for (int i = 0; i < listAdapter.getCount(); i++) {
                View listItem = listAdapter.getView(i, null, list1);
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = list1.getLayoutParams();
            params.height = totalHeight + (list1.getDividerHeight() * (listAdapter.getCount() - 1));
            list1.setLayoutParams(params);
        }
    }
