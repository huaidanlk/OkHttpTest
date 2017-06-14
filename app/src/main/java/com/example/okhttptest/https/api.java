package com.example.okhttptest.https;

import com.example.okhttptest.been.GsonBen;
import com.example.okhttptest.been.LoginRequest;
import com.example.okhttptest.been.LoginResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by 坏蛋 on 2017/5/27.
 */

public interface API {
    //@GET("")里面一定要有数据，不能为@GET 或者@GET("")
    // 没有数据就加 . 或者 /
    @POST("/")
    Observable<LoginResponse> Login(@Body LoginRequest request);

    @GET(".")
    Observable<LoginResponse> Register(@QueryMap Map<String, String> map);

    @GET(".")
    Observable<LoginResponse> Register1(@FieldMap Map<String, String> map);

//    http://gank.io/api/data/Android/10/1
    @GET("api/data/Android/10/1")
    Observable<GsonBen> getAndroidInfo();

}
