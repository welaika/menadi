package com.welaika.menadi;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import com.welaika.menadi.fragments.RoomDetailsFragment;

public class RoomActivity extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String roomName = intent.getExtras().getString("room");

        setContentView(R.layout.activity_room);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        selectItem(roomName);
    }

    public void selectItem(String roomName) {
        RoomDetailsFragment roomFrag = new RoomDetailsFragment();
        roomFrag.setRoomName(roomName);
        getSupportFragmentManager().beginTransaction().add(R.id.room_container, roomFrag)
                .commit();
    }
}
