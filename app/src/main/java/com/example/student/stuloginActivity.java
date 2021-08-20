package com.example.student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class stuloginActivity extends AppCompatActivity {
private ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stulogin);
        back=(ImageButton)findViewById(R.id.stulogin_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(stuloginActivity.this,loginselectActivity.class);
                startActivity(intent);
            }
        });
    }
}