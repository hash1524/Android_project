package com.example.myapplication;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class RemainderBroadCast extends BroadcastReceiver {
    static public String kohli ="TASK REMINDER";
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context , "notifylemubit")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(kohli)
                .setContentText("You have a task to do")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(250, builder.build());
    }
}
