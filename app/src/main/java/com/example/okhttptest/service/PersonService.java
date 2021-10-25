package com.example.okhttptest.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.okhttptest.myAidl.IPersonImpl;

public class PersonService extends Service {
    public PersonService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new IPersonImpl();
    }
}
