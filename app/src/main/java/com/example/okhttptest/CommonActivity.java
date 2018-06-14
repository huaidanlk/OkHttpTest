package com.example.okhttptest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by lk on 2018/6/14.
 */

public class CommonActivity extends AppCompatActivity implements View.OnClickListener {
    public static String TAG="TAG";
    public static void startActivity(Activity activity,String tag){
        Intent intent=new Intent(activity,CommonActivity.class);
        intent.putExtra(TAG,tag);
        activity.startActivity(intent);
    }
    public interface onCommonClick{
        public void onClick();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        findViewById(R.id.btn_1).setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                if("MainActivity".equals(getIntent().getStringExtra(TAG))){
                    new MainActivity().onClick();
                }
                break;
        }
    }
}
