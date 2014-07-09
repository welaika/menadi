package com.welaika.menadi.adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.welaika.menadi.R;
import com.welaika.menadi.db.Room;

import java.util.List;

public class SpinnerRoomAdapter extends ArrayAdapter implements SpinnerAdapter {
    private final Context context;
    private final List<Room> rooms;

    public SpinnerRoomAdapter(Context context, List<Room> rooms) {
        super(context, R.layout.simple_list_room, rooms);

        this.context = context;
        this.rooms = rooms;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.simple_list_room, null);
        TextView textView = (TextView) rowView.findViewById(R.id.room_text);
        textView.setText(rooms.get(position).name);
        return rowView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.simple_list_room, null);
        TextView textView = (TextView) rowView.findViewById(R.id.room_text);
        textView.setText(rooms.get(position).name);
        return rowView;
    }
}
