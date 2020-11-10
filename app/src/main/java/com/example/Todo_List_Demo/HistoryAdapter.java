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

import com.example.Todo_List_Demo.ROOM.MY_History_List;
import com.example.Todo_List_Demo.ROOM.My_Item_List;

import java.util.ArrayList;

public class HistoryAdapter extends ArrayAdapter<MY_History_List> {
    ArrayList<MY_History_List> data;
    Context context;
    private static class ViewHolder {
        TextView Item; CheckBox checkbox;

    }

    public HistoryAdapter(Context context, ArrayList<MY_History_List> data) {
        super(context, R.layout.history_item_list, data);
        this.data = data;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        MY_History_List my_history_list = getItem(position);
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.history_item_list, parent, false);
            viewHolder.Item = (TextView) convertView.findViewById(R.id.hisory_item_title);


            convertView.setTag(viewHolder);


        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        convertView.setTag(viewHolder);
        viewHolder.Item.setTag(position);
        viewHolder.Item.setText(my_history_list.getItem());

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



}