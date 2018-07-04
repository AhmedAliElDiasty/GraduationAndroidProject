package com.example.ahmedel_diasty.mas;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmedel_diasty.mas.Model.DataSchedule;
import com.example.ahmedel_diasty.mas.Model.Schedule;
import com.example.ahmedel_diasty.mas.Remote.ApiClient;
import com.example.ahmedel_diasty.mas.Remote.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Lectures extends AppCompatActivity {

    public OuterRecyclerAdapter recyclerAdapter;
    public RecyclerView.LayoutManager outerLayoutManager;
    TextView textView;
    private ApiInterface apiInterface;
    private Schedule schedule;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lectures);
        final RecyclerView recyclerView = findViewById(R.id.recyclerView);
        outerLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(outerLayoutManager);
        recyclerView.setHasFixedSize(true);
//        recyclerAdapter = new OuterRecyclerAdapter(this,schedule);
//        recyclerView.setAdapter(recyclerAdapter);


        // Attempt to receive subjects' schedule



        schedule = new Schedule();

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Schedule> scModelCall = apiInterface.getScheduleCall();
        scModelCall.enqueue(new Callback<Schedule>() {
            @Override
            public void onResponse(Call<Schedule> call, Response<Schedule> response) {
                schedule = response.body();

                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                recyclerAdapter = new OuterRecyclerAdapter(Lectures.this,schedule);
                recyclerView.setAdapter(recyclerAdapter);


            }

            @Override
            public void onFailure(Call<Schedule> call, Throwable t) {
                Intent intent = new Intent(getApplicationContext(),Home.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Check the internet connection", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
