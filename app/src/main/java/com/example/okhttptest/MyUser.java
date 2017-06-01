package com.example.okhttptest;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

/**
 * Created by 坏蛋 on 2017/5/24.
 */

public interface MyUser {
   @GET("")
   Call<String> getUsers(@Body User request);

   @GET
   Observable<User> login(@Body User request);
}
