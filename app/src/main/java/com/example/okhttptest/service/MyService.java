package com.example.okhttptest.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class MyService extends Service {

    private OnCallbackListener onCallbackListener;

    public interface OnCallbackListener {
        void onCallback(int progress);
    }

    public void setOnCallbackListener(OnCallbackListener onCallbackListener) {
        this.onCallbackListener = onCallbackListener;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    class MyBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }

    int i = 0;

    public void startDownload() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                    onCallbackListener.onCallback(i);
                }
            }
        }).start();
    }
}
