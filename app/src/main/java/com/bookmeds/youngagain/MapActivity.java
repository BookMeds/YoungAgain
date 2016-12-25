package com.bookmeds.youngagain;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MapActivity extends AppCompatActivity {
    private String name;
    private String location;
    private double lat;
    private double lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Bundle extras = getIntent().getExtras();
        name = extras.getString(getString(R.string.map_name));
        location = extras.getString(getString(R.string.map_location));
        lat = extras.getDouble(getString(R.string.map_lat));
        lon = extras.getDouble(getString(R.string.map_long));
    }
}
