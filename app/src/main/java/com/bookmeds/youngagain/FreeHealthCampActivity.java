package com.bookmeds.youngagain;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class FreeHealthCampActivity extends AppCompatActivity {

    private GridView freeHealthCampList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_health_camp);


        freeHealthCampList = (GridView) findViewById(R.id.free_health_camp_list);

        final ArrayList<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem(android.R.drawable.ic_dialog_map, "Free BP Check", "Chennai Jayanth Acupuncture Hospital, Anna Nagar, Chennai, Tamil Nadu, India", 13.095658, 80.206116));
        items.add(new MenuItem(android.R.drawable.ic_dialog_map, "Free Sugar Check", "Srinivas Priya Hospital Pvt Ltd, Patel Road, Perambur, Chennai, India", 13.109593, 80.246666));
        items.add(new MenuItem(android.R.drawable.ic_dialog_map, "Free Dental Check", "RELAX Hospital, Cuttack, Orrisa, Odisha, India", 20.457838, 85.871536));
        items.add(new MenuItem(android.R.drawable.ic_dialog_map, "Free General Check", "Hunsur, Karnataka, India", 12.300942, 76.288460));
        items.add(new MenuItem(android.R.drawable.ic_dialog_map, "Free Homeopathy Checkup", "Korukonda, Andhra Pradesh, India", 17.170408, 81.826538));
        menuAdapter adapter = new menuAdapter(this, items);

        freeHealthCampList.setAdapter(adapter);

        freeHealthCampList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MenuItem item = items.get(position);

                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + item.getLat() + "," + item.getLon());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);

            }
        });
    }
}
