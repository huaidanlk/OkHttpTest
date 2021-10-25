package com.example.okhttptest.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.okhttptest.R;
import com.example.okhttptest.myAidl.IPersonAidlInterface;

public class MyPersonActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity1234556";
    private IPersonAidlInterface iPersonAidlInterface;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MyPersonActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);


    }

    public void bindServiceClick(View view) {
        Log.i(TAG,"绑定服务...");
        Intent intent = new Intent(this,PersonService.class);

        // 绑定服务时自动创建服务
        bindService(intent,conn, Context.BIND_AUTO_CREATE);
    }

    public void unbindServiceClick(View view) {
        Log.i(TAG,"解绑服务...");
        unbindService(conn);
    }

    public void callRemoteClick(View view) {
        Log.i(TAG,"远程调用具体服务...");
        try {
            iPersonAidlInterface.setName("Tom");
            iPersonAidlInterface.setAge(10);
            String info = iPersonAidlInterface.getInfo();
            System.out.println("MainActivity1234556 这是远程调用的服务信息："+info);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 根据实际情况返回 IBinder 的本地对象或其代理对象
            iPersonAidlInterface = IPersonAidlInterface.Stub.asInterface(service);
            System.out.println("MainActivity1234556 具体的业务对象："+iPersonAidlInterface);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // Service 意外中断时调用
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                bindServiceClick(view);
                break;
            case R.id.btn_2:
                callRemoteClick(view);
                break;
            case R.id.btn_3:
                unbindServiceClick(view);
                break;
        }
    }
}
