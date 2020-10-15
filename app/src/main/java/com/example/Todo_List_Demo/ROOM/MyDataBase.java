package com.example.Todo_List_Demo.ROOM;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = My_Item_List.class,version = 1,exportSchema = false)
public abstract  class MyDataBase extends RoomDatabase {
    public  abstract  DAO dao();
}
