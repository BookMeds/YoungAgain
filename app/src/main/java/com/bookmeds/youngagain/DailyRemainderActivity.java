package com.bookmeds.youngagain;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

public class DailyRemainderActivity extends AppCompatActivity {
    GridView menuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_remainder);
        menuList = (GridView) findViewById(R.id.menulist);
        ArrayList<MenuItem> items = new ArrayList<>();
    }
}
