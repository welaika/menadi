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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.welaika.menadi.EventActivity;
import com.welaika.menadi.R;
import com.welaika.menadi.db.Event;
import com.welaika.menadi.db.EventInDate;

public class EventDetailsFragment extends Fragment {

    private int id;
    private View view;
    private EventInDate event;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        event = EventInDate.getEventInDateById(id);
        setHasOptionsMenu(true);
        getActivity().getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        getActivity().getActionBar().setDisplayShowTitleEnabled(true);
        getActivity().getActionBar().setTitle(event.idA.title);

        view = inflater.inflate(R.layout.fragment_event_details, container, false);

        TextView start = (TextView) view.findViewById(R.id.start_text);
        TextView end = (TextView) view.findViewById(R.id.end_text);
        start.setText("Ora Inizio: " + event.startH);
        end.setText("Ora Fine: " + event.endH);

        TextView description = (TextView) view.findViewById(R.id.description_text);
        description.setText(event.idA.description);

        Button btn = (Button) view.findViewById(R.id.relators_btn);
        btn.setText(getResources().getString(R.string.btn_title));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((EventActivity) getActivity()).callActivityForGridFragment(event.idEID);
            }
        });

        return view;
    }
        public void eventDetailsId(int id) {
        this.id = id;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }
}
