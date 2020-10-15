package com.example.Todo_List_Demo;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import static android.content.Context.ALARM_SERVICE;

public class ReminderBr_this extends BroadcastReceiver {

    private static final int NOTIFICATION_ID_this = 3;
    private static final String PRIMARY_CHANNEL_ID_this =
            "primary_notification_channel_this";
    private NotificationManager mNotificationManager;
//larmManager alarmManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        mNotificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Deliver the notification.
        deliverNotification(context);
//        Intent notifyIntent = new Intent(context, ReminderBr_this.class);
//        PendingIntent notifyPendingIntent = PendingIntent.getBroadcast
//                (context, NOTIFICATION_ID_this, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);



           // alarmManager.cancel(notifyPendingIntent);
        //        int alarmId=intent.getExtras().getInt("Reminder_ID");
//        PendingIntent alarmIntent;
//        alarmIntent=PendingIntent.getBroadcast(context,NOTIFICATION_ID_this,intent,PendingIntent.FLAG_CANCEL_CURRENT);
//
//
//        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
//        alarmManager.cancel(alarmIntent);
//        Toast.makeText(context,"Alarm Off",Toast.LENGTH_SHORT).show();

    }

    /**
     * Builds and delivers the notification.
     *
     * @param context, activity context.
     */
    private void deliverNotification(Context context) {
        // Create the content intent for the notification, which launches
        // this activity
        Intent contentIntent = new Intent(context, TODO.class);

        PendingIntent contentPendingIntent = PendingIntent.getActivity
                (context, NOTIFICATION_ID_this, contentIntent, PendingIntent
                        .FLAG_UPDATE_CURRENT);
        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder
                (context, PRIMARY_CHANNEL_ID_this)
                .setSmallIcon(android.R.drawable.ic_btn_speak_now)
                .setContentTitle("To-day List")
                .setContentText("Reminder of this ToDo")
                .setContentIntent(contentPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL);
               // .addAction(R.drawable.common_google_signin_btn_icon_light_normal, "Delete Notification",contentPendingIntent);

        // Deliver the notification
        mNotificationManager.notify(NOTIFICATION_ID_this, builder.build());
    }



}


