package com.example.student.my.mypost;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.student.R;
import com.xuexiang.xui.widget.alpha.XUIAlphaButton;
import com.xuexiang.xui.widget.picker.widget.OptionsPickerView;
import com.xuexiang.xui.widget.picker.widget.builder.OptionsPickerBuilder;

import java.util.ArrayList;
import java.util.List;

public class mypostActivity extends AppCompatActivity {

    private XUIAlphaButton postname,where,company,walfare,time2;

    private boolean mHasLoaded;
    private List<PostInfo> postname1item= new ArrayList<>();
    private List<List<String>> postname2Item=new ArrayList<>();

    private List<PostInfo> postwhere1item= new ArrayList<>();
    private List<List<String>> postwhere2Item=new ArrayList<>();

    private List<PostInfo> postcompany1item= new ArrayList<>();

    private List<PostInfo> postwolf1item= new ArrayList<>();

    private List<PostInfo> posttime1item= new ArrayList<>();
    private List<List<String>> posttime2Item=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypost);

        //设置顶部状态栏为透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        postname=(XUIAlphaButton)findViewById(R.id.mypost_postname);
        where=(XUIAlphaButton)findViewById(R.id.mypost_where);
        company=(XUIAlphaButton)findViewById(R.id.mypost_company);
        walfare=(XUIAlphaButton)findViewById(R.id.mypost_walfare);
        time2=(XUIAlphaButton)findViewById(R.id.mypost_time2);

        loadDataJob(DemoDataProvider.getJobInfos());
        loadDataWhere(DemoDataProvider.getWhereInfos());
        loadDataCompany(DemoDataProvider.getCompanyInfos());
        //loadDataWolf();
        loadDataTime(DemoDataProvider.getTimeInfos());


        setlistener();




    }



    public void setlistener(){
        OnClick onClick=new OnClick();
        postname.setOnClickListener(onClick);
        where.setOnClickListener(onClick);
        company.setOnClickListener(onClick);
        walfare.setOnClickListener(onClick);
        time2.setOnClickListener(onClick);


    }

    public class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
                switch (v.getId()){
                    case R.id.mypost_postname:
                        showJobView(false);
                        break;
                    case R.id.mypost_where:
                        showWhereView(false);
                        break;
                    case R.id.mypost_company:
                        showCompanyView(false);
                        break;
                    /*case R.id.mypost_walfare:
                        showWalfareView(false);
                        break;*/
                    case R.id.mypost_time2:
                        showTimesView(false);
                        break;
                    default:

                }
        }
    }
//-----------------------------弹出选择器-----------------------------------------------------------------------------------

    private void showJobView(boolean isDialog){
        if(!mHasLoaded){
            Toast.makeText(this,"数据加载中",Toast.LENGTH_SHORT).show();
            return;
        }

        int[] defaultSelectOptions = getDefaultJob();
        OptionsPickerBuilder optionsPickerBuilder = new OptionsPickerBuilder(this, (v,options1, options2,option3) -> {
            //返回的分别是三个级别的选中位置


            String tx = postname1item.get(options1).getPickerViewText() + "-" +
                    postname2Item.get(options1).get(options2);
            postname.setText(tx);
            Toast.makeText(this, tx, Toast.LENGTH_SHORT).show();
            return false;
        });
        optionsPickerBuilder.setTitleText("职位选择");
        optionsPickerBuilder.setDividerColor(Color.BLACK);
        optionsPickerBuilder.isRestoreItem(true);
        optionsPickerBuilder.setTextColorCenter(Color.BLACK);
        optionsPickerBuilder.setContentTextSize(20);
        optionsPickerBuilder.isDialog(isDialog);
        optionsPickerBuilder.setSelectOptions(defaultSelectOptions[0], defaultSelectOptions[1]);//返回的分别是三个级别的选中位置
        OptionsPickerView pvoptions= optionsPickerBuilder.build();

        pvoptions.setPicker(postname1item,postname2Item);
        pvoptions.show();
    }


    private void showWhereView(boolean isDialog){
        if(!mHasLoaded){
            Toast.makeText(this,"数据加载中",Toast.LENGTH_SHORT).show();
            return;
        }

        int[] defaultSelectOptions = getDefaultWhere();
        OptionsPickerBuilder optionsPickerBuilder = new OptionsPickerBuilder(this, (v,options1, options2,option3) -> {
            //返回的分别是三个级别的选中位置


            String tx = postwhere1item.get(options1).getPickerViewText() + "-" +
                    postwhere2Item.get(options1).get(options2);
            where.setText(tx);
            Toast.makeText(this, tx, Toast.LENGTH_SHORT).show();
            return false;
        });
        optionsPickerBuilder.setTitleText("城市选择");
        optionsPickerBuilder.setDividerColor(Color.BLACK);
        optionsPickerBuilder.isRestoreItem(true);
        optionsPickerBuilder.setTextColorCenter(Color.BLACK);
        optionsPickerBuilder.setContentTextSize(20);
        optionsPickerBuilder.isDialog(isDialog);
        optionsPickerBuilder.setSelectOptions(defaultSelectOptions[0], defaultSelectOptions[1]);//返回的分别是三个级别的选中位置
        OptionsPickerView pvoptions= optionsPickerBuilder.build();

        pvoptions.setPicker(postwhere1item,postwhere2Item);
        pvoptions.show();
    }


    private void showCompanyView(boolean isDialog){
        if(!mHasLoaded){
            Toast.makeText(this,"数据加载中",Toast.LENGTH_SHORT).show();
            return;
        }

        int[] defaultSelectOptions = getDefaultCompany();
        OptionsPickerBuilder optionsPickerBuilder = new OptionsPickerBuilder(this, (v,options1, options2,option3) -> {
            //返回的分别是三个级别的选中位置


            String tx = postcompany1item.get(options1).getPickerViewText();
            company.setText(tx);
            Toast.makeText(this, tx, Toast.LENGTH_SHORT).show();
            return false;
        });
        optionsPickerBuilder.setTitleText("公司选择");
        optionsPickerBuilder.setDividerColor(Color.BLACK);
        optionsPickerBuilder.isRestoreItem(true);
        optionsPickerBuilder.setTextColorCenter(Color.BLACK);
        optionsPickerBuilder.setContentTextSize(20);
        optionsPickerBuilder.isDialog(isDialog);
        optionsPickerBuilder.setSelectOptions(defaultSelectOptions[0]);//返回的分别是三个级别的选中位置
        OptionsPickerView pvoptions= optionsPickerBuilder.build();

        pvoptions.setPicker(postcompany1item);
        pvoptions.show();
    }


    /*private void showWalfareView(boolean isDialog){
        if(!mHasLoaded){
            Toast.makeText(this,"数据加载中",Toast.LENGTH_SHORT).show();
            return;
        }

        int[] defaultSelectOptions = getDefaultWalf();
        OptionsPickerBuilder optionsPickerBuilder = new OptionsPickerBuilder(this, (v,options1, options2,option3) -> {
            //返回的分别是三个级别的选中位置


            String tx = postwolf1item.get(options1).getPickerViewText();
            walfare.setText(tx);
            Toast.makeText(this, tx, Toast.LENGTH_SHORT).show();
            return false;
        });
        optionsPickerBuilder.setTitleText("福利选择");
        optionsPickerBuilder.setDividerColor(Color.BLACK);
        optionsPickerBuilder.isRestoreItem(true);
        optionsPickerBuilder.setTextColorCenter(Color.BLACK);
        optionsPickerBuilder.setContentTextSize(20);
        optionsPickerBuilder.isDialog(isDialog);
        optionsPickerBuilder.setSelectOptions(defaultSelectOptions[0], defaultSelectOptions[1]);//返回的分别是三个级别的选中位置
        OptionsPickerView pvoptions= optionsPickerBuilder.build();

        pvoptions.setPicker(postwhere1item,postwhere2Item);
        pvoptions.show();
    }*/


    private void showTimesView(boolean isDialog){
        if(!mHasLoaded){
            Toast.makeText(this,"数据加载中",Toast.LENGTH_SHORT).show();
            return;
        }

        int[] defaultSelectOptions = getDefaultTime();
        OptionsPickerBuilder optionsPickerBuilder = new OptionsPickerBuilder(this, (v,options1, options2,option3) -> {
            //返回的分别是三个级别的选中位置


            String tx = posttime1item.get(options1).getPickerViewText() + "-" +
                    posttime2Item.get(options1).get(options2);
            time2.setText(tx);
            Toast.makeText(this, tx, Toast.LENGTH_SHORT).show();
            return false;
        });
        optionsPickerBuilder.setTitleText("时间选择");
        optionsPickerBuilder.setDividerColor(Color.BLACK);
        optionsPickerBuilder.isRestoreItem(true);
        optionsPickerBuilder.setTextColorCenter(Color.BLACK);
        optionsPickerBuilder.setContentTextSize(20);
        optionsPickerBuilder.isDialog(isDialog);
        optionsPickerBuilder.setSelectOptions(defaultSelectOptions[0], defaultSelectOptions[1]);//返回的分别是三个级别的选中位置
        OptionsPickerView pvoptions= optionsPickerBuilder.build();

        pvoptions.setPicker(posttime1item,posttime2Item);
        pvoptions.show();
    }
//-----------------------------------------默认选项索引----------------------------------------------------------
    /**
     * @return 获取默认工作的索引
     */
    private int[] getDefaultJob() {
        int[] res = new int[3];
        PostInfo postInfo;
        List<String> ares;

        for (int i = 0; i < postname1item.size(); i++) {
            postInfo = postname1item.get(i);
            if ("销售".equals(postInfo.getName())) {
                res[0] = i;
                ares = postInfo.getJob();
                for (int j = 0; j < ares.size(); j++) {

                    if ("全部".equals(ares.get(j))) {
                        res[1] = j;
                        break;
                    }
                }
                break;
            }
        }
        return res;
    }

    /**
     * @return 获取默认城市的索引
     */

    private int[] getDefaultWhere() {
        int[] res = new int[3];
        PostInfo postInfo;
        List<String> ares;

        for (int i = 0; i < postname1item.size(); i++) {
            postInfo = postname1item.get(i);
            if ("浙江省".equals(postInfo.getName())) {
                res[0] = i;
                ares = postInfo.getJob();
                for (int j = 0; j < ares.size(); j++) {

                    if ("杭州市".equals(ares.get(j))) {
                        res[1] = j;
                        break;
                    }
                }
                break;
            }
        }
        return res;
    }

    /**
     * @return 获取默认公司的索引
     */

    private int[] getDefaultCompany() {
        int[] res = new int[3];
        PostInfo postInfo;
        List<String> ares;

        for (int i = 0; i < postname1item.size(); i++) {
            postInfo = postname1item.get(i);
            if ("浙江省".equals(postInfo.getName())) {
                res[0] = i;

                break;
            }
        }
        return res;
    }

    /**
     * @return 获取默认时间的索引
     */

    private int[] getDefaultTime() {
        int[] res = new int[3];
        PostInfo postInfo;
        List<String> ares;

        for (int i = 0; i < postname1item.size(); i++) {
            postInfo = postname1item.get(i);
            if ("00:00".equals(postInfo.getName())) {
                res[0] = i;
                ares = postInfo.getJob();
                for (int j = 0; j < ares.size(); j++) {

                    if ("00:00".equals(ares.get(j))) {
                        res[1] = j;
                        break;
                    }
                }
                break;
            }
        }
        return res;
    }

//----------------------------------------------加载数据----------------------------------------------------------
    private void loadDataJob(List<PostInfo> postInfos){
        postname1item=postInfos;

        for (PostInfo postInfo:postInfos){
            List<String> joblist=new ArrayList<>();

            for(String job: postInfo.getJob() ){

                joblist.add(job);

            }
            postname2Item.add(joblist);
        }
        mHasLoaded=true;

    }

    private void loadDataWhere(List<PostInfo> postInfos){
        postwhere1item=postInfos;

        for (PostInfo postInfo:postInfos){
            List<String> joblist=new ArrayList<>();

            for(String job: postInfo.getJob() ){

                joblist.add(job);

            }
            postwhere2Item.add(joblist);
        }
        mHasLoaded=true;

    }

    private void loadDataCompany(List<PostInfo> postInfos){
        postcompany1item=postInfos;

        mHasLoaded=true;

    }

    private void loadDataTime(List<PostInfo> postInfos){
        posttime1item=postInfos;

        for (PostInfo postInfo:postInfos){
            List<String> joblist=new ArrayList<>();

            for(String job: postInfo.getJob() ){

                joblist.add(job);

            }
            posttime2Item.add(joblist);
        }
        mHasLoaded=true;

    }
//---------------------------------数据复原--------------------------------------------------------------------------
    @Override
    protected void onDestroy() {

        postname1item.clear();
        postname2Item.clear();

        postwhere2Item.clear();
        postwhere1item.clear();

        postcompany1item.clear();

        posttime1item.clear();
        posttime2Item.clear();


        mHasLoaded=false;

        super.onDestroy();
    }
}