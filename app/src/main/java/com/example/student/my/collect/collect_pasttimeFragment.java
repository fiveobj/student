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
import com.example.student.my.collect.myclass.CollectPasttimeAdapter;
import com.example.student.my.collect.myclass.CollectPasttimeItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link collect_pasttimeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class collect_pasttimeFragment extends android.app.Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListView pastlistview;
    private CollectPasttimeAdapter adapter;
    private List<CollectPasttimeItem> pastlist;



    public collect_pasttimeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment collect_pasttimeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static collect_pasttimeFragment newInstance(String param1, String param2) {
        collect_pasttimeFragment fragment = new collect_pasttimeFragment();
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
        View view=inflater.inflate(R.layout.fragment_collect_pasttime, null);

        pastlistview=(ListView)view.findViewById(R.id.con_pasttime_lv);
        pastlist=getData();
        adapter=new CollectPasttimeAdapter(this.getActivity(),R.layout.collectpasttime_itemlayout,pastlist);
        pastlistview.setAdapter(adapter);
        pastlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //兼职具体信息跳转


            }
        });
        return view;
    }

    public List<CollectPasttimeItem> getData(){
        List<CollectPasttimeItem> list=new ArrayList<CollectPasttimeItem>();

        list.add(new CollectPasttimeItem("印象影视注册","3.5元/次","8月3日截止"));
        return list;
    }
}