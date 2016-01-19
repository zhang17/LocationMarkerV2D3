package com.first_teck.locationmarkerv2drawer;



import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


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

        final View view = inflater.inflate(R.layout.fragment_map, container, false);

        initializeMap();

        myGoogleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                //Dialog
                //1.Instantiate an AlertDialog.Builder with its constructor
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                // Get the layout inflater
                final View customized_dialog_map_view = getActivity().getLayoutInflater().inflate(R.layout.dialog_map, null);

                final EditText editTextLocationName = (EditText)customized_dialog_map_view.findViewById(R.id.edit_text_location_name);
                final RadioGroup radioGroupCategories = (RadioGroup) customized_dialog_map_view.findViewById(R.id.radio_group_categories);
                final EditText editTextDescriptions = (EditText) customized_dialog_map_view.findViewById(R.id.edit_text_descriptions);



                // Add the buttons
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        int checkedRadioButtonId = radioGroupCategories.getCheckedRadioButtonId();
                        final RadioButton radioButtonCategories = (RadioButton) customized_dialog_map_view.findViewById(checkedRadioButtonId);


                        // User clicked OK button
                        Toast.makeText(getActivity(), " Location name: " + editTextLocationName.getText().toString()
                                + " Categories: "+ radioButtonCategories.getText()
                                + " Descriptions: "+ editTextDescriptions.getText(), Toast.LENGTH_SHORT).show();

                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

                //2.Chain together various setter methods to set the dialog characteristics
                builder.setTitle(R.string.dialog_map_title)
                        .setView(customized_dialog_map_view);

                AlertDialog dialog = builder.create();

                dialog.show();
            }
        });


        myGoogleMap.addMarker(new MarkerOptions().position(new LatLng(70.70, 70.70)));


        return view;
    }

    public void initializeMap(){
        //为什么这里需要用getChildFragmentManager，而不是getSupportFragmentManager()
        SupportMapFragment supportMapFragment = (SupportMapFragment)this.getChildFragmentManager().findFragmentById(R.id.map);
        myGoogleMap = supportMapFragment.getMap();
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                myGoogleMap = googleMap;
            }
        });
    }




}
