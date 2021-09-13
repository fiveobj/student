package com.example.student.course.detail;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;

import com.example.student.R;

public class evaluateDialog extends Dialog implements View.OnClickListener {

    private EditText ed_text;
    private ImageButton ib_submit;

    private String text,submit;

    private IOnCanceListener canceListener;

    public void setText(String text) {
        this.text = text;
    }

    public void setSubmit(IOnCanceListener canceListener) {
        this.canceListener=canceListener;
    }

    public evaluateDialog(@NonNull Context context) {
        super(context);
    }

    public evaluateDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluate_layout);

        //设置弹窗的宽度
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p =getWindow().getAttributes();
        Point size = new Point();
        d.getSize(size);
        p.width = (int)(size.x);//是dialog的宽度为app界面的80%
        getWindow().setAttributes(p);

        //找到组件
        ed_text=findViewById(R.id.evaluate_edit);
        ib_submit=findViewById(R.id.evaluate_ib);

        //设置组件对象的text参数
        /*if(!TextUtils.isEmpty(text)){

        }*/

        ib_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.evaluate_ib:
                if(canceListener!=null){
                    canceListener.onCancel(this);
                }
                dismiss();
                break;

        }
    }
    public interface IOnCanceListener{
        void onCancel(evaluateDialog dialog);
    }
}
