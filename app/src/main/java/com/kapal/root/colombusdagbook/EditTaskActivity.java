package com.kapal.root.colombusdagbook;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class EditTaskActivity extends Activity {

    public String TAG = "xxx";
    SQLiteDatabase db;
    MyDatabaseHandler dbh = new MyDatabaseHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);


        TextView id = (TextView) findViewById(R.id.id_edit_task);
        TextView location = (TextView) findViewById(R.id.location_edit_task);
        TextView desc = (TextView) findViewById(R.id.description_edit_task);
        TextView lat = (TextView) findViewById(R.id.latitude_edit_task);
        TextView longt = (TextView) findViewById(R.id.longtitude_edit_task);

        int x = (int) getIntent().getExtras().getLong("id");
        String s = ""+id;
        Log.d(TAG, "ID is : " + s);



        db = openOrCreateDatabase(dbh.DB_NAME, MODE_PRIVATE, null);
        Log.d(TAG, "DB opened!");

        Cursor c = db.rawQuery(dbh.getRow(x), null);
        Log.d(TAG, "Cursor OK !");

        try {
            c.moveToFirst();
            Log.d(TAG, "Moved to first");
            Log.d(TAG, c.getColumnName(0));
            Log.d(TAG, c.getString(c.getColumnIndex("_id")));
            Log.d(TAG, c.getString(c.getColumnIndex("TASK_LOCATION")));
            Log.d(TAG, c.getString(c.getColumnIndex("TASK_DESCRIPTION")));
            Log.d(TAG, c.getString(c.getColumnIndex("TASK_LATITUDE")));
            Log.d(TAG, c.getString(c.getColumnIndex("TASK_LONGTITUDE")));
        } catch (Exception e) {
            e.printStackTrace();
        }



        /*String a1 = c.getString(c.getColumnIndex("_id")).toString() ;
        String a2 = c.getString(c.getColumnIndex("TASK_LOCATION ")).toString();
        String a3 = c.getString(c.getColumnIndex("TASK_DESCRIPTION ")).toString();
        String a4 = c.getString(c.getColumnIndex("TASK_LATITUDE")).toString();
        String a5= c.getString(c.getColumnIndex("TASK_LONGTITUDE")).toString();*/

        String a1 = c.getString(0) ;
        String a2 = c.getString(1);
        String a3 = c.getString(2);
        String a4 = c.getString(3);
        String a5 = c.getString(4);

        id.setText(a1);
        location.setText(a2);
        desc.setText(a3);
        lat.setText(a4);
        longt.setText(a5);





    }


    }

