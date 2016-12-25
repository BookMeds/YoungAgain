package com.bookmeds.youngagain;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class DailyRemainderActivity extends AppCompatActivity {
    GridView menuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_remainder);

        menuList = (GridView) findViewById(R.id.remainder_list);

        final ArrayList<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem(R.drawable.daily_remainders, "Ideal Diet Chart", ""));
        items.add(new MenuItem(R.drawable.daily_remainders, "Timetable", ""));
        final menuAdapter adapter = new menuAdapter(this, items);
        menuList.setAdapter(adapter);
        menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(DailyRemainderActivity.this, DietActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(DailyRemainderActivity.this, TimeTableActivity.class));
                        break;
                }
            }
        });
    }
}
