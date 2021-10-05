package com.example.student.my.id;

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
import com.example.student.course.addclassDialog;

public class myid_changeDialog extends Dialog implements View.OnClickListener{

    private EditText ed_text;
    private ImageButton im_submit;
    private IOnCanceListener canceListener;
    public void setSubmit(IOnCanceListener canceListener) {
        this.canceListener = canceListener;
    }

    public myid_changeDialog(@NonNull Context context) {
        super(context);
    }
    public myid_changeDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.myidchange_ib:
                if(canceListener!=null){
                    canceListener.onCancel(this);
                }
                dismiss();
                break;

        }
    }

    public EditText getEd_text() {
        return ed_text;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myid_changelayout);

        //设置弹窗的宽度
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p =getWindow().getAttributes();
        Point size = new Point();
        d.getSize(size);
        p.width = (int)(size.x);//是dialog的宽度为app界面的80%
        getWindow().setAttributes(p);

        //找到组件
        ed_text=findViewById(R.id.myidchange_ed);
        im_submit=findViewById(R.id.myidchange_ib);

        im_submit.setOnClickListener(this);
    }


    public interface IOnCanceListener{
        void onCancel(myid_changeDialog dialog);
    }

}
