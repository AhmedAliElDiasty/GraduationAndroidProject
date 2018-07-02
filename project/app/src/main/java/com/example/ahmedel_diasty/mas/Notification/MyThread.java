package com.example.ahmedel_diasty.mas.Notification;

import android.content.Context;
import android.content.Intent;
import android.text.format.Time;
import android.util.Log;
import android.widget.Toast;

import com.example.ahmedel_diasty.mas.Sqlite.DBConnection;

public class MyThread implements Runnable{
    private Context context;
    private DBConnection dbConnection;
    public MyThread(Context context) {
        this.context = context;
        dbConnection = new DBConnection(context);
    }

    @Override
    public void run() {

        Time mtime = new Time();
        mtime.setToNow();
        int hour =mtime.hour;
        int minutes = mtime.minute;
//        if (minutes == 00){
//            Toast.makeText(context, "Success Service", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context,MyService.class);
            context.startService(intent);
//            long insert = dbConnection.dataInsert("Computer Science","This is very useful subject","9:10");
//            Toast.makeText(context, "Success Insertion", Toast.LENGTH_SHORT).show();
//            Log.i("++++++++++++++++=","Successful insertion   "+insert);
//        }



    }
}
