package com.example.student.customclass;

import android.app.Activity;
import android.util.Log;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

public class OkHttpClass {
    //private PersistentCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(getApplicationContext()));
    private static final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();
    private static OkHttpClient okHttpClient= new OkHttpClient.Builder().connectTimeout(8000, TimeUnit.MILLISECONDS).cookieJar(new CookieJar() {
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

    public OkHttpClass(){

    }
    //------------------------------post------------------------------------------------------------
    public String Login(String stuNo,String password ){
        FormBody.Builder builder=new FormBody.Builder();
        RequestBody requestBody=builder.add("username",stuNo).add("password",password).build();
        Request request=new Request.Builder().url("http://1.116.114.32:18081/student/login").post(requestBody).build();
        try (Response response=okHttpClient.newCall(request).execute()){
            if(response.isSuccessful()){
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("cookic",cookieStore.toString());
        return "FW";
    }

    public String addClass(String invitation_code){
        FormBody.Builder builder=new FormBody.Builder();
        RequestBody requestBody=builder.add("username",invitation_code).build();
        return "FW";
    }




    //------------------------------get------------------------------------------------------------
    public String courseList(){
        Request.Builder builder=new Request.Builder().url("http://1.116.114.32:18081/student/trainingCourse");
        builder.method("GET",null);
        Request request=builder.build();
        try(Response response=okHttpClient.newCall(request).execute()){
            if(response.isSuccessful()){
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("11111",e.toString());
        }
        return "FW";
    }

}
