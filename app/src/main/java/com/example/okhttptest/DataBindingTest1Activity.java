package com.example.okhttptest;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.okhttptest.databinding.ActivityDatabindingTest1Binding;

public class DataBindingTest1Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_databinding_test1);

        ActivityDatabindingTest1Binding mBinding=DataBindingUtil.setContentView(this, R.layout.activity_databinding_test1);
    }
}
