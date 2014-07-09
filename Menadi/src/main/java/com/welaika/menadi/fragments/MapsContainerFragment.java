package com.welaika.menadi.fragments;


import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.welaika.menadi.R;

public class MapsContainerFragment extends Fragment {

    private FragmentTabHost tabHost;
    private String[] mapsTabs;

    public MapsContainerFragment(){}

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        getActivity().getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        getActivity().getActionBar().setDisplayShowTitleEnabled(true);
        getActivity().getActionBar().setTitle((getResources().getStringArray(R.array.menu_array))[1]);

        Bundle arg = new Bundle();
        mapsTabs = getResources().getStringArray(R.array.maps_tabs);

        tabHost = new FragmentTabHost(getActivity());
        tabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);

        for(int i=0; i<mapsTabs.length; i++) {
            arg.putString("map" + i, mapsTabs[i]);
        }
        tabHost.addTab(tabHost.newTabSpec("tabMapTest1").setIndicator(mapsTabs[0]), GlobalMapFragment.class, arg);
        tabHost.addTab(tabHost.newTabSpec("tabMapTest2").setIndicator(mapsTabs[1]), LocalMapFragment.class, arg);
        return tabHost;
    }
}
