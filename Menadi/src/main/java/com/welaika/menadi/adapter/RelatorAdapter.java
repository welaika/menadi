package com.welaika.menadi.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.welaika.menadi.R;
import com.welaika.menadi.db.Relator;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class RelatorAdapter extends ArrayAdapter<Relator> {

    private  final Context context;
    private final List<Relator> relators;

    public RelatorAdapter(Context context, List<Relator> relators) {
        super(context, R.layout.grid_cell, relators);

        this.context = context;
        this.relators = relators;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = (View) convertView;
        if (view == null) {
            view = new View(context);
        }
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RelativeLayout rowView = (RelativeLayout) inflater.inflate(R.layout.grid_cell, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.relator_text);
        textView.setText(relators.get(position).name);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.relator_image);


        if(relators.get(position).url != null && !relators.get(position).url.isEmpty()) {

                Picasso
                        .with(context)
                        .load(relators.get(position).url)
                        .resize(400, 400)
                        .centerCrop()
                        .placeholder(R.drawable.default_user)
                        .error(R.drawable.default_user)
                        .into(imageView);
        } else {
            Picasso
                    .with(context)
                    .load(R.drawable.default_user)
                    .resize(400, 400)
                    .centerCrop()
                    .into(imageView);
        }
        return rowView;
    }
}
