package com.welaika.menadi;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.content.Intent;

import com.welaika.menadi.adapter.DrawableAdapter;
import com.welaika.menadi.db.Event;
import com.welaika.menadi.db.EventInDate;
import com.welaika.menadi.db.Relator;
import com.welaika.menadi.db.RelatorEvent;
import com.welaika.menadi.db.Room;
import com.welaika.menadi.fragments.EventsListContainerFragment;
import com.welaika.menadi.fragments.MapsContainerFragment;
import com.welaika.menadi.fragments.NotificationReceiverFragment;
import com.welaika.menadi.fragments.RelatorsGridFragment;

import com.welaika.menadi.fragments.TwitterFragment;

import java.util.ArrayList;


public class MainActivity extends FragmentActivity {

    private static final String FRAGMENT_CLASS = "fragment_class";
    private String[] mMenu;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private ListView mDrawerList;
    private boolean notification;
    private Bundle savedInstanceState;

    private EventsListContainerFragment eventsListContainerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        boolean haveWeShownPreferences = prefs.getBoolean("HaveShownPrefs", false);
        if (!haveWeShownPreferences) {
            ArrayList<String> contacts1 = new ArrayList<String>();
            contacts1.add("twitter");
            contacts1.add("facebook");
            contacts1.add("instagram");
            contacts1.add("foursquare");

            ArrayList<String> contacts2 = new ArrayList<String>();
            contacts2.add("facebook");
            contacts2.add("google+");


            Room all_rooms_fake = new Room(0, 0, "Tutte le sale", "");
            all_rooms_fake.save();
            Room room_fake = new Room(1, 1, "Sala1", "");
            room_fake.save();
            Room room_fake2 = new Room(2, 2, "Sala2", "");
            room_fake2.save();
            Room room_fake3 = new Room(3, 3, "Sala3", "");
            room_fake3.save();
            Room room_fake4 = new Room(4, 4, "Sala4", "");
            room_fake4.save();
            Room room_fake5 = new Room(5, 5, "Sala5", "");
            room_fake5.save();

            Event event_fake = new Event(10, "Rails for zombies", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt " +
                    "ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. " +
                    "Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, " +
                    "sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo " +
                    "duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, " +
                    "consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At " +
                    "vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.");
            event_fake.save();
            Event event_fake2 = new Event(11, "Android <3", "");
            event_fake2.save();
            Event event_fake3 = new Event(12, "Google+ Conference", "");
            event_fake3.save();
            Event event_fake4 = new Event(13, "Apple iTunes Conference", "");
            event_fake4.save();
            Event event_fake5 = new Event(14, "Regular Expressions", "");
            event_fake5.save();
            Event event_fake6 = new Event(15, "Active Android Conference", "");
            event_fake6.save();

            EventInDate eventDate_fake = new EventInDate(1,"20140512", room_fake, event_fake, "12:00", "13:00");
            eventDate_fake.save();
            EventInDate eventDate_fake2 = new EventInDate(2,"20140512", room_fake2, event_fake2, "11:00", "13:00");
            eventDate_fake2.save();
            EventInDate eventDate_fake3 = new EventInDate(3,"20140512", room_fake3, event_fake3, "12:00", "13:00");
            eventDate_fake3.save();
            EventInDate eventDate_fake4 = new EventInDate(4,"20140513", room_fake4, event_fake4, "12:00", "13:00");
            eventDate_fake4.save();
            EventInDate eventDate_fake5 = new EventInDate(5,"20140513", room_fake3, event_fake5, "12:00", "13:00");
            eventDate_fake5.save();
            EventInDate eventDate_fake6 = new EventInDate(6,"20140514", room_fake4, event_fake6, "12:00", "13:00");
            eventDate_fake6.save();

            Relator relator1 = new Relator(1, "Faber", "", "http://www.pagetopremiere.com/wp-content/uploads/2013/05/thor-600x400.jpg", "bla bla");
            relator1.save();
            Relator relator2 = new Relator(2, "Ju", "", "http://cdn.jolie.de/bilder/jude-law-400x400-22547.jpg", "bla bla bla bla");
            relator2.save();
            Relator relator3 = new Relator(3, "Il Don", "", "http://1-media-cdn.foolz.us/ffuuka/board/co/thumb/1393/37/1393373780003s.jpg", "gatti gatti gatti");
            relator3.save();
            Relator relator4 = new Relator(4, "Jimmy", "", "http://www.fondosgratis.mx/archivos/temp/5589/400_1227568143_ironmanmoviewallpaperbyakasparviero.jpg", "blalblablab");
            relator4.save();
            Relator relator5 = new Relator(5, "Fazzino", "", "http://cdn.mos.totalfilm.com/images/p/pirates-of-the-caribbean-4-400-80.jpg", "nada");
            relator5.save();
            Relator relator6 = new Relator(6, "Filippo", "", "http://renegadecinema.com/wp-content/uploads/2013/02/url-1-650x400.jpg", "");
            relator6.save();
            Relator relator7 = new Relator(7, "Scilla", "", "http://i.imgur.com/rB4gmNb.jpg", "paranoia paranoia paranoia");
            relator7.save();
            Relator relator8 = new Relator(8, "Davide", "", "http://nl.wallpapersus.com/wallpapers/2012/05/green-lantern-films-800x960.jpg", "Daviddaviddavid");
            relator8.save();

            RelatorEvent relatorEvent_fake1 = new RelatorEvent(relator1.idR, relator1, eventDate_fake.idEID, eventDate_fake);
            relatorEvent_fake1.save();
            RelatorEvent relatorEvent_fake2 = new RelatorEvent(relator2.idR, relator2, eventDate_fake.idEID, eventDate_fake);
            relatorEvent_fake2.save();
            RelatorEvent relatorEvent_fake3 = new RelatorEvent(relator3.idR, relator3, eventDate_fake.idEID, eventDate_fake);
            relatorEvent_fake3.save();
            RelatorEvent relatorEvent_fake4 = new RelatorEvent(relator2.idR, relator2, eventDate_fake2.idEID, eventDate_fake2);
            relatorEvent_fake4.save();
            RelatorEvent relatorEvent_fake5 = new RelatorEvent(relator2.idR, relator2, eventDate_fake5.idEID, eventDate_fake5);
            relatorEvent_fake5.save();
            RelatorEvent relatorEvent_fake6 = new RelatorEvent(relator7.idR, relator7, eventDate_fake5.idEID, eventDate_fake5);
            relatorEvent_fake6.save();
            RelatorEvent relatorEvent_fake7 = new RelatorEvent(relator7.idR, relator7, eventDate_fake4.idEID, eventDate_fake4);
            relatorEvent_fake7.save();
            RelatorEvent relatorEvent_fake8 = new RelatorEvent(relator8.idR, relator8, eventDate_fake6.idEID, eventDate_fake6);
            relatorEvent_fake8.save();

            SharedPreferences.Editor ed = prefs.edit();
            ed.putBoolean("HaveShownPrefs", true);
            ed.commit();
        } else { // we have already shown the preferences activity before
         }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mTitle = mDrawerTitle = getTitle();
        mMenu = getResources().getStringArray(R.array.menu_array);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // ActionBarDrawerToggle ties together the the proper interactions between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        // Set the adapter for the list view
        mDrawerList.setAdapter(new DrawableAdapter(this, mMenu));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        //onRestoreInstanceState(savedInstanceState);
        notification = false;
        this.savedInstanceState = savedInstanceState;
        onNewIntent(getIntent());

    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }


    private void selectItem(int position) {

        switch (position) {
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new MapsContainerFragment())
                        .commit();
                break;

            case 2:
                eventsListContainerFragment = new EventsListContainerFragment();
                eventsListContainerFragment.setType(1);
                getSupportFragmentManager().beginTransaction().replace(R.id.container, eventsListContainerFragment)
                    .commit();
                break;

            case 3:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new RelatorsGridFragment())
                        .commit();
                break;

            case 4:
                eventsListContainerFragment = new EventsListContainerFragment();
                eventsListContainerFragment.setType(2);
                getSupportFragmentManager().beginTransaction().replace(R.id.container, eventsListContainerFragment)
                        .commit();
                break;

            case 5:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new TwitterFragment())
                        .commit();
                break;

            case 6:
                /*getSupportFragmentManager().beginTransaction().replace(R.id.container, new NotificationReceiverFragment())
                        .commit();*/
                Intent notif = new Intent(this, NotificationReceiverActivity.class);
                startActivity(notif);
                break;

            case 7:
                Intent intent = new Intent(this, CreateNotificationPROVA.class);
                startActivity(intent);
            default:
                break;
        }
        mDrawerLayout.closeDrawer(mDrawerList);
    }


    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return(true);
        }

        return(super.onOptionsItemSelected(item));
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    public void replaceWithRelatorActivity(int id) {

        Intent intent = new Intent(this, RelatorActivity.class);
        intent.putExtra("idR", id);
        startActivity(intent);
    }


    public void replaceWithEventActivity(int id) {

        Intent intent = new Intent(this, EventActivity.class);
        intent.putExtra("idEID", id);
        startActivity(intent);
    }


    public void replaceWithExternalActivity(Double latitude, Double longitude) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/?q=" + latitude + "," + longitude));
        startActivity(intent);
    }


    public void replaceWithRoomActivity(String room) {
        Intent intent = new Intent(this, RoomActivity.class);
        intent.putExtra("room", room);
        startActivity(intent);
    }


    @Override
    public void onNewIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {

            if (extras.containsKey("NotificationMessage")) {
                selectItem(6);
                notification = true;

            }
        }
        else if (savedInstanceState == null) {
            eventsListContainerFragment = new EventsListContainerFragment();
            eventsListContainerFragment.setType(1);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, eventsListContainerFragment)
                    .commit();
        }
    }

}
