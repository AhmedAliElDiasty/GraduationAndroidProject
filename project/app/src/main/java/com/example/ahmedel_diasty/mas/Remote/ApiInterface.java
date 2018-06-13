package com.example.ahmedel_diasty.mas.Remote;

import com.example.ahmedel_diasty.mas.Model.Model;
import com.example.ahmedel_diasty.mas.Model.Schedule;
import com.example.ahmedel_diasty.mas.Model.StudentsInLocation;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("api/students")
    Call<Model> getData();


    @POST("api/studentLogin")
    @FormUrlEncoded
    Call<Model> setData(@Field("username")String Username,
                        @Field("password")String Password);

    @POST("api/instructorLogin")
    @FormUrlEncoded
    Call<Model> setInstructorDta(@Field("username")String Username,
                        @Field("password")String Password);

    @GET("api/schedule")
    Call<Schedule>getScheduleCall();

    @GET("api/students_in_Location")
    Call<StudentsInLocation>getStudentsCall();


    @PUT("api/students_in_Location/{id}")
    Call<StudentsInLocation>setStudentsCall(@Path("id") int ID,
                                            @Field("id")int id,
                                            @Field("name")String name,
                                            @Field("level")String level,
                                            @Field("status")String status);

}
