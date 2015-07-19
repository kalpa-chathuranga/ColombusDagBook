package com.kapal.root.colombusdagbook;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class EditTaskActivity extends Activity {

    SQLiteDatabase db;
    MyDatabaseHandler dbh = new MyDatabaseHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);


        TextView id = (TextView) findViewById(R.id.id_edit_task);
        TextView location = (TextView) findViewById(R.id.location_edit_text);
        TextView desc = (TextView) findViewById(R.id.description_edit_text);
        TextView lat = (TextView) findViewById(R.id.latitude_edit_text);
        TextView longt = (TextView) findViewById(R.id.longtitude_edit_text);

        int x = (int) getIntent().getExtras().getLong("id");
        String s = ""+id;


        db = openOrCreateDatabase(dbh.DB_NAME, MODE_PRIVATE, null);
        Cursor c = db.rawQuery(dbh.getRow(x), null);

        c.moveToFirst();
        id.setText(c.getString(c.getColumnIndex("_id")) );
        location.setText(c.getString(c.getColumnIndex("TASK_LOCATION ")));
        desc.setText(c.getString(c.getColumnIndex("TASK_DESCRIPTION ")));
        lat.setText(c.getString(c.getColumnIndex("TASK_LATITUDE")));
        longt.setText(c.getString(c.getColumnIndex("TASK_LONGTITUDE")));







    }


    }

