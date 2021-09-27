package com.example.student.contacts;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.student.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link newFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class newFragment extends android.app.Fragment implements RecyclerViewAdapter.onSlidingViewClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    private RecyclerView recycler;              //在xml 中 RecyclerView 布局
    private RecyclerViewAdapter rvAdapter;      //item_recycler 布局的 适配器

    //设置数据
    private List<Bitmap> dataImage;    //头像（谁的头像）
    private List<String> dataTitle;     //标题（谁的消息）
    private List<String> datasContent;  //内容（消息内容）
    private List<String> datasTime;     //时间（消息时间）

    /*private ListView listView;

    private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    int[] imageId = new int[]{R.mipmap.toux11, R.mipmap.toux1, R.mipmap.toux5, R.mipmap.toux10, R.mipmap.toux6, R.mipmap.toux3, R.mipmap.toux4};
    String[] title = new String[]{"刘一", "陈二", "张三", "李四", "王五", "赵六", "孙七"};
    String[] detail=new String[]{"刘一：下次再说","陈二：下次一定","张三：明天见","[链接]邀请你加入群聊","我说好了，没事","还可以吧","那就这样"};*/

    public newFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment newFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static newFragment newInstance(String param1, String param2) {
        newFragment fragment = new newFragment();
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

        View view = inflater.inflate(R.layout.fragment_new, null);

        recycler = (RecyclerView)view.findViewById(R.id.mListView);
        //将 RecyclerView 布局设置为线性布局
        recycler.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        datas();//插入数据
        //更新界面
        updateInterface();

        /*listView = (ListView) view.findViewById(R.id.mListView);
        list = getData();
        SimpleAdapter adapter = new SimpleAdapter(this.getActivity(), list,
                R.layout.new_peopleitemlayout, new String[]{"名字", "image","内容"}, new int[]{
                R.id.tv_name, R.id.image,R.id.tv_detail});
        listView.setAdapter(adapter);*/


        return view;
    }

    public void updateInterface(){
        if (rvAdapter == null) {
            //实例化 RecyclerViewAdapter 并设置数据
            rvAdapter = new RecyclerViewAdapter(this.getActivity(),
                    dataImage, dataTitle, datasContent, datasTime);
            //将适配的内容放入 mRecyclerView
            recycler.setAdapter(rvAdapter);
            //控制Item增删的动画，需要通过ItemAnimator  DefaultItemAnimator -- 实现自定义动画
            recycler.setItemAnimator(new DefaultItemAnimator());
        }else {
            //强调通过 getView() 刷新每个Item的内容
            rvAdapter.notifyDataSetChanged();
        }
        //设置滑动监听器 （侧滑）
        rvAdapter.setOnSlidListener(this);
    }


    /*public List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < imageId.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", imageId[i]);
            map.put("名字", title[i]);
            map.put("内容",detail[i]);
            list.add(map);
        }
        return list;


    }*/

    @Override
    public void onItemClick(View view, int position) {
        Intent intent=new Intent(this.getActivity(),ChatActivity.class);
        startActivity(intent);
        Toast.makeText(this.getActivity(),"点击了：" + position,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteBtnCilck(View view, int position) {
        rvAdapter.removeData(position);
    }

    public void datas(){
        dataImage = new ArrayList<Bitmap>();    //头像（谁的头像）
        dataTitle = new ArrayList<String>();     //标题（谁的消息）
        datasContent = new ArrayList<String>();  //内容（消息内容）
        datasTime = new ArrayList<String>();     //时间（消息时间）
        dataImage.add(RecyclerUtils.toRoundBitmap(RecyclerUtils.bitmaps(R.mipmap.toux1, this.getActivity())));
        dataImage.add(RecyclerUtils.toRoundBitmap(RecyclerUtils.bitmaps(R.mipmap.toux2, this.getActivity())));
        dataImage.add(RecyclerUtils.toRoundBitmap(RecyclerUtils.bitmaps(R.mipmap.toux3, this.getActivity())));
        dataImage.add(RecyclerUtils.toRoundBitmap(RecyclerUtils.bitmaps(R.mipmap.toux4, this.getActivity())));
        dataImage.add(RecyclerUtils.toRoundBitmap(RecyclerUtils.bitmaps(R.mipmap.toux5, this.getActivity())));
        dataImage.add(RecyclerUtils.toRoundBitmap(RecyclerUtils.bitmaps(R.mipmap.toux6, this.getActivity())));
        dataImage.add(RecyclerUtils.toRoundBitmap(RecyclerUtils.bitmaps(R.mipmap.toux7, this.getActivity())));
        dataImage.add(RecyclerUtils.toRoundBitmap(RecyclerUtils.bitmaps(R.mipmap.toux8, this.getActivity())));
        dataImage.add(RecyclerUtils.toRoundBitmap(RecyclerUtils.bitmaps(R.mipmap.toux9, this.getActivity())));
        dataImage.add(RecyclerUtils.toRoundBitmap(RecyclerUtils.bitmaps(R.mipmap.toux10, this.getActivity())));
        dataImage.add(RecyclerUtils.toRoundBitmap(RecyclerUtils.bitmaps(R.mipmap.toux11, this.getActivity())));

        dataTitle.add("Android开发交流群");
        dataTitle.add("R语言初级入门学习");
        dataTitle.add("刘亦菲");
        dataTitle.add("策划书交流群");
        dataTitle.add("15生态宜居学院学生群");
        dataTitle.add("湘环资助 （助学贷款）");
        dataTitle.add("湘环编程研讨会");
        dataTitle.add("丰风");
        dataTitle.add("阿娇");
        dataTitle.add("图书馆流通服务交流群");
        dataTitle.add("one3胡了");

        datasContent.add("广州_Even：[图片]");
        datasContent.add("轻舟飘飘：auto基本不准");
        datasContent.add("不会的");
        datasContent.add("残留的余温。：分享[熊猫直播]");
        datasContent.add("刘老师：[文件]2018年6月全国大学……");
        datasContent.add("17级园林");
        datasContent.add("黄晓明：20k不到");
        datasContent.add("[文件]编程之美");
        datasContent.add("i5的处理器，比较稳定，蛮好的");
        datasContent.add("寥寥：好的，谢谢老师");
        datasContent.add("易天：阿龙还在面试呢");

        datasTime.add("16:24");
        datasTime.add("14:37");
        datasTime.add("10:58");
        datasTime.add("昨天");
        datasTime.add("昨天");
        datasTime.add("昨天");
        datasTime.add("星期三");
        datasTime.add("星期三");
        datasTime.add("星期二");
        datasTime.add("星期二");
        datasTime.add("星期二");


    }
}