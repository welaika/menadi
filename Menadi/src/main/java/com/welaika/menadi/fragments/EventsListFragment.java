package com.welaika.menadi.fragments;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ListView;

import com.welaika.menadi.MainActivity;
import com.welaika.menadi.R;
import com.welaika.menadi.adapter.EventInDateAdapter;
import com.welaika.menadi.adapter.SpinnerRoomAdapter;
import com.welaika.menadi.db.EventInDate;
import com.welaika.menadi.db.Room;

import java.util.List;

public class EventsListFragment extends ListFragment implements ActionBar.OnNavigationListener {

    private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
    private List<EventInDate> events;
    private List<Room> rooms;
    private String day;


     public EventsListFragment() {
     }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

        int i = 0;
        while (getArguments().getString("day"+i) == null)
            i++;

        day = getArguments().getString("day"+i);


        events = EventInDate.getAll(day);
        setListAdapter(new EventInDateAdapter(getActivity(), events));

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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        // When the given dropdown item is selected, show its contents in the container view.
        eventsInRoomSelection(itemPosition);

        return true;
    }


    public void eventsInRoomSelection(int itemPosition) {
        if (Room.getRoomById(itemPosition).idS == 0) {
            events = EventInDate.getAll(day);
        }
        else {
            events = EventInDate.getEventsInRoom(rooms.get(itemPosition), day);
        }

        setListAdapter(new EventInDateAdapter(getActivity(), events));
    }


    @Override
    public void onListItemClick (ListView l, View v, int position, long id) {

        EventInDate event  = (EventInDate) l.getItemAtPosition(position);
        ((MainActivity) getActivity()).replaceWithEventActivity(event.idEID);

    }

}
