package com.bookmeds.youngagain;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ChatScreenActivity extends AppCompatActivity {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private ListView chatMessageList;
    private long messageNo = 0;
    private DatabaseReference myRef;
    private TextView groupCodeTextView;
    private EditText messageBox;
    private ArrayList<String> messages;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);
        Bundle extras = getIntent().getExtras();
        String groupCode = extras.getString(getString(R.string.groups));

        messageBox = (EditText) findViewById(R.id.new_message);
        chatMessageList = (ListView) findViewById(R.id.chat_message_list);
        groupCodeTextView = (TextView) findViewById(R.id.group_code);
        groupCodeTextView.setText("Group Code :" + groupCode);

        messages = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, messages);
        chatMessageList.setAdapter(adapter);

        myRef = database.getReference(getString(R.string.groups)).child(groupCode);

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                messages.add(dataSnapshot.getValue(String.class));
                adapter.notifyDataSetChanged();
                chatMessageList.smoothScrollToPosition((int) messageNo);
                messageNo++;
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
        String message = messageBox.getText().toString().trim();
        if (message.length() > 0) {
            myRef.child(messageNo + "").setValue(Userdetails.name + ": " + message);
            messageBox.setText("");
        }
    }
}
