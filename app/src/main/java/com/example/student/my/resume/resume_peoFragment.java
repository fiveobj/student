package com.example.student.my.resume;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.student.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link resume_peoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class resume_peoFragment extends android.app.Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListView listView;
    private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    String[] title = new String[]{"a", "aa", "ab", "abc"};


    public resume_peoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment resume_peoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static resume_peoFragment newInstance(String param1, String param2) {
        resume_peoFragment fragment = new resume_peoFragment();
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

        View view=inflater.inflate(R.layout.fragment_resume_peo, null);

        listView=(ListView)view.findViewById(R.id.resume_peo_listview);
        list=getData();
        resumeItemAdapter adapter=new resumeItemAdapter(this.getActivity(),list,R.layout.myresume_itemlayout,new String[]{"??????"},new int[]{R.id.myresume_name});
        listView.setAdapter(adapter);

        adapter.setmOnClickListener(new resumeItemAdapter.onClickListener() {
            @Override
            public void onClick(View view, int position) {
                ImageButton btn=view.findViewById(R.id.myresume_btn);

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getActivity(),people_resumeActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });


        return view;
    }

    public List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < title.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("??????", title[i]);
            list.add(map);
        }
        return list;
    }


}