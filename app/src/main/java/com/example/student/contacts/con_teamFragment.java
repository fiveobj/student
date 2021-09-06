package com.example.student.contacts;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.student.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link con_teamFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class con_teamFragment extends android.app.Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private ListView listView;

    private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    int[] imageId = new int[]{R.mipmap.logo, R.mipmap.logo};
    String[] title = new String[]{"省赛7对", "群聊2"};


    public con_teamFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment con_teamFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static con_teamFragment newInstance(String param1, String param2) {
        con_teamFragment fragment = new con_teamFragment();
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
        View view=inflater.inflate(R.layout.fragment_con_team, null);

        listView = (ListView) view.findViewById(R.id.con_team_lv);
        list = getData();
        SimpleAdapter adapter = new SimpleAdapter(this.getActivity(), list,
                R.layout.new_peopleitemlayout, new String[]{"名字", "image"}, new int[]{
                R.id.tv, R.id.image});
        listView.setAdapter(adapter);

        return view;
    }
    public List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < imageId.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", imageId[i]);
            map.put("名字", title[i]);
            list.add(map);
        }
        return list;


    }
}