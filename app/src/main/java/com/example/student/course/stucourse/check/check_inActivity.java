package com.example.student.course.stucourse.check;

import android.app.Activity;
import android.graphics.Color;
import android.icu.lang.UCharacterEnums;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.student.R;

public class check_inActivity extends Activity {


    private GraphicLockView unlock;
    private EditText et;
    private Button a,b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);

        //设置顶部状态栏为透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        unlock=(GraphicLockView) findViewById(R.id.unlock);


        unlock.setOnGraphicLockListener(new GraphicLockView.OnGraphicLockListener() {
            @Override
            public void LockSuccess(int what, String password) {


                    GraphicLockView.mPassword="123654";
                    //Toast.makeText(check_inActivity.this,"再次绘制",Toast.LENGTH_SHORT).show();
                    SPUtil.put(check_inActivity.this,"PASSWORD",password);
                    check_inActivity.this.finish();
                    Toast.makeText(check_inActivity.this,"签到成功",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void LockFailure() {
                Toast.makeText(check_inActivity.this,"手势错误",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void LockShort() {
                Toast.makeText(check_inActivity.this,"4个点",Toast.LENGTH_SHORT).show();
            }
        });
       // unlock.setRightPwd((String)null);
       // unlock.setRightPwd("123654");

        /*unlock.setOnUnlockListener(new UnlockView.OnUnlockListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(check_inActivity.this,"密码正确",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError() {
                Toast.makeText(check_inActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
            }
        });

       unlock.setOnGetPwdListener(new UnlockView.OnGetPwdListener() {
            @Override
            public void onSetting(String pwd) {
                Toast.makeText(check_inActivity.this,"密码"+pwd,Toast.LENGTH_SHORT).show();
            }
        });*/
    }
    /*public void setting(View view) {
        this.unlock.setRightPwd((String)null);
    }

    public void verify(View view) {
        String rightPwd = this.et.getText().toString();
        if (TextUtils.isEmpty(rightPwd)) {
            Toast.makeText(this, "请输入正确密码", Toast.LENGTH_SHORT).show();
        } else {
            this.unlock.setRightPwd(rightPwd);
        }
    }*/

}