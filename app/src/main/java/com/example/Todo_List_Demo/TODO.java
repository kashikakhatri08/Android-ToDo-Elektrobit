package com.example.Todo_List_Demo;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;


import com.example.Todo_List_Demo.ROOM.My_Item_List;
import com.example.Todo_List_Demo.ROOM.MyDataBase;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.AlarmManagerCompat;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.room.Room;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

public class TODO extends AppCompatActivity   {
    private ListView ItemList;

   private Calendar c2;
    private ImageButton Delete;
    private ImageButton Alarm_button;
    private String text;
    private String currentTime;
    private Date currentDate;
    private SimpleDateFormat simpleDateFormat;
    private SimpleDateFormat simpleTimeFormat;
    private SimpleDateFormat simpleDate1;
    private SimpleDateFormat simpleDate2;
    private String todayDate;
    //ArrayList<String> array_list;
   // private ArrayAdapter<String> adapter;
    private MyDataBase db;
    //private CheckBox checkBoxbutton;
    private Date myDate;

    public static final String Extra_message1 = "com.example.setting.MESSAGE";
    public static final String alarm_message = "com.example.alarm.MESSAGE";
    public static final String Extra_message2 = "com.example.history.MESSAGE";
    private SharedPreferences theamsh;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch switchtheam;
    private long l2;
    String[] spin_list = {"Select Time","5"+" "+"min","10"+" "+"min","30"+" "+"min","1"+" "+"hour","3"+" "+"hour","6"+" "+"hour","12"+" "+"hour"};

    private NotificationManager myNotificationManager;
    private static final int NOTIFICATION_ID_this = 3;
    private static final String PRIMARY_CHANNEL_ID_this =
            "primary_notification_channel_this";
    private Calendar c1;
    String s1;
    String s2;
    private   DatabaseReference ref;
    String toastMessage;
    private AlarmManager alarmManager1;
    private static final String ACTION_UPDATE_NOTIFICATION =
            "com.example.TODO.ACTION_Delete_this_TODO_NOTIFICATION";
    private TODO.NotificationReceiver mReceiver = new TODO.NotificationReceiver();
    private static final int NOTIFICATION_ID_SET = 4;
    Spinner spin;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private int day,month,year,hour,minute;
private SharedPreferences sharedPreferences;
private  String UserData;
private  static boolean alarm_mode = false;
UserAdapter adapter;
FirebaseUser user;
String userId;
    FirebaseListAdapter listAdapter;
    FirebaseListOptions<My_Item_List> firebaseListOptions;
    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_t_o_d_o);



        Toolbar toolbar = findViewById(R.id.toolbar);


      setSupportActionBar(toolbar);
        ItemList = findViewById(R.id.listview);
        Delete = findViewById(R.id.DeleteButton);
        simpleTimeFormat=new SimpleDateFormat("MM/dd/yyyy hh:mm a");
user=FirebaseAuth.getInstance().getCurrentUser();
if(user==null){
    return;
}
userId=user.getUid();
        switchtheam=findViewById(R.id.themeSwitch);
        switchtheam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                  if(switchtheam.isChecked())
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


else

                      AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

              SharedPreferences  sh1 = getApplicationContext().getSharedPreferences("MySwitch", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sh1.edit();
   myEdit.putBoolean("switchTheme", switchtheam.isChecked());
myEdit.commit();
            }
        });
        SharedPreferences  sh1 = getApplicationContext().getSharedPreferences("MySwitch", MODE_PRIVATE);
        Boolean s1 = sh1.getBoolean("switchTheme", false);
        if (s1) {
            switchtheam.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        } else {
           switchtheam.setChecked(false);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        }
        currentTime = SimpleDateFormat.getDateTimeInstance().format(new Date());
        currentDate = Calendar.getInstance().getTime();


        simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        todayDate = simpleDateFormat.format(currentDate);
        SetupDB();
        RelativeLayout mainLayout1=(RelativeLayout)findViewById(R.id.main_layout);
        LayoutInflater inflater1=getLayoutInflater();
        View myLayout1=inflater1.inflate(R.layout.item_list,mainLayout1,false);

        firebaseListOptions=new FirebaseListOptions.Builder<My_Item_List>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("My Todo").child(userId),My_Item_List.class)
                .setLayout(R.layout.item_list)
                .build();

       listAdapter=new FirebaseListAdapter<My_Item_List>(firebaseListOptions) {
            @Override
            protected void populateView(@NonNull View v, @NonNull My_Item_List model, int position) {
                TextView textView=(TextView) v.findViewById(R.id.item_title);
                CheckBox checkBox=(CheckBox) v.findViewById(R.id.todocheck);
                textView.setText(model.getItem()+"\n"+model.getDate());
                checkBox.setChecked(model.isChecked());

            }


        };
        ItemList.setAdapter(listAdapter);

        simpleDate1=new SimpleDateFormat("dd/MM/yyyy HH:mm");

       // showItemList();



        myNotificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        createNotificationChannel();


        alarmManager1 = (AlarmManager) getSystemService(ALARM_SERVICE);
        registerReceiver(mReceiver, new IntentFilter(ACTION_UPDATE_NOTIFICATION));


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText itemEditText = new EditText(TODO.this);
                final EditText dateEditText = new EditText(TODO.this);

                final LinearLayout layout = new LinearLayout(TODO.this);
                layout.setOrientation(LinearLayout.VERTICAL);

                layout.addView(itemEditText);

                dateEditText.setClickable(true);
                dateEditText.setFocusable(false);
                dateEditText.setInputType(InputType.TYPE_NULL);
                dateEditText.setOnClickListener(new View.OnClickListener() {

                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        Calendar mcurrentcalender = Calendar.getInstance();
                        year = mcurrentcalender.get(Calendar.YEAR);
                        month = mcurrentcalender.get(Calendar.MONTH);
                        day = mcurrentcalender.get(Calendar.DAY_OF_MONTH);
                        DatePickerDialog datePickerDialog = new DatePickerDialog(TODO.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, final int i, final int i1, final int i2) {
                                dateEditText.setText(i2+"-"+i1+"-"+i);
                                final int this_year=i;
                                final int this_month=i1;
                                final int this_day=i2;
                                Calendar this_c = Calendar.getInstance();
                                year = this_c.get(Calendar.HOUR_OF_DAY);
                                month = this_c.get(Calendar.MINUTE);
                                TimePickerDialog mTimePicker;
                                mTimePicker = new TimePickerDialog(TODO.this, new TimePickerDialog.OnTimeSetListener() {
                                    @SuppressLint("SetTextI18n")
                                    @Override
                                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                                        dateEditText.setText(this_day+"/"+this_month+"/"+this_year+"\n"+selectedHour+":"+selectedMinute);
                 }
                                }, mHour, mMinute, false);//Yes 24 hour time
                                mTimePicker.setTitle("Select Time");
                                mTimePicker.show();

                            }
                        }, year, month, day);

                        datePickerDialog.show();

                    } });
                layout.addView(dateEditText);
                AlertDialog dialog = new AlertDialog.Builder(TODO.this)
                        .setTitle("Add new Todo")
                        .setMessage("What's next?")

                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String Item = String.valueOf(itemEditText.getText());
                                String date = String.valueOf(dateEditText.getText());
                                My_Item_List newItem = new My_Item_List(Item, date, false);
                                db = Room.databaseBuilder(TODO.this, MyDataBase.class, "My_Todo").allowMainThreadQueries().build();
                                db.dao().ItemInsertion(newItem);

//                                sharedPreferences=getApplicationContext().getSharedPreferences("getUser", MODE_PRIVATE);
//                                UserData=sharedPreferences.getString("Current User","");
                              //  List<My_Item_List> list = db.dao().getItem();
                                FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
//.getReference().child("My TODO List").child(UserData).push().setValue(map);
                                ref=firebaseDatabase.getReference("My Todo");

                                ref.child(userId).push().setValue(newItem);
                                Toast.makeText(TODO.this, "values added to Firebase Storage successfully", Toast.LENGTH_LONG).show();


                                FirebaseFirestore db =FirebaseFirestore.getInstance();

        Map<String,Object> data = new HashMap<>();
   data.put("ListItem",Item+""+date);
       db.collection("Item").add(data).addOnCompleteListener(new  OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if(task.isSuccessful()){

                    Toast.makeText(TODO.this,"values added to cloud successfully",Toast.LENGTH_LONG).show();

                }
            }
        });


                                showItemList();
                            }
                        })


                        .setNegativeButton("Cancel", null)

                        .create();

                dialog.setView(layout);
                dialog.show();

            }
        });



    }

    private void SetupDB() {
        db = Room.databaseBuilder(TODO.this, MyDataBase.class, "My_Todo").allowMainThreadQueries().build();


    }



    private void showItemList() {

            List<My_Item_List> list = db.dao().getItem();
if(adapter==null) {

    adapter = new UserAdapter(this, (ArrayList<My_Item_List>) list);
    ItemList.setAdapter(adapter);
    adapter.notifyDataSetChanged();
}
else{
    adapter.clear();


    adapter.addAll(list);

    adapter.notifyDataSetChanged();


}

        }

    @Override
    protected void onStop() {
        super.onStop();

    }

    public void createNotificationChannel() {


        myNotificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {


            NotificationChannel notificationChannel = new NotificationChannel
                    (PRIMARY_CHANNEL_ID_this,
                            "Who Wrote It notification",
                            NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription
                    ("Notifies every Day to Search for a book");

            myNotificationManager.createNotificationChannel(notificationChannel);
        }
    }
    public void setCheck(View view) {
        View parent = (View) view.getParent();
        TextView itemTextView = (TextView) parent.findViewById(R.id.item_title);

        String itemTextString = String.valueOf(itemTextView.getText());
        String[] string = itemTextString.split("\n");
        final String item1 = string[0];
        final String item2 = string[1];
        CheckBox checkbox = (CheckBox) parent.findViewById(R.id.todocheck);

        checkbox.setChecked(checkbox.isChecked());
        db.dao().updateCheckbox(checkbox.isChecked(), item1);


      databaseReference.child("TODO List").child(UserData).child("checkbox Value").setValue(checkbox.isChecked());
        Toast.makeText(this, String.valueOf(checkbox.isChecked()), Toast.LENGTH_LONG);

    }







    public void edit(View view) {
        View parent = (View) view.getParent();
        TextView itemTextView = (TextView) parent.findViewById(R.id.item_title);

        String itemTextString = String.valueOf(itemTextView.getText());
        String[] string = itemTextString.split("\n");
        final String item1 = string[0];
        final String item2 = string[1];
        final String item3 = string[1];


        final EditText itemUpdateText = new EditText(TODO.this);
        final EditText dateUpdateText = new EditText(TODO.this);

        LinearLayout layout = new LinearLayout(TODO.this);
        layout.setOrientation(LinearLayout.VERTICAL);

        layout.addView(itemUpdateText);

        dateUpdateText.setClickable(true);
        dateUpdateText.setFocusable(false);
        dateUpdateText.setInputType(InputType.TYPE_NULL);
        dateUpdateText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentcalender = Calendar.getInstance();
                year = mcurrentcalender.get(Calendar.YEAR);
                month = mcurrentcalender.get(Calendar.MONTH);
                day = mcurrentcalender.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(TODO.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, final int i, final int i1, final int i2) {
                        dateUpdateText.setText(i2+"-"+i1+"-"+i);
                        final int this_year=i;
                        final int this_month=i1;
                        final int this_day=i2;
                        Calendar this_c = Calendar.getInstance();
                        year = this_c.get(Calendar.HOUR_OF_DAY);
                        month = this_c.get(Calendar.MINUTE);
                        TimePickerDialog mTimePicker;
                        mTimePicker = new TimePickerDialog(TODO.this, new TimePickerDialog.OnTimeSetListener() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                                dateUpdateText.setText(this_day+"/"+this_month+"/"+this_year+"\n"+selectedHour+":"+selectedMinute);

                            }
                        }, mHour, mMinute, false);//Yes 24 hour time
                        mTimePicker.setTitle("Select Time");
                        mTimePicker.show();

                    }
                }, year, month, day);

                datePickerDialog.show();
//
            } });
        layout.addView(dateUpdateText);
        AlertDialog dialog = new AlertDialog.Builder(TODO.this)
                .setTitle("Edit Todo")
                .setMessage("What's the Change?")
                .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String Item = String.valueOf(itemUpdateText.getText());
                        String date = String.valueOf(dateUpdateText.getText());
                        //    ItemList1 newItem = new ItemList1(Item,date,false);

                        db.dao().updateItem(Item, date, item1);
        showItemList();


                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.setView(layout);
        dialog.show();

    }


    public void delete(View view) {
        View parent = (View) view.getParent();
        TextView itemTextView = (TextView) parent.findViewById(R.id.item_title);
        String item = String.valueOf(itemTextView.getText());
        String[] string = item.split("\n");
        String item1 = string[0];
        String item2 = string[1];
        db.dao().DeleteItem(item1);
        showItemList();


    }





public void setAlarm(View view){
    View parent = (View) view.getParent();
    final TextView itemTextView = (TextView) parent.findViewById(R.id.item_title);
        final String itemTextString = String.valueOf(itemTextView.getText());
        final String[] stringitemTextView = itemTextString.split("\n");
        final String itemTextView1 = stringitemTextView[0];
        final String itemTextView2 = stringitemTextView[1];
    final String itemTextView3 = stringitemTextView[2];

        List<My_Item_List> list = db.dao().getItem();
        ArrayList<My_Item_List> lst = new ArrayList<>(list.size());
        lst.addAll(list);
        final ArrayList<String> aryLst = new ArrayList<String>();
        for (int i = 0; i < lst.size(); i++) {
            aryLst.add(lst.get(i).getItem() + "\n" + lst.get(i).getDate());
        }


   Spinner spin = new Spinner(TODO.this);
    ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,spin_list);
    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


    spin.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    spin.setAdapter(aa);

    spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(!spin_list[i].equals("Select Time")){
            String spin_value=spin_list[i];

            String[] string1 = spin_value.split(" ");
            int itemreminder = Integer.parseInt(string1[0]);

            for (int value = 0; value < aryLst.size(); value++) {

                String forarylist = aryLst.get(value);


                    if(itemTextString.equals(forarylist)) {
                        try {
                            Date   date1 = simpleDate1.parse(itemTextView2+" "+itemTextView3);
                            c2 = Calendar.getInstance();

//
//
c2.setTime(date1);
c2.add(Calendar.MONTH,+1);

                    }
                        catch (Exception e) {
       e.printStackTrace();

    }
                        Intent notifyIntent = new Intent(TODO.this, ReminderBr_this.class);

                        PendingIntent notifyPendingIntent = PendingIntent.getBroadcast
                                (TODO.this, NOTIFICATION_ID_this, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                        if(spin_list[i].equals("5" + " " + "min") || spin_list[i].equals("10" + " " + "min") || spin_list[i].equals("30" + " " + "min")){
                            c2.add(Calendar.MINUTE,-itemreminder);

                        }else if(spin_list[i].equals("1" + " " + "hour") || spin_list[i].equals("3" + " " + "hour") ||spin_list[i].equals("6" + " " + "hour") || spin_list[i].equals("12" + " " + "hour")){
                            c2.add(Calendar.HOUR_OF_DAY,-itemreminder);
                        }
                        if (alarmManager1 != null) {
                            alarmManager1.set(AlarmManager.RTC_WAKEUP,
                                    c2.getTimeInMillis(),notifyPendingIntent);
                            alarm_mode=true;
                        }
                       
                    }
                Intent updateIntent = new Intent(ACTION_UPDATE_NOTIFICATION);
                PendingIntent updatePendingIntent = PendingIntent.getBroadcast
                        (TODO.this, NOTIFICATION_ID_SET, updateIntent, PendingIntent.FLAG_ONE_SHOT);

                NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
                notifyBuilder.addAction(R.drawable.common_google_signin_btn_icon_light_normal, "Delete Notification", updatePendingIntent);
                myNotificationManager.notify(NOTIFICATION_ID_SET, notifyBuilder.build());

            }
            }
        }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
});






    final AlertDialog dialog = new AlertDialog.Builder(TODO.this)
            .setTitle("Add Before time For reminder")
            .setMessage("choose from spinner")

            .setView(spin)
            .setPositiveButton("add",new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                   if(alarm_mode) {
                       toastMessage = "Alarm On!";
                       Toast.makeText(getBaseContext(), toastMessage, Toast.LENGTH_SHORT).show();
                   }
                   else{
                       toastMessage = "No Alarm set";
                       Toast.makeText(getBaseContext(), toastMessage, Toast.LENGTH_SHORT).show();
                   }
                }
            })
.setNeutralButton("Stop Alarm", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        Intent notifyIntent = new Intent(TODO.this, ReminderBr_this.class);
        PendingIntent notifyPendingIntent = PendingIntent.getBroadcast
                (TODO.this, NOTIFICATION_ID_this, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        myNotificationManager.cancelAll();


        alarmManager1.cancel(notifyPendingIntent);


        //Set the toast message for the "off" case
        toastMessage = "Alarm Off!";
        Toast.makeText(getBaseContext(),toastMessage,Toast.LENGTH_SHORT).show();

    }
})

            .create();

    dialog.show();


}


    private NotificationCompat.Builder getNotificationBuilder(){
        Intent notificationIntent = new Intent(this, Settings.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(this,
                NOTIFICATION_ID_SET, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID_this);
        notifyBuilder.setContentTitle("To-day list!");
        notifyBuilder .setContentText("Your Alarm is set");

        notifyBuilder .setSmallIcon(android.R.drawable.ic_btn_speak_now);
        notifyBuilder.setContentIntent(notificationPendingIntent);
        notifyBuilder.setAutoCancel(true);
        notifyBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        notifyBuilder.setDefaults(NotificationCompat.DEFAULT_ALL);
        return notifyBuilder;

    }
    public void cancelNotification() {
        myNotificationManager.cancel(NOTIFICATION_ID_SET);

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
        }}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();
        List<My_Item_List> list = db.dao().getItem();
        ArrayList<My_Item_List> lst = new ArrayList<>(list.size());
        lst.addAll(list);

        ArrayList<String> aryLst1 = new ArrayList<String>();
        for (int i = 0; i < lst.size(); i++) {
            aryLst1.add(lst.get(i).getDate());
        }

        ArrayList<String> aryLst2 = new ArrayList<String>();
        for (int i = 0; i < lst.size(); i++) {
            aryLst2.add(lst.get(i).getItem() + "\n" + lst.get(i).getDate());
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this,
                    Settings.class);
            settingsIntent.putExtra(Extra_message1, aryLst1);
            startActivity(settingsIntent);

            return true;
        }
        if (id == R.id.action_history) {
            Intent historysIntent = new Intent(this,
                    MyHistory.class);
            historysIntent.putExtra(Extra_message2, aryLst2);
            startActivity(historysIntent);

            return true;
        }
        if (id == R.id.action_log_out) {

            FirebaseAuth.getInstance().signOut();
            Toast.makeText(TODO.this, "LogOut", Toast.LENGTH_LONG).show();
            startActivity(new Intent(TODO.this, StartActivity.class));
        }


        return super.onOptionsItemSelected(item);
    }






}

