package com.example.ahmedel_diasty.mas.Remote;

import com.example.ahmedel_diasty.mas.Model.Data;
import com.example.ahmedel_diasty.mas.Model.Model;
import com.google.gson.JsonArray;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("api/students")
    Call<Model> getData();
}
