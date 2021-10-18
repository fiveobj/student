package com.example.student.my.id;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.student.R;
import com.example.student.contacts.RecyclerViewAdapter;
import com.example.student.customclass.OkHttpClass;
import com.example.student.my.mypost.DemoDataProvider;
import com.example.student.my.mypost.PostInfo;
import com.xuexiang.xui.widget.picker.widget.OptionsPickerView;
import com.xuexiang.xui.widget.picker.widget.builder.OptionsPickerBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CertActivity extends AppCompatActivity {

    private RelativeLayout school,dapartment,major,classes,sid;
    private ImageButton back,certbtn;
    private TextView schooltv,dapartmenttv,majortv,classestv;
    private EditText sidtv;
    private String schoolS,dapartmentS,majorS,classesS,sidS,certbtnS;

    private List<PostInfo> SchoolItem=new ArrayList<>();
    private List<PostInfo> DapartmentItem=new ArrayList<>();
    private List<PostInfo> MajorItem=new ArrayList<>();
    private List<PostInfo> ClassesItem=new ArrayList<>();
    private boolean mHasLoaded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cert);

        //设置顶部状态栏为透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        initView();
        setOnClick();
        loadDataSchool(DemoDataProvider.getCompanyInfos());
        loadDataDapar(DemoDataProvider.getCompanyInfos());
        loadDataMajor(DemoDataProvider.getCompanyInfos());
        loadDataClass(DemoDataProvider.getCompanyInfos());
    }

    private void initView(){
        school=(RelativeLayout)findViewById(R.id.cert_school);
        dapartment=(RelativeLayout)findViewById(R.id.cert_dapartment);
        major=(RelativeLayout)findViewById(R.id.cert_major);
        classes=(RelativeLayout)findViewById(R.id.cert_class);
        sid=(RelativeLayout)findViewById(R.id.cert_sid);

        certbtn=(ImageButton)findViewById(R.id.cert_btn);
        back=(ImageButton) findViewById(R.id.cert_back);

        schooltv=(TextView)findViewById(R.id.cert_school_tv);
        dapartmenttv=(TextView)findViewById(R.id.cert_dapartment_tv);
        majortv=(TextView)findViewById(R.id.cert_major_tv);
        classestv=(TextView)findViewById(R.id.cert_class_tv);
        sidtv=(EditText)findViewById(R.id.cert_sid_tv);

    }

    private void setOnClick(){

        OnClick onClick=new OnClick();
        school.setOnClickListener(onClick);
        dapartment.setOnClickListener(onClick);
        major.setOnClickListener(onClick);
        classes.setOnClickListener(onClick);
        sid.setOnClickListener(onClick);
        certbtn.setOnClickListener(onClick);
        back.setOnClickListener(onClick);

    }

    private class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.cert_school:
                    showSchoolView(false);
                    break;
                case R.id.cert_dapartment:
                    showDaparView(false);
                    break;
                case R.id.cert_major:
                    showMajorView(false);
                    break;
                case R.id.cert_class:
                    showClassView(false);
                    break;
                case R.id.cert_sid:
                    break;
                case R.id.cert_back:
                    CertActivity.this.finish();
                    break;
                case R.id.cert_btn:
                    sidS=sidtv.getText().toString();
                    runCert();

                default:
                    break;
            }
        }
    }


    public void runCert(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClass tools=new OkHttpClass();
                String result=tools.setcert(schoolS,dapartmentS,majorS,classesS,sidS);
                Log.d("cert-",result);
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    String rsp=jsonObject.getString("rspCode");

                    if(rsp.equals("666")){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(CertActivity.this,"认证成功",Toast.LENGTH_SHORT).show();
                                CertActivity.this.finish();
                            }
                        });

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void showSchoolView(boolean isDialog){
        if(!mHasLoaded){
            Toast.makeText(this,"数据加载中",Toast.LENGTH_SHORT).show();
            return;
        }

        int[] defaultSelectOptions = getSchool();
        OptionsPickerBuilder optionsPickerBuilder = new OptionsPickerBuilder(this, (v, options1, options2, option3) -> {
            //返回的分别是三个级别的选中位置

            schoolS = SchoolItem.get(options1).getPickerViewText();
            schooltv.setText(schoolS);
            Toast.makeText(this, schoolS, Toast.LENGTH_SHORT).show();
            return false;
        });
        optionsPickerBuilder.setTitleText("学校名称");
        optionsPickerBuilder.setDividerColor(Color.BLACK);
        optionsPickerBuilder.isRestoreItem(true);
        optionsPickerBuilder.setTextColorCenter(Color.BLACK);
        optionsPickerBuilder.setContentTextSize(20);
        optionsPickerBuilder.isDialog(isDialog);
        optionsPickerBuilder.setSelectOptions(defaultSelectOptions[0], defaultSelectOptions[1]);//返回的分别是三个级别的选中位置
        OptionsPickerView pvoptions= optionsPickerBuilder.build();

        pvoptions.setPicker(SchoolItem);
        pvoptions.show();
    }

    /**
     * @return 获取默认学校的索引
     */
    public int[] getSchool() {
        int[] res = new int[3];
        PostInfo postInfo;
        List<String> ares;

        for (int i = 0; i < SchoolItem.size(); i++) {
            postInfo = SchoolItem.get(i);
            if ("销售".equals(postInfo.getName())) {
                res[0] = i;

                break;
            }
        }
        return res;
    }

    public void loadDataSchool(List<PostInfo> postInfos){
        SchoolItem=postInfos;

        mHasLoaded=true;

    }

    public void showDaparView(boolean isDialog){
        if(!mHasLoaded){
            Toast.makeText(this,"数据加载中",Toast.LENGTH_SHORT).show();
            return;
        }

        int[] defaultSelectOptions = getDapter();
        OptionsPickerBuilder optionsPickerBuilder = new OptionsPickerBuilder(this, (v, options1, options2, option3) -> {
            //返回的分别是三个级别的选中位置

            dapartmentS = DapartmentItem.get(options1).getPickerViewText();
            dapartmenttv.setText(dapartmentS);
            Toast.makeText(this, dapartmentS, Toast.LENGTH_SHORT).show();
            return false;
        });
        optionsPickerBuilder.setTitleText("院系名称");
        optionsPickerBuilder.setDividerColor(Color.BLACK);
        optionsPickerBuilder.isRestoreItem(true);
        optionsPickerBuilder.setTextColorCenter(Color.BLACK);
        optionsPickerBuilder.setContentTextSize(20);
        optionsPickerBuilder.isDialog(isDialog);
        optionsPickerBuilder.setSelectOptions(defaultSelectOptions[0], defaultSelectOptions[1]);//返回的分别是三个级别的选中位置
        OptionsPickerView pvoptions= optionsPickerBuilder.build();

        pvoptions.setPicker(DapartmentItem);
        pvoptions.show();
    }

    /**
     * @return 获取默认院系的索引
     */
    public int[] getDapter() {
        int[] res = new int[3];
        PostInfo postInfo;
        List<String> ares;

        for (int i = 0; i < SchoolItem.size(); i++) {
            postInfo = DapartmentItem.get(i);
            if ("销售".equals(postInfo.getName())) {
                res[0] = i;

                break;
            }
        }
        return res;
    }

    public void loadDataDapar(List<PostInfo> postInfos){
        DapartmentItem=postInfos;

        mHasLoaded=true;

    }


    public void showMajorView(boolean isDialog){
        if(!mHasLoaded){
            Toast.makeText(this,"数据加载中",Toast.LENGTH_SHORT).show();
            return;
        }

        int[] defaultSelectOptions = getMajor();
        OptionsPickerBuilder optionsPickerBuilder = new OptionsPickerBuilder(this, (v, options1, options2, option3) -> {
            //返回的分别是三个级别的选中位置

            majorS = MajorItem.get(options1).getPickerViewText();
            majortv.setText(majorS);
            Toast.makeText(this, majorS, Toast.LENGTH_SHORT).show();
            return false;
        });
        optionsPickerBuilder.setTitleText("专业名称");
        optionsPickerBuilder.setDividerColor(Color.BLACK);
        optionsPickerBuilder.isRestoreItem(true);
        optionsPickerBuilder.setTextColorCenter(Color.BLACK);
        optionsPickerBuilder.setContentTextSize(20);
        optionsPickerBuilder.isDialog(isDialog);
        optionsPickerBuilder.setSelectOptions(defaultSelectOptions[0], defaultSelectOptions[1]);//返回的分别是三个级别的选中位置
        OptionsPickerView pvoptions= optionsPickerBuilder.build();

        pvoptions.setPicker(MajorItem);
        pvoptions.show();
    }

    /**
     * @return 获取默认专业的索引
     */
    public int[] getMajor() {
        int[] res = new int[3];
        PostInfo postInfo;
        List<String> ares;

        for (int i = 0; i < MajorItem.size(); i++) {
            postInfo = MajorItem.get(i);
            if ("销售".equals(postInfo.getName())) {
                res[0] = i;

                break;
            }
        }
        return res;
    }

    public void loadDataMajor(List<PostInfo> postInfos){
        MajorItem=postInfos;

        mHasLoaded=true;

    }


    public void showClassView(boolean isDialog){
        if(!mHasLoaded){
            Toast.makeText(this,"数据加载中",Toast.LENGTH_SHORT).show();
            return;
        }

        int[] defaultSelectOptions = getClasser();
        OptionsPickerBuilder optionsPickerBuilder = new OptionsPickerBuilder(this, (v, options1, options2, option3) -> {
            //返回的分别是三个级别的选中位置

            classesS = ClassesItem.get(options1).getPickerViewText();
            classestv.setText(classesS);
            Toast.makeText(this, classesS, Toast.LENGTH_SHORT).show();
            return false;
        });
        optionsPickerBuilder.setTitleText("班级名称");
        optionsPickerBuilder.setDividerColor(Color.BLACK);
        optionsPickerBuilder.isRestoreItem(true);
        optionsPickerBuilder.setTextColorCenter(Color.BLACK);
        optionsPickerBuilder.setContentTextSize(20);
        optionsPickerBuilder.isDialog(isDialog);
        optionsPickerBuilder.setSelectOptions(defaultSelectOptions[0], defaultSelectOptions[1]);//返回的分别是三个级别的选中位置
        OptionsPickerView pvoptions= optionsPickerBuilder.build();

        pvoptions.setPicker(ClassesItem);
        pvoptions.show();
    }

    /**
     * @return 获取默认班级的索引
     */
    public int[] getClasser() {
        int[] res = new int[3];
        PostInfo postInfo;
        List<String> ares;

        for (int i = 0; i < ClassesItem.size(); i++) {
            postInfo = ClassesItem.get(i);
            if ("销售".equals(postInfo.getName())) {
                res[0] = i;

                break;
            }
        }
        return res;
    }

    public void loadDataClass(List<PostInfo> postInfos){
        ClassesItem=postInfos;

        mHasLoaded=true;

    }
}