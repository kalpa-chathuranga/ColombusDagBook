package com.kapal.root.colombusdagbook;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;

import java.text.DateFormat;
import java.util.Date;


public class AddTaskActivity extends Activity implements OnClickListener {

    private static  String TAG = "xxx";
    private TextView created_tv;
    private Button save_task_btn;

    public MyDatabaseHandler dbh;
    public SQLiteDatabase db;

    private EditText description_et, location_et, latitude_et, longtitude_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        description_et = (EditText) findViewById(R.id.description_et);
        location_et = (EditText) findViewById(R.id.location_et);
        latitude_et = (EditText) findViewById(R.id.latitude_et);
        longtitude_et = (EditText) findViewById(R.id.longtitude_et);

        created_tv = (TextView) findViewById(R.id.created_tv);

        save_task_btn = (Button) findViewById(R.id.save_task_btn);
        save_task_btn.setOnClickListener(this);


        String currentDateTime = DateFormat.getDateTimeInstance().format(new Date());
        created_tv.setText( currentDateTime);

        dbh = new MyDatabaseHandler();


        db = openOrCreateDatabase(dbh.DB_NAME, MODE_PRIVATE, null);
        db.execSQL(dbh.createTable());




    }


    @Override
    public void onClick(View v) {


        if (v.getId() == R.id.save_task_btn)
        {
            try {
                db.execSQL(dbh.insertRow(
                        description_et.getText().toString(),
                        location_et.getText().toString(),
                        latitude_et.getAlpha(),
                        longtitude_et.getAlpha()
                ));


                Log.d(TAG, "DATA inserted");

                startActivity(new Intent(AddTaskActivity.this, ListTasksActivity.class));


            } catch (SQLException e) {
                Log.d(TAG, "DATA NOT inserted");
                e.printStackTrace();
            }

        }


    }
}
