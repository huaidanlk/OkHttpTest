package com.example.okhttptest.been;

import android.util.Log;

public class Car {
    private String name;


    public Car(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        Log.d("CarGc", "finalize: ");
        System.out.println("Apple： " + name + " finalize。");
    }
}
