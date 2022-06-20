package com.example.okhttptest.jetpack;

import android.arch.core.util.Function;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.okhttptest.R;


public class JetPackActivity extends AppCompatActivity {

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, JetPackActivity.class);
        context.startActivity(intent);
    }

    private static final String TAG = "JetPackActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jetpack);

        //Lifecycle
        getLifecycle().addObserver(new MyObserver());

        //LiveData
        final MutableLiveData<String> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d(TAG, "onChanged-1: " + s);
            }
        });
//        mutableLiveData.postValue("谁的骨盆最端正");

        LiveData transformedLiveData = Transformations.map(mutableLiveData, new Function() {
            @Override
            public Object apply(Object o) {
                return o.toString() + "+我的骨盆最端正";
            }
        });

        transformedLiveData.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                Log.d(TAG, "onChanged-2: " + o.toString());
            }
        });

        mutableLiveData.postValue("对对对对");


        //ViewModel
        MyViewModel model = ViewModelProviders.of(this).get(MyViewModel.class);
        model.getName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Log.d(TAG, "onChanged: " + s);
            }
        });



    }


    public class MyObserver implements LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        void onResume() {
            Log.d(TAG, "onResume: Lifecycle.Event.ON_RESUME");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        void onPause() {
            Log.d(TAG, "onPause: Lifecycle.Event.ON_PAUSE");
        }
    }
}
