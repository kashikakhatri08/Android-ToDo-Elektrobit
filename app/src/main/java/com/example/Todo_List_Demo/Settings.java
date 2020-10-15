package com.example.Todo_List_Demo;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Settings extends AppCompatActivity  implements
        AdapterView.OnItemSelectedListener {
    private AlarmManager MyAlarmManager;
    private static final int NOTIFICATION_ID = 2;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch switchbtn;

    SharedPreferences sh1;
    AlarmManager alarmManager;
    String s1;
    String s2;
    ArrayList<String> item;
    Date item_date;
    String item2;
    SimpleDateFormat simpleDateFormat;
    private static final int Reminder_ID = 1;

    private NotificationManager mNotificationManager;
    Calendar c;
    private NotificationManager mNotifyManager;
    private static final String ACTION_UPDATE_NOTIFICATION =
            "com.example.TODO.ACTION_Delete_NOTIFICATION";
    private NotificationReceiver mReceiver = new NotificationReceiver();
    private static final String DELETE_ACTION = "com.example.Todo_List_Demo.delete";
    private static final String PRIMARY_CHANNEL_ID_TODO =
            "primary_notification_channel_todo";
    String toastMessage;
    Button stop;
    String[] minute = {"Select Time", "5" + " " + "min", "10" + " " + "min", "30" + " " + "min", "1" + " " + "hour", "3" + " " + "hour", "6" + " " + "hour", "12" + " " + "hour"};

    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_settings);
        sh1 = getApplicationContext().getSharedPreferences("MyOwnSwitch", MODE_PRIVATE);

        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) Settings.this);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, minute);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spin.setAdapter(aa);

        Intent i = getIntent();
        //switchbtn = findViewById(R.id.themeSwitch);
        sh1 = getApplicationContext().getSharedPreferences("MyOwnSwitch", MODE_PRIVATE);
        // saveState = new SaveState(this);
        //  switchbtn = findViewById(R.id.switch1);
        item = (ArrayList<String>) i.getSerializableExtra(TODO.Extra_message1);
        stop = (Button) findViewById(R.id.stop);
        mNotificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        createNotificationChannel();


        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        registerReceiver(mReceiver, new IntentFilter(ACTION_UPDATE_NOTIFICATION));

        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        // alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {

        if (!minute[position].equals("Select Time")) {

            for (int value = 0; value < item.size(); value++) {

                String forarylist = item.get(value);
                final String[] stringitemTextView = forarylist.split("\n");
                final String itemTextView1 = stringitemTextView[0];
                final String itemTextView2 = stringitemTextView[1];

                String spin_value = minute[position];

                String[] string1 = spin_value.split(" ");
                int itemreminder = Integer.parseInt(string1[0]);


                try {
                    Date date1 = simpleDateFormat.parse(itemTextView1 + " " + itemTextView2);
                    c = Calendar.getInstance();

//
//
                    c.setTime(date1);
                    c.add(Calendar.MONTH, +1);

                } catch (Exception e) {
                    e.printStackTrace();

                }
                Intent notifyIntent = new Intent(Settings.this, ReminderBR.class);


                PendingIntent notifyPendingIntent = PendingIntent.getBroadcast
                        (Settings.this, Reminder_ID, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);


                if (minute[position].equals("5" + " " + "min") || minute[position].equals("10" + " " + "min") || minute[position].equals("30" + " " + "min")) {
                    c.add(Calendar.MINUTE, -itemreminder);


                } else if (minute[position].equals("1" + " " + "hour") || minute[position].equals("3" + " " + "hour") || minute[position].equals("6" + " " + "hour") || minute[position].equals("12" + " " + "hour")) {
                    c.add(Calendar.HOUR_OF_DAY, -itemreminder);
                }
//If the Toggle is turned on, set the repeating alarm with a 15 minute interval
                if (alarmManager != null) {
                    alarmManager.set(AlarmManager.RTC_WAKEUP,
                            c.getTimeInMillis(), notifyPendingIntent);
                }
                toastMessage = "Alarm On!";
                Toast.makeText(getBaseContext(), toastMessage, Toast.LENGTH_SHORT).show();

            }
            Intent updateIntent = new Intent(ACTION_UPDATE_NOTIFICATION);
            PendingIntent updatePendingIntent = PendingIntent.getBroadcast
                    (this, NOTIFICATION_ID, updateIntent, PendingIntent.FLAG_ONE_SHOT);

            NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
            notifyBuilder.addAction(R.drawable.common_google_signin_btn_icon_light_normal, "Delete Notification", updatePendingIntent);
            //  notifyBuilder.addAction(R.drawable.common_google_signin_btn_icon_light_normal, "Specific Todo Delete Notification", updatePendingIntent);
            mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());


        }

    }


    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        Toast.makeText(getApplicationContext(), "select before time for reminder", Toast.LENGTH_LONG).show();


    }

    public void createNotificationChannel() {

        // Create a notification manager object.
        mNotifyManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Notification channels are only available in OREO and higher.
        // So, add a check on SDK version.
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {

            // Create the NotificationChannel with all the parameters.
            NotificationChannel notificationChannel = new NotificationChannel
                    (PRIMARY_CHANNEL_ID_TODO,
                            "Who Wrote It notification",
                            NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription
                    ("Notifies every Day to Search for a book");

            mNotifyManager.createNotificationChannel(notificationChannel);
        }
    }


    public void stop(View view) {
        Intent notifyIntent = new Intent(this, ReminderBR.class);

        PendingIntent notifyPendingIntent = PendingIntent.getBroadcast
                (this, Reminder_ID, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mNotifyManager.cancelAll();

        if (alarmManager != null) {
            alarmManager.cancel(notifyPendingIntent);
        }

        //Set the toast message for the "off" case
        toastMessage = "Alarm Off!";
        Toast.makeText(getBaseContext(), toastMessage, Toast.LENGTH_SHORT).show();


    }

    private NotificationCompat.Builder getNotificationBuilder() {
        Intent notificationIntent = new Intent(this, Settings.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(this,
                NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID_TODO);
        notifyBuilder.setContentTitle("To-day list!");
        notifyBuilder.setContentText("Your Alarm is set");

        notifyBuilder.setSmallIcon(android.R.drawable.ic_btn_speak_now);
        notifyBuilder.setContentIntent(notificationPendingIntent);
        notifyBuilder.setAutoCancel(true);
        notifyBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        notifyBuilder.setDefaults(NotificationCompat.DEFAULT_ALL);
        return notifyBuilder;

    }

    public void cancelNotification() {
        mNotifyManager.cancel(NOTIFICATION_ID);

    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    public class NotificationReceiver extends BroadcastReceiver {

        public NotificationReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            cancelNotification();
            // Update the notification
        }


    }


}