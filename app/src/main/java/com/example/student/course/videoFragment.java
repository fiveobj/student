package com.example.student.course;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.student.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link videoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class videoFragment extends android.app.Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //----------------------------------------------------------------------------------------------
    private VideoView mvideoView;
    private TextView starttv;
    private ImageButton startimb;
    MediaController mMediaController;
    private ListView listView;
    private List<Map<String,Object>> list=new ArrayList<Map<String, Object>>();
    public videoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment videoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static videoFragment newInstance(String param1, String param2) {
        videoFragment fragment = new videoFragment();
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
        View view=inflater.inflate(R.layout.fragment_video, null);

        mvideoView=new VideoView(getActivity());
        mvideoView=(VideoView)view.findViewById(R.id.stucourse_recourse_videoview);
        mMediaController=new MediaController(getActivity());
        listView=(ListView)view.findViewById(R.id.voide_listview);
        list=getData();
        SimpleAdapter adapter=new SimpleAdapter(this.getActivity(),list,R.layout.video_item,new String[]{"名字"},new int[]{R.id.video_tv});
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        String url="android.resource://"+"com.example.student"+"/"+R.raw.ces;
                        mvideoView.setVideoURI(Uri.parse(url));
                        mMediaController.setMediaPlayer(mvideoView);
                        mvideoView.setMediaController(mMediaController);
                        mvideoView.start();
                }
            }
        });
        return view;
    }
    /*public class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {


            startimb.setImageResource(R.mipmap.stuc_rec_play);

            if(v==starttv){

            }

        }
    }*/

    public List<Map<String,Object>> getData(){
        List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
        Map<String,Object> map=new HashMap<String,Object>();
        for (int i=0;i<=4;i++){
            map.put("名字","第一章");
            list.add(map);
        }



        return list;
    }
}