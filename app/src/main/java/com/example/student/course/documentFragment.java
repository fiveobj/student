package com.example.student.course;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.student.R;
import com.example.student.customclass.ExpandableListviewAdapter;
import com.example.student.signinActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link documentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class documentFragment extends android.app.Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    //--------------------------------------------------------------------------
    private ExpandableListView expand;
    public String[] groupStrings = {"课程须知", "阅读书籍电子版", "实验报告", "课程设计"};
    public String[][] childStrings = {
            {"课程介绍", "课程要求", "考核时间", "考试方式"},
            {"Java编程思想第四版", "Java并发编程实践", "Java加密与解密的艺术", "深入理解Java虚拟机"},
            {"实验一", "实验二", "实验三", "实验四", "实验五"},
            {"课程设计要求", "课程设计模板"}
    };

    public int[][] childimg = {
            {R.mipmap.stuc_rec_childppt, R.mipmap.stuc_rec_childword, R.mipmap.stuc_rec_childword, R.mipmap.stuc_rec_childword},
            {R.mipmap.stuc_rec_childppt, R.mipmap.stuc_rec_childppt, R.mipmap.stuc_rec_childppt, R.mipmap.stuc_rec_childppt},
            {R.mipmap.stuc_rec_childword, R.mipmap.stuc_rec_childword, R.mipmap.stuc_rec_childword, R.mipmap.stuc_rec_childword, R.mipmap.stuc_rec_childword},
            {R.mipmap.stuc_rec_childword,R.mipmap.stuc_rec_childword}
    };

    public documentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment documentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static documentFragment newInstance(String param1, String param2) {
        documentFragment fragment = new documentFragment();
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
        View view=inflater.inflate(R.layout.fragment_document,null);

        expand=(ExpandableListView)view.findViewById(R.id.stuc_rec_expandlist);
        ExpandableListviewAdapter adapter=new ExpandableListviewAdapter(getActivity(),groupStrings,childStrings,childimg);
        expand.setAdapter(adapter);
        //默认展开第一个数组
        expand.expandGroup(0);
        //关闭数组某个数组，可以通过该属性来实现全部展开和只展开一个列表功能
        //expand_list_id.collapseGroup(0);
        expand.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Toast.makeText(getActivity(),groupStrings[groupPosition],Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        expand.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(getActivity(),childStrings[groupPosition][childPosition],Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        //组项折叠时的通知
        expand.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getActivity(),"折叠了--"+groupStrings[groupPosition],Toast.LENGTH_SHORT).show();
            }
        });
        expand.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getActivity(),"展开了--"+groupStrings[groupPosition],Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }


}