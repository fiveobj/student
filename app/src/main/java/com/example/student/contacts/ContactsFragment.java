package com.example.student.contacts;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.student.R;
import com.example.student.course.detailFragment;
import com.example.student.course.jobFragment;
import com.example.student.course.liveFragment;
import com.example.student.course.newsFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactsFragment extends android.app.Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    //定义控件
    private ImageButton news,people,team;
    private TextView newtv,peopletv,teamtv;
    private FragmentManager manager;
    private FragmentTransaction transaction;


    public ContactsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactsFragment newInstance(String param1, String param2) {
        ContactsFragment fragment = new ContactsFragment();
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
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_contacts,null);

        //定义
        news=(ImageButton) view.findViewById(R.id.new_new);
        people=(ImageButton)view.findViewById(R.id.new_people);
        team=(ImageButton)view.findViewById(R.id.new_team);

        newtv=(TextView)view.findViewById(R.id.new_newtv);
        peopletv=(TextView)view.findViewById(R.id.new_peopletv);
        teamtv=(TextView)view.findViewById(R.id.new_teamtv);

        //设置默认按钮选中颜色
        news.setBackgroundResource(R.mipmap.new_new1);
        newtv.setTextColor(Color.parseColor("#1E90FF"));

        //设置监听事件
        setListeners();

        //设置显示默认界面
        manager=getFragmentManager();
        transaction=manager.beginTransaction();
        transaction.add(R.id.new_layout,new newFragment());
        transaction.commit();




        return view;
    }


    private void setListeners()
    {
        OnClick onClick=new OnClick();
        news.setOnClickListener(onClick);
        people.setOnClickListener(onClick);
        team.setOnClickListener(onClick);
    }


    private class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            ResetImg();//改变顶部Tab为默认黑色
            transaction=manager.beginTransaction();
            switch (v.getId()){
                case R.id.new_new:
                    transaction.replace(R.id.new_layout,new newFragment());
                    news.setBackgroundResource(R.mipmap.new_new1);
                    newtv.setTextColor(Color.parseColor("#1E90FF"));
                    break;
                case R.id.new_people:
                    transaction.replace(R.id.new_layout,new peopleFragment());
                    people.setBackgroundResource(R.mipmap.new_people1);
                    peopletv.setTextColor(Color.parseColor("#1E90FF"));
                    break;
                case R.id.new_team:
                    transaction.replace(R.id.new_layout,new teamFragment());
                    team.setBackgroundResource(R.mipmap.new_team1);
                    teamtv.setTextColor(Color.parseColor("#1E90FF"));
                    break;
                default:
                    break;
            }
            transaction.commit();
        }
    }
    private void ResetImg(){
        news.setBackgroundResource(R.mipmap.new_new);
        people.setBackgroundResource(R.mipmap.new_people);
        team.setBackgroundResource(R.mipmap.new_team);

        newtv.setTextColor(Color.parseColor("#565657"));
        peopletv.setTextColor(Color.parseColor("#565657"));
        teamtv.setTextColor(Color.parseColor("#565657"));
    }

}