package com.example.okhttptest.myJava.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

// https://www.cnblogs.com/renyuanwei/p/9394247.html 反射实例
public class ReflectTest {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        fun();
        funMethod();
    }

    public static void fun() throws IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        Class clazz = InnerContainer.class;
        InnerContainer container = (InnerContainer) clazz.newInstance();
        Class innerClazz[] = clazz.getDeclaredClasses();
        for (Class cls : innerClazz) {
            int mod = cls.getModifiers();
            String modifier = Modifier.toString(mod);
            if (modifier.contains("static")) {
                //构造静态内部类实例
//              Constructor con1 = cls.getDeclaredConstructor();
                Object obj1 = cls.newInstance();
                Field field1 = cls.getDeclaredField("f");
                field1.setAccessible(true);
                System.out.println(field1.get(obj1));
            } else {
                // 构造成员内部类实例
                Constructor con2 = cls.getDeclaredConstructor(clazz);
                con2.setAccessible(true);
                Object obj2 = con2.newInstance(container);
                Field field2 = cls.getDeclaredField("f");
                field2.setAccessible(true);
                System.out.println(field2.get(obj2));
            }
        }
        // 获取匿名内部类实例
        Field field = clazz.getDeclaredField("r");
        field.setAccessible(true);
        Runnable r = (Runnable) field.get(container);
        r.run();
    }

    public static void funMethod() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
//        Class<Person> clazz = Person.class;
        Class clazz = Class.forName("com.example.okhttptest.myJava.reflect.Person");
        Object obj = clazz.newInstance();

        //1.共有 无参方法
//        Method  personPublicPrin = clazz.getMethod("public_prin",null);
        Method  personPublicPrin = clazz.getMethod("public_prin");
//        personPublicPrin.invoke(obj,null);
        personPublicPrin.invoke(obj);

        //2.共有 有参方法
        Method personPublicShow = clazz.getMethod("public_show",String.class,int.class);
        personPublicShow.invoke(obj,"Alex",28);

        //3.私有 无参方法
//        Method personPrivatePrin = clazz.getDeclaredMethod("private_prin",null);
        Method personPrivatePrin = clazz.getDeclaredMethod("private_prin");
        personPrivatePrin.setAccessible(true);
//        personPrivatePrin.invoke(obj,null);
        personPrivatePrin.invoke(obj);

        //4.私有 有参方法
        Method personPrivateShow = clazz.getDeclaredMethod("private_show",String.class,int.class);
        personPrivateShow.setAccessible(true);
        personPrivateShow.invoke(obj,"Alex11",88);


    }
}




