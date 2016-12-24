package com.bookmeds.youngagain;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    private GridView chatMenuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chatMenuList = (GridView) findViewById(R.id.menulist);

        ArrayList<MenuItem> chatItems = new ArrayList<>();
        chatItems.add(new MenuItem(R.drawable.join_group, "Join Group", "Join a already existing group"));
        chatItems.add(new MenuItem(R.drawable.create_group, "Create Group", "Create a new group"));
        menuAdapter adapter = new menuAdapter(this, chatItems);

        chatMenuList.setAdapter(adapter);

        chatMenuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(ChatActivity.this, DailyRemainderActivity.class));
                        //todo: change
                        break;
                    case 1:
                        break;
                }
            }
        });
    }
}
