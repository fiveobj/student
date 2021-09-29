package com.example.student.contacts;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.student.MainActivity;
import com.example.student.R;
import com.example.student.my.mypost.DemoDataProvider;
import com.example.student.my.mypost.PostInfo;
import com.xuexiang.xui.widget.picker.widget.OptionsPickerView;
import com.xuexiang.xui.widget.picker.widget.builder.OptionsPickerBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.agora.rtc.Constants;
import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;

import static io.agora.log.UploadManager.Params.LOG;

public class people_introActivity extends AppCompatActivity {


    private ImageButton back,select;
    private List<PostInfo> voiceselect=new ArrayList<>();
    private boolean mHasLoaded;
    private static final int PERMISSION_REQ_ID_RECORD_AUDIO = 22;
    private RtcEngine mRtcEngine;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_intro);

        //设置顶部状态栏为透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        back=(ImageButton)findViewById(R.id.people_intro_back);
        select=(ImageButton)findViewById(R.id.peo_intro_voiceselect);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                people_introActivity.this.finish();
            }

        });

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showVoiceSelect(false);
            }
        });

        loadDataVoice(DemoDataProvider.getVoiceInfos());
    }
    private void loadDataVoice(List<PostInfo> postInfos){
        voiceselect=postInfos;

        mHasLoaded=true;

    }
    private void showVoiceSelect(Boolean isDialog){
        if(!mHasLoaded){
            Toast.makeText(this,"数据加载中",Toast.LENGTH_SHORT).show();
            return;
        }

        int[] defaultSelectOptions = getDefaultCompany();
        OptionsPickerBuilder optionsPickerBuilder = new OptionsPickerBuilder(this, (v, options1, options2, option3) -> {
            //返回的分别是三个级别的选中位置


            String tx = voiceselect.get(options1).getPickerViewText();

            if(options1==0){
                Toast.makeText(this, tx, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(this,VoiceChatViewActivity.class);
                startActivity(intent);


            }else if(options1==1){
                Toast.makeText(this, tx, Toast.LENGTH_SHORT).show();
            }

            //Toast.makeText(this, tx, Toast.LENGTH_SHORT).show();
            return false;
        });
        optionsPickerBuilder.setTitleText("你可以选择以下通话操作");
        optionsPickerBuilder.setDividerColor(Color.BLACK);
        optionsPickerBuilder.isRestoreItem(true);
        optionsPickerBuilder.setTextColorCenter(Color.BLACK);
        optionsPickerBuilder.setContentTextSize(20);
        optionsPickerBuilder.isDialog(isDialog);
        optionsPickerBuilder.setSelectOptions(defaultSelectOptions[0]);//返回的分别是三个级别的选中位置
        OptionsPickerView pvoptions= optionsPickerBuilder.build();

        pvoptions.setPicker(voiceselect);
        pvoptions.show();
    }






    /**
     * @return 获取默认通话的索引
     */

    private int[] getDefaultCompany() {
        int[] res = new int[3];
        PostInfo postInfo;
        List<String> ares;

        for (int i = 0; i < voiceselect.size(); i++) {
            postInfo = voiceselect.get(i);
            if ("语音通话".equals(postInfo.getName())) {
                res[0] = i;

                break;
            }
        }
        return res;
    }


}