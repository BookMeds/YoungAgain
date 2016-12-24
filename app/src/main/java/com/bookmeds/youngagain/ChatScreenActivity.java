package com.bookmeds.youngagain;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ChatScreenActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    ListView chatmessagelist;
    long messageno = 0;
    DatabaseReference myRef;
    EditText messagebox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);
        Bundle extras = getIntent().getExtras();
        messagebox = (EditText) findViewById(R.id.new_message);
        String groupCode = extras.getString(getString(R.string.groups));
        chatmessagelist = (ListView) findViewById(R.id.chat_message_list);
        final ArrayList messages = new ArrayList();
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, messages);
        chatmessagelist.setAdapter(adapter);

        myRef = database.getReference(getString(R.string.groups)).child(groupCode);

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                messages.add(dataSnapshot.child(s).getValue(String.class));
                adapter.notifyDataSetChanged();
                messageno++;
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
    }

    public void sendMessage(View view) {
        String message = messagebox.getText().toString().trim();
        if (message.length() > 0) {
            myRef.child(messageno + "").setValue(message);
            messageno++;
        }
    }
}
