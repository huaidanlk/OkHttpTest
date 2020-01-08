package com.example.okhttptest.annotation;

import android.app.Activity;
import android.util.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//TestAnnotation2 只有一个名字为value的属性时可以直接填入 233
@TestAnnotation2(value2 = 233)
public class Test2 {
    @TestAnnotation()
    int a;

    @TestAnnotation3
    public void fun() {

    }

    private static Class<?> clazz;

    private static void getAnnotation(Activity activity) {

        clazz = activity.getClass();

        boolean hasAnnotation = clazz.isAnnotationPresent(TestAnnotation2.class);
        if (hasAnnotation) {
            //获取类的注解
            TestAnnotation2 testAnnotation2 = clazz.getAnnotation(TestAnnotation2.class);
            int id = testAnnotation2.value2();
            try {
                //获取一个成员变量上的注解
                Field a = clazz.getDeclaredField("a");
                a.setAccessible(true);
                TestAnnotation testAnnotation = a.getAnnotation(TestAnnotation.class);
                Log.d("Annotation", testAnnotation.id() + "" + testAnnotation.msg());
                Method testMethod = clazz.getDeclaredMethod("fun");
                if (testMethod != null) {
                    Annotation[] ans = testMethod.getAnnotations();
                    for (int i = 0; i < ans.length; i++) {
                        String method = String.valueOf(ans[i].annotationType());
                    }
                }

            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }

            try {
                clazz.getMethod("setContentView", Integer.TYPE).invoke(activity, id);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

    }
}
