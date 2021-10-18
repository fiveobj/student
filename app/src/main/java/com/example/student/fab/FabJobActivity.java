package com.example.student.fab;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.student.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;
import java.util.List;

public class FabJobActivity extends AppCompatActivity implements OnChartValueSelectedListener {

    private LinearLayout tv1,tv2;
    private PieChart chart1;
    private ImageButton back,next;
    private int x=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab_job);

        //设置顶部状态栏为透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        next=(ImageButton)findViewById(R.id.fab_job_next);
        back=(ImageButton)findViewById(R.id.fab_job_back);
        tv1=(LinearLayout)findViewById(R.id.fab_job_tv1);
        tv2=(LinearLayout)findViewById(R.id.fab_job_tv2);
        chart1=(PieChart)findViewById(R.id.job_chart1);


        tv2.setVisibility(View.INVISIBLE);
        doAlphaAndTransAnimation(tv1);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FabJobActivity.this.finish();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv1.setVisibility(View.INVISIBLE);
                tv2.setVisibility(View.VISIBLE);
                doAlphaAndTransAnimation(tv2);
            }
        });

        initView();

    }

    private void doAlphaAndTransAnimation(View view){
        ObjectAnimator alpha=ObjectAnimator.ofFloat(view,"alpha",0,1);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(view, "translationY",  200, 0);
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.play(alpha).with(animatorY);
        animatorSet.setDuration(2000).start();


    }

    protected void initView(){
        initChartStyle();
        initChartLabel();

        setChart2Data(5,16);



        chart1.animateY(1400, Easing.EaseInOutQuad);
        chart1.setOnChartValueSelectedListener(this);
    }

    protected void initChartStyle(){
        //使用百分百显示
        chart1.setUsePercentValues(true);
        chart1.getDescription().setEnabled(false);
        chart1.setExtraOffsets(5, 10, 5, 5);

        //设置拖拽的阻尼，0为立即停止
        chart1.setDragDecelerationFrictionCoef(0.95f);

        //设置图标中心文字
        chart1.setCenterText(generateCenterSpannableText());
        chart1.setDrawCenterText(true);
        //设置图标中心空白，空心
        chart1.setDrawHoleEnabled(true);
        //设置空心圆的弧度百分比，最大100
        chart1.setHoleRadius(58f);
        chart1.setHoleColor(Color.WHITE);
        //设置透明弧的样式
        chart1.setTransparentCircleColor(Color.WHITE);
        chart1.setTransparentCircleAlpha(110);
        chart1.setTransparentCircleRadius(61f);

        //设置可以旋转
        chart1.setRotationAngle(0);
        chart1.setRotationEnabled(true);
        chart1.setHighlightPerTapEnabled(true);
    }

    /**
     * 生成饼图中间的文字
     *
     * @return
     */
    protected SpannableString generateCenterSpannableText() {
        SpannableString s = new SpannableString("本周开会时间统计");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, s.length(), 0);
        //s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
        //s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
        //s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0);
        //s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);
        //s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);
        return s;
    }

    protected  void initChartLabel(){
        Legend l = chart1.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        chart1.setEntryLabelColor(Color.WHITE);
        chart1.setEntryLabelTextSize(12f);
    }

    /**
     * 设置图表数据
     *
     * @param count 柱状图中柱的数量
     * @param range
     */

    protected void setChart2Data(int count, float range) {
        List<PieEntry> entries = new ArrayList<>();
        /*for (int i = 0; i < count; i++) {
            //设置数据源
            String[] parties = new String[]{
                    ">=90%", ">=80%", ">=70%", ">=60%", "<60%"
            };
            entries.add(new PieEntry((float) ((Math.random() * range) + range / 5), parties[i % parties.length], getResources().getDrawable(R.drawable.ic_star_green)));
        }*/
        entries.add(new PieEntry( (float)20.9, "早上8：00-12：00", getResources().getDrawable(R.drawable.ic_star_green)));
        entries.add(new PieEntry( (float)39.1, "中午12：00-17：00", getResources().getDrawable(R.drawable.ic_star_green)));
        entries.add(new PieEntry( (float)29.8, "晚上17：00-22：00", getResources().getDrawable(R.drawable.ic_star_green)));

        PieDataSet dataSet = new PieDataSet(entries, "Election Results");
        dataSet.setDrawIcons(false);
        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 90));
        dataSet.setSelectionShift(5f);

        List<Integer> colors = new ArrayList<>();
        for (int c : ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(c);
        }
        for (int c : ColorTemplate.JOYFUL_COLORS) {
            colors.add(c);
        }
        for (int c : ColorTemplate.COLORFUL_COLORS) {
            colors.add(c);
        }
        for (int c : ColorTemplate.LIBERTY_COLORS) {
            colors.add(c);
        }
        for (int c : ColorTemplate.PASTEL_COLORS) {
            colors.add(c);
        }
        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter(chart1));
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        chart1.setData(data);

        // undo all highlights
        chart1.highlightValues(null);
        chart1.invalidate();
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}