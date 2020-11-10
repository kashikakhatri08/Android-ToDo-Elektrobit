package com.example.Todo_List_Demo.ROOM;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.*;
import java.util.List;

@Dao
public interface History_DAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
      void ItemInsertion(MY_History_List itemList);
    @Query("Select *from MY_History_List ")
    List<MY_History_List> getItem();
    @Query("Update MY_History_List set Item=:Item where Item=:Item2")
    void  updateItem(String Item, String Item2);


    @Query("Delete from  MY_History_List where Item=:Item ")
    void  DeleteItem(String Item);

}
