package com.welaika.menadi.fragments;


import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.welaika.menadi.MainActivity;
import com.welaika.menadi.adapter.EventInDateAdapter;
import com.welaika.menadi.adapter.MyEventAdapter;
import com.welaika.menadi.adapter.SpinnerRoomAdapter;
import com.welaika.menadi.db.EventInDate;
import com.welaika.menadi.db.Room;

import java.util.ArrayList;
import java.util.List;

public class MyEventFragment extends ListFragment implements ActionBar.OnNavigationListener {

    private List<EventInDate> events, aux;
    private List<Room> rooms;
    private String day;

    public MyEventFragment(){}


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

        int i = 0;
        while (getArguments().getString("day"+i) == null)
            i++;

        day = getArguments().getString("day"+i);

        //events = EventInDate.getAllChecked(day);
        aux = EventInDate.getAll(day);
        events = new ArrayList<EventInDate>();
        for (EventInDate ev : aux) {
            if (ev.isChecked == true)
                events.add(ev);
        }



        if (events.size() == 0) {
            Log.v("TEST", "non ci sono eventi");
        }
        setListAdapter(new MyEventAdapter(getActivity(), events));

        /* Set up the action bar to show a dropdown list. */
        getActivity().getActionBar().setDisplayShowTitleEnabled(false);
        getActivity().getActionBar().setNavigationMode(getActivity().getActionBar().NAVIGATION_MODE_LIST);

        /* Specify a SpinnerAdapter to populate the dropdown list. */
        rooms = Room.getAll();
        SpinnerRoomAdapter spinnerRoomAdapter = new SpinnerRoomAdapter(getActivity(), rooms);
        /* Set up the dropdown list navigation in the action bar */
        getActivity().getActionBar().setListNavigationCallbacks(spinnerRoomAdapter, this);
    }

    @Override
    public boolean onNavigationItemSelected(int i, long l) {
        // When the given dropdown item is selected, show its contents in the container view.
        eventsInRoomSelection(i);

        return true;
    }

    private void eventsInRoomSelection(int itemPosition) {
        if (Room.getRoomById(itemPosition).idS == 0) {
            //events = EventInDate.getAllChecked(day);
            aux = EventInDate.getAll(day);
            events = new ArrayList<EventInDate>();
            for (EventInDate ev : aux) {
                if (ev.isChecked == true)
                    events.add(ev);
            }
        }
        else {
            aux = EventInDate.getEventsInRoom(rooms.get(itemPosition), day);
            events = new ArrayList<EventInDate>();
            for (EventInDate ev : aux) {
                if (ev.isChecked == true)
                    events.add(ev);
            }
        }

        setListAdapter(new MyEventAdapter(getActivity(), events));
    }

    @Override
    public void onListItemClick (ListView l, View v, int position, long id) {

        EventInDate event  = (EventInDate) l.getItemAtPosition(position);
        ((MainActivity) getActivity()).replaceWithEventActivity(event.idEID);

    }
}
