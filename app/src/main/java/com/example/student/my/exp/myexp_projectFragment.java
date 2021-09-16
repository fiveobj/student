package com.example.student.my.exp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.student.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link myexp_projectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class myexp_projectFragment extends android.app.Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListView listView;
    private MyexpProjectAdapter adapter;
    private List<MyexpProjectItem> list;

    public myexp_projectFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment myexp_projectFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static myexp_projectFragment newInstance(String param1, String param2) {
        myexp_projectFragment fragment = new myexp_projectFragment();
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
        View view=inflater.inflate(R.layout.fragment_myexp_project, null);

        listView=(ListView) view.findViewById(R.id.myexp_project_listview);
        list=getData();
        adapter=new MyexpProjectAdapter(this.getActivity(),R.layout.myexp_projectitemlayout,list);
        listView.setAdapter(adapter);

        return view;
    }

    public List<MyexpProjectItem> getData(){
        List<MyexpProjectItem> list=new ArrayList<MyexpProjectItem>();

        list.add(new MyexpProjectItem("字节跳动","苏州","完成时间：2021.08.23~2021.09.01"));
        list.add(new MyexpProjectItem("字节跳动","苏州","完成时间：2021.08.23~2021.09.01"));
        list.add(new MyexpProjectItem("字节跳动","苏州","完成时间：2021.08.23~2021.09.01"));
        list.add(new MyexpProjectItem("字节跳动","苏州","完成时间：2021.08.23~2021.09.01"));
        list.add(new MyexpProjectItem("字节跳动","苏州","完成时间：2021.08.23~2021.09.01"));
        list.add(new MyexpProjectItem("字节跳动","苏州","完成时间：2021.08.23~2021.09.01"));
        list.add(new MyexpProjectItem("字节跳动","苏州","完成时间：2021.08.23~2021.09.01"));
        return list;
    }
}