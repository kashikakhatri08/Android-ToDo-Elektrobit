package com.example.Todo_List_Demo.ROOM;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DAO {
    @Insert
    public  void ItemInsertion(My_Item_List itemList);
    @Query("Select *from My_Item_List")
   List<My_Item_List> getItem();
    @Query("Update My_Item_List set Item=:Item,Date=:date where Item=:Item2")
    void  updateItem(String Item, String date, String Item2);
    @Query("Update My_Item_List set Checked=:checked  where Item=:Item")
    void  updateCheckbox(Boolean checked, String Item);


    @Query("Delete from  My_Item_List where Item=:Item ")
    void  DeleteItem(String Item);

}
