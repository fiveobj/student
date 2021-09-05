package com.example.student.customclass;

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

public class addclassDialog extends Dialog implements View.OnClickListener{

    private EditText ed_text;
    private ImageButton im_submit;
    private String submit;

    private IOnCanceListener canceListener;

    public void setSubmit(IOnCanceListener canceListener) {
        this.canceListener = canceListener;
    }

    public addclassDialog(@NonNull Context context) {
        super(context);
    }

    public addclassDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addclass_ib:
                if(canceListener!=null){
                    canceListener.onCancel(this);
                }
                dismiss();
                break;

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addclass_layout);

        //设置弹窗的宽度
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p =getWindow().getAttributes();
        Point size = new Point();
        d.getSize(size);
        p.width = (int)(size.x);//是dialog的宽度为app界面的80%
        getWindow().setAttributes(p);

        //找到组件
        ed_text=findViewById(R.id.addclass_ed);
        im_submit=findViewById(R.id.addclass_ib);


        im_submit.setOnClickListener(this);
    }


    public interface IOnCanceListener{
        void onCancel(addclassDialog dialog);
    }
}
