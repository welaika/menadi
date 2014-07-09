package com.welaika.menadi.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.welaika.menadi.R;

import java.util.List;


public class NotificationAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final List<String> notification;

    public NotificationAdapter(Context context, List<String> notification) {
        super(context, R.layout.notifications_result_item, notification);

        this.context = context;
        this.notification = notification;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.notifications_result_item, parent, false);

        TextView title = (TextView) rowView.findViewById(R.id.notification_title);
        TextView text = (TextView) rowView.findViewById(R.id.notification_text);

        title.setText("The Title Of The Notification");
        text.setText("The text of the notification. very long text. so bright text. so nice. so awesome. so so so incredible.");
        return rowView;
    }
}
