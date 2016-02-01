package com.first_teck.locationmarkerv2drawer;



import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
    public class MyMapFragment extends android.support.v4.app.Fragment {

    private  GoogleMap myGoogleMap;

    private MyDatabaseHelper myDatabaseHelperM;
;


    public MyMapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.fragment_map, container, false);

        initializeMap();
        loadSavedMarkers();
        setOnMapLongClickListener();
        myGoogleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener(){

            @Override
            public boolean onMarkerClick(Marker marker) {

                myGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(),15));
                myDatabaseHelperM = ((MainActivity)getActivity()).getMyDatabaseHelper();
                SQLiteDatabase db = myDatabaseHelperM.getWritableDatabase();
                Cursor cursor = db.query("locationTable", null,null,null,null,null, null);
                if(cursor.moveToFirst()){
                   do{
                       if(cursor.getDouble(cursor.getColumnIndex("lat")) == marker.getPosition().latitude
                               && cursor.getDouble(cursor.getColumnIndex("lng") )== marker.getPosition().longitude){
                           String LocationName = cursor.getString(cursor.getColumnIndex("locationName"));
                           String categories = cursor.getString((cursor.getColumnIndex("categories")));
                           marker.setTitle("name: " + LocationName + " categories: " + categories);

                       }
                   }while(cursor.moveToNext());
                }


                return false;
            }
        });

        return view;
    }

    public void initializeMap(){
        //为什么这里需要用getChildFragmentManager，而不是getSupportFragmentManager()
        SupportMapFragment supportMapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map);
        myGoogleMap = supportMapFragment.getMap();
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                myGoogleMap = googleMap;
            }
        });

    }

    public void loadSavedMarkers(){
        myDatabaseHelperM = ((MainActivity)getActivity()).getMyDatabaseHelper();
        SQLiteDatabase db = myDatabaseHelperM.getWritableDatabase();
        Cursor cursor = db.query("locationTable", null,null,null,null,null, null);
        if(cursor.moveToFirst()){
            do{
                String LocationName = cursor.getString(cursor.getColumnIndex("locationName"));
                double lat = cursor.getDouble(cursor.getColumnIndex("lat"));
                double lng = cursor.getDouble(cursor.getColumnIndex("lng"));
                Date savedDate = new Date(cursor.getInt(cursor.getColumnIndex("savedDate")));
                Date lastVisitedDate = new Date(cursor.getInt(cursor.getColumnIndex("lastVisitedDate")));
                String categories = cursor.getString((cursor.getColumnIndex("categories")));
                String description = cursor.getString(cursor.getColumnIndex("description"));

               // float color = 210;

                switch (categories){
                    case "Shopping": myGoogleMap.addMarker(new MarkerOptions().position(new LatLng(lat,lng )))
                            .setIcon(BitmapDescriptorFactory.fromResource(R.drawable.shopping_small));

                        break;
                    case "Eating":   myGoogleMap.addMarker(new MarkerOptions().position(new LatLng(lat,lng )))
                            .setIcon(BitmapDescriptorFactory.fromResource(R.drawable.eating_small));
                        break;
                    case  "Living":  myGoogleMap.addMarker(new MarkerOptions().position(new LatLng(lat,lng )))
                            .setIcon(BitmapDescriptorFactory.fromResource(R.drawable.living_small));
                        break;
                    case "Entertaining":  myGoogleMap.addMarker(new MarkerOptions().position(new LatLng(lat,lng )))
                            .setIcon(BitmapDescriptorFactory.fromResource(R.drawable.entertaining_small));
                        break;
                    default:
                        break;
                }



//                Log.d("MainActivity", "location name is " + LocationName);
//                Log.d("MainActivity", "lat is " + lat);
//                Log.d("MainActivity", "lng is" + lng);
//                Log.d("MainActivity", "savedDate is " + savedDate);
//                Log.d("MainActivity", "lastVisitedDate is " + lastVisitedDate);
//                Log.d("MainActivity", "category is " + categories );
//                Log.d("MainActivity", "description is " + description);

            }while (cursor.moveToNext());
        }
        cursor.close();
    }

    public void setOnMapLongClickListener(){
        myGoogleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(final LatLng latLng) {
                //Dialog
                //1.Instantiate an AlertDialog.Builder with its constructor
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


                // Get the layout inflater
                final View customized_dialog_map_view = getActivity().getLayoutInflater().inflate(R.layout.dialog_map, null);

                final EditText editTextLocationName = (EditText) customized_dialog_map_view.findViewById(R.id.edit_text_location_name);
                final RadioGroup radioGroupCategories = (RadioGroup) customized_dialog_map_view.findViewById(R.id.radio_group_categories);
                final EditText editTextDescriptions = (EditText) customized_dialog_map_view.findViewById(R.id.edit_text_descriptions);


                myDatabaseHelperM = ((MainActivity) getActivity()).getMyDatabaseHelper();


                // Add the buttons
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        int checkedRadioButtonId = radioGroupCategories.getCheckedRadioButtonId();
                        final RadioButton radioButtonCategories = (RadioButton) customized_dialog_map_view.findViewById(checkedRadioButtonId);


                        SQLiteDatabase db = myDatabaseHelperM.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.put("locationName", editTextLocationName.getText().toString());
                        values.put("lat", latLng.latitude);
                        values.put("lng", latLng.longitude);
                        values.put("savedDate", System.currentTimeMillis());
                        Log.d("MyMapFragment", "savedDate Integer: " + String.valueOf(System.currentTimeMillis()));
                        Log.d("MyMapFragment", "savedDate : " + new Date(System.currentTimeMillis()));
                        values.put("lastVisitedDate", System.currentTimeMillis());
                        Log.d("MyMapFragment", "lastVisitedDate Integer: " + String.valueOf(System.currentTimeMillis()));
                        Log.d("MyMapFragment", "lastVisitedDate : " + new Date(System.currentTimeMillis()));
                        values.put("categories", radioButtonCategories.getText().toString());
                        values.put("description", editTextDescriptions.getText().toString());
                        db.insert("locationTable", null, values);

                        // User clicked OK button
                        Toast.makeText(getActivity(), " Location name: " + editTextLocationName.getText().toString()
                                + " Categories: " + radioButtonCategories.getText().toString()
                                + " Descriptions: " + editTextDescriptions.getText().toString()
                                + "savedDate" + System.currentTimeMillis() , Toast.LENGTH_LONG).show();

                        myGoogleMap.addMarker(new MarkerOptions().position(latLng));


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
    }

    public void zoomTo(LatLng latLng){

        Log.d("MyMapFragment", "latitude is: " + latLng.latitude + "longitude" + latLng.longitude);
        System.out.println("latitude is: " + latLng.latitude + "longitude" + latLng.longitude);
        myGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));

    }


}
