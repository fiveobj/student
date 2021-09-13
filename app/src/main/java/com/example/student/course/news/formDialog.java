package com.example.student.course.news;

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

public class formDialog extends Dialog implements View.OnClickListener {

    private TextView tv_title,tv_detail;
    private ImageButton ib_close;

    private String title,detail,close;

    private IOnCanceListener canceListener;

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setClose(IOnCanceListener canceListener) {
        this.canceListener=canceListener;
    }

    public formDialog(@NonNull Context context) {
        super(context);
    }

    public formDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inform_detail_layout);

        //设置弹窗的宽度
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p =getWindow().getAttributes();
        Point size = new Point();
        d.getSize(size);
        p.width = (int)(size.x);//是dialog的宽度为app界面的80%
        getWindow().setAttributes(p);

        //找到组件
        tv_title=findViewById(R.id.inform_title);
        tv_detail=findViewById(R.id.inform_detail);
        ib_close=findViewById(R.id.inform_close);

        //设置组件对象的text参数
        if (!TextUtils.isEmpty(title)){
            tv_title.setText(title);
        }
        if (!TextUtils.isEmpty(detail)){
            tv_detail.setText(detail);
        }

        ib_close.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.inform_close:
                if(canceListener!=null){
                    canceListener.onCancel(this);
                }
                dismiss();
                break;

        }
    }

    public interface IOnCanceListener{
        void onCancel(formDialog dialog);
    }
}
