package com.welaika.menadi.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.welaika.menadi.MainActivity;
import com.welaika.menadi.R;

import java.util.zip.Inflater;

public class GlobalMapFragment extends SupportMapFragment {

    private String arg;
    private Double latitude, longitude;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        arg = getArguments().getString("map0");

    }

    @Override
    public void onStart(){
        super.onStart();
        addMarkers();
    }

    private void addMarkers(){
        latitude = 40.76793169992044;
        longitude = -73.98180484771729;

        LatLng startPosition = new LatLng(latitude, longitude);

        MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title(getResources().getString(R.string.place_name));

        // adding marker
        getMap().addMarker(marker);
        getMap().setMyLocationEnabled(true);
        getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(startPosition, 10));
        //getMap().animateCamera(CameraUpdateFactory.zoomTo(13), 2000, null);

        getMap().setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                callExternalMapActivity();
                return false;
            }
        });
    }

    public void callExternalMapActivity() {
        ((MainActivity) getActivity()).replaceWithExternalActivity(latitude, longitude);
    }
}

// TODO intent per chiamare un'applicazione esterna che calcoli il percorso

