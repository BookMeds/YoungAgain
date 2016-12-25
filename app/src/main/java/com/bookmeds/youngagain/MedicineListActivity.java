package com.bookmeds.youngagain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class MedicineListActivity extends AppCompatActivity {
    GridView menuList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_list);
        menuList = (GridView) findViewById(R.id.menulist);

        ArrayList<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem(R.drawable.Anacin, "Prescribed for headache and cold", "Quantity"));
        items.add(new MenuItem(R.drawable.crocin_advance_tablets, "prescribed for small pains ", "Quantity"));
        items.add(new MenuItem(R.drawable.Saridon, "prescrbied for headache", "Quantity"));
        items.add(new MenuItem(R.drawable.advin, "prescribed for body pains", "Quantity"));
        items.add(new MenuItem(R.drawable.crocin_pain_relief_1433739634, "body pain relifs", "Quantity"));
        items.add(new MenuItem(R.drawable.moov_spery,"for injuries during playing","Quantity"));
        items.add(new MenuItem(R.drawable.dcold_250x250, "prescribed for body pains and cold", "Quantity"));
        items.add(new MenuItem(R.drawable.sleeping, "you cannot buy this until you upload the Doctors prescription", ""));
        menuAdapter adapter = new menuAdapter(this, items);
        menuList.setAdapter(adapter);
        menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(MedicineListActivity.this,ThanksActivity.class));
            }
        });
    };
}
