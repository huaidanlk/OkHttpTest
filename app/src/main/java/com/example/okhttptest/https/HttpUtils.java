package com.example.okhttptest.https;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 坏蛋 on 2017/5/27.
 */

public class HttpUtils {
    public static String BASE_URL="http://gank.io/";
    public static Retrofit create(){
        OkHttpClient.Builder builder=new OkHttpClient().newBuilder();
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(9,TimeUnit.SECONDS);

        return new Retrofit.Builder().baseUrl(BASE_URL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static void setBaseUrl(String baseUrl) {
        BASE_URL = baseUrl;
    }
}
