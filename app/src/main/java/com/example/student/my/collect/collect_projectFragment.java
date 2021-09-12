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
import com.example.student.my.collect.myclass.CollectProjectAdapter;
import com.example.student.my.collect.myclass.CollectProjectItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link collect_projectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class collect_projectFragment extends android.app.Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListView projectlistview;
    private CollectProjectAdapter adapter;
    private List<CollectProjectItem> projectlist;



    public collect_projectFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment projectFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static collect_projectFragment newInstance(String param1, String param2) {
        collect_projectFragment fragment = new collect_projectFragment();
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
        View view=inflater.inflate(R.layout.fragment_collect_project, container, false);

        projectlistview=(ListView)view.findViewById(R.id.con_project_lv);
        projectlist=getData();
        adapter=new CollectProjectAdapter(this.getActivity(),R.layout.collectproject_itemlayout,projectlist);
        projectlistview.setAdapter(adapter);
        projectlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //项目具体信息跳转


            }
        });
        return view;
    }

    public List<CollectProjectItem> getData(){
        List<CollectProjectItem> list=new ArrayList<CollectProjectItem>();

        list.add(new CollectProjectItem("小区改造工程","苏州","拟建","2021-07-21"));
        return list;
    }
}