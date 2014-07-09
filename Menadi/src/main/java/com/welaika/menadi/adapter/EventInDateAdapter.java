package com.welaika.menadi.adapter;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.welaika.menadi.R;
import com.welaika.menadi.db.EventInDate;

import java.util.List;

public class EventInDateAdapter extends ArrayAdapter<EventInDate>{

    private final Context context;
    private final List<EventInDate> events;

    public EventInDateAdapter(Context context, List<EventInDate> events) {
        super(context, R.layout.simple_list_event, events);

        this.context = context;
        this.events = events;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.simple_list_event, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.event_text);
        TextView textView2 = (TextView) rowView.findViewById(R.id.event_text2);
        TextView textView3 = (TextView) rowView.findViewById(R.id.event_text3);
        textView.setText(events.get(position).startH);

        textView2.setText(events.get(position).idA.title);

        textView3.setText(events.get(position).room.name);

        CheckBox selection = (CheckBox) rowView.findViewById(R.id.event_checkbox);
        selection.setChecked(events.get(position).isChecked);

        selection.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                Toast.makeText(getContext(), "clicked on checkbox", Toast.LENGTH_SHORT).show();
                if (isChecked) {
                    Log.v("TEST", "isChecked");
                    events.get(position).setChecked();
                    events.get(position).save();
                    Log.v("TEST", "isChecked = " + events.get(position).isChecked);
                }
                else {
                    Log.v("TEST", "isNotChecked");
                    events.get(position).setNotChecked();
                    events.get(position).save();
                    Log.v("TEST", "isChecked = " + events.get(position).isChecked);
                }
            }
        });


        return rowView;
    }

}
