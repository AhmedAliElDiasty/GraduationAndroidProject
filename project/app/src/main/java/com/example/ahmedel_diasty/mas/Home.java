package com.example.ahmedel_diasty.mas;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ahmedel_diasty.mas.AboutUs.AboutUs;
import com.example.ahmedel_diasty.mas.Notification.MyNotification;

import java.util.Objects;



public class Home extends AppCompatActivity {
    TextView textView;
    String role;
    String level;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        textView = findViewById(R.id.name);
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
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }

    // Schedule Button

    public void schedule(View view) {
        Intent intent = new Intent(this,Lectures.class);
        startActivity(intent);
    }
    //MyNotification Buton
    public void Notify(View view){
        Intent intent = new Intent(getApplicationContext(),MyNotification.class);
        startActivity(intent);
    }

    // About us Button
    public void aboutUs(View view){
        Intent intent = new Intent(getApplicationContext(), AboutUs.class);
        startActivity(intent);
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
        else
            manualAttendance.setAlpha(0);

        // End Session Button

        Button endSession = dialog.findViewById(R.id.IAP_EndSession);
        endSession.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                Time mtime = new Time();
                mtime.setToNow();
                int hour =mtime.hour;
                int minutes = mtime.minute;


                Log.i("++++++++++Miliseconds",""+hour+" : "+minutes);
//                Log.i("++++++++++Miliseconds",""+hours);
            }
        });


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

    @Override

    public void onBackPressed() {

        //Display alert message when back button has been pressed

        backButtonHandler();

    }
    public void backButtonHandler() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        // Setting Dialog Title

        alertDialog.setTitle("Leave application?");

        // Setting Dialog Message

        alertDialog.setMessage("Are you sure you want to leave the application?");

        // Setting Icon to Dialog

//        alertDialog.setIcon(R.drawable.dialog_icon);

        // Setting Positive "Yes" Button

        alertDialog.setPositiveButton("YES",

                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        finish();

                    }

                });

        // Setting Negative "NO" Button

        alertDialog.setNegativeButton("NO",

                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        // Write your code here to invoke NO event

                        dialog.cancel();

                    }

                });

        // Showing Alert Message

        alertDialog.show();

    }



}
