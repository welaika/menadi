package com.welaika.menadi.fragments;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.welaika.menadi.R;
import com.welaika.menadi.db.Room;


public class RoomDetailsFragment extends Fragment {

    private View view;
    private String roomName;
    private Room room;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        getActivity().getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        getActivity().getActionBar().setDisplayShowTitleEnabled(true);
        getActivity().getActionBar().setTitle("Room");

        view = inflater.inflate(R.layout.fragment_room_details, container, false);

        room = Room.getRoomByName(roomName);
        if (room != null) {
            Log.v("TEST", "ho i dati della stanza");
        }

        TextView roomName = (TextView) view.findViewById(R.id.room_name);
        roomName.setText(room.name);
        TextView roomId = (TextView) view.findViewById(R.id.room_id);
        roomId.setText(room.idS.toString());
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    public void setRoomName(String name) {
        roomName = name;
    }
}
