package com.example.okhttptest.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.okhttptest.R;

public class MyServiceActivity extends AppCompatActivity {

    private Button mButton;
    private TextView mProgress;


    private MyService myService;


    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MyServiceActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myservice);
        initView();
    }

    private void initView() {
        mButton = (Button) findViewById(R.id.btn_1);
        mProgress = (TextView) findViewById(R.id.progress);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myService.startDownload();
            }
        });

        Intent intent = new Intent(MyServiceActivity.this, MyService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {
        //通过IBinder拿到Service
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myService = ((MyService.MyBinder) service).getService();
            myService.setOnCallbackListener(new MyService.OnCallbackListener() {
                @Override
                public void onCallback(final int progress) {

                    mProgress.post(new Runnable() {
                        @Override
                        public void run() {
                            mProgress.setText(String.valueOf(progress));
                        }
                    });
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onDestroy() {
        unbindService(connection);
        super.onDestroy();
    }
}
