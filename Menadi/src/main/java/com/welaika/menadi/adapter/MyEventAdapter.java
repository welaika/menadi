package com.welaika.menadi.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.welaika.menadi.R;
import com.welaika.menadi.db.EventInDate;

import java.util.List;

public class MyEventAdapter extends ArrayAdapter<EventInDate> {

    private final Context context;
    private final List<EventInDate> events;

    public MyEventAdapter(Context context, List<EventInDate> events) {
        super(context, R.layout.simple_row_my_event, events);

        this.context = context;
        this.events = events;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.simple_row_my_event, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.my_event_text);
        TextView textView2 = (TextView) rowView.findViewById(R.id.my_event_text2);
        TextView textView3 = (TextView) rowView.findViewById(R.id.my_event_text3);

        textView.setText(events.get(position).startH);
        textView2.setText(events.get(position).idA.title);
        textView3.setText(events.get(position).room.name);

        return rowView;
    }
}
