package com.example.Todo_List_Demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.Todo_List_Demo.ROOM.My_Item_List;

import java.util.ArrayList;

public class UserAdapter extends ArrayAdapter<My_Item_List> {
private static class ViewHolder{
    TextView Item;
    CheckBox checkbox;

}
public UserAdapter(Context context, ArrayList<My_Item_List> data){
    super(context,R.layout.item_list,data);
}

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    My_Item_List my_item_list=getItem(position);
    ViewHolder viewHolder;
    if(convertView==null){
        viewHolder=new ViewHolder();
        LayoutInflater inflater=LayoutInflater.from(getContext());
        convertView=inflater.inflate(R.layout.item_list,parent,false);
        viewHolder.Item=(TextView)convertView.findViewById(R.id.item_title);
        viewHolder.checkbox=(CheckBox) convertView.findViewById(R.id.todocheck);
        convertView.setTag(viewHolder);


    }
    else{
        viewHolder=(ViewHolder)convertView.getTag();
    }

    viewHolder.Item.setText(my_item_list.getItem()+"\n"+my_item_list.getDate());
    viewHolder.checkbox.setChecked(my_item_list.isChecked());


        return convertView;
    }

}
