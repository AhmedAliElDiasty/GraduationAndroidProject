package com.example.ahmedel_diasty.mas;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ahmedel_diasty.mas.Remote.ApiInterface;

public class HomePage extends AppCompatActivity {
    final String DEFAULT = "default";
    String username ,name ,email ,level;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        SharedPreferences sharedPreferences = getSharedPreferences("Login data",MODE_PRIVATE);
         username = sharedPreferences.getString("username",DEFAULT);
         name = sharedPreferences.getString("name",DEFAULT);
         email = sharedPreferences.getString("email",DEFAULT);
         level = sharedPreferences.getString("level",DEFAULT);
         if(!username.equals(DEFAULT)){
             Intent intent = new Intent(this,ManualAttendance.class);
             intent.putExtra("name",name);
             intent.putExtra("email",email);
             intent.putExtra("level",level);
             intent.putExtra("type","student");
             startActivity(intent);
         }
    }
    public void nextStudent(View view){
        Intent intent = new Intent(this,LoginForm.class);
        intent.putExtra("type","student");
        startActivity(intent);
    }
    public void nextInstructor(View view){
        Intent intent = new Intent(this,LoginForm.class);
        intent.putExtra("type","instructor");
        startActivity(intent);
    }
    public void newAccount(View view){
        Intent intent = new Intent(this,Lectures.class);
        startActivity(intent);
    }
}
