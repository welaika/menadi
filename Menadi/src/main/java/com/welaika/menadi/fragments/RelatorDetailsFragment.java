package com.welaika.menadi.fragments;


import android.app.ActionBar;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.welaika.menadi.MainActivity;
import com.welaika.menadi.R;
import com.welaika.menadi.RelatorActivity;
import com.welaika.menadi.adapter.RelatorDetailsExpandableListAdapter;
import com.welaika.menadi.db.Relator;

import java.util.ArrayList;


public class RelatorDetailsFragment extends Fragment implements ExpandableListView.OnChildClickListener {

    private ArrayList<String> parentItems = new ArrayList<String>();
    private ArrayList<Object> childItems = new ArrayList<Object>();

    private String[] detailsTitles;
    private int id;
    private View view;
    private Relator relator;
    private ImageView imageView;


    public RelatorDetailsFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        relator = Relator.getRelatorById(id);

        setHasOptionsMenu(true);
        getActivity().getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        getActivity().getActionBar().setDisplayShowTitleEnabled(true);
        getActivity().getActionBar().setTitle(relator.name);

        view = inflater.inflate(R.layout.fragment_relator_details, container, false);
        setRelatorDetailsImage();

        ExpandableListView expandableList = (ExpandableListView) view.findViewById(R.id.exp_list_details);

        expandableList.setDividerHeight(2);
        //expandableList.setGroupIndicator(null);
        //expandableList.setClickable(true);

        detailsTitles  = getResources().getStringArray(R.array.details_titles_array);


        RelatorDetailsExpandableListAdapter adapter = new RelatorDetailsExpandableListAdapter(detailsTitles, relator);
        adapter.setInflater(inflater, getActivity());

        expandableList.setAdapter(adapter);
        expandableList.setOnChildClickListener(this);

        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }


    public void relatorDetailsId(int id) {
        this.id = id;
    }


    public void setRelatorDetailsImage() {

        imageView = (ImageView) view.findViewById(R.id.relator_details_image);
        Picasso
                .with(getActivity())
                .load(relator.url)
                .resize(400, 400)
                .centerCrop()
                .placeholder(R.drawable.default_user)
                .error(R.drawable.default_user)
                .into(imageView);
    }

    @Override
    public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long id) {

        ((RelatorActivity) getActivity()).replaceWithEventActivity(relator.events().get(childPosition).eventInD.idEID);
        return true;
    }
}