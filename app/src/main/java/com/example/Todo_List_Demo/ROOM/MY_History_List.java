package com.example.Todo_List_Demo.ROOM;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MY_History_List {
    @PrimaryKey
    @NonNull
    String Item;

    public MY_History_List(String item) {
        Item = item;
    }

    public MY_History_List() {
    }


    public String getItem() {
        return Item;
    }

    public void setItem(String item) {
        Item = item;
    }
}
