package com.example.ahmedel_diasty.mas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.ahmedel_diasty.mas.model.Data;
import com.example.ahmedel_diasty.mas.model.Schedule;

import java.util.List;

public class Lectures extends AppCompatActivity {

    private RecyclerView recyclerView;
    public OuterRecyclerAdapter recyclerAdapter;
    public RecyclerView.LayoutManager outerLayoutManager;
    // Attemp to initialize Inner RecyclerView
    private RecyclerView innerRecycler;
    public RecyclerView.LayoutManager innerLayoutManager;
    public InnerRecyclerAdapter innerRecyclerAdapter;

    List<Schedule> schedules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lectures);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        outerLayoutManager = new LinearLayoutManager(Lectures.this);
        recyclerView.setLayoutManager(outerLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerAdapter = new OuterRecyclerAdapter(getApplicationContext());
        recyclerView.setAdapter(recyclerAdapter);


        AndroidNetworking.initialize(Lectures.this);
        get_Subject();
    }

    public void get_Subject() {
        AndroidNetworking.get("http://www.syntax-eg.esy.es/api/schedule")
                .setPriority(Priority.HIGH)
                .build()
                .getAsObject(new Data().getClass(),
                        new ParsedRequestListener<Data>() {
                            @Override
                            public void onResponse(Data response) {
                                schedules = response.getSchedules();
                                innerRecyclerAdapter= new InnerRecyclerAdapter(Lectures.this, schedules);
                                recyclerView.setAdapter(innerRecyclerAdapter);


                                Log.e("Recived_success", schedules.toString());
                                Toast.makeText(getApplicationContext(),
                                        "Get Data Succussefuly ", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onError(ANError anError) {
                                Log.e("Recived_Error", anError.toString());
                            }
                        });
    }

}

