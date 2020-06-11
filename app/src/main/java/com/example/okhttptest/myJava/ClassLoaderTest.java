package com.example.okhttptest.myJava;

import com.example.okhttptest.myJava.DiskClassLoader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassLoaderTest {
    public static void main(String[] args) {
        DiskClassLoader diskClassLoader = new DiskClassLoader("/Users/lk/myJava");//1
        try {
            Class c = diskClassLoader.loadClass("com.example.Jobs1");//2
            if (c != null) {
                try {
                    Object obj = c.newInstance();
                    System.out.println(obj.getClass().getClassLoader());
                    Method method = c.getDeclaredMethod("say", null);
                    method.invoke(obj, null);//3
                } catch (InstantiationException | IllegalAccessException
                        | NoSuchMethodException
                        | SecurityException |
                        IllegalArgumentException |
                        InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}