package com.example.student.my.id;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.student.MainActivity;
import com.example.student.R;
import com.example.student.customclass.OkHttpClass;
import com.example.student.signinActivity;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static io.agora.download.DownloadHelper.TAG;

public class myidActivity extends AppCompatActivity {
    private ImageButton back;
    private RelativeLayout toux,username,id,sex,nowwhere,firstwhere,phone,email,biethday,realname,highest_edu,studCer;
    private ImageView touximg;
    private Context context;
    private TextView usernametv,idtv,sextv,nowwheretv,firstwheretv,phonetv,emailtv,birthdaytv,realnametv,highest_edutv,studCertv;
    private OkHttpClass okHttpClass;
    private String name,data,url,path;

    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    private static OkHttpClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myid);

        //设置顶部状态栏为透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        context=this;
        init();
        setListeners();

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
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                studCertv.setText("已认证");
                                studCertv.setTextColor(Color.parseColor("#949494"));
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void init(){
        back=(ImageButton)findViewById(R.id.myid_back);
        toux=(RelativeLayout)findViewById(R.id.myid_toux);
        username=(RelativeLayout)findViewById(R.id.id_name);
        id=(RelativeLayout)findViewById(R.id.id_id);
        sex=(RelativeLayout)findViewById(R.id.id_sex);
        nowwhere=(RelativeLayout)findViewById(R.id.id_where);
        firstwhere=(RelativeLayout)findViewById(R.id.id_firstwhere);
        phone=(RelativeLayout)findViewById(R.id.id_phone);
        email=(RelativeLayout)findViewById(R.id.id_email);
        biethday=(RelativeLayout)findViewById(R.id.id_birthday);
        realname=(RelativeLayout)findViewById(R.id.id_realname);
        highest_edu=(RelativeLayout)findViewById(R.id.id_school);
        studCer=(RelativeLayout)findViewById(R.id.id_student_cert);

        touximg=(ImageView)findViewById(R.id.myid_touxim);
        usernametv=(TextView)findViewById(R.id.id_nametv);
        idtv=(TextView)findViewById(R.id.id_idtv);
        sextv=(TextView)findViewById(R.id.id_sextv);
        nowwheretv=(TextView)findViewById(R.id.id_wheretv);
        firstwheretv=(TextView)findViewById(R.id.id_firstwheretv);
        phonetv=(TextView)findViewById(R.id.id_phonetv);
        emailtv=(TextView)findViewById(R.id.id_emailtv);
        birthdaytv=(TextView)findViewById(R.id.id_birthdaytv);
        realnametv=(TextView)findViewById(R.id.id_realnametv);
        highest_edutv=(TextView)findViewById(R.id.id_schooltv);
        studCertv=(TextView)findViewById(R.id.id_certtv);


    }

    private void setListeners(){
        OnClick onClick=new OnClick();
        toux.setOnClickListener(onClick);
        username.setOnClickListener(onClick);
        id.setOnClickListener(onClick);
        sex.setOnClickListener(onClick);
        nowwhere.setOnClickListener(onClick);
        firstwhere.setOnClickListener(onClick);
        phone.setOnClickListener(onClick);
        email.setOnClickListener(onClick);
        biethday.setOnClickListener(onClick);
        realname.setOnClickListener(onClick);
        highest_edu.setOnClickListener(onClick);
        studCer.setOnClickListener(onClick);



    }

    private class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.myid_back:
                    myidActivity.this.finish();
                    break;
                case R.id.myid_toux:
                    Intent i=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i,2);
                    break;
                case R.id.id_name:
                    myid_changeDialog dialog=new myid_changeDialog(context);
                    dialog.setSubmit(new myid_changeDialog.IOnCanceListener() {
                        @Override
                        public void onCancel(myid_changeDialog dialog) {
                            data=dialog.getEd_text().getText().toString();
                            name="username";
                            url="http://1.116.114.32:18081/student/login";


                        }
                    });
                    dialog.show();
                    break;
                case R.id.id_id:
                case R.id.id_sex:
                case R.id.id_where:
                case R.id.id_firstwhere:
                case R.id.id_phone:
                case R.id.id_email:
                case R.id.id_birthday:
                case R.id.id_realname:
                case R.id.id_school:
                case R.id.id_student_cert:
                    Intent intent=new Intent(myidActivity.this,CertActivity.class);
                    startActivity(intent);
                default:
                break;
            }

        }
    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2){
            try{
                Uri uri=data.getData();
                String[] pojo={MediaStore.Images.Media.DATA};
                Cursor cursor=managedQuery(uri,pojo,null,null,null);
                if(cursor!=null){
                    ContentResolver cr=this.getContentResolver();
                    int colunm_index=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    path=cursor.getString(colunm_index);
                    final File file=new File(path);
                    Bitmap bitmap= BitmapFactory.decodeStream(cr.openInputStream(uri));
                    Bitmap option = BitmapOption.bitmapOption(bitmap,5);
                    touximg.setImageBitmap(option);
                    //new Thread(runnable).start();
                    if (Build.VERSION.SDK_INT >= 23) {
                        int REQUEST_CODE_CONTACT = 101;
                        String[] permissions = {
                                Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        //验证是否许可权限
                        for (String str : permissions) {
                            if (myidActivity.this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                                //申请权限
                                myidActivity.this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                                return;
                            } else {
                                //这里就是权限打开之后自己要操作的逻辑
                                uploadImage("http://1.116.114.32:18081/fileInfo/upload",path);
                            }
                        }
                    }


                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 上传图片
     * @param url
     * @param imagePath 图片路径
     * @return 新图片的路径
     * @throws IOException
     * @throws JSONException
     */
    public static void uploadImage(String url, String imagePath) throws IOException, JSONException {

                OkHttpClient okHttpClient = new OkHttpClient();
                Log.d("imagePath", imagePath);
                File file = new File(imagePath);
                RequestBody image = RequestBody.create(file,MediaType.parse("multipart/form-data"));

                RequestBody body = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("file", "aaa.jpg", image)
                        .build();
                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();


                Call call=okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Log.d(TAG, "onFailure: 访问失败!");
                        Log.e(TAG,e.toString());
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        Log.d(TAG, "onResponse: 访问成功!");
                        String json = response.body().string();
                        Log.d(TAG, "onResponse: json:" + json);

                    }
                });
            }





    public static class BitmapOption {

        private static final BitmapOption bitmapOption = new BitmapOption();

        private BitmapOption() {
        }

        public static BitmapOption getBitmapOption() {
            return bitmapOption;
        }


        public static Bitmap bitmapOption(Bitmap image, int size) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 85, out);
            float zoom = (float) Math.sqrt(size * 1024 / (float) out.toByteArray().length);
            Matrix matrix = new Matrix();
            matrix.setScale(zoom, zoom);
            Bitmap result = Bitmap.createBitmap(image, 0, 0, image.getWidth(), image.getHeight(), matrix, true);
            out.reset();
            result.compress(Bitmap.CompressFormat.JPEG, 85, out);
            while (out.toByteArray().length > size * 1024) {
                System.out.println(out.toByteArray().length);
                matrix.setScale(0.9f, 0.9f);
                result = Bitmap.createBitmap(result, 0, 0, result.getWidth(), result.getHeight(), matrix, true);
                out.reset();
                result.compress(Bitmap.CompressFormat.JPEG, 85, out);
            }
            return result;
        }


    }


}