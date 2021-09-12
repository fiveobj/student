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

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link collect_fairFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class collect_fairFragment extends android.app.Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListView fairlistview;
    private CollectFairAdapter adapter;
    private List<CollectFairItem> fairlist;



    public collect_fairFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment collect_fairFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static collect_fairFragment newInstance(String param1, String param2) {
        collect_fairFragment fragment = new collect_fairFragment();
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
        View view=inflater.inflate(R.layout.fragment_collect_fair, null);

        fairlistview=(ListView)view.findViewById(R.id.collect_fair_lv);
        fairlist=getData();
        adapter=new CollectFairAdapter(this.getActivity(),R.layout.collectfair_itemlayout,fairlist);
        fairlistview.setAdapter(adapter);
        fairlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //招聘会具体信息跳转

            }
        });

        return view;
    }


    public List<CollectFairItem> getData(){
        List<CollectFairItem> list=new ArrayList<CollectFairItem>();

        list.add(new CollectFairItem("浙江广厦大学智能制造学院","时间：2021.06.07",R.mipmap.fair_img));
        return list;
    }
}