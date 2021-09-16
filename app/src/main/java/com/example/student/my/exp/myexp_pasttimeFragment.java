package com.example.student.my.exp;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.student.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link myexp_pasttimeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class myexp_pasttimeFragment extends android.app.Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListView listView;
    private MyexpPasttimrAdapter adapter;
    private List<MyexpPasttimeItme> list;
    private TextView in,out;
    private Context is;

    public myexp_pasttimeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment myexp_pasttimeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static myexp_pasttimeFragment newInstance(String param1, String param2) {
        myexp_pasttimeFragment fragment = new myexp_pasttimeFragment();
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

        View view=inflater.inflate(R.layout.fragment_myexp_pasttime, null);

        listView=(ListView) view.findViewById(R.id.myexp_pasttime_listview);
        list=getDatain();
        adapter=new MyexpPasttimrAdapter(this.getActivity(),R.layout.myexp_pasttimeitemlayout,list);
        listView.setAdapter(adapter);


        in=(TextView)view.findViewById(R.id.myexp_pasttime_in);
        out=(TextView)view.findViewById(R.id.myexp_pasttime_out);

        in.setTextColor(Color.parseColor("#1E90FF"));
        is=this.getActivity();


        setListeners();

        return view;
    }

    public void setListeners(){
        OnClick onClick=new OnClick();
        in.setOnClickListener(onClick);
        out.setOnClickListener(onClick);

    }

    public class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.myexp_pasttime_in:
                    in.setTextColor(Color.parseColor("#1E90FF"));
                    out.setTextColor(Color.parseColor("#000000"));
                    list=getDatain();
                    break;
                case R.id.myexp_pasttime_out:
                    in.setTextColor(Color.parseColor("#000000"));
                    out.setTextColor(Color.parseColor("#1E90FF"));
                    list=getDataout();
                    break;
                default:
                    break;
            }

            adapter=new MyexpPasttimrAdapter(is,R.layout.myexp_pasttimeitemlayout,list);
            listView.setAdapter(adapter);
        }
    }
    public List<MyexpPasttimeItme> getDatain(){
        List<MyexpPasttimeItme> list=new ArrayList<MyexpPasttimeItme>();

        list.add(new MyexpPasttimeItme("字节跳动","3.8元","完成时间：2021.08.23~2021.09.01"));
        list.add(new MyexpPasttimeItme("字节跳动","3.8元","完成时间：2021.08.23~2021.09.01"));
        list.add(new MyexpPasttimeItme("字节跳动","3.8元","完成时间：2021.08.23~2021.09.01"));
        list.add(new MyexpPasttimeItme("字节跳动","3.8元","完成时间：2021.08.23~2021.09.01"));
        list.add(new MyexpPasttimeItme("字节跳动","3.8元","完成时间：2021.08.23~2021.09.01"));
        list.add(new MyexpPasttimeItme("字节跳动","3.8元","完成时间：2021.08.23~2021.09.01"));
        list.add(new MyexpPasttimeItme("字节跳动","3.8元","完成时间：2021.08.23~2021.09.01"));
        list.add(new MyexpPasttimeItme("字节跳动","3.8元","完成时间：2021.08.23~2021.09.01"));
        list.add(new MyexpPasttimeItme("字节跳动","3.8元","完成时间：2021.08.23~2021.09.01"));
        list.add(new MyexpPasttimeItme("字节跳动","3.8元","完成时间：2021.08.23~2021.09.01"));

        return list;
    }

    public List<MyexpPasttimeItme> getDataout(){
        List<MyexpPasttimeItme> list=new ArrayList<MyexpPasttimeItme>();

        list.add(new MyexpPasttimeItme("兼职","3.8元","完成时间：2021.08.23~2021.09.01"));
        list.add(new MyexpPasttimeItme("兼职","3.8元","完成时间：2021.08.23~2021.09.01"));
        list.add(new MyexpPasttimeItme("兼职","3.8元","完成时间：2021.08.23~2021.09.01"));
        list.add(new MyexpPasttimeItme("兼职","3.8元","完成时间：2021.08.23~2021.09.01"));
        list.add(new MyexpPasttimeItme("兼职","3.8元","完成时间：2021.08.23~2021.09.01"));
        list.add(new MyexpPasttimeItme("兼职","3.8元","完成时间：2021.08.23~2021.09.01"));
        list.add(new MyexpPasttimeItme("字节跳动","3.8元","完成时间：2021.08.23~2021.09.01"));
        list.add(new MyexpPasttimeItme("字节跳动","3.8元","完成时间：2021.08.23~2021.09.01"));
        list.add(new MyexpPasttimeItme("字节跳动","3.8元","完成时间：2021.08.23~2021.09.01"));
        list.add(new MyexpPasttimeItme("字节跳动","3.8元","完成时间：2021.08.23~2021.09.01"));

        return list;
    }
}