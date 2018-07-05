package com.example.ahmedel_diasty.mas.Notification;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.format.Time;
import android.util.Log;

import com.example.ahmedel_diasty.mas.Model.Schedule;
import com.example.ahmedel_diasty.mas.R;
import com.example.ahmedel_diasty.mas.Remote.ApiClient;
import com.example.ahmedel_diasty.mas.Remote.ApiInterface;
import com.example.ahmedel_diasty.mas.Sqlite.DBConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyThread implements Runnable{
    private Schedule schedule;
    private Context context;
    private DBConnection dbConnection;
    private String day;
    private int hourSystem;
    private int minuteSystem;
    private Time mtime;
    private int i = 0;


    public MyThread(Context context) {
        this.context = context;
        dbConnection = new DBConnection(context);
       schedule = new Schedule();

        mtime = new Time();




        mtime.setToNow();
        int weekDay = mtime.weekDay;
        switch (weekDay){
            case 0:
                day = "Sunday";
                break;
            case 1:
                day = "Monday";
                break;
            case 2:
                day = "Tuesday";
                break;
            case 3:
                day = "Wednesday";
                break;
            case 4:
                day = "Thursday";
                break;
            case 6:
                day = "Saturday";
                break;

            default:
                day = "Error";
                break;
        }
    }

    @Override
    public void run() {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Schedule> scModelCall = apiInterface.getScheduleCall();
        scModelCall.enqueue(new Callback<Schedule>() {

            @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
            @Override
            public void onResponse(Call<Schedule> call, Response<Schedule> response) {
                schedule = response.body();
                for (int i = 0;i<schedule.getDataSchedules().size();i++){
                    String startTimeString = schedule.getDataSchedules().get(i).getStartTime();
                    String[] separated = startTimeString.split(":");
                    String hourString = separated[0].trim();
                    String minuteString = separated[1].trim();
                    int hour = Integer.parseInt(hourString);
                    int minute = Integer.parseInt(minuteString);
                    if (day.equals(schedule.getDataSchedules().get(i).getDay())){
                        if (hour == hourSystem && minute == minuteSystem){
                            Intent intent = new Intent(context,MyService.class);
                            context.startService(intent);
                            dbConnection.dataInsert(
                                    schedule.getDataSchedules().get(i).getSubjectName(),
                                    context.getString(R.string.description1),
                                    startTimeString);
                            Log.i("++++++++++++++++++",day+" "+hourSystem+" "+minuteSystem);

                        }
                        if (hour == hourSystem && minute == minuteSystem +10){
                            Intent intent = new Intent(context,MyService.class);
                            context.startService(intent);
                            dbConnection.dataInsert(
                                    schedule.getDataSchedules().get(i).getSubjectName(),
                                    context.getString(R.string.description2),
                                    startTimeString);
                        }
                        // Delete Data in the end of day

                        if (hour == hourSystem && minute == minuteSystem +15){
                            dbConnection.deleteAll();
                        }

                    }
                }

            }

            @Override
            public void onFailure(Call<Schedule> call, Throwable t) {

            }
        });
        hourSystem =mtime.hour;
        minuteSystem = mtime.minute;
    }
}
