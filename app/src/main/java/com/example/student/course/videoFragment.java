package com.example.student.course;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.student.R;



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
    private ImageButton startimb;
    MediaController mMediaController;

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
        startimb=(ImageButton)view.findViewById(R.id.stucourse_recourse_startImB);
        startimb.setOnClickListener(new OnClick());
        return view;
    }
    public class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            String url="android.resource://"+"com.example.student"+"/"+R.raw.ces;
            mvideoView.setVideoURI(Uri.parse(url));
            mMediaController.setMediaPlayer(mvideoView);
            mvideoView.setMediaController(mMediaController);
            if(v==startimb){
                mvideoView.start();
            }

        }
    }
}