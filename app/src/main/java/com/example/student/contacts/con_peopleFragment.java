package com.example.student.contacts;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.student.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link con_peopleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class con_peopleFragment extends android.app.Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private ListView listView1;

    private List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
    int[] imageId1 = new int[]{R.mipmap.logo, R.mipmap.logo, R.mipmap.logo, R.mipmap.logo};
    String[] title1 = new String[]{"a", "aa", "ab", "abc"};
    private ListView listView2;

    private List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
    int[] imageId2 = new int[]{R.mipmap.logo, R.mipmap.logo, R.mipmap.logo};
    String[] title2 = new String[]{"ba", "bc", "bz"};
    private ListView listView3;

    private List<Map<String, Object>> list3 = new ArrayList<Map<String, Object>>();
    int[] imageId3 = new int[]{R.mipmap.logo, R.mipmap.logo};
    String[] title3 = new String[]{"ca", "cc"};


    public con_peopleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment con_peopleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static con_peopleFragment newInstance(String param1, String param2) {
        con_peopleFragment fragment = new con_peopleFragment();
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
        View view=inflater.inflate(R.layout.fragment_con_people, null);

        listView1=(ListView) view.findViewById(R.id.peo_a);
        listView2=(ListView) view.findViewById(R.id.peo_b);
        listView3=(ListView) view.findViewById(R.id.peo_c);

        list1=getData1();
        list2=getData2();
        list3=getData3();

        SimpleAdapter adapter1 = new SimpleAdapter(this.getActivity(), list1,
                R.layout.new_people_playout, new String[]{"名字", "image"}, new int[]{
                R.id.tv, R.id.image});
        listView1.setAdapter(adapter1);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent l1=null;
                switch (position){
                    case 0:
                        l1=new Intent(getActivity(),people_introActivity.class);
                        break;
                }
                startActivity(l1);
            }
        });

        SimpleAdapter adapter2 = new SimpleAdapter(this.getActivity(), list2,
                R.layout.new_people_playout, new String[]{"名字", "image"}, new int[]{
                R.id.tv, R.id.image});
        listView2.setAdapter(adapter2);

        SimpleAdapter adapter3 = new SimpleAdapter(this.getActivity(), list3,
                R.layout.new_people_playout, new String[]{"名字", "image"}, new int[]{
                R.id.tv, R.id.image});
        listView3.setAdapter(adapter3);

        setlistviewhigh1();
        setlistviewhigh2();
        setlistviewhigh3();



        return view;
    }
    public List<Map<String, Object>> getData1() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < imageId1.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", imageId1[i]);
            map.put("名字", title1[i]);
            list.add(map);
        }
        return list;


    }
    public List<Map<String, Object>> getData2() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < imageId2.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", imageId2[i]);
            map.put("名字", title2[i]);
            list.add(map);
        }
        return list;


    }
    public List<Map<String, Object>> getData3() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < imageId3.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", imageId3[i]);
            map.put("名字", title3[i]);
            list.add(map);
        }
        return list;


    }
    public void setlistviewhigh1(){
        ListAdapter listAdapter=listView1.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView1);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView1.getLayoutParams();
        params.height = totalHeight + (listView1.getDividerHeight() * (listAdapter.getCount() - 1));
        listView1.setLayoutParams(params);
    }
    public void setlistviewhigh2(){
        ListAdapter listAdapter=listView2.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView2);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView2.getLayoutParams();
        params.height = totalHeight + (listView2.getDividerHeight() * (listAdapter.getCount() - 1));
        listView2.setLayoutParams(params);
    }
    public void setlistviewhigh3(){
        ListAdapter listAdapter=listView3.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView3);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView3.getLayoutParams();
        params.height = totalHeight + (listView3.getDividerHeight() * (listAdapter.getCount() - 1));
        listView3.setLayoutParams(params);
    }
}