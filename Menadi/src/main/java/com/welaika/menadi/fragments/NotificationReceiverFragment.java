package com.welaika.menadi.fragments;


import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.welaika.menadi.R;


public class NotificationReceiverFragment extends Fragment {

    View view;

    public NotificationReceiverFragment(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        getActivity().getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        getActivity().getActionBar().setDisplayShowTitleEnabled(true);

        view = inflater.inflate(R.layout.notifications_result_item, container, false);
        return view;
    }
}
