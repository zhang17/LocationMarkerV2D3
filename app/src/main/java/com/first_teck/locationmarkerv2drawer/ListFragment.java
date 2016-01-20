package com.first_teck.locationmarkerv2drawer;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import android.support.v4.app.Fragment;
import java.util.ArrayList;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends android.support.v4.app.Fragment {

    private ArrayList<MenuItem> locationItem = new ArrayList<com.first_teck.locationmarkerv2drawer.MenuItem>();
    private ListView locationList;



    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        MenuItem test1 = new ItemLocation("RedCarpet Inn",20.20, 20.20, "Living", "This is my sweet home", new Date(115, 11, 23, 16, 45, 12), new Date(115, 10, 20, 19, 34, 10));
        locationItem.add(test1);
        MenuItem test2 = new ItemLocation("Noodle Go Go",30.30, 30.30, "eating", "This is bala bala", new Date(115, 11, 23, 16, 45, 12), new Date(115, 10, 20, 19, 34, 10));
        locationItem.add(test2);
        MenuItem test3 = new ItemLocation("WalMart",40.40,40.40, "Shopping", "This is bala bala", new Date(115, 11, 23, 16, 45, 12), new Date(115, 10, 20, 19, 34, 10));
        locationItem.add(test3);
        MenuItem test4 = new ItemLocation("AMC theatres",50.50, 50.50, "Entertaining", "This is bala bala", new Date(115, 11, 23, 16, 45, 12), new Date(115, 10, 20, 19, 34, 10));
        locationItem.add(test4);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        MenuItemAdapter locationItemAdapter = new MenuItemAdapter(getActivity(), R.layout.location_item, locationItem);
        locationList = (ListView)view.findViewById(R.id.location_list);
        locationList.setAdapter(locationItemAdapter);


        locationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO

            }
        });
        return view;
    }

}
