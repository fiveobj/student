package com.example.student.course.job;

import androidx.annotation.RequiresApi;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.student.R;


import com.example.student.calmaster.base.activity.BaseActivity;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;


import java.util.HashMap;
import java.util.Map;

public class ajobsubmintActivity extends BaseActivity implements
        CalendarView.OnCalendarSelectListener,
        CalendarView.OnYearChangeListener,
        View.OnClickListener{

    TextView mTextMonthDay;

    TextView mTextYear;

    TextView mTextLunar;

    TextView mTextCurrentDay;

    CalendarView mCalendarView;

    RelativeLayout mRelativeTool;

    RelativeLayout mRecyclerView;
    private int mYear;
    CalendarLayout mCalendarLayout;


    private FragmentManager manager;
    private FragmentTransaction transaction;
    private EditText editText;
    private TextView subtv;
    private  ImageButton back;
    public static void show(Context context) {
        context.startActivity(new Intent(context, ajobsubmintActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ajobsubmint;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("SetTextI18n")
    @Override
    protected void initView() {
        setStatusBarDarkMode();
        mTextMonthDay = findViewById(R.id.tv_month_day);
        mTextYear = findViewById(R.id.tv_year);
        mTextLunar = findViewById(R.id.tv_lunar);
        mRelativeTool = findViewById(R.id.rl_tool);
        mCalendarView = findViewById(R.id.calendarView);
        mTextCurrentDay = findViewById(R.id.tv_current_day);
        mTextMonthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mCalendarLayout.isExpand()) {
                    mCalendarLayout.expand();
                    return;
                }
                mCalendarView.showYearSelectLayout(mYear);
                mTextLunar.setVisibility(View.GONE);
                mTextYear.setVisibility(View.GONE);
                mTextMonthDay.setText(String.valueOf(mYear));
            }
        });
        findViewById(R.id.fl_current).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarView.scrollToCurrent();
            }
        });

        mCalendarLayout = findViewById(R.id.calendarLayout);
        mCalendarView.setOnYearChangeListener(this);
        mCalendarView.setOnCalendarSelectListener(this);
        mTextYear.setText(String.valueOf(mCalendarView.getCurYear()));
        mYear = mCalendarView.getCurYear();
        mTextMonthDay.setText(mCalendarView.getCurMonth() + "月" + mCalendarView.getCurDay() + "日");
        mTextLunar.setText("今日");
        mTextCurrentDay.setText(String.valueOf(mCalendarView.getCurDay()));
        mRecyclerView = findViewById(R.id.recyclerView);

        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //mRecyclerView.addItemDecoration(new GroupItemDecoration<String, Article>());
        //mRecyclerView.setAdapter(new ArticleAdapter(this));
        //mRecyclerView.notifyDataSetChanged();


        //manager=getFragmentManager();
        //transaction=manager.beginTransaction();
        //transaction.add(R.id.recyclerView,new submintjobitemFragment());
        //transaction.commit();

        //设置顶部状态栏为透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        editText=findViewById(R.id.answer_edit_item);
        subtv=findViewById(R.id.subjob_btntv);
        back=findViewById(R.id.ajobsubmit_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ajobsubmintActivity.this.finish();
            }
        });
    }

    @Override
    protected void initData() {
        int year = mCalendarView.getCurYear();
        int month = mCalendarView.getCurMonth();
        int day=mCalendarView.getCurDay();
        Map<String, Calendar> map = new HashMap<>();
        map.put(getSchemeCalendar(year, month, day-1, 0xFF40db25, "假").toString(),
                getSchemeCalendar(year, month, day-1, 0xFF40db25, "假"));
        map.put(getSchemeCalendar(year, month, 6, 0xFFe69138, "事").toString(),
                getSchemeCalendar(year, month, 6, 0xFFe69138, "事"));
        map.put(getSchemeCalendar(year, month, 9, 0xFFdf1356, "议").toString(),
                getSchemeCalendar(year, month, 9, 0xFFdf1356, "议"));
        map.put(getSchemeCalendar(year, month, 13, 0xFFedc56d, "记").toString(),
                getSchemeCalendar(year, month, 13, 0xFFedc56d, "记"));
        map.put(getSchemeCalendar(year, month, 14, 0xFFedc56d, "记").toString(),
                getSchemeCalendar(year, month, 14, 0xFFedc56d, "记"));
        map.put(getSchemeCalendar(year, month, 15, 0xFFaacc44, "假").toString(),
                getSchemeCalendar(year, month, 15, 0xFFaacc44, "假"));
        map.put(getSchemeCalendar(year, month, 18, 0xFFbc13f0, "记").toString(),
                getSchemeCalendar(year, month, 18, 0xFFbc13f0, "记"));
        map.put(getSchemeCalendar(year, month, 25, 0xFF13acf0, "假").toString(),
                getSchemeCalendar(year, month, 25, 0xFF13acf0, "假"));
        map.put(getSchemeCalendar(year, month, 27, 0xFF13acf0, "多").toString(),
                getSchemeCalendar(year, month, 27, 0xFF13acf0, "多"));

        map.put(getSchemeCalendar(year, month, day-2, 0xFF40db25, "假").toString(),
                getSchemeCalendar(year, month, day-2, 0xFF40db25, "假"));
        map.put(getSchemeCalendar(year, month, day-3, 0xFF40db25, "假").toString(),
                getSchemeCalendar(year, month, day-3, 0xFF40db25, "假"));
        map.put(getSchemeCalendar(year, month, day-4, 0xFF40db25, "假").toString(),
                getSchemeCalendar(year, month, day-4, 0xFF40db25, "假"));
        //此方法在巨大的数据量上不影响遍历性能，推荐使用
        mCalendarView.setSchemeDate(map);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajobsubmint);



        //manager=getFragmentManager();
        //transaction=manager.beginTransaction();
        //transaction.add(R.id.recyclerView,new submintjobitemFragment());
        //transaction.commit();

        initView();
        initData();

    }




    @Override
    public void onClick(View v) {

    }

    private Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);//如果单独标记颜色、则会使用这个颜色
        calendar.setScheme(text);
        return calendar;
    }

    @Override
    public void onCalendarOutOfRange(Calendar calendar) {

    }


    @Override
    public void onYearChange(int year) {
        mTextMonthDay.setText(String.valueOf(year));
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onCalendarSelect(Calendar calendar, boolean isClick) {
        mTextLunar.setVisibility(View.VISIBLE);
        mTextYear.setVisibility(View.VISIBLE);
        mTextMonthDay.setText(calendar.getMonth() + "月" + calendar.getDay() + "日");
        mTextYear.setText(String.valueOf(calendar.getYear()));
        mTextLunar.setText(calendar.getLunar());
        mYear = calendar.getYear();

        int num=mCalendarView.getCurDay()-calendar.getDay();
        switch (num){
            case 0:
                editText.setText("");
                break;
            case 1:
                editText.setText("dhakjhfkjbdvkabdvkabvjbdakvjbsjdv");
                break;
            case 2:
                editText.setText("dhakjhfkjbdvkvjbdakvjbsjdv");
                break;
            case 3:
                editText.setText("dhaksjdv");
                break;
            case 4:
                editText.setText("dhakjhfkjbdvkabdvkabv");
                break;
            default:
                editText.setText("");
                break;
        }
        if(TextUtils.isEmpty(editText.getText())){
            subtv.setText("提     交");
            editText.setFocusableInTouchMode(true);
        }else{
            subtv.setText("已提交");
            editText.setFocusableInTouchMode(false);
        }
       /*if(calendar.getDay()==mCalendarView.getCurDay()-1){
           editText.setText("dhakjhfkjbdvkabdvkabvjbdakvjbsjdv");
           if(TextUtils.isEmpty(editText.getText())){
               subtv.setText("提     交");
           }else{
               subtv.setText("已提交");
               editText.setFocusableInTouchMode(false);
           }
       }
        if(calendar.getDay()==mCalendarView.getCurDay()-2){
            editText.setText("dvdzvs");
            if(TextUtils.isEmpty(editText.getText())){
                subtv.setText("提     交");
            }else{
                subtv.setText("已提交");
                editText.setFocusableInTouchMode(false);
            }
        }
        if(calendar.getDay()==mCalendarView.getCurDay()-3){
            editText.setText("bfxb");
            if(TextUtils.isEmpty(editText.getText())){
                subtv.setText("提     交");
            }else{
                subtv.setText("已提交");
                editText.setFocusableInTouchMode(false);
            }
        }
        if(calendar.getDay()==mCalendarView.getCurDay()-4){
            editText.setText("xvbxv");
            if(TextUtils.isEmpty(editText.getText())){
                subtv.setText("提     交");
            }else{
                subtv.setText("已提交");
                editText.setFocusableInTouchMode(false);
            }
        }
        if(calendar.getDay()==mCalendarView.getCurDay()-5){
            if(TextUtils.isEmpty(editText.getText())){
                subtv.setText("提     交");
            }else{
                subtv.setText("已提交");
                editText.setFocusableInTouchMode(false);
            }
        }*/
    }
}