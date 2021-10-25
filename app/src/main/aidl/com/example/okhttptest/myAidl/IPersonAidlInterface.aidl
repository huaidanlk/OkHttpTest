// Book.aidl
package com.example.okhttptest.myAidl;

// Declare any non-default types here with import statements

interface IPersonAidlInterface {
    void setName(String name);
    void setAge(int age);
    String getInfo();
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
}