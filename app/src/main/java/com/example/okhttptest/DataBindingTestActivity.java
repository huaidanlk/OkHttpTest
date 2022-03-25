package com.example.okhttptest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;

import com.example.okhttptest.been.Car;
import com.example.okhttptest.been.User1;
import com.example.okhttptest.databinding.TestDataBinding;

import java.lang.ref.WeakReference;

public class DataBindingTestActivity extends AppCompatActivity {

    private static final String TAG = "CarGc";

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, DataBindingTestActivity.class);
        activity.startActivity(intent);
    }

    private User1 mUser1;
    TestDataBinding binding;
    private Handler handlerStatic = new MyHandler(this);

    public static class MyHandler extends Handler {
        private WeakReference<DataBindingTestActivity> weakReference;

        public MyHandler(DataBindingTestActivity context) {
            this.weakReference = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            if (weakReference.get() == null) {
                Log.d(TAG, "handleMessage: -- activity gc");
            } else {
                Log.d(TAG, "handleMessage: -- not gc");

            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        handlerStatic.removeCallbacksAndMessages(null);

    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            binding.name.setText("rrrrrr");
            Log.d(TAG, "handleMessage:");
        }
    };

    public static void fun() {
        //        mCar= new Car();
        WeakReference<Car> weakReference = new WeakReference<>(new Car("111"));
        Log.d(TAG, "System gc");
        System.gc();
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        while (weakReference.get() == null) {
            Log.d(TAG, "System gc Car object --- ");
            break;
        }
        if (weakReference.get() == null) {
            Log.d(TAG, "System gc Car object");
        } else {
            Log.d(TAG, weakReference.get().getName());
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding);
        mUser1 = new User1();
        mUser1.setName("Alex");
        mUser1.setEmail("1208529858@qq.com");
        binding.setUser123(mUser1);
        binding.setPresenter(new Presenter());
        fun();
//        handler.sendMessageDelayed(new Message(), 5000);
        //default value    @{user123.name,default= 让我康康}
//        binding.content.name.setText("让我康康");

        //change callback
        mUser1.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (propertyId == BR.name)
                    return;
            }
        });
        handlerStatic.sendMessageDelayed(new Message(), 30 * 1000);

        TClass tClass = new TClass<A>();
        getTMethod(tClass);
    }

    public <T> void getTMethod(T tClass) {

    }

    public <T> T getTMethod1(T tClass) {
        return tClass;
    }

    public class Presenter {
        public void setName() {
            mUser1.setName("name");
            mUser1.setEmail("name");

        }

        public void setEmail() {
            mUser1.setName("email");
            mUser1.setEmail("email");
        }

        public void afterTextChanged(Editable s) {
            mUser1.setName(s.toString());
        }
    }
}
