package com.bookmeds.youngagain;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        menuList = (GridView) findViewById(R.id.medicine_list);

        final ArrayList<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem(R.drawable.anacin, "Anacin", "For Cold and Headache"));
        items.add(new MenuItem(R.drawable.crocin_advance_tablets, "Crocin", "For small pains"));
        items.add(new MenuItem(R.drawable.saridon, "Saridon", "For headache"));
        items.add(new MenuItem(R.drawable.advin, "Advin", "For Joint relief"));
        items.add(new MenuItem(R.drawable.crocin_pain_relief_, "Crocin", "For instant pain relief"));
        items.add(new MenuItem(R.drawable.moov_spery, "Moov Spray", "For sprains"));
        items.add(new MenuItem(R.drawable.dcold, "DCold", "For common cold"));
        items.add(new MenuItem(R.drawable.sleeping, "Sleeping Pills*", "(Available Only on doctor's prescription)"));
        final menuAdapter adapter = new menuAdapter(this, items);
        menuList.setAdapter(adapter);
        menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle extras = new Bundle();
                Intent intent = new Intent(MedicineListActivity.this, OrderActivity.class);
                extras.putString(getString(R.string.med_name), items.get(position).getName());
                extras.putString(getString(R.string.med_desc), items.get(position).getDetail());
                extras.putInt(getString(R.string.med_icon), items.get(position).getImage());
                switch (position) {
                    case 0:
                        extras.putFloat(getString(R.string.med_price), 10);
                        extras.putBoolean(getString(R.string.med_restrict), false);
                        break;
                    case 1:
                        extras.putFloat(getString(R.string.med_price), 20);
                        extras.putBoolean(getString(R.string.med_restrict), false);
                        break;
                    case 2:
                        extras.putFloat(getString(R.string.med_price), 30);
                        extras.putBoolean(getString(R.string.med_restrict), false);
                        break;
                    case 3:
                        extras.putFloat(getString(R.string.med_price), 40);
                        extras.putBoolean(getString(R.string.med_restrict), false);
                        break;
                    case 4:
                        extras.putFloat(getString(R.string.med_price), 50);
                        extras.putBoolean(getString(R.string.med_restrict), false);
                        break;
                    case 5:
                        extras.putFloat(getString(R.string.med_price), 60);
                        extras.putBoolean(getString(R.string.med_restrict), false);
                        break;
                    case 6:
                        extras.putFloat(getString(R.string.med_price), 70);
                        extras.putBoolean(getString(R.string.med_restrict), false);
                        break;
                    case 7:
                        extras.putFloat(getString(R.string.med_price), 80);
                        extras.putBoolean(getString(R.string.med_restrict), true);
                        break;
                }
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
    }


}
