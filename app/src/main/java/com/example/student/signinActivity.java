package com.example.student;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.student.customclass.OkHttpClass;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.agora.base.network.RetrofitManager;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class signinActivity extends AppCompatActivity {

    private EditText id,pass;
    private ImageButton signin;
    private TextView login;
    private String myid,mypass,path,result;
    private Context context;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        //??????????????????????????????
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        context=this;
        //??????????????????
        login=(TextView)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(signinActivity.this,stuloginActivity.class);
                startActivityForResult(intent,1);
            }
        });

        //????????????????????????
        id=(EditText)findViewById(R.id.signin_id);
        pass=(EditText)findViewById(R.id.signin_pass);

        signin=(ImageButton)findViewById(R.id.signin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginPost();
                //new Thread(networkTask).start();
            }
        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
            Log.i("mylog", "???????????????-->" + val);
            // TODO
            // UI??????????????????????????????
        }
    };

    /**
     * ??????????????????????????????
     */
    Runnable networkTask = new Runnable() {

        @Override
        public void run() {
            // TODO
            // ??????????????? http request.????????????????????????
            myid=id.getText().toString();
            mypass=pass.getText().toString();
            OkHttpClass tools=new OkHttpClass();
            String result=tools.Login(myid,mypass);

            Message msg = new Message();
            Bundle data = new Bundle();
            data.putString("value", "????????????");
            msg.setData(data);
            handler.sendMessage(msg);
        }
    };
    public void loginPost(){
        myid=id.getText().toString();
        mypass=pass.getText().toString();

        //getData(myid,mypass);

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClass tools=new OkHttpClass();
                String result=tools.Login(myid,mypass);
                Log.d("result",result);
                try {
                    /*response=okHttpClient.newCall(request).execute();
                    List<String> headers1=response.priorResponse().networkResponse().headers("Set-Cookie");
                    String cc="";
                    for(String c:headers1){
                        String s=c.split(";")[0];
                        cc=cc+s+"";
                    }
                    Log.d("Cookie",cc);*/
                    JSONObject jsonObject=new JSONObject(result);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Toast.makeText(signinActivity.this,str,Toast.LENGTH_SHORT).show();
                            try {
                                if(jsonObject.getString("rspMsg").equals("????????????")){
                                    Intent intent=new Intent(signinActivity.this,MainActivity.class);
                                    //intent.putExtra("username",stuNo);
                                    signinActivity.this.finish();
                                    startActivity(intent);
                                }else if(myid.isEmpty()){
                                    Toast.makeText(signinActivity.this,"???????????????",Toast.LENGTH_SHORT).show();
                                }else if(mypass.isEmpty()){
                                    Toast.makeText(signinActivity.this,"???????????????",Toast.LENGTH_SHORT).show();
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
        }).start();


        }
        //path="http://1.116.114.32:18081/student/login";
        //new postTask().execute(myid,mypass,path);




    //okhttp3
    private void getData(String stuNo,String password){
        //CookieJar cookieJar=new PersistentCookieJar(new SetCookieCache(),new SharedPrefsCookiePersistor(context));
        PersistentCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(getApplicationContext()));
        final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();
        OkHttpClient okHttpClient= new OkHttpClient.Builder().connectTimeout(8000, TimeUnit.MILLISECONDS).cookieJar(new CookieJar() {
            @Override
            public void saveFromResponse(@NotNull HttpUrl httpUrl, @NotNull List<Cookie> list) {
                cookieStore.put(httpUrl.host(), list);
            }

            @NotNull
            @Override
            public List<Cookie> loadForRequest(@NotNull HttpUrl httpUrl) {
                List<Cookie> cookies = cookieStore.get(httpUrl.host());
                //for(Cookie cookie:cookies)
                //Log.e("cookie","c"+cookie);
                return cookies != null ? cookies : new ArrayList<Cookie>();
            }
        }).build();
        FormBody.Builder builder=new FormBody.Builder();
        final RequestBody requestBody=builder.add("username",stuNo).add("password",password).build();
        final Request request=new Request.Builder().url("http://1.116.114.32:18081/student/login").post(requestBody).build();
        Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("TAG","???????????????????????????");
                Log.e("TAG","??????????????????");
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
                    /*response=okHttpClient.newCall(request).execute();
                    List<String> headers1=response.priorResponse().networkResponse().headers("Set-Cookie");
                    String cc="";
                    for(String c:headers1){
                        String s=c.split(";")[0];
                        cc=cc+s+"";
                    }
                    Log.d("Cookie",cc);*/
                    JSONObject jsonObject=new JSONObject(str);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Toast.makeText(signinActivity.this,str,Toast.LENGTH_SHORT).show();
                            try {
                                if(jsonObject.getString("rspMsg").equals("????????????")){
                                    Intent intent=new Intent(signinActivity.this,MainActivity.class);
                                    intent.putExtra("username",stuNo);
                                    signinActivity.this.finish();
                                    startActivity(intent);
                                }else if(myid.isEmpty()){
                                    Toast.makeText(signinActivity.this,"???????????????",Toast.LENGTH_SHORT).show();
                                }else if(mypass.isEmpty()){
                                    Toast.makeText(signinActivity.this,"???????????????",Toast.LENGTH_SHORT).show();
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
            //???????????????????????????????????????
            String name=params[0].toString();
            String pass=params[1].toString();
            String path=params[2].toString();
            try {
                //???????????????get???????????????????????????
                URL url=new URL(path);
                //??????????????????
                HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                //Toast.makeText(signinActivity.this, "????????????", Toast.LENGTH_SHORT).show();
                //??????????????????
                conn.setRequestMethod("POST");
                //????????????????????????
                conn.setConnectTimeout(15000);
                //conn.setReadTimeout(15000);
                //??????????????????????????????????????????
                String s="username="+name+"&password"+pass;
                //???????????????
                //conn.setRequestProperty("Content-Length",s.length()+"");//???????????????
                conn.setRequestProperty("Content-Type","application/json;charset=UTF-8");//?????????????????????
                //??????????????????????????????
                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.setUseCaches(false);
                //????????????????????????????????????
                //OutputStream os=conn.getOutputStream();
                //os.write(s.getBytes());

                StringBuffer stringBuffer=new StringBuffer();
                stringBuffer.append(s);
                conn.getOutputStream().write(s.getBytes());
                conn.getOutputStream().flush();
                conn.getOutputStream().close();


                if(conn.getResponseCode()==200){
                    //Toast.makeText(signinActivity.this, "????????????", Toast.LENGTH_SHORT).show();
                    //???io??????web????????????????????????
                    InputStream is=conn.getInputStream();
                    //?????????????????????
                    BufferedReader reader=new BufferedReader(new InputStreamReader(is));

                    StringBuffer buffer=new StringBuffer();

                    //????????????????????????
                    String str="";
                    while ((str=reader.readLine())!=null){
                        buffer.append(str);
                    }
                    is.close();
                    reader.close();
                    conn.disconnect();
                    //?????????????????????????????????
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
            //??????Android studio???web??????????????????????????????
            String s= (String) o;
            //??????Android studio???web??????????????????????????????
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
            //????????????
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
            // ????????????????????????byte??????
//            byte[] postData = params.getBytes();
            // ????????????URL??????
            URL url = new URL(baseUrl);
            // ????????????HttpURLConnection??????
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            // ????????????????????????
            urlConn.setConnectTimeout(5 * 1000);
            //?????????????????????????????????
            urlConn.setReadTimeout(5 * 1000);
            // Post?????????????????????????????? ??????false
            urlConn.setDoOutput(true);
            //???????????????????????? ?????????true
            urlConn.setDoInput(true);
            // Post????????????????????????
            urlConn.setUseCaches(false);
            // ?????????Post??????
            urlConn.setRequestMethod("POST");
            //?????????????????????????????????????????????
            urlConn.setInstanceFollowRedirects(true);
            //????????????Content-Type
//            urlConn.setRequestProperty("Content-Type", "application/json");//post????????????????????????
            // ????????????
            urlConn.connect();

            // ??????????????????
            PrintWriter dos = new PrintWriter(urlConn.getOutputStream());
            dos.write(params);
            dos.flush();
            dos.close();
            // ????????????????????????
            if (urlConn.getResponseCode() == 200) {
                // ?????????????????????
                String result = streamToString(urlConn.getInputStream());
                Log.e(TAG, "Post?????????????????????result--->" + result);
            } else {
                Log.e(TAG, "Post??????????????????");
            }
            // ????????????
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