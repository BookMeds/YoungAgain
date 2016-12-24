package com.bookmeds.youngagain;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    GridView menuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        menuList = (GridView) findViewById(R.id.menulist);

        ArrayList<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem(R.drawable.daily_remainders, "Daily Remainder", ""));
        items.add(new MenuItem(R.drawable.medicines, "Medicines", "Buy medicines online"));
        items.add(new MenuItem(R.drawable.health_camps, "Free Health Camps", "View free health camps near you"));
        items.add(new MenuItem(R.drawable.chat, "Chat", "Chat with friends"));
        items.add(new MenuItem(R.drawable.gather, "Gatherings", "Create or attend a gathering around"));
        menuAdapter adapter = new menuAdapter(this, items);

        menuList.setAdapter(adapter);
    }
}
