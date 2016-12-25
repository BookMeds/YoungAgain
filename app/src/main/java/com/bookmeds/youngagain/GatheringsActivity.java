package com.bookmeds.youngagain;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class GatheringsActivity extends AppCompatActivity {

    public static LatLng latLng;

    private GridView gatheringList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gatherings);

        gatheringList = (GridView) findViewById(R.id.gathering_list);

        final ArrayList<MenuItem> items = new ArrayList<>();

        items.add(new MenuItem(R.drawable.gathering_menu, "Create Gathering", "", 0, 0));
        items.add(new MenuItem(R.drawable.gathering_menu, "Gathering", "Hosted by Asif", 17.416785, 78.382209));

        final menuAdapter adapter = new menuAdapter(this, items);

        gatheringList.setAdapter(adapter);

        gatheringList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    MenuItem item = items.get(position);
                    Uri gmmIntentUri = Uri.parse("google.navigation:q=" + item.getLat() + "," + item.getLon());
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                } else {
                    latLng = null;
                    startActivity(new Intent(GatheringsActivity.this, MapsActivity.class));
                    if (latLng != null) {
                        items.add(new MenuItem(R.drawable.gathering_menu, "Gathering", "Hosted by " + Userdetails.name, latLng.latitude, latLng.longitude));
                        adapter.notifyDataSetChanged();
                    }
                }

            }
        });

    }
}
