package com.welaika.menadi.adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.welaika.menadi.R;

public class DrawableAdapter extends ArrayAdapter {

    Context context;
    String[] mMenu;

    int[] listItemBackground = new int[] { R.drawable.fancy_list_background1,
            R.drawable.fancy_list_background2 };

    public DrawableAdapter(Context context, String[] mMenu) {
        super(context, R.layout.drawer_list_item, mMenu);

        this.context = context;
        this.mMenu = mMenu.clone();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = new View(context);
        }
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout rowView = (LinearLayout) inflater.inflate(R.layout.drawer_list_item, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.drawer_item);
        textView.setText(mMenu[position]);

        int listItemBackgroundPosition = position % listItemBackground.length;
        rowView.setBackgroundResource(listItemBackground[listItemBackgroundPosition]);
        return rowView;
    }
}
