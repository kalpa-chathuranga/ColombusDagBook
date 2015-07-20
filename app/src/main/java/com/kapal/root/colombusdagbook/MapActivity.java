package com.kapal.root.colombusdagbook;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    final Context context = this;
    private String TAG= "xxx";
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    public MyDatabaseHandler dbh ;
    public SQLiteDatabase db ;


    public EditText description;
    public EditText location;
    public Button save_dialog_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        //setUpMapIfNeeded();


        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);

        // Getting a reference to the map
        mMap = supportMapFragment.getMap();
        setUpMap();

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Log.d(TAG, latLng.toString());

                Context context = getApplicationContext();
                CharSequence text = "You have clicked on : " + latLng.toString();
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(final LatLng latLng) {

                Log.d("yyy", latLng.toString());

                // Add Marker on selected location
                //Todo: Add custom icon for selected location
                mMap.addMarker(new MarkerOptions()
                                .position(latLng)
                );

                //Create a dialog widndow
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.my_custom_dialog);


                //Get references to dialog window view items
                TextView tv1 = (TextView) findViewById(R.id.textView1);
                TextView tv2 = (TextView) findViewById(R.id.textView2);
                location = (EditText) dialog.findViewById(R.id.dialog_location_edit_text);
                description = (EditText) dialog.findViewById(R.id.dialog_descriptio_edit_text);
                save_dialog_btn = (Button) dialog.findViewById(R.id.save_dialog_btn);

                // Show dialog
                dialog.show();


                // onClick: save button , Send place name, description, lat, long to DB
                save_dialog_btn.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dbh = new MyDatabaseHandler();

                        db = openOrCreateDatabase(dbh.DB_NAME, MODE_PRIVATE, null);

                        try {
                            db.execSQL(dbh.insertRow(
                                    location.getText().toString(),
                                    description.getText().toString(),
                                    latLng.latitude,
                                    latLng.longitude
                            ));


                            Log.d(TAG, "DATA inserted");

                            Context context = getApplicationContext();
                            CharSequence text = " Marker added on " + latLng.toString();
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();

                            startActivity(new Intent(MapActivity.this, ListTasksActivity.class));


                        } catch (SQLException e) {
                            Log.d(TAG, "DATA NOT inserted");
                            e.printStackTrace();
                        }

                        Log.d(TAG,"Save button clicked");


                    }
                });
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {

        mMap.setMyLocationEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(7.0000, 81.0000), 13));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(7.0000, 81.0000))
                .title("SRI LANKA")
                .snippet("Most beautifull country in the world"));
        mMap.getUiSettings().setZoomControlsEnabled(true);

    }

    @Override
    public void onMapReady(GoogleMap map) {


        LatLng sydney = new LatLng(-33.867, 151.206);

        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));

        map.addMarker(new MarkerOptions()
                .title("Sydney")
                .snippet("The most populous city in Australia.")
                .position(sydney)
                 );
    }
}
