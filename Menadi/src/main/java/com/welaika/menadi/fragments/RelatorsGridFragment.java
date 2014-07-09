package com.welaika.menadi.fragments;


import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.welaika.menadi.EventActivity;
import com.welaika.menadi.EventRelatorsActivity;
import com.welaika.menadi.MainActivity;
import com.welaika.menadi.R;
import com.welaika.menadi.SquareGridView;
import com.welaika.menadi.adapter.RelatorAdapter;
import com.welaika.menadi.db.Event;
import com.welaika.menadi.db.EventInDate;
import com.welaika.menadi.db.Relator;
import com.welaika.menadi.db.RelatorEvent;

import java.util.List;


public class RelatorsGridFragment extends Fragment implements AdapterView.OnItemClickListener  {

    private View view;
    private GridView grid;
    //private TextView selection;
    private List<Relator> relators;


    public RelatorsGridFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        getActivity().getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        getActivity().getActionBar().setDisplayShowTitleEnabled(true);
        getActivity().getActionBar().setTitle((getResources().getStringArray(R.array.menu_array))[3]);

        if(getArguments() != null) {

            int idEventInDate = getArguments().getInt("idEID");
            relators = EventInDate.getEventInDateById(idEventInDate).relators();
        }
        else
            relators = Relator.getAll();

        view = inflater.inflate(R.layout.fragment_grid, container, false);
       // selection = (TextView) view.findViewById(R.id.selection);

        grid = (GridView) view.findViewById(R.id.relators_grid);
        grid.setAdapter(new RelatorAdapter(getActivity(), relators));
        grid.setOnItemClickListener(this);
        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        Relator rel = (Relator) grid.getItemAtPosition(position);
        if (getActivity() instanceof MainActivity)
            ((MainActivity) getActivity()).replaceWithRelatorActivity(rel.idR);
        else if (getActivity() instanceof EventRelatorsActivity)
            ((EventRelatorsActivity) getActivity()).replaceWithRelatorActivity(rel.idR);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

    }
}
