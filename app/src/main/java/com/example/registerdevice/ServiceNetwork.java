package com.example.registerdevice;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface ServiceNetwork {
    @POST("user/register")
    Call<Response> registerUser(@Body RequestBody requestUser);


    @POST("device/register")
    Call<Response> registerDevice(@Body RequestBody requestUser) ;

}
