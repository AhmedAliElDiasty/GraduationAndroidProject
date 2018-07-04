package com.example.ahmedel_diasty.mas.Notification;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

import com.example.ahmedel_diasty.mas.R;

import java.util.Objects;

public class MyService extends Service{

    int i = 0;
    boolean isRunning = true;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Thread thread = new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {
                createNotification(i);
            }
        });
        thread.start();
        return super.onStartCommand(intent, flags, startId);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    private void createNotification(int i) {

        Intent intent = new Intent(MyService.this,MyNotification.class);
        @SuppressLint("WrongConstant") PendingIntent pendingIntent =
                PendingIntent.getActivity(MyService.this,0,intent,Intent.FLAG_ACTIVITY_NEW_TASK);

        Notification.Builder builder = new Notification.Builder(MyService.this) ;
        builder.setContentTitle("Warring !!");
        builder.setContentText("Watch Out ! Someone in your car ");
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        Notification.Action  action = new Notification.Action.Builder(android.R.drawable.ic_media_play,"Ok",pendingIntent).build();
        builder.addAction(action);
        Notification notification = builder.build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(i,notification);


    }
    @Override
    public void onDestroy(){
    }
}
