package com.welaika.menadi;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

import com.welaika.menadi.fragments.RelatorDetailsFragment;

public class RelatorActivity extends ActionBarActivity {

    private int relatorId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        relatorId = intent.getExtras().getInt("idR");

        setContentView(R.layout.activity_relator);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        selectItem(relatorId);
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
        RelatorDetailsFragment relatorDetailsFragment = new RelatorDetailsFragment();
        relatorDetailsFragment.relatorDetailsId(id);
        getSupportFragmentManager().beginTransaction().add(R.id.relator_container, relatorDetailsFragment).commit();
    }

    public void replaceWithEventActivity(int id) {

        Intent intent = new Intent(this, EventActivity.class);
        intent.putExtra("idEID", id);
        startActivity(intent);
    }
}

// TODO implementare up button che torni al fragment dell'activity corretta.