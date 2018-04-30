package com.example.ahmedel_diasty.mas.Remote;

import com.example.ahmedel_diasty.mas.Model.Model;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @GET("api/students")
    Call<Model> getData();


    @POST("api/studentLogin")
    @FormUrlEncoded
    Call<Model> setData(@Field("username")String Username,
                        @Field("password")String Password);
}
