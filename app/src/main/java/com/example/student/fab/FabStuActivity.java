package com.example.student.fab;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.student.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;
import java.util.List;

public class FabStuActivity extends AppCompatActivity implements OnChartValueSelectedListener {

    private LinearLayout tv1,tv2,tv3;
    private BarChart chart1;
    private PieChart chart2;
    private ImageButton back,next;
    private int x=2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab_stu);

        //??????????????????????????????
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        back=(ImageButton)findViewById(R.id.fab_stu_back);
        next=(ImageButton)findViewById(R.id.fab_stu_next);
        tv1=(LinearLayout) findViewById(R.id.stu_layout1);
        tv2=(LinearLayout)findViewById(R.id.stu_layout2);
        tv3=(LinearLayout)findViewById(R.id.stu_layout3);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FabStuActivity.this.finish();
            }
        });
        tv2.setVisibility(View.INVISIBLE);
        tv3.setVisibility(View.INVISIBLE);
        doAlphaAndTransAnimation(tv1);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(x==2){
                    tv2.setVisibility(View.VISIBLE);
                    tv1.setVisibility(View.INVISIBLE);
                    doAlphaAndTransAnimation(tv2);
                    x--;
                }else if(x==1){
                    tv2.setVisibility(View.INVISIBLE);
                    tv3.setVisibility(View.VISIBLE);
                    next.setVisibility(View.INVISIBLE);
                    doAlphaAndTransAnimation(tv3);
                    x--;
                }
            }
        });





        chart1=(BarChart)findViewById(R.id.chart1);
        chart2=(PieChart)findViewById(R.id.chart2);
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
        setChart1Data(7,16);
        setChart2Data(5,16);


        chart1.setOnChartValueSelectedListener(this);
        chart2.animateY(1400, Easing.EaseInOutQuad);
        chart2.setOnChartValueSelectedListener(this);
    }
    /**
     * ????????????????????????
     */
    protected void initChartStyle(){
        //------------------------------chart1-----------------------------------------------------
        //????????????
        chart1.getDescription().setEnabled(false);
        //???????????????????????????????????????
        chart1.setMaxVisibleValueCount(60);

        //?????????????????????x??????y????????????
        chart1.setPinchZoom(false);

        chart1.setDrawBarShadow(false);
        //????????????????????????
        chart1.setDrawGridBackground(false);

        //??????X?????????
        XAxis xAxis = chart1.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        chart1.getAxisLeft().setDrawGridLines(false);

        //------------------------------chart2-----------------------------------------------------
        //?????????????????????
        chart2.setUsePercentValues(true);
        chart2.getDescription().setEnabled(false);
        chart2.setExtraOffsets(5, 10, 5, 5);

        //????????????????????????0???????????????
        chart2.setDragDecelerationFrictionCoef(0.95f);

        //????????????????????????
        chart2.setCenterText(generateCenterSpannableText());
        chart2.setDrawCenterText(true);
        //?????????????????????????????????
        chart2.setDrawHoleEnabled(true);
        //??????????????????????????????????????????100
        chart2.setHoleRadius(58f);
        chart2.setHoleColor(Color.WHITE);
        //????????????????????????
        chart2.setTransparentCircleColor(Color.WHITE);
        chart2.setTransparentCircleAlpha(110);
        chart2.setTransparentCircleRadius(61f);

        //??????????????????
        chart2.setRotationAngle(0);
        chart2.setRotationEnabled(true);
        chart2.setHighlightPerTapEnabled(true);
    }

    /**
     * ???????????????????????????
     *
     * @return
     */
    private SpannableString generateCenterSpannableText() {
        SpannableString s = new SpannableString("??????????????????");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, s.length(), 0);
        //s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
        //s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
        //s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0);
        //s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);
        //s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);
        return s;
    }
    /**
     * ?????????????????? ?????? ??????
     */
    protected void initChartLabel() {
        //------------------------chart1-------------------------------------
        //??????????????? ??????
        chart1.getLegend().setEnabled(false);


        //------------------------chart2-------------------------------------
        Legend l = chart2.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        chart2.setEntryLabelColor(Color.WHITE);
        chart2.setEntryLabelTextSize(12f);
    }
    /**
     * ??????????????????
     *
     * @param count ????????????????????????
     * @param range
     */
    protected void setChart1Data(int count, float range) {
        List<BarEntry> values = new ArrayList<>();
        //???????????????
        for (int i = 1; i < count+1; i++) {
            float multi = (range -10);
            float val = (float) (Math.random() * multi) ;
            values.add(new BarEntry(i, val));
        }

        BarDataSet set1;
        if (chart1.getData() != null && chart1.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) chart1.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart1.getData().notifyDataChanged();
            chart1.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(values, "Data Set");
            set1.setColors(ColorTemplate.VORDIPLOM_COLORS);
            set1.setDrawValues(false);

            List<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            chart1.setData(data);
            chart1.setFitBars(true);
        }
        chart1.invalidate();
    }

    /**
     * ??????????????????
     *
     * @param count ????????????????????????
     * @param range
     */

    protected void setChart2Data(int count, float range) {
        List<PieEntry> entries = new ArrayList<>();
        /*for (int i = 0; i < count; i++) {
            //???????????????
            String[] parties = new String[]{
                    ">=90%", ">=80%", ">=70%", ">=60%", "<60%"
            };
            entries.add(new PieEntry((float) ((Math.random() * range) + range / 5), parties[i % parties.length], getResources().getDrawable(R.drawable.ic_star_green)));
        }*/
        entries.add(new PieEntry( (float)20.9, ">=90%", getResources().getDrawable(R.drawable.ic_star_green)));
        entries.add(new PieEntry( (float)39.1, ">=80%", getResources().getDrawable(R.drawable.ic_star_green)));
        entries.add(new PieEntry( (float)29.8, ">=70%", getResources().getDrawable(R.drawable.ic_star_green)));
        entries.add(new PieEntry( (float)11.2, ">=60%", getResources().getDrawable(R.drawable.ic_star_green)));
        entries.add(new PieEntry( (float)14, "<60%", getResources().getDrawable(R.drawable.ic_star_green)));

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
        data.setValueFormatter(new PercentFormatter(chart2));
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        chart2.setData(data);

        // undo all highlights
        chart2.highlightValues(null);
        chart2.invalidate();
    }
    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}