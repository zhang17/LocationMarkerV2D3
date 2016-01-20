package com.first_teck.locationmarkerv2drawer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.Date;

/**
 * Created by zyqzh_000 on 2016/1/19.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper{

    public static final String CREATE_LOCATION_TABLE = "create table locationTable( "
            + "id integer primary key autoincrement, "// need to create id in MyLocation?
            + "locationName Text, "
            + "lat real, "
            + "lng real, "
            + "savedDate integer, "
            + "lastVisitedDate integer, "
            + "categories text, "
            + "description text)";

    private Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_LOCATION_TABLE);
        Toast.makeText(mContext, "Create locationTable succeed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
