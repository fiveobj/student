package com.example.student.my.myatt;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.student.R;

import org.w3c.dom.Text;

public class attDialog extends Dialog implements View.OnClickListener {

    private TextView cancel_tatil;
    private ImageButton cacel,con;
    private String cancel_tatiltv;
    private IOnCancelListener cancelListener;
    private IOnConfirmListener confirmListener;


    public void setCancel_tatiltv(String cancel_tatiltv) {
        this.cancel_tatiltv = cancel_tatiltv;
    }

    public attDialog(@NonNull Context context) {
        super(context);
    }

    public attDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public void setCancel(IOnCancelListener cancelListener) {

        this.cancelListener=cancelListener;
    }
    public void setConfirm(IOnConfirmListener confirmListener){

        this.confirmListener=confirmListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cancel_attlayout);

        //设置弹窗的宽度
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p =getWindow().getAttributes();
        Point size = new Point();
        d.getSize(size);
        p.width = (int)(size.x);//是dialog的宽度为app界面的80%
        getWindow().setAttributes(p);

        cancel_tatil=(TextView)findViewById(R.id.cancelmatt_tv);
        cacel=(ImageButton)findViewById(R.id.myatt_cancel_btn);
        con=(ImageButton)findViewById(R.id.myattcon_btn);

        if(!TextUtils.isEmpty(cancel_tatiltv)){
            cancel_tatil.setText(cancel_tatiltv);
        }

        cacel.setOnClickListener(this);
        con.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.myatt_cancel_btn:
                if(cancelListener!=null){
                    cancelListener.onCancel(this);
                }
                dismiss();
                break;
            case R.id.myattcon_btn:
                if(confirmListener!=null){
                    confirmListener.onCancel(this);
                }
                dismiss();
                break;
        }
    }

    public interface IOnCancelListener{
        void onCancel(attDialog dialog);
    }

    public interface IOnConfirmListener{
        void onCancel(attDialog dialog);
    }
}
