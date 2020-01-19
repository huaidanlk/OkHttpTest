package com.example.okhttptest.mvp;

public interface IMvpModel<T> {
    void callback(T t);
}
