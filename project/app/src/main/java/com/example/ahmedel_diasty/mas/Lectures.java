package com.example.ahmedel_diasty.mas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class Lectures extends AppCompatActivity {

    private RecyclerView recyclerView;
    public OuterRecyclerAdapter recyclerAdapter;
    public RecyclerView.LayoutManager outerLayoutManager;


    // Attemp to initialize Inner RecyclerView

    private RecyclerView innerRecycler;
    public RecyclerView.LayoutManager innerLayoutManager;
    public InnerRecyclerAdapter innerRecyclerAdapter;

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



    }
}
