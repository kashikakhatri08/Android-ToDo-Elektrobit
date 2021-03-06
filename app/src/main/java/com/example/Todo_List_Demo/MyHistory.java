package com.example.Todo_List_Demo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.room.Room;

import com.example.Todo_List_Demo.ROOM.MY_History_List;
import com.example.Todo_List_Demo.ROOM.MyDataBase;
import com.example.Todo_List_Demo.ROOM.MyHistoryDb;
import com.example.Todo_List_Demo.ROOM.My_Item_List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyHistory extends AppCompatActivity {
    private HistoryAdapter adapter;
    private String s1;
    private ArrayList<String> history_item;
    private ArrayList<String> get_data;
    private ListView ItemList;
    private Date date;
    private Date item_date;
    private MyHistoryDb db;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_history);

        Intent i = getIntent();
        ArrayList<String> item = (ArrayList<String>) i.getSerializableExtra(TODO.Extra_message2);
        ItemList = findViewById(R.id.history_ListView);
        SetupDB();
        get_data = new ArrayList<String>();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat inputParser =  new SimpleDateFormat("dd/MM/yyyy HH:mm");
        //String s1 = null;
        // Date time1,time2;
        // ArrayAdapter adapter;
        //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm")
//        String currentTime= SimpleDateFormat.getDateTimeInstance().format(new Date());
        history_item = new ArrayList<>();
       showItemList();
        for (int value = 0; value < item.size(); value++) {

            s1 = item.get(value);
            //  Toast.makeText(this, item.toString(), Toast.LENGTH_LONG).show();
            String[] string = s1.split("\n");
            String item1 = string[0];
            String item2 = string[1];
String item3 =string[2];

            MY_History_List newItem = new MY_History_List(item1+"\n"+item2+"\n"+item3);
            db = Room.databaseBuilder(MyHistory.this, MyHistoryDb.class, "My_History_Item_List").allowMainThreadQueries().build();
            db.historydao().ItemInsertion(newItem);
            showItemList();
       }
        //    Calendar now = Calendar.getInstance();
//            int hour = now.get(Calendar.HOUR);
//            int minute = now.get(Calendar.MINUTE);
//            int day=now.get(Calendar.DAY_OF_MONTH);
//            int month=now.get(Calendar.MONTH);
//            int year=now.get(Calendar.YEAR);


              //  date = inputParser.parse(day+"/"+month+"/"+year+"\n"+hour + ":" + minute);
               // item_date = inputParser.parse(item2+" "+item3);
             // Calendar  cal = Calendar.getInstance();

//
//
               // cal.setTime(item_date);
              //  if (cal.before(now)) {
                    // history_item.add(item1+"\n"+item_date);


                  // get_data.add(item.get(value));

                  //  Toast.makeText(this,item3,Toast.LENGTH_LONG).show();
                    // Toast.makeText(this, item.get(value) , Toast.LENGTH_LONG).show();

                  //  adapter = new ArrayAdapter<String>(this, R.layout.history_item_list, R.id.hisory_item_title, history_item);
           // history_item.addAll(get_data);



                    //  Toast.makeText(this, history_item.toString() , Toast.LENGTH_LONG).show();
                   // ItemList.setAdapter(adapter);
                    //adapter.notifyDataSetChanged();


               // }


        }




    private void showItemList() {

        List<MY_History_List>  list = db.historydao().getItem();



        adapter = new HistoryAdapter(this, (ArrayList<MY_History_List>) list);

        ItemList.setAdapter(adapter);


        adapter.notifyDataSetChanged();

        //  adapter.clear();
}


    private void SetupDB() {
        db = Room.databaseBuilder(MyHistory.this, MyHistoryDb.class, "My_History_Item_List").allowMainThreadQueries().build();


    }
    public void deleteHistory(View view) {
        View parent = (View) view.getParent();
        TextView itemTextView = (TextView) parent.findViewById(R.id.hisory_item_title);
        String item = String.valueOf(itemTextView.getText());
        String[] string = item.split("\n");
        String item1 = string[0];
        String item2 = string[1];
        db.historydao().DeleteItem(item);
        showItemList();


    }


}
