package com.example.okhttptest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.okhttptest.been.Car;
import com.example.okhttptest.fragment.TestFragment;
import com.example.okhttptest.mvp.MvpActivity;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CommonActivity.onCommonClick {
    private static final String TAG = "CarGc";
    private Car mCar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_1).setOnClickListener(this);
//        findViewById(R.id.btn_2).setOnClickListener(this);
//        findViewById(R.id.btn_3).setOnClickListener(this);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
        initView();

    }


    private void initView() {
        TestFragment testFragment = TestFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.activity_main, testFragment).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
//                CommonActivity.startActivity(this,"MainActivity");
//                DataBindingTestActivity.startActivity(this);
                 MvpActivity.startActivity(this);
                break;
//            case R.id.btn_2:
//                Intent intent2=new Intent(this,RetrofitActivity.class);
//                startActivityForResult(intent2,1);
//                break;
//            case R.id.btn_3:
//                Intent intent3=new Intent(this,RxJavaActivity.class);
//                startActivityForResult(intent3,1);
//                break;
        }
    }

    @Override
    public void onClick(Context context) {
        Toast.makeText(context, "MainActivity", Toast.LENGTH_SHORT).show();
    }
}
