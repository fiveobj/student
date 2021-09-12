package com.example.student.my.collect;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.student.R;
import com.example.student.my.collect.myclass.CollectFairAdapter;
import com.example.student.my.collect.myclass.CollectFairItem;
import com.example.student.my.collect.myclass.CollectJobAdapter;
import com.example.student.my.collect.myclass.CollectJobItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link collect_jobFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class collect_jobFragment extends android.app.Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListView joblistview;
    private CollectJobAdapter adapter;
    private List<CollectJobItem> joblist;

    public collect_jobFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment collect_jobFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static collect_jobFragment newInstance(String param1, String param2) {
        collect_jobFragment fragment = new collect_jobFragment();
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
        View view=inflater.inflate(R.layout.fragment_collect_job, null);

        joblistview=(ListView)view.findViewById(R.id.con_job_lv);
        joblist=getData();
        adapter=new CollectJobAdapter(this.getActivity(),R.layout.collectjob_itemlayout,joblist);
        joblistview.setAdapter(adapter);
        /*joblistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //岗位信息跳转
            }
        });*/
        return view;
    }

    public List<CollectJobItem> getData(){
        List<CollectJobItem> list=new ArrayList<CollectJobItem>();

        list.add(new CollectJobItem("250-300/天","视觉算法工程实习生","大平台","导师制度","杭州","5天/周/3个月","互联网/游戏/软件","微软","2000人以上",R.mipmap.my_collect_weiruan));
        return list;
    }
}