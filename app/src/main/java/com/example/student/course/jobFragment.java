package com.example.student.course;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.student.R;
import com.example.student.customclass.stuJobAdapter;
import com.example.student.customclass.stuJobListViewItem;

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
        stulist1=getData(logo1,stateimg1,pnum1,name1,start1,state1,end1,goal1);
        stulist2=getData(logo2,stateimg2,pnum2,name2,start2,state2,end2,goal2);

        adapter1=new stuJobAdapter(this.getActivity(),R.layout.stu_jobitem,stulist1);
        adapter2=new stuJobAdapter(this.getActivity(),R.layout.stu_jobitem,stulist2);

        list1.setAdapter(adapter1);
        list2.setAdapter(adapter2);
        return view;
    }

    public List<stuJobListViewItem> getData(int[] logo,int[] stateimg,String[] pnum, String[] name,String[] start,String[] state,String[] end,String[] goal){
        List<stuJobListViewItem> list=new ArrayList<stuJobListViewItem>();

        for(int i=0;i<name.length;i++)
        {
            list.add(new stuJobListViewItem(logo[i],stateimg[i],name[i],end[i],start[i],goal[i],state[i],pnum[i]));
        }


        return list;
    }
}