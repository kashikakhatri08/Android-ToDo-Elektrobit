package com.example.Todo_List_Demo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.Todo_List_Demo.ROOM.My_Item_List;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends ArrayAdapter<My_Item_List> {
    ArrayList<My_Item_List> data;
Context context;
    private static class ViewHolder {
        TextView Item;
        CheckBox checkbox;

    }

    public UserAdapter(Context context, ArrayList<My_Item_List> data) {
        super(context, R.layout.item_list, data);
        this.data = data;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
         My_Item_List my_item_list = getItem(position);
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_list, parent, false);
            viewHolder.Item = (TextView) convertView.findViewById(R.id.item_title);
            viewHolder.checkbox = (CheckBox) convertView.findViewById(R.id.todocheck);

            convertView.setTag(viewHolder);


        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        convertView.setTag(viewHolder);
        viewHolder.Item.setTag(position);
        viewHolder.Item.setText(my_item_list.getItem() + "\n" + my_item_list.getDate());
        viewHolder.checkbox.setTag(position);
viewHolder.checkbox.setChecked(my_item_list.isChecked());
//viewHolder.checkbox.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//        int tag=(Integer) viewHolder.checkbox.getTag();
//        if(viewHolder.checkbox.isChecked()){
//            data.remove(tag);
//
//
//            UserAdapter.this.notifyDataSetChanged();
//        }
//
//    }
//});

//convertView.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//        if(data.get(position).isChecked()){
//            data.remove(position);
//
//        }
//        Toast.makeText(context,"here",Toast.LENGTH_LONG).show();
//        UserAdapter.this.notifyDataSetChanged();
//    }
//});



        return convertView;



}



    public void remove(int position) {
       data.remove(position);
        UserAdapter.this.notifyDataSetChanged();
    }
}