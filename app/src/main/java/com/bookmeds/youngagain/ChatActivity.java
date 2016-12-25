package com.bookmeds.youngagain;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    private GridView chatMenuList;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        myRef = database.getReference(getString(R.string.users) + '/' +
                Userdetails.UID + '/' +
                getString(R.string.groups));


        chatMenuList = (GridView) findViewById(R.id.chat_menu_grid);

        final ArrayList<MenuItem> chatItems = new ArrayList<>();
        chatItems.add(new MenuItem(R.drawable.join_group, "Join Group", "Join a already existing group"));
        chatItems.add(new MenuItem(R.drawable.create_group, "Create Group", "Create a new group"));

        final menuAdapter adapter = new menuAdapter(this, chatItems);

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.getValue(String.class);
                chatItems.add(new MenuItem(R.drawable.innergroupicon, value, ""));
                adapter.notifyDataSetChanged();
            }


            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        chatMenuList.setAdapter(adapter);

        chatMenuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(ChatActivity.this, JoinGroupActivity.class));
                        break;
                    case 1:
                        int groupCode = (int) (Math.random() * 10000);
                        if (myRef.child(getString(R.string.groups) + groupCode)
                                .setValue(getString(R.string.groups) + groupCode).isComplete()) {

                            Bundle extras = new Bundle();
                            extras.putString(getString(R.string.groups), groupCode + "");
                            Intent intent = new Intent(ChatActivity.this, ChatScreenActivity.class);
                            intent.putExtras(extras);
                            startActivity(intent);
                        }
                        break;
                    default:
                        String existingGroupCode = adapter.getItem(position).getName();
                        Bundle extras = new Bundle();
                        extras.putString(getString(R.string.groups), existingGroupCode);
                        Intent intent = new Intent(ChatActivity.this, ChatScreenActivity.class);
                        intent.putExtras(extras);
                        startActivity(intent);
                }
            }
        });
    }
}
