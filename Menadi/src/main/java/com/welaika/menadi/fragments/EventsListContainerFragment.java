package com.welaika.menadi.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TabHost;
import android.widget.TabWidget;

import com.welaika.menadi.R;

public class EventsListContainerFragment extends Fragment {

    private FragmentTabHost tabHost;
    private String[] days;
    private int type;


    public EventsListContainerFragment() {
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        days = getResources().getStringArray(R.array.days_array);
        Bundle arg1, arg2, arg3;
        arg1 = new Bundle(); arg1.putString("day1", days[0]);
        arg2 = new Bundle(); arg2.putString("day2", days[1]);
        arg3 = new Bundle(); arg3.putString("day3", days[2]);


        tabHost = new FragmentTabHost(getActivity());
        tabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);

        if(type == 1) {

            tabHost.addTab(tabHost.newTabSpec("tabTest1").setIndicator("GIORNO 1"), EventsListFragment.class, arg1);
            tabHost.addTab(tabHost.newTabSpec("tabTest2").setIndicator("GIORNO 2"), EventsListFragment.class, arg2);
            tabHost.addTab(tabHost.newTabSpec("tabTest3").setIndicator("GIORNO 3"), EventsListFragment.class, arg3);
        }

        else if (type == 2) {

            tabHost.addTab(tabHost.newTabSpec("tabTest1").setIndicator("GIORNO 1"), MyEventFragment.class, arg1);
            tabHost.addTab(tabHost.newTabSpec("tabTest2").setIndicator("GIORNO 2"), MyEventFragment.class, arg2);
            tabHost.addTab(tabHost.newTabSpec("tabTest3").setIndicator("GIORNO 3"), MyEventFragment.class, arg3);
        }

        return tabHost;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        tabHost = null;
    }

    public void setType(int type) {
        this.type = type;
    }
/*
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.event_checkbox:
                Log.v("TEST", "sono in EventsListContainerFragment");
                EventsListFragment eventsListFragment = new EventsListFragment();
                if (eventsListFragment != null)
                    eventsListFragment.onCheckboxClicked(view);
                else
                    Log.v("TEST", "non sono in EventsListFragment");
                break;
        }
    }
*/
}
