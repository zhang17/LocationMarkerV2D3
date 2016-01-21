package com.first_teck.locationmarkerv2drawer;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import android.support.v4.app.Fragment;
import android.widget.Toast;

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

        myDatabaseHelperL = ((MainActivity)getActivity()).getMyDatabaseHelper();
        SQLiteDatabase db = myDatabaseHelperL.getWritableDatabase();
        Cursor cursor = db.query("locationTable", null,null,null,null,null, null);
        if(cursor.moveToFirst()) {
            do {
                String LocationName = cursor.getString(cursor.getColumnIndex("locationName"));
                double lat = cursor.getDouble(cursor.getColumnIndex("lat"));
                double lng = cursor.getDouble(cursor.getColumnIndex("lng"));
                Date savedDate = new Date(cursor.getInt(cursor.getColumnIndex("savedDate")));
                Date lastVisitedDate = new Date(cursor.getInt(cursor.getColumnIndex("lastVisitedDate")));
                String categories = cursor.getString((cursor.getColumnIndex("categories")));
                String description = cursor.getString(cursor.getColumnIndex("description"));

                MenuItem menuItem = new ItemLocation(LocationName, lat, lng, categories, description, savedDate, lastVisitedDate);
                locationItem.add(menuItem);


            }while (cursor.moveToNext());
        }


//        MenuItem test1 = new ItemLocation("RedCarpet Inn",20.20, 20.20, "Living", "This is my sweet home", new Date(115, 11, 23, 16, 45, 12), new Date(115, 10, 20, 19, 34, 10));
//        locationItem.add(test1);
//        MenuItem test2 = new ItemLocation("Noodle Go Go",30.30, 30.30, "eating", "This is bala bala", new Date(115, 11, 23, 16, 45, 12), new Date(115, 10, 20, 19, 34, 10));
//        locationItem.add(test2);
//        MenuItem test3 = new ItemLocation("WalMart",40.40,40.40, "Shopping", "This is bala bala", new Date(115, 11, 23, 16, 45, 12), new Date(115, 10, 20, 19, 34, 10));
//        locationItem.add(test3);
//        MenuItem test4 = new ItemLocation("AMC theatres",50.50, 50.50, "Entertaining", "This is bala bala", new Date(115, 11, 23, 16, 45, 12), new Date(115, 10, 20, 19, 34, 10));
//        locationItem.add(test4);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        MenuItemAdapter locationItemAdapter = new MenuItemAdapter(getActivity(), R.layout.location_item, locationItem);
        locationList = (ListView)view.findViewById(R.id.location_list);
        locationList.setAdapter(locationItemAdapter);


//        locationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                //TODO
//
//            }
//        });
        locationList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(R.string.dialog_list_title)
                        .setItems(R.array.dialog_list_item, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        Toast.makeText(getActivity(), "first button", Toast.LENGTH_LONG).show();
                                        break;
                                    case 1:
                                        Toast.makeText(getActivity(), "second button", Toast.LENGTH_LONG).show();
                                        break;
                                    case 2:
                                        Toast.makeText(getActivity(), "third button", Toast.LENGTH_LONG).show();
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

}
