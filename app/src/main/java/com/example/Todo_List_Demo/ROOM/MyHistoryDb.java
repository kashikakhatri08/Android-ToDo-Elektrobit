package com.example.Todo_List_Demo.ROOM;


import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = MY_History_List.class,version = 1,exportSchema = false)
public abstract class MyHistoryDb extends RoomDatabase{
    public  abstract  History_DAO historydao();

}
