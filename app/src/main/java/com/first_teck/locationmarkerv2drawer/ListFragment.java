package com.first_teck.locationmarkerv2drawer;


import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends android.support.v4.app.Fragment {

    private ArrayList<MenuItem> locationItem = new ArrayList<com.first_teck.locationmarkerv2drawer.MenuItem>();
    private ListView locationList;

    private MyDatabaseHelper myDatabaseHelperL;






    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        initializeLocationItem();

        View view = inflater.inflate(R.layout.fragment_list, container, false);
        final MenuItemAdapter locationItemAdapter = new MenuItemAdapter(getActivity(), R.layout.location_item, locationItem);
        locationList = (ListView)view.findViewById(R.id.location_list);
        locationList.setAdapter(locationItemAdapter);


        locationList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                //应该是从这里获取Item的信息
                ///location这个item里
                final double lat = ((ItemLocation) locationItem.get(position)).getLat();
                final double lng = ((ItemLocation) locationItem.get(position)).getLng();
                final String locationName =  ((ItemLocation) locationItem.get(position)).getLocationName();

                final SQLiteDatabase db = myDatabaseHelperL.getWritableDatabase();


                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(R.string.dialog_list_title)
                        .setItems(R.array.dialog_list_item, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        android.support.v4.app.Fragment newFragment = new MyMapFragment();


                                        Bundle args = new Bundle();
                                        args.putDouble("lat", lat);
                                        args.putDouble("lng", lng);
                                        newFragment.setArguments(args);


                                        android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                        fragmentTransaction.replace(R.id.content_frame, newFragment);
                                        fragmentTransaction.commit();


                                        //Toast.makeText(getActivity(), "lat: " + lat + "lng: " + lng, Toast.LENGTH_LONG).show();

                                        //((MyMapFragment)getActivity().getSupportFragmentManager().findFragmentById(R.id.map)).zoomTo(new LatLng(lat, lng));

                                        //((MyMapFragment) newFragment).zoomTo(new LatLng(lat, lng));//???????????????
                                        //((MyMapFragment)getActivity().getSupportFragmentManager().findFragmentById(R.id.map)).zoomTo();
                                        //    ((MyMapFragment)newFragment.getChildFragmentManager().findFragmentById(R.id.map)).zoomTo(new LatLng(lat, lng));
                                        break;
                                    case 1:
                                        ContentValues values = new ContentValues();
                                        values.put("lastVisitedDate", System.currentTimeMillis());
                                        db.update("locationTable", values, " lat = ?", new String[]{String.valueOf(lat)});
                                        break;
                                    case 2:
                                        db.delete("locationTable", "locationName = ?",new String[]{locationName});

                                        //TODO: 根据名字也有漏洞，可以在存marker时check

                                        locationItemAdapter.remove(locationItem.get(position));
                                        locationItemAdapter.notifyDataSetChanged();


                                        break;
                                    default:
                                        break;
                                }
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
                return false;
            }
        });
        return view;
    }

    public void initializeLocationItem(){
        myDatabaseHelperL = ((MainActivity)getActivity()).getMyDatabaseHelper();
        SQLiteDatabase db = myDatabaseHelperL.getWritableDatabase();
        Cursor cursor = db.query("locationTable", null,null,null,null,null, null);
        if(cursor.moveToFirst()) {
            do {
                String LocationName = cursor.getString(cursor.getColumnIndex("locationName"));
                double lat = cursor.getDouble(cursor.getColumnIndex("lat"));
                double lng = cursor.getDouble(cursor.getColumnIndex("lng"));
                Date savedDate = new Date(cursor.getLong(cursor.getColumnIndex("savedDate")));
                Date lastVisitedDate = new Date( cursor.getLong(cursor.getColumnIndex("lastVisitedDate")));
                String categories = cursor.getString((cursor.getColumnIndex("categories")));
                String description = cursor.getString(cursor.getColumnIndex("description"));

                Log.d("Load Data from DB","id: " + String.valueOf(cursor.getInt(cursor.getColumnIndex("id"))));
                Log.d("Load Data from DB","Name: " + String.valueOf(lng));
                Log.d("Load Data from DB","lat: " + String.valueOf(lat));
                Log.d("Load Data from DB","lng: " + LocationName);


                MenuItem menuItem = new ItemLocation(LocationName, lat, lng, categories, description,lastVisitedDate, savedDate);
                locationItem.add(menuItem);


            }while (cursor.moveToNext());
        }
    }

}
