package com.example.Todo_List_Demo;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import static android.content.Context.ALARM_SERVICE;

public class ReminderBR extends BroadcastReceiver {

    private static final int Reminder_ID = 1;
    private NotificationManager mNotificationManager;

    private static final String PRIMARY_CHANNEL_ID_TODO =
            "primary_notification_channel_todo";
    @Override
    public void onReceive(Context context, Intent intent) {
        mNotificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Deliver the notification.
        deliverNotification(context,intent);
//deleteNotify(context,intent);


    }

    /**
     * Builds and delivers the notification.
     *
     * @param context, activity context.
     */
    private void deliverNotification(Context context,Intent intent) {
        // Create the content intent for the notification, which launches
        // this activity
        Intent contentIntent = new Intent(context, TODO.class);
        int idalarm=intent.getExtras().getInt("idalarm1");
        PendingIntent contentPendingIntent = PendingIntent.getActivity
                (context, idalarm, contentIntent, PendingIntent
                        .FLAG_UPDATE_CURRENT);
        // Build the notification

        NotificationCompat.Builder builder = new NotificationCompat.Builder
                (context, PRIMARY_CHANNEL_ID_TODO)
                .setSmallIcon(android.R.drawable.ic_btn_speak_now)
                .setContentTitle("To-day List")
                .setContentText("Reminder of a ToDo")
                .setContentIntent(contentPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL);
             //   .addAction(R.drawable.common_google_signin_btn_icon_light_normal, "Delete Notification",contentPendingIntent);

        // Deliver the notification
        mNotificationManager.notify(Reminder_ID, builder.build());
    }



}


