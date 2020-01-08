package com.example.okhttptest.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


// @interface 注解关键
@Retention(RetentionPolicy.CLASS)
@interface TestAnnotation {
    int id() default 123;
    String msg() default "alex";
}
