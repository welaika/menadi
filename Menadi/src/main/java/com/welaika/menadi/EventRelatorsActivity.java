package com.welaika.menadi;


import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;

import com.welaika.menadi.fragments.RelatorsGridFragment;

public class EventRelatorsActivity extends ActionBarActivity {

    Bundle bundleId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        bundleId = new Bundle();
        Log.v("TEST", "EventRelatorsActivity idEID: " + intent.getExtras().getInt("idEID"));
        bundleId.putInt("idEID", intent.getExtras().getInt("idEID"));

        setContentView(R.layout.activity_event_relators);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        setFragment();
    }


    public void setFragment() {

        RelatorsGridFragment relatorsGridFragment = new RelatorsGridFragment();
        relatorsGridFragment.setArguments(bundleId);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.event_relator_container, relatorsGridFragment)
                .commit();

    }


    public void replaceWithRelatorActivity(int idR) {

        Intent intent = new Intent(this, RelatorActivity.class);
        intent.putExtra("idR", idR);
        startActivity(intent);
    }

}
