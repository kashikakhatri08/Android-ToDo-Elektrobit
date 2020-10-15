package com.example.Todo_List_Demo.ROOM;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class My_Item_List {

    @PrimaryKey(autoGenerate = true)
        int ID;
    String Item;
    String Date;
    boolean Checked;

    public My_Item_List(String item, String date, boolean Checked) {

        this.Item = item;
        this.Date = date;
        this.Checked=Checked;

    }

    public boolean isChecked() {
        return Checked;
    }

    public void setChecked(boolean checked) {
        Checked = checked;
    }

    public My_Item_List() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getItem() {
        return Item;
    }

    public void setItem(String item) {
        Item = item;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
