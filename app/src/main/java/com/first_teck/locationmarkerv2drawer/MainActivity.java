package com.first_teck.locationmarkerv2drawer;


import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<com.first_teck.locationmarkerv2drawer.MenuItem> mDrawerItems = new ArrayList<com.first_teck.locationmarkerv2drawer.MenuItem>();
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;



    //////
    private MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerSetUp();
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        ///////////////////////////////////
        myDatabaseHelper = new MyDatabaseHelper(this, "Location.db", null, 1);
        android.support.v4.app.Fragment newFragment = null;
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        newFragment = new ListFragment();
        fragmentTransaction.replace(R.id.content_frame, newFragment);
        //transaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void drawerSetUp() {
        com.first_teck.locationmarkerv2drawer.MenuItem mainView = new Item1("MainView");
        mDrawerItems.add(mainView);
        com.first_teck.locationmarkerv2drawer.MenuItem savedMarkersList = new Item2("SavedMarkersList");
        mDrawerItems.add(savedMarkersList);
        com.first_teck.locationmarkerv2drawer.MenuItem mapView = new Item2("MapView");
        mDrawerItems.add(mapView);
        com.first_teck.locationmarkerv2drawer.MenuItem categories = new Item1("Categories");
        mDrawerItems.add(categories);
        com.first_teck.locationmarkerv2drawer.MenuItem shopping = new Item2("Shopping");
        mDrawerItems.add(shopping);
        com.first_teck.locationmarkerv2drawer.MenuItem eating = new Item2("Eating");
        mDrawerItems.add(eating);
        com.first_teck.locationmarkerv2drawer.MenuItem living = new Item2("Living");
        mDrawerItems.add(living);
        com.first_teck.locationmarkerv2drawer.MenuItem entertaining = new Item2("Entertaining");
        mDrawerItems.add(entertaining);



        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        MenuItemAdapter menuItemAdapter = new MenuItemAdapter(this, R.layout.menu_item, mDrawerItems);
        // Set the list's click listener
        mDrawerList.setAdapter(menuItemAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                android.support.v4.app.Fragment newFragment = null;
                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch (position) {

                    case 1:newFragment = new ListFragment();
                        fragmentTransaction.replace(R.id.content_frame, newFragment);
                            //transaction.addToBackStack(null);
                        fragmentTransaction.commit();
                            break;
                    case 2:newFragment = new MyMapFragment();
                        fragmentTransaction.replace(R.id.content_frame, newFragment);
                            //transaction.addToBackStack(null);
                        fragmentTransaction.commit();
                            break;

                    case 4:
                        newFragment = new ListFragmentShopping();
                        fragmentTransaction.replace(R.id.content_frame, newFragment);
                        //transaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        break;
                    case 5:
                        newFragment = new ListFragmentEating();
                        fragmentTransaction.replace(R.id.content_frame, newFragment);
                        //transaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        Log.d("MainActivity", "case 5");
                        break;
                    case 6:
                        newFragment = new ListFragmentLiving();
                        fragmentTransaction.replace(R.id.content_frame, newFragment);
                        //transaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        Log.d("MainActivity", "case 6");
                        break;
                    case 7:
                        newFragment = new ListFragmentEntertaining();
                        fragmentTransaction.replace(R.id.content_frame, newFragment);
                        //transaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        Log.d("MainActivity", "case 7");
                        break;
                    default:
                        break;

                    }

                mDrawerList.setItemChecked(position, true);
                //setTitle之后可加
                mDrawerLayout.closeDrawer(mDrawerList);
            }
        });

    }


    ////////////////////databaseHelper
    public MyDatabaseHelper getMyDatabaseHelper() {
        return myDatabaseHelper;
    }

    public void setMyDatabaseHelper(MyDatabaseHelper myDatabaseHelper) {
        this.myDatabaseHelper = myDatabaseHelper;
    }

}
