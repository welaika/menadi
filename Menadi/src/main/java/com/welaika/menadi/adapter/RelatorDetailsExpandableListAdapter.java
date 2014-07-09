package com.welaika.menadi.adapter;


import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.welaika.menadi.R;
import com.welaika.menadi.db.Relator;

import java.util.ArrayList;

public class RelatorDetailsExpandableListAdapter extends BaseExpandableListAdapter {

    private String[] detailsTitles;
    private LayoutInflater inflater;
    private Relator relator;
    private Activity activity;
    private View view;

    public RelatorDetailsExpandableListAdapter(String[] parents, Relator relator) {
        this.detailsTitles = parents;
        this.relator = relator;

    }

    public void setInflater(LayoutInflater inflater, Activity activity) {
        this.inflater = inflater;
        this.activity = activity;
    }

    @Override
    public int getGroupCount() {
            return detailsTitles.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if(groupPosition == 2) {
            return relator.events().size();
        }
        else if (groupPosition == 3)
            return 0;
        else
            return 1;
    }

    @Override
    public Object getGroup(int i) {
        return null;
    }

    @Override
    public Object getChild(int i, int i2) {
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i2) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.expandablelist_relator_details_parent, null);
        }


        TextView textView = (TextView) convertView.findViewById(R.id.textView_parent);
        textView.setText(detailsTitles[groupPosition]);
/*
        ImageView icon = (ImageView) convertView.findViewById(R.id.exp_list_icon);
        icon.setImageResource(R.drawable.ic_action_expand);
*/
        view = convertView;

        return convertView;
    }


    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        TextView textView;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.expandablelist_relator_details_child, null);
        }
        textView = (TextView) convertView.findViewById(R.id.textView_child);

        switch (groupPosition) {
            case 0:
                textView.setText(relator.info);
                break;
            case 1:
                textView.setText(relator.email);
                break;
            case 2:
                textView.setText(relator.events().get(childPosition).eventInD.idA.title);
                break;
            case 3:
                //textView.setText(relator.contacts.get(childPosition));
                break;
            default:
                break;
        }
        return convertView;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        switch (groupPosition) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                return true;
            case 3:
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }
}
