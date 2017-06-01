package com.example.okhttptest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_1:
                Intent intent=new Intent(this,OkHttpActivity.class);
                startActivityForResult(intent,1);
                break;
            case R.id.btn_2:
                Intent intent2=new Intent(this,RetrofitActivity.class);
                startActivityForResult(intent2,1);
                break;
            case R.id.btn_3:
                Intent intent3=new Intent(this,RxJavaActivity.class);
                startActivityForResult(intent3,1);
                break;
        }
    }
}
