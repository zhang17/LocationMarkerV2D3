package com.first_teck.locationmarkerv2drawer;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.lang.reflect.Field;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyMapFragment extends android.support.v4.app.Fragment {

    private GoogleMap myGoogleMap;


    public MyMapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_map, container, false);
 //       if(myGoogleMap == null){
            SupportMapFragment supportMapFragment = (SupportMapFragment)this.getChildFragmentManager().findFragmentById(R.id.map);
            //为什么这里需要用getChildFragmentManager，而不是getSupportFragmentManager()
            myGoogleMap = supportMapFragment.getMap();
                supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    myGoogleMap = googleMap;
                }
            });
        myGoogleMap.addMarker(new MarkerOptions().position(new LatLng(70.70, 70.70)));
//        }



        //googleMap = ((MyMapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
       // googleMap = ((SupportMapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        //googleMap = (GoogleMap)view.findViewById(R.id.map);
        return view;
    }


//    public void onDestroyView() {
//        super.onDestroyView();
//        android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
//        android.support.v4.app.FragmentTransaction ft = fragmentManager.beginTransaction();
//        ft.remove(myMapFragment);
//        ft.commit();
//    }

}
