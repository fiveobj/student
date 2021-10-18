package com.example.student.my.resume;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.student.R;
import com.example.student.customclass.OkHttpClass;
import com.example.student.my.id.myidActivity;
import com.example.student.my.id.myid_changeDialog;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BadPdfFormatException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.util.TimeZone.getTimeZone;

public class people_resume_changeActivity extends AppCompatActivity {
    private ImageButton back,resume_change,add1,add2;
    private RelativeLayout picture,phone,method;
    private LinearLayout edu,job;
    private ImageView pictureim;
    private Context context;
    private String path;
    private TextView phonetv;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_resume_change);

        //设置顶部状态栏为透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        back=(ImageButton)findViewById(R.id.myresume_peo_change_back);
        resume_change=(ImageButton)findViewById(R.id.mysume_change);
        add1=(ImageButton)findViewById(R.id.resume_add);
        add2=(ImageButton)findViewById(R.id.resume_add1);
        picture=(RelativeLayout)findViewById(R.id.resume_change_picture);
        phone=(RelativeLayout)findViewById(R.id.resume_change_phone);
        method=(RelativeLayout)findViewById(R.id.myresume_change_method);
        pictureim=(ImageView)findViewById(R.id.resume_change_picturetm);
        edu=(LinearLayout)findViewById(R.id.resume_change_edulayout);
        job=(LinearLayout)findViewById(R.id.resume_change_joblayout);
        phonetv=(TextView)findViewById(R.id.resume_change_phonetv);
        context=this;



        setLinsteners();
    }


    private void setLinsteners(){
        OnClick onClick=new OnClick();
        back.setOnClickListener(onClick);
        resume_change.setOnClickListener(onClick);
        add1.setOnClickListener(onClick);
        add2.setOnClickListener(onClick);
        picture.setOnClickListener(onClick);
        phone.setOnClickListener(onClick);
        method.setOnClickListener(onClick);

    }



    private class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.myresume_peo_change_back:
                    people_resume_changeActivity.this.finish();
                    break;
                case R.id.resume_add:
                    myid_changeDialog dialog=new myid_changeDialog(context);
                    dialog.setSubmit(new myid_changeDialog.IOnCanceListener() {
                        @Override
                        public void onCancel(myid_changeDialog dialog) {
                            String data=dialog.getEd_text().getText().toString();
                            TextView textView=new TextView(context);
                            textView.setText(data);
                            textView.setTextSize(15);
                            textView.setTextColor(Color.parseColor("#000000"));
                            textView.setPadding(120,10,0,0);
                            edu.addView(textView);

                        }
                    });
                    dialog.show();
                    break;
                case R.id.resume_add1:
                    break;
                case R.id.resume_change_picture:
                    Intent i=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i,2);
                    break;
                case R.id.resume_change_phone:
                    myid_changeDialog dialog1=new myid_changeDialog(context);
                    dialog1.setSubmit(new myid_changeDialog.IOnCanceListener() {
                        @Override
                        public void onCancel(myid_changeDialog dialog) {
                            String data=dialog.getEd_text().getText().toString();
                            phonetv.setText(data);
                        }
                    });
                    dialog1.show();
                    break;
                case R.id.myresume_change_method:
                    setDialogMethod();
                    break;
                case R.id.mysume_change:
                    try {
                        if(Build.VERSION.SDK_INT >= 23){
                            int REQUEST_CODE_CONTACT = 101;
                            String[] permissions = {
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE};

                            for (String str : permissions) {
                                if (people_resume_changeActivity.this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                                    //申请权限
                                    people_resume_changeActivity.this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                                    return;
                                } else {
                                    //这里就是权限打开之后自己要操作的逻辑
                                    FillPdfTemplate();
                                }

                            }
                        }


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2){
            try{
                Uri uri=data.getData();
                String[] pojo={MediaStore.Images.Media.DATA};
                Cursor cursor=managedQuery(uri,pojo,null,null,null);
                if(cursor!=null){
                    ContentResolver cr=this.getContentResolver();
                    int colunm_index=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    path=cursor.getString(colunm_index);
                    final File file=new File(path);
                    Bitmap bitmap= BitmapFactory.decodeStream(cr.openInputStream(uri));
                    Bitmap option = myidActivity.BitmapOption.bitmapOption(bitmap,5);
                    pictureim.setImageBitmap(option);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setDialogMethod(){
        Dialog dialog=new Dialog(this,R.style.BottomDialog);
        LinearLayout root=(LinearLayout) LayoutInflater.from(this).inflate(R.layout.resume_bottom_dialog_changelayout,null);
        //初始化视图
        root.findViewById(R.id.resume_change_qxiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.setContentView(root);
        Window window=dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp=window.getAttributes();

        lp.x=0;
        lp.y=0;
        lp.width=(int)getResources().getDisplayMetrics().widthPixels;
        root.measure(0,0);
        lp.height=root.getMeasuredHeight();
        lp.alpha=9f;
        window.setAttributes(lp);
        dialog.show();

    }

    public void FillPdfTemplate() throws IOException {

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClass tools=new OkHttpClass();
                String result=tools.getResumeThoned("1");
                Log.d("resume-url",result);
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    String data=jsonObject.getString("data");
                    JSONObject jsonObject1=new JSONObject(data);
                    String url=jsonObject1.getString("url");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            OkHttpClass tool=new OkHttpClass();
                            InputStream stream=tool.getResume(url);
                            Log.d("stream",stream.toString());
                            FileOutputStream fileOutputStream=null;
                            try {
                                fileOutputStream=new FileOutputStream(new File("/sdcard/demo.pdf"));
                                byte[] buffer = new byte[2048];
                                int len = 0;
                                while ((len=stream.read(buffer))!=-1){
                                    fileOutputStream.write(buffer,0,len);
                                }
                                fileOutputStream.flush();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                                Log.d("resume-e1",e.toString());

                            } catch (IOException e) {
                                e.printStackTrace();
                                Log.d("resume-e2",e.toString());
                            }
                            Log.d("Down","成功");
                        }
                    }).start();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }).start();
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(context, "SD卡不可用~", Toast.LENGTH_SHORT).show();
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmmss");// HH:mm:ss
        //设置默认时区
        simpleDateFormat.setTimeZone(getTimeZone("GMT+8:00"));
        //获取当前时间
        Date date2 = new Date(System.currentTimeMillis());
        String sim2 = simpleDateFormat.format(date2);

        String folderName_WaterImage = "WaterImage";
        String folderName_WaterDB = "WaterDB";
        String folderName_WaterPdf = "WaterPdf";

        File sdCardDir_PdfTemplate = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), folderName_WaterDB);
       /* File sdCardDir_WaterPdf = new File(Environment.getExternalStorageDirectory(),
                folderName_WaterPdf);*/
        //创建文件路径
        File dir=new File(context.getExternalFilesDir(null).getPath()+"myApk");
        if (!dir.exists()){
            dir.mkdir();
        }
        //创建文件
        File file = new File(dir+"/"+"111.pdf");
        if (!file.exists()){
            file.createNewFile();
        }

        FileOutputStream fos = new FileOutputStream(file);

        //模板路径
        String templatePath = sdCardDir_PdfTemplate+"/"+ "Demo.pdf" ;
        Log.d("templatePath",templatePath);
        //生成的新文件路径
        String newPDFPath = dir + "/" + "111.pdf";
        Log.d("newPDFpath",newPDFPath);
/**
 * 使用中文字体
 * 如果是利用 AcroFields填充值的不需要在程序中设置字体，在模板文件中设置字体为中文字体就行了
 */
        /*BaseFont bf = null;
        try {
            bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Font FontChinese = new Font(bf, 12, Font.NORMAL);*/

        PdfReader reader;
        FileOutputStream out;
        ByteArrayOutputStream bos;
        PdfStamper stamper;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    FileOutputStream   out = new FileOutputStream(newPDFPath);//输出流
                    PdfReader  reader = new PdfReader(templatePath);//读取pdf模板
                    ByteArrayOutputStream   bos = new ByteArrayOutputStream();
                    PdfStamper  stamper = new PdfStamper(reader, bos);
                    AcroFields form = stamper.getAcroFields();

                    //String[] strDate = mWaterInfo.SamplingDate.split("-");
                    String[] str = {
                            "123456789","小鲁","男","1994-00-00",
                            "130222111133338888"
                            ,"河北省唐山市"};

            /*String[] it = new String[]{
                    "fill_1", "Text2", "Text3", "Text4", "Text5", "Text6", "Text7",
                    "Text8", "Text9", "Text10", "Text11", "Text12", "Text13", "Text14", "Text15",
                    "Text16", "Text17", "Text18", "Text19", "Text20", "Text21", "Text22",};*/
                    java.util.Iterator<String> it = form.getFields().keySet().iterator();
                    int i = 0;
            /*for (int i = 0; i < 22; i++) {
                form.setFieldProperty(it[i], "textfont", bf, null);
                form.setField(it[i], str[i]);
            }*/

                    while(it.hasNext()){
                        String name = it.next().toString();
                        System.out.println(name);
                        form.setField(name, str[i++]);
                    }

                    stamper.setFormFlattening(true);//如果为false那么生成的PDF文件还能编辑，一定要设为true
                    stamper.close();

                    Document doc = new Document();

                    PdfCopy copy = new PdfCopy(doc, out);
                    doc.open();
                    PdfImportedPage importPage = copy.getImportedPage(
                            new PdfReader(bos.toByteArray()), 1);
                    copy.addPage(importPage);


            /*File sdCardDir_WaterImage = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS), folderName_WaterImage);

            String imagePath1 = sdCardDir_WaterImage + "/" + "Image" + id + "_1";
            String imagePath2 = sdCardDir_WaterImage + "/" + "Image" + id + "_2";
            //插入现场图片
            Image image1 = Image.getInstance(imagePath1);
            doc.add(image1);
            Image image2 = Image.getInstance(imagePath2);
            doc.add(image2);*/

                    doc.close();
                    Log.d("successful","yes");
                    //Toast.makeText(this, "导出pdf完成", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    System.out.println(1);
                    Log.d("e1",e.toString());
                } catch (BadPdfFormatException e) {
                    e.printStackTrace();
                    Log.d("e2",e.toString());
                } catch (DocumentException e) {
                    e.printStackTrace();
                    Log.d("e3",e.toString());
                }
            }
        }).start();

    }
}