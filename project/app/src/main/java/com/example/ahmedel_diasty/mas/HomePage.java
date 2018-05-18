package com.example.ahmedel_diasty.mas;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.ahmedel_diasty.mas.Remote.ApiInterface;

public class HomePage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
    }
    public void next(View view){
        Intent intent = new Intent(this,LoginForm.class);
        startActivity(intent);
    }
    public void newAccount(View view){
        Intent intent = new Intent(this,Lectures.class);
        startActivity(intent);
    }
}
