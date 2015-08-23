package com.kapal.root.colombusdagbook;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class HelpActivity extends ActionBarActivity {

    public Button show_map_btn,task_lst_btn,help_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        show_map_btn = (Button) findViewById(R.id.show_map_btn);
        task_lst_btn = (Button) findViewById(R.id.task_lst_btn);
        help_btn = (Button) findViewById(R.id.help_btn);

        show_map_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HelpActivity.this, MapActivity.class));
            }
        });

        task_lst_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HelpActivity.this, ListTasksActivity.class));
            }
        });
        help_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });



    }


}
