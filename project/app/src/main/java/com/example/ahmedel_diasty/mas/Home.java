package com.example.ahmedel_diasty.mas;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Objects;

public class Home extends AppCompatActivity {
    TextView textView;
    String role;
    String level;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        textView = (TextView)findViewById(R.id.name);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"MTCORSVA.TTF");
        textView.setTypeface(typeface);
        SharedPreferences sharedPreferences = getSharedPreferences("Login data",MODE_PRIVATE);
        role = sharedPreferences.getString("role","default");
        level = sharedPreferences.getString("level","default");
        }

    public void logout(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("Login data",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username","default");
        editor.putString("name","default");
        editor.putString("role","default");
        editor.putString("level","default");
        editor.apply();
        Intent intent = new Intent(this,HomePage.class);
        startActivity(intent);

    }

    public void schedule(View view) {
        Intent intent = new Intent(this,Lectures.class);
        startActivity(intent);
    }

    public void aboutUs(View view) {
    }

    public void currentAttendance(View view) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.instructor_attendance_page);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final Button manualAttendance = dialog.findViewById(R.id.IAP_ManualAttendance);


        // Manual Attendance Button


        if (role.equals("1")){
            manualAttendance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    manualAttendance.setAlpha(1);
                    Intent intent = new Intent(getApplicationContext(),ManualAttendance.class);
                    startActivity(intent);
                }
            });
        }
        else{
            manualAttendance.setAlpha(0);
        }

        // Cancel Button

        ImageButton cancel = dialog.findViewById(R.id.IAP_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });



        dialog.show();

    }
}
