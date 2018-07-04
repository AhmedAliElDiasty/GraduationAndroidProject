package com.example.ahmedel_diasty.mas.Notification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmedel_diasty.mas.R;
import com.example.ahmedel_diasty.mas.Sqlite.DBConnection;

public class MyNotification extends AppCompatActivity {
    RecyclerView recyclerView;
    NotificationRowAdapter notificationRowAdapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        recyclerView = findViewById(R.id.notification);
        notificationRowAdapter = new NotificationRowAdapter(this);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        try {
            recyclerView.setAdapter(notificationRowAdapter);
        }
        catch (Exception ex){
            Toast.makeText(this, "No Data to show", Toast.LENGTH_SHORT).show();
        }
    }
}
