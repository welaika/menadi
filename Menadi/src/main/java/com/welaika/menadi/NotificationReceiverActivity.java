package com.welaika.menadi;

import android.app.ActionBar;
import android.app.ListActivity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import com.welaika.menadi.adapter.NotificationAdapter;

import java.util.ArrayList;
import java.util.List;


public class NotificationReceiverActivity extends ListActivity {

    List<String> notifications;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        getActionBar().setDisplayShowTitleEnabled(true);

        setContentView(R.layout.notifications_list_activity);

        // CARICARE TUTTE LE NOTIFICHE
        notifications = new ArrayList<String>();
        notifications.add("1");
        notifications.add("2");
        notifications.add("3");

        // ADAPTER PER L'ARRAYLIST
        setListAdapter(new NotificationAdapter(this, notifications));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
