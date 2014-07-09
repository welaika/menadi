package com.welaika.menadi;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;

import com.welaika.menadi.fragments.EventDetailsFragment;

public class EventActivity extends ActionBarActivity {

    private int eventId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        eventId = intent.getExtras().getInt("idEID");

        setContentView(R.layout.activity_event);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        selectItem(eventId);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void selectItem(int id) {
        EventDetailsFragment eventDetailsFragment = new EventDetailsFragment();
        eventDetailsFragment.eventDetailsId(id);
        getSupportFragmentManager().beginTransaction().add(R.id.event_container, eventDetailsFragment).commit();
    }


    public void callActivityForGridFragment(int idEID) {
        Intent intent = new Intent(this, EventRelatorsActivity.class);
        intent.putExtra("idEID", idEID);
        Log.v("TEST", "idEID: " + idEID);
        startActivity(intent);
    }

}
