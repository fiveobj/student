package com.example.student.contacts;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.student.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link peopleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class peopleFragment extends android.app.Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private TextView people,team;
    private FragmentManager manager;
    private FragmentTransaction transaction;



    public peopleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment peopleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static peopleFragment newInstance(String param1, String param2) {
        peopleFragment fragment = new peopleFragment();
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

        View view=inflater.inflate(R.layout.fragment_people, null);

        people=(TextView)view.findViewById(R.id.contacts_people_p);
        team=(TextView)view.findViewById(R.id.contacts_people_t);

        //设置默认显示页面为主页
        manager=getFragmentManager();
        transaction=manager.beginTransaction();
        transaction.add(R.id.contacts_peoplelayout,new con_peopleFragment());
        transaction.commit();
        people.setTextColor(Color.parseColor("#1E90FF"));

        //设置监听事件
        setListener();
        return view;
    }

    public void setListener(){
        OnClick onClick= new OnClick();
        people.setOnClickListener(onClick);
        team.setOnClickListener(onClick);

    }

    public class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            ResetImg();
            transaction=manager.beginTransaction();
            switch (v.getId()){
                case R.id.contacts_people_p:
                    transaction.replace(R.id.contacts_peoplelayout,new con_peopleFragment());
                    people.setTextColor(Color.parseColor("#1E90FF"));
                    break;
                case R.id.contacts_people_t:
                    transaction.replace(R.id.contacts_peoplelayout,new con_teamFragment());
                    team.setTextColor(Color.parseColor("#1E90FF"));
                    break;
                default:
                    break;
            }
            transaction.commit();
        }

    }
    public void ResetImg(){
        people.setTextColor(Color.parseColor("#565657"));
        team.setTextColor(Color.parseColor("#565657"));
    }
}