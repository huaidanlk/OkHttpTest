package com.example.okhttptest.myAidl;

import android.os.RemoteException;

public class IPersonImpl extends IPersonAidlInterface.Stub{
    private String name;
    private int age;

    @Override
    public void setName(String name) throws RemoteException {
        this.name = name;
    }

    @Override
    public void setAge(int age) throws RemoteException {
        this.age = age;
    }

    @Override
    public String getInfo() throws RemoteException {
        return "My name is "+name+", age is "+age+"!";
    }

    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

    }
}
