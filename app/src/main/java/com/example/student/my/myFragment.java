package com.example.student.my;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.student.R;
import com.example.student.customclass.OkHttpClass;
import com.example.student.my.account.myaccountActivity;
import com.example.student.my.collect.mycollectActivity;
import com.example.student.my.course.mycourseActivity;
import com.example.student.my.exp.myexpActivity;
import com.example.student.my.id.myidActivity;
import com.example.student.my.myatt.myattActivity;
import com.example.student.my.mypost.mypostActivity;
import com.example.student.my.mysand.mysandActivity;
import com.example.student.my.resume.myresumeActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link myFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class myFragment extends android.app.Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListView mylistView1,mylistView2,mylistView3;
    private List<Map<String,Object>> list1=new ArrayList<Map<String,Object>>();
    private List<Map<String,Object>> list2=new ArrayList<Map<String,Object>>();
    private List<Map<String,Object>> list3=new ArrayList<Map<String,Object>>();
    int[] imageId1=new int[]{R.mipmap.my_id,R.mipmap.my_account};
    String[] title1=new String[]{"我的账号","我的账户"};
    int[] imageId2=new int[]{R.mipmap.my_course,R.mipmap.my_exp,R.mipmap.my_sand,R.mipmap.my_post};
    String[] title2=new String[]{"我的课程","我的经历","投递记录","理想职位"};
    int[] imageId3=new int[]{R.mipmap.my_set,R.mipmap.my_service};
    String[] title3=new String[]{"设置","智能客服"};
    private TextView collect,att,resume,isAuth;
    public myFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment myFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static myFragment newInstance(String param1, String param2) {
        myFragment fragment = new myFragment();
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
        View view=inflater.inflate(R.layout.fragment_my, null);

        collect=(TextView)view.findViewById(R.id.my_collect);
        att=(TextView)view.findViewById(R.id.my_att);
        resume=(TextView)view.findViewById(R.id.my_resume);
        isAuth=(TextView)view.findViewById(R.id.my_ifschool);

        mylistView1=(ListView)view.findViewById(R.id.my_listview1);
        mylistView2=(ListView)view.findViewById(R.id.my_listview2);
        mylistView3=(ListView)view.findViewById(R.id.my_listview3);
        list1=getData1();
        list2=getData2();
        list3=getData3();

        SimpleAdapter adapter1=new SimpleAdapter(this.getActivity(),list1,
                R.layout.my_listviewitem,new String[]{"tab","image"},new int[]{R.id.tv,R.id.image
        });
        mylistView1.setAdapter(adapter1);

        SimpleAdapter adapter2=new SimpleAdapter(this.getActivity(),list2,
                R.layout.my_listviewitem,new String[]{"tab","image"},new int[]{R.id.tv,R.id.image
        });
        mylistView2.setAdapter(adapter2);

        SimpleAdapter adapter3=new SimpleAdapter(this.getActivity(),list3,
                R.layout.my_listviewitem,new String[]{"tab","image"},new int[]{R.id.tv,R.id.image
        });
        mylistView3.setAdapter(adapter3);


        //textview点击事件
        setListener();
        //mylistview1点击事件
        mylistView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent l1=null;
                switch (position){
                    case 0:
                        l1=new Intent(getActivity(), myidActivity.class);
                        break;
                    case 1:
                        l1=new Intent(getActivity(), myaccountActivity.class);
                        break;
                    default:
                        break;
                }
                startActivity(l1);
            }
        });
        setlistvhigh1();


        //mylistview2点击事件

        mylistView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent l2=null;
                switch (position){
                    case 0:
                        l2=new Intent(getActivity(), mycourseActivity.class);
                        break;
                    case 1:
                        l2=new Intent(getActivity(), myexpActivity.class);
                        break;
                    case 2:
                        l2=new Intent(getActivity(), mysandActivity.class);
                        break;
                    case 3:
                        l2=new Intent(getActivity(), mypostActivity.class);
                        break;
                    default:
                        break;
                }
                startActivity(l2);
            }
        });

        setlistvhigh2();
        //mylistview3点击事件
        mylistView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent l3=null;
                switch (position){
                    case 0:
                        l3=new Intent(getActivity(),mysetActivity.class);
                        break;
                    case 1:
                        l3=new Intent(getActivity(),myserviceActivity.class);
                        break;
                    default:
                        break;
                }
                startActivity(l3);
            }
        });

        setlistvhigh3();
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClass tools=new OkHttpClass();
                String result=tools.isAuth();
                Log.d("isAuth",result);
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    String data=jsonObject.getString("data");
                    JSONObject jsonObject1=new JSONObject(data);
                    String is=jsonObject1.getString("isAuth");
                    if(is.equals("1")){
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                isAuth.setText("学籍已认证");
                                isAuth.setTextColor(Color.parseColor("#575757"));
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return view;
    }
    public List<Map<String,Object>> getData1(){
        List<Map<String ,Object>> list=new ArrayList<Map<String, Object>>();
        for (int i=0;i<imageId1.length;i++){
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("image",imageId1[i]);
            map.put("tab",title1[i]);
            list.add(map);
        }
        return list;
    }

    public List<Map<String,Object>> getData2(){
        List<Map<String ,Object>> list=new ArrayList<Map<String, Object>>();
        for (int i=0;i<imageId2.length;i++){
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("image",imageId2[i]);
            map.put("tab",title2[i]);
            list.add(map);
        }
        return list;
    }


    public List<Map<String,Object>> getData3(){
        List<Map<String ,Object>> list=new ArrayList<Map<String, Object>>();
        for (int i=0;i<imageId3.length;i++){
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("image",imageId3[i]);
            map.put("tab",title3[i]);
            list.add(map);
        }
        return list;
    }

    private void setListener(){
        OnClick onClick=new OnClick();
        collect.setOnClickListener(onClick);
        att.setOnClickListener(onClick);
        resume.setOnClickListener(onClick);
    }
    private class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent=null;
            switch (v.getId()){
                case R.id.my_collect:
                    intent=new Intent(getActivity(), mycollectActivity.class);
                    break;
                case R.id.my_att:
                    intent=new Intent(getActivity(), myattActivity.class);
                    break;
                case R.id.my_resume:
                    intent=new Intent(getActivity(), myresumeActivity.class);
                    break;
                default:
                    break;
            }
            startActivity(intent);
        }
    }

    public void setlistvhigh1(){
        ListAdapter listAdapter=mylistView1.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, mylistView1);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = mylistView1.getLayoutParams();
        params.height = totalHeight + (mylistView1.getDividerHeight() * (listAdapter.getCount() - 1));
        mylistView1.setLayoutParams(params);
    }

    public void setlistvhigh2(){
        ListAdapter listAdapter=mylistView2.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, mylistView2);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = mylistView2.getLayoutParams();
        params.height = totalHeight + (mylistView2.getDividerHeight() * (listAdapter.getCount() - 1));
        mylistView2.setLayoutParams(params);
    }


    public void setlistvhigh3(){
        ListAdapter listAdapter=mylistView3.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, mylistView3);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = mylistView3.getLayoutParams();
        params.height = totalHeight + (mylistView3.getDividerHeight() * (listAdapter.getCount() - 1));
        mylistView3.setLayoutParams(params);
    }
}