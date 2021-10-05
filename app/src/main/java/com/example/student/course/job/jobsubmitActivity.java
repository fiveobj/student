package com.example.student.course.job;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.student.R;
import com.example.student.customclass.OkHttpClass;

import java.io.File;

import static io.agora.download.DownloadHelper.TAG;

public class jobsubmitActivity extends AppCompatActivity {

    private ImageButton santfile,santbtn,back;
    private TextView filename,scoretv,timetv,detailtv,jobissand;
    private ImageView fileimv;
    private EditText editText;
    private String score,detail,jobid,time;

    private String path,uploadfile;
    private File file;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobsubmit);

        //设置顶部状态栏为透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        initView();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jobsubmitActivity.this.finish();
            }
        });

        Intent intent=getIntent();
        if(intent!=null){
            score="分值： "+intent.getStringExtra("full");
            detail=intent.getStringExtra("detail");
            jobid=intent.getStringExtra("jobid");
            time="截止时间： "+intent.getStringExtra("deadline");
            scoretv.setText(score);
            detailtv.setText(detail);
            timetv.setText(time);


        }

        String job=editText.getText().toString();


        santbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(editText.getText())){
                    Toast.makeText(jobsubmitActivity.this,"请输入答案",Toast.LENGTH_SHORT).show();
                }else{
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            OkHttpClass tools=new OkHttpClass();
                            String result=tools.setjob(jobid,job);
                            Log.d("result-setjob",result);

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        editText.setFocusableInTouchMode(false);
                                        jobissand.setText("已提交，点击修改");
                                    }
                                });


                        }
                    }).start();
                    Toast.makeText(jobsubmitActivity.this,"提交成功",Toast.LENGTH_SHORT).show();
                }
            }
        });

        santfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });
       /* santfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission();//检查危险权限
            }
        });*/
    }

    private void initView() {
        santfile = (ImageButton) findViewById(R.id.santjob);
        santbtn = (ImageButton) findViewById(R.id.job_santbtn);
        filename = (TextView) findViewById(R.id.santjobtv);
        fileimv = (ImageView) findViewById(R.id.santjobimv);
        back=(ImageButton) findViewById(R.id.jobsubmit_back);
        editText=(EditText)findViewById(R.id.answer_edit);
        scoretv =(TextView) findViewById(R.id.job_score);
        timetv=(TextView) findViewById(R.id.job_deadline);
        detailtv=(TextView) findViewById(R.id.job_detail);
        jobissand=(TextView)findViewById(R.id.job_issand);
        //setListeners();
    }

    private void setListeners(){

    }

    private void showFileChooser(){
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.w(TAG,"返回的数据"+data);
        if(resultCode== Activity.RESULT_OK){
            Toast.makeText(this,"path",Toast.LENGTH_SHORT).show();
            Uri uri=data.getData();


                path=uri.getPath();
                file=new File(path);
                uploadfile=file.getName();
                filename.setText(uploadfile);
                fileimv.setImageResource(R.mipmap.stuc_rec_childword);
                Log.w(TAG,"getName==="+uploadfile);
                Toast.makeText(this,path,Toast.LENGTH_SHORT).show();
                return;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /*private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            try {
                upLodeFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},201);
        }
    }

    private File file;
    private void upLodeFile() throws IOException {
        file = new File(Environment.getExternalStorageDirectory() + "/Pictures/Screenshots/a.png");//获取目录（不加后面的字符串是你的根目录+后面是继续找的意思）
        getFileLog();
        //ok上传
        /**
         * ok上传文件实例
         * 由于服务器暂停维护，接口无法访问
         * 代码就这些
         */
        //创建OK
        /*OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        //请求体
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
        MultipartBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("key", "1")                      //post请求Key,value
                .addFormDataPart("file", file.getName(),requestBody)     //post请求Key,value
                .build();
        //构建请求
        Request request = new Request.Builder()
//                .url("http://yun918.cn/study/public/index.php/file_upload.php")
                .url("http://yun918.cn/study/public/file_upload.php")
                .post(body)
                .build();
        //call对象
        Call call = okHttpClient.newCall(request);
        //call执行请求
        call.enqueue(new Callback() {   //异步
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("tag", "onFailure: "+e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                final UpLoadBean upLoadBean = new Gson().fromJson(json, UpLoadBean.class);
                if (!TextUtils.isEmpty(json)){
                    int code = upLoadBean.getCode();
                    String str = String.valueOf(code);
                    if (str.equals("200")){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                uploadTv.setText(upLoadBean.getRes());
                                Glide.with(jobsubmitActivity.this).load(upLoadBean.getData().getUrl()).into(fileimv);
                            }
                        });
                    }else {
                        Toast.makeText(jobsubmitActivity.this, "上传失败", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private void getFileLog() {
        File absoluteFile = file.getAbsoluteFile();
        String absolutePath = file.getAbsolutePath();
        File canonicalFile = null;
        try {
            canonicalFile = file.getCanonicalFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String canonicalPath = null;
        try {
            canonicalPath = file.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long freeSpace = file.getFreeSpace();
        String parent = file.getParent();
        File parentFile = file.getParentFile();
        String path = file.getPath();
        long totalSpace = file.getTotalSpace();
        long usableSpace = file.getUsableSpace();
        Log.e("tag", "absoluteFile: "+absoluteFile
                +"\t\n"+"absolutePath: "+absolutePath
                +"\t\n"+"canonicalFile: "+canonicalFile
                +"\t\n"+"canonicalPath: "+canonicalPath
                +"\t\n"+"freeSpace: "+freeSpace
                +"\t\n"+"parent: "+parent
                +"\t\n"+"parentFile: "+parentFile
                +"\t\n"+"path: "+path
                +"\t\n"+"totalSpace: "+totalSpace
                +"\t\n"+"usableSpace: "+usableSpace);
    }*/
}