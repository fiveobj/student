package com.example.student;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class signinActivity extends AppCompatActivity {

    private EditText id,pass;
    private ImageButton signin;
    private TextView login;
    private String myid,mypass,path,result;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        //设置顶部状态栏为透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        //跳转注册页面
        login=(TextView)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(signinActivity.this,stuloginActivity.class);
                startActivityForResult(intent,1);
            }
        });

        //定义密码账号控件
        id=(EditText)findViewById(R.id.signin_id);
        pass=(EditText)findViewById(R.id.signin_pass);

        signin=(ImageButton)findViewById(R.id.signin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginPost();
            }
        });
    }

    public void loginPost(){
        myid=id.getText().toString();
        mypass=pass.getText().toString();
        getData(myid,mypass);
        //path="http://1.116.114.32:18081/student/login";
        //new postTask().execute(myid,mypass,path);

    }


    //okhttp3
    private void getData(String stuNo,String password){
        OkHttpClient okHttpClient= new OkHttpClient.Builder().connectTimeout(8000, TimeUnit.MILLISECONDS).build();
        FormBody.Builder builder=new FormBody.Builder();
        final RequestBody requestBody=builder.add("username",stuNo).add("password",password).build();
        final Request request=new Request.Builder().url("http://1.116.114.32:18081/student/login").post(requestBody).build();
        Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("TAG","网络错误，登录失败");
                Log.e("TAG","发送失败消息");
                Log.e("TAG",e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                //Gson gson=new Gson();
                String str=response.body().string();
                //LoginMessage data = gson.fromJson(str, LoginMessage.class);
                //Log.e("TAG","ResponseCode:"+response.code());
                //Log.e("TAG",data.getRspMsg());
                try {
                    JSONObject jsonObject=new JSONObject(str);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Toast.makeText(signinActivity.this,str,Toast.LENGTH_SHORT).show();
                            try {
                                if(jsonObject.getString("rspMsg").equals("操作成功")){
                                    Intent intent=new Intent(signinActivity.this,MainActivity.class);
                                    intent.putExtra("username",stuNo);
                                    signinActivity.this.finish();
                                    startActivity(intent);
                                }else if(myid.isEmpty()){
                                    Toast.makeText(signinActivity.this,"请输入账号",Toast.LENGTH_SHORT).show();
                                }else if(mypass.isEmpty()){
                                    Toast.makeText(signinActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                                } else
                                {
                                    Toast.makeText(signinActivity.this,jsonObject.getString("rspMsg"),Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }
    /*public class postTask extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] params) {
            //依次获取用户名，密码与路径
            String name=params[0].toString();
            String pass=params[1].toString();
            String path=params[2].toString();
            try {
                //获取网络上get方式提交的整个路径
                URL url=new URL(path);
                //打开网络连接
                HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                //Toast.makeText(signinActivity.this, "连接成功", Toast.LENGTH_SHORT).show();
                //设置提交方式
                conn.setRequestMethod("POST");
                //设置网络超时时间
                conn.setConnectTimeout(15000);
                //conn.setReadTimeout(15000);
                //界面上所有的参数名加上他的值
                String s="username="+name+"&password"+pass;
                //获取请求头
                //conn.setRequestProperty("Content-Length",s.length()+"");//键是固定的
                conn.setRequestProperty("Content-Type","application/json;charset=UTF-8");//键和值是固定的
                //设置允许对外输出数据
                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.setUseCaches(false);
                //把界面上的所有数据写出去
                //OutputStream os=conn.getOutputStream();
                //os.write(s.getBytes());

                StringBuffer stringBuffer=new StringBuffer();
                stringBuffer.append(s);
                conn.getOutputStream().write(s.getBytes());
                conn.getOutputStream().flush();
                conn.getOutputStream().close();


                if(conn.getResponseCode()==200){
                    //Toast.makeText(signinActivity.this, "连接成功", Toast.LENGTH_SHORT).show();
                    //用io流与web后台进行数据交互
                    InputStream is=conn.getInputStream();
                    //字节流转字符流
                    BufferedReader reader=new BufferedReader(new InputStreamReader(is));

                    StringBuffer buffer=new StringBuffer();

                    //读出每一行的数据
                    String str="";
                    while ((str=reader.readLine())!=null){
                        buffer.append(str);
                    }
                    is.close();
                    reader.close();
                    conn.disconnect();
                    //返回读出的每一行的数据
                    //return str;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            //获取Android studio与web后台数据交互获得的值
            String s= (String) o;
            //吐司Android studio与web后台数据交互获得的值
            Toast.makeText(signinActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    }




   /* Runnable postRun = new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            requestPost(stringHashMap);
        }
    };
    private void requestPost(HashMap<String, String> paramsMap) {
        try {
            String baseUrl = "http://1.116.114.32:18081/doc.html#/default/student-controller/loginUsingPOST_1";
            //合成参数
            StringBuilder tempParams = new StringBuilder();
            int pos = 0;
            for (String key : paramsMap.keySet()) {
                if (pos >0) {
                    tempParams.append("&");
                }
                tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key), "utf-8")));
                pos++;
            }
            String params = tempParams.toString();
            Log.e(TAG,"params--post-->>"+params);
            // 请求的参数转换为byte数组
//            byte[] postData = params.getBytes();
            // 新建一个URL对象
            URL url = new URL(baseUrl);
            // 打开一个HttpURLConnection连接
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            // 设置连接超时时间
            urlConn.setConnectTimeout(5 * 1000);
            //设置从主机读取数据超时
            urlConn.setReadTimeout(5 * 1000);
            // Post请求必须设置允许输出 默认false
            urlConn.setDoOutput(true);
            //设置请求允许输入 默认是true
            urlConn.setDoInput(true);
            // Post请求不能使用缓存
            urlConn.setUseCaches(false);
            // 设置为Post请求
            urlConn.setRequestMethod("POST");
            //设置本次连接是否自动处理重定向
            urlConn.setInstanceFollowRedirects(true);
            //配置请求Content-Type
//            urlConn.setRequestProperty("Content-Type", "application/json");//post请求不能设置这个
            // 开始连接
            urlConn.connect();

            // 发送请求参数
            PrintWriter dos = new PrintWriter(urlConn.getOutputStream());
            dos.write(params);
            dos.flush();
            dos.close();
            // 判断请求是否成功
            if (urlConn.getResponseCode() == 200) {
                // 获取返回的数据
                String result = streamToString(urlConn.getInputStream());
                Log.e(TAG, "Post方式请求成功，result--->" + result);
            } else {
                Log.e(TAG, "Post方式请求失败");
            }
            // 关闭连接
            urlConn.disconnect();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }
    public String streamToString(InputStream is) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            baos.close();
            is.close();
            byte[] byteArray = baos.toByteArray();
            return new String(byteArray);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            return null;
        }
    }*/


}