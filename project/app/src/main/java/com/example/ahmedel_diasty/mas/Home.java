package com.example.ahmedel_diasty.mas;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ahmedel_diasty.mas.AboutUs.AboutUs;
import com.example.ahmedel_diasty.mas.Model.StudentsInLocation;
import com.example.ahmedel_diasty.mas.Notification.MyNotification;
import com.example.ahmedel_diasty.mas.Remote.ApiClient;
import com.example.ahmedel_diasty.mas.Remote.ApiInterface;
import com.example.ahmedel_diasty.mas.Sqlite.DBConnection;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Home extends AppCompatActivity {
    TextView textView;
    String role;
    String level;
    DBConnection dbConnection;
    TextView counter;
    SharedPreferences sharedPreferences;
    SharedPreferences preferences;
    String startTimeString;
    String endTimeString;
    int minuteRemaining;
    int hourRemaning;
    StudentsInLocation students1;
    int state = 0;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        textView = findViewById(R.id.name);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"MTCORSVA.TTF");
        textView.setTypeface(typeface);
        SharedPreferences sharedPreferences = getSharedPreferences("Login data",MODE_PRIVATE);
        role = sharedPreferences.getString("role","0");
        level = sharedPreferences.getString("level","0");
        dbConnection = new DBConnection(this);
        counter = findViewById(R.id.notification_counts);
        counter.setText("+"+dbConnection.retrieveData().getRowObjects().size());
        preferences = getSharedPreferences("current attendance",MODE_PRIVATE);

        Button schedule = findViewById(R.id.home_schedule);
        if (Integer.parseInt(role) ==1){
            schedule.setAlpha((float) 0.2);
            schedule.setEnabled(false);
        }
        }

    public void logout(View view) {
        sharedPreferences = getSharedPreferences("Login data",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username","default");
        editor.putString("name","default");
        editor.putString("role","default");
        editor.putString("level","0");
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


    @SuppressLint("SetTextI18n")
    public void currentAttendance(View view) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.instructor_attendance_page);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final Button manualAttendance = dialog.findViewById(R.id.IAP_ManualAttendance);


        // Manual Attendance Button


        if (role.equals("1")) {
            manualAttendance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    manualAttendance.setAlpha(1);
                    Intent intent = new Intent(getApplicationContext(), ManualAttendance.class);
                    startActivity(intent);
                }
            });
        } else
            manualAttendance.setAlpha(0);

        // End Session Button

        final Button endSession = dialog.findViewById(R.id.IAP_EndSession);
        if (state == 0){
            endSession.setEnabled(false);
            endSession.setAlpha((float) 0.2);
        }
        else{
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<StudentsInLocation> locationCall =
                    apiInterface.setStudentsCall(Integer.parseInt(
                            sharedPreferences.getString("id","0")),"0");
            locationCall.enqueue(new Callback<StudentsInLocation>() {
                @Override
                public void onResponse(Call<StudentsInLocation> call, Response<StudentsInLocation> response) {
                    Log.i("++++++++++","Switch off");
                    state = 0;
                }

                @Override
                public void onFailure(Call<StudentsInLocation> call, Throwable t) {
                    Log.i("++++++++++","failure");

                }
            });
        }


        // Lecture name
        final TextView subjectName = dialog.findViewById(R.id.IAP_SubjectName);
        subjectName.setText(preferences.getString("Activity name", "No Activity Now"));

        // Started time
        startTimeString = preferences.getString("Start time", "00:00");
        final TextView startTime = dialog.findViewById(R.id.IAP_StartedTime);
        startTime.setText(startTimeString);


        final TextView remaining = dialog.findViewById(R.id.IAP_TimeLeft);

        // Calculate remaining time


        Log.i("Start time String",startTimeString);

        Time mtime = new Time();
        mtime.setToNow();
        int currentHour = mtime.hour;
        int currentMinute = mtime.minute;

        endTimeString = preferences.getString("end time", "default");
        String[] separatedEnd = startTimeString.split(":");
        String hourStringEnd = separatedEnd[0].trim();
        String minuteStringEnd = separatedEnd[1].trim();
        int hourEnd = Integer.parseInt(hourStringEnd);
        int minuteEnd = Integer.parseInt(minuteStringEnd);


        hourRemaning = hourEnd - currentHour;
        minuteRemaining = minuteEnd - currentMinute;
        int timer = 1000 * 60 * (minuteRemaining + 60 * hourRemaning);

        Log.i("-----------Local time",hourEnd+":"+minuteEnd);
        if (hourEnd == 0){
            remaining.setText(hourEnd + ":" + minuteEnd);
        }
        else {
            new CountDownTimer(timer, 60000) {
                int minutes;
                int hours;
                int tempMinutes;

                @Override
                public void onTick(long millisUntilFinished) {
                    minutes = (int) (millisUntilFinished / (60000));
                    hours = minutes / 60;
                    minutes = minutes % 60;

                    // Remaining time

                    remaining.setText(hours + ":" + minutes);

                    if (tempMinutes == -1) {
                        hours--;
                        minutes = 59;
                    }
                }

                @Override
                public void onFinish() {
                    subjectName.setText("No Activity Now");
                    startTime.setText("");
                    remaining.setText("00:00");

                }
            }.start();


        Log.i("++++++++++++++++++++",""+Integer.parseInt(level));
//        Log.i("++++++++++++++++++++",level);
        }
//



        // Students' number
        final TextView students = dialog.findViewById(R.id.IAP_Students);

        students1 = new StudentsInLocation();

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<StudentsInLocation> getStudentsCall = apiInterface.getStudentsNumber(Integer.parseInt(level));
        getStudentsCall.enqueue(new Callback<StudentsInLocation>() {
            @Override
            public void onResponse(Call<StudentsInLocation> call, Response<StudentsInLocation> response) {
                students1 = response.body();
                Log.i("+++++++++++++++",""+students1.getData().size());
                students.setText(""+students1.getData().size());
            }

            @Override
            public void onFailure(Call<StudentsInLocation> call, Throwable t) {

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
