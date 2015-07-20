package com.kapal.root.colombusdagbook;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class ListTasksActivity extends Activity implements AdapterView.OnItemClickListener{

    private String TAG = "xxx";
    private Button add_task_btn;
    private ListView myList_lv;

    public SQLiteDatabase db;
    public MyDatabaseHandler dbh = new MyDatabaseHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tasks);


        add_task_btn = (Button) findViewById(R.id.add_task_button);

        add_task_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListTasksActivity.this, AddTaskActivity.class));
            }
        });


        try {
            db = openOrCreateDatabase(dbh.DB_NAME, MODE_PRIVATE, null);
            Log.d(TAG, "DB CONNECTED");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "NO DB");
        }

        myList_lv = (ListView) findViewById(R.id.list);
        myList_lv.setOnItemClickListener(this);


        try {

            populateListView();

            Log.d(TAG, "List View populated");
        } catch (Exception e) {

            e.printStackTrace();
            Log.d(TAG, "List View NOT populated");
        }




    }



    public void populateListView(){

        Cursor cursor = db.rawQuery("SELECT * FROM "+dbh.TABLE_NAME , null);
        Log.d(TAG, "Cursor OK");

        if (cursor != null) {
            String[] fromFiledNames = new String[] {"_id","TASK_LOCATION", "TASK_DESCRIPTION"};


            int[] toListView = new int[]{R.id.list_id, R.id.list_place, R.id.list_description};

            SimpleCursorAdapter mySimpleCursorAdapter;


            mySimpleCursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.my_list_layout, cursor, fromFiledNames, toListView, 0);
            Log.d(TAG, "Simple CUrsor Adapter OK");

            myList_lv.setAdapter(mySimpleCursorAdapter);
            Log.d(TAG, "set ADAPTER OK");

        }
    }

//Todo: Create edit view and update butto.Map intent was crated to test
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent i  = new Intent(ListTasksActivity.this, EditTaskActivity.class);
        i.putExtra("id", id);

        startActivity(i);

    }
}
