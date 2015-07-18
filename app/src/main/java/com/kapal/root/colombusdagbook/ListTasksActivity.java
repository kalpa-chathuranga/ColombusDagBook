package com.kapal.root.colombusdagbook;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;


public class ListTasksActivity extends Activity {

    private Button add_task_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tasks);


        add_task_btn = (Button) findViewById(R.id.add_task_button);

        add_task_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListTasksActivity.this , AddTaskActivity.class));
            }
        });


        /*setListAdapter(new ArrayAdapter<String>(this,
                                                android.R.layout.simple_list_item_1,
                                                getResources().getStringArray(R.array.countries)));
*/


    }


}
