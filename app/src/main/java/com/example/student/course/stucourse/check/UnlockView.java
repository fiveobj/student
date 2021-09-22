package com.example.student.course.stucourse.check;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.student.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UnlockView extends FrameLayout {

    //private static String backgroundColor = "01000000";

    private int view_width;//整个空间宽度
    private int btn_width;//按钮宽度,直径
    private int view_height;
    //  private ArrayList<int[]> btn_coord_list;
    private int[][] btn_coord;
    private List<CheckBox> checkBox_btns;

    /**
     * 密码
     */
    private List<Integer> password = new ArrayList<Integer>();
    private float x;//当前手指坐标
    private float y;

    private boolean isError = false;//当前密码是否正确
    private boolean isTouch = false;//是否正在操作

    /**
     * 正确密码.
     *  为空时,当前模式为捕捉用户输入(用于设置手势)
     *  不为空,当前模式为验证密码模式(用于解锁验证)
     */
    private String rightPwd;
    private OnUnlockListener onUnlockListener;
    private OnGetPwdListener onGetPwdListener;
    Handler handler;

    public void setRightPwd(String rightPwd){
        this.rightPwd=rightPwd;
    }

    public void setOnUnlockListener(OnUnlockListener onUnlockListener) {
        this.onUnlockListener = onUnlockListener;
    }

    public void setOnGetPwdListener(OnGetPwdListener onGetPwdListener) {
        this.onGetPwdListener = onGetPwdListener;
    }

    public UnlockView(@NonNull Context context) {
        super(context);
        this.password = new ArrayList();
        this.isError = false;
        this.isTouch = false;

        class NamelessClass_1 extends Handler {
            NamelessClass_1() {
            }

            public void handleMessage(Message msg) {
                switch(msg.what) {
                    case 1:
                        if (!UnlockView.this.isTouch) {
                            UnlockView.this.reset();
                        }
                    default:
                }
            }
        }

        this.handler = new NamelessClass_1();

        this.initView();
    }



    public UnlockView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
    }

    public UnlockView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.password = new ArrayList();
        this.isError = false;
        this.isTouch = false;

        class NamelessClass_1 extends Handler {
            NamelessClass_1() {
            }

            public void handleMessage(Message msg) {
                switch(msg.what) {
                    case 1:
                        if (!UnlockView.this.isTouch) {
                            UnlockView.this.reset();
                        }
                    default:
                }
            }
        }

        this.handler = new NamelessClass_1();
        initView();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }


    /**
     * 初始化view
     */
    private void initView() {
        //将9宫格按钮的布局文件加载进来
        inflate(getContext(), R.layout.cbheckboxlayout, this);
        setBackgroundColor(Color.parseColor("#01000000"));

        CheckBox unlock_cb_0 = (CheckBox) findViewById(R.id.unlock_cb_0);
        CheckBox unlock_cb_1 = (CheckBox) findViewById(R.id.unlock_cb_1);
        CheckBox unlock_cb_2 = (CheckBox) findViewById(R.id.unlock_cb_2);
        CheckBox unlock_cb_3 = (CheckBox) findViewById(R.id.unlock_cb_3);
        CheckBox unlock_cb_4 = (CheckBox) findViewById(R.id.unlock_cb_4);
        CheckBox unlock_cb_5 = (CheckBox) findViewById(R.id.unlock_cb_5);
        CheckBox unlock_cb_6 = (CheckBox) findViewById(R.id.unlock_cb_6);
        CheckBox unlock_cb_7 = (CheckBox) findViewById(R.id.unlock_cb_7);
        CheckBox unlock_cb_8 = (CheckBox) findViewById(R.id.unlock_cb_8);

        checkBox_btns = new ArrayList();
        checkBox_btns.add(unlock_cb_0);
        checkBox_btns.add(unlock_cb_1);
        checkBox_btns.add(unlock_cb_2);
        checkBox_btns.add(unlock_cb_3);
        checkBox_btns.add(unlock_cb_4);
        checkBox_btns.add(unlock_cb_5);
        checkBox_btns.add(unlock_cb_6);
        checkBox_btns.add(unlock_cb_7);
        checkBox_btns.add(unlock_cb_8);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        //在这里获取按钮的尺寸,view的尺寸等
        view_width = getWidth();
        view_height = getHeight();
        View lock_btn = findViewById(R.id.unlock_cb_0);
        btn_width = lock_btn.getWidth();

        //计算各按钮中点坐标
        int[] btn1_coord = {btn_width/2,btn_width/2};
        int[] btn2_coord = {view_width/2,btn_width/2};
        int[] btn3_coord = {view_width-(btn_width/2),btn_width/2};
        int[] btn4_coord = {btn_width/2,view_height/2};
        int[] btn5_coord = {view_width/2,view_height/2};
        int[] btn6_coord = {view_width-(btn_width/2),view_height/2};
        int[] btn7_coord = {btn_width/2,view_height-(btn_width/2)};
        int[] btn8_coord = {view_width/2,view_height-(btn_width/2)};
        int[] btn9_coord = {view_width-(btn_width/2),view_height-(btn_width/2)};

        //保存到一个二维数组中
        btn_coord = new int[9][2];
        btn_coord[0][0] = btn1_coord[0];
        btn_coord[0][1] = btn1_coord[1];

        btn_coord[1][0] = btn2_coord[0];
        btn_coord[1][1] = btn2_coord[1];

        btn_coord[2][0] = btn3_coord[0];
        btn_coord[2][1] = btn3_coord[1];

        btn_coord[3][0] = btn4_coord[0];
        btn_coord[3][1] = btn4_coord[1];

        btn_coord[4][0] = btn5_coord[0];
        btn_coord[4][1] = btn5_coord[1];

        btn_coord[5][0] = btn6_coord[0];
        btn_coord[5][1] = btn6_coord[1];

        btn_coord[6][0] = btn7_coord[0];
        btn_coord[6][1] = btn7_coord[1];

        btn_coord[7][0] = btn8_coord[0];
        btn_coord[7][1] = btn8_coord[1];

        btn_coord[8][0] = btn9_coord[0];
        btn_coord[8][1] = btn9_coord[1];

        //拦截Touch事件

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //当 当前密码不为空时,说明用户正在绘制手势,现在就要把手势按钮中间的连线画出来
        if (password!=null) {
            Paint paint=new Paint();
            //按钮之间的连接画线
            if (isError) {
                //输入错误时线的颜色
                paint.setColor(Color.RED);
            }else{
                //正常输入时线的颜色
                paint.setColor(Color.YELLOW);
            }
            paint.setAlpha(125);
            paint.setStrokeWidth(10.0F);
            //      canvas.drawLine(50, 50, 500, 500, paint);

            //绘制按钮间中间连线
            for (int i = 0; i < password.size(); i++) {
                if (i==password.size()-1) {
                    //最后一个,只有一个
                    if (x>0&&y>0) {
                        canvas.drawLine(btn_coord[password.get(i)-1][0], btn_coord[password.get(i)-1][1], x, y, paint);
                    }
                }else{
                    //不是第一个,也不是最后一个
                    canvas.drawLine(btn_coord[password.get(i)-1][0], btn_coord[password.get(i)-1][1], btn_coord[password.get(i+1)-1][0], btn_coord[password.get(i+1)-1][1], paint);
                }
            }
        }


        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {


        // Log.e("yuc", "X:"+e.getX());

        switch(e.getAction()) {
            case 0:
                this.isTouch = true;
                this.reset();
                break;
            case 1:
                this.isTouch = false;
                this.x = 0.0F;
                this.y = 0.0F;
                StringBuffer sb = new StringBuffer("");
                Iterator var9 = this.password.iterator();

                while(var9.hasNext()) {
                    Integer i = (Integer)var9.next();
                    sb.append(i);
                }

                if (TextUtils.isEmpty(this.rightPwd)) {
                    this.reset();
                    if (this.onGetPwdListener != null) {
                        this.onGetPwdListener.onSetting(sb.toString());
                    }
                } else {
                    if (sb.toString().equals(this.rightPwd)) {
                        this.isError = false;
                    } else {
                        this.isError = true;
                    }

                    if (!this.isError) {
                        if (this.onUnlockListener != null) {
                            this.onUnlockListener.onSuccess();
                        }
                    } else if (this.onUnlockListener != null) {
                        this.onUnlockListener.onError();
                    }

                    var9 = this.checkBox_btns.iterator();

                    while(var9.hasNext()) {
                        CheckBox checkBox = (CheckBox)var9.next();
                        checkBox.setEnabled(false);
                    }

                    if (this.isError) {
                        Message msg = new Message();
                        msg.what = 1;
                        this.handler.sendMessageDelayed(msg, 600L);
                    } else {
                        this.reset();
                    }
                }
                break;
            case 2:
                this.x = e.getX();
                this.y = e.getY();

                for(int i = 0; i < this.btn_coord.length; ++i) {
                    float dx = this.x - (float)this.btn_coord[i][0];
                    float dy = this.y - (float)this.btn_coord[i][1];
                    if (dx * dx + dy * dy <= (float)(this.btn_width * this.btn_width / 2) && !this.password.contains(i + 1)) {
                        this.password.add(i + 1);
                        ((CheckBox)this.checkBox_btns.get(i)).setChecked(true);
                    }
                }
        }
        invalidate();

        return true;

    }

    private void reset() {
        if (this.password.size() > 0) {
            this.password.clear();
            this.isError = false;
            Iterator var2 = this.checkBox_btns.iterator();

            while(var2.hasNext()) {
                CheckBox cb = (CheckBox)var2.next();
                cb.setEnabled(true);
                cb.setChecked(false);
            }

            this.invalidate();
        }

    }

    /**
     * 验证模式下,密码验证监听
     * @author Administrator
     */
    public interface OnUnlockListener{
        void onSuccess();
        void onError();
    }
    /**
     * 捕捉用户输入模式下,密码获取监听
     * @author Administrator
     */
    public interface OnGetPwdListener{
        void onSetting(String pwd);
    }
}
