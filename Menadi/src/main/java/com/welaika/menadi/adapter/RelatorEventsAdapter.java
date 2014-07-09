package com.welaika.menadi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.welaika.menadi.R;
import com.welaika.menadi.db.Event;

import java.util.List;

public class RelatorEventsAdapter extends ArrayAdapter<Event> {

    private Context context;
    private List<Event> events;


    public RelatorEventsAdapter(Context context, List<Event> events) {
        super(context, R.layout.simple_list_event, events);

        this.context = context;
        this.events = events;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = new View(context);
        }

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RelativeLayout rowView = (RelativeLayout) inflater.inflate(R.layout.simple_list_event, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.event_text);
        textView.setText(events.get(position).title);

        return view;
    }
}
