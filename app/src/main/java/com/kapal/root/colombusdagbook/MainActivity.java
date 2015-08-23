package com.kapal.root.colombusdagbook;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Locale;
import java.util.Timer;
import java.util.logging.Handler;
import java.util.logging.LogRecord;


public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*
        *
        * Three buttons are created
        * This for user to select between 3 languages Engish, Sinhala and French
        *
        * */
        Button b1 = (Button) findViewById(R.id.english_button);
        Button b2 = (Button) findViewById(R.id.sinhala_button);
        Button b3 = (Button) findViewById(R.id.french_button);


        /*
        * Buttons OnCLick even is handled here
        * OnCLick Listen has been implemented as an interface with the class definition
        * Therefore no need to add anonymous inner classes for button event handling
        * */

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);



    }

    /*
    *
    * Button OnCLick Event code goes here
    *
    * */
    @Override
    public void onClick(View v) {

        /*
        * If English Button is clicked App can be start MapActivity directly
        * No need to set locale here because default locale is English
        *
        * */

        if (v.getId() == R.id.english_button)
        {

            startActivity(new Intent(MainActivity.this, MapActivity.class));

        }

        else if (v.getId() == R.id.sinhala_button){

            // New Locale object has been instantiated
            Locale locale = new Locale("si");

            // Created Locale has been set as the default locale
            Locale.setDefault(locale);

            // Configuration object has been instantiated
            Configuration config = new Configuration();

            //Configuration locale is set to created locale
            config.locale = locale;

            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());


            // Go to MapActivity with new locale set up
            startActivity(new Intent(MainActivity.this, MapActivity.class));

        }


        // Above locale set up is applied to French Button also
        // See the relevant code below

        else if (v.getId() == R.id.french_button){

            Locale locale = new Locale("fr");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());

            startActivity(new Intent(MainActivity.this, MapActivity.class));

        }
    }
}
