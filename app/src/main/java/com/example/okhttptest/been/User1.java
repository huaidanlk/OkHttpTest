package com.example.okhttptest.been;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.okhttptest.BR;

public class User1 extends BaseObservable {
    //如果是 public 修饰符，则可以直接在成员变量上方加上 @Bindable 注解
    @Bindable
    public String name;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public String getEmail() {
        return email;
    }

    @Bindable
    public void setEmail(String email) {
        this.email = email;
        notifyChange();
    }

}
