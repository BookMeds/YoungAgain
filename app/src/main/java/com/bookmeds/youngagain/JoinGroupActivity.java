package com.bookmeds.youngagain;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class JoinGroupActivity extends AppCompatActivity {


    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_group);
    }

    public void joinGroup(View view) {
        TextInputEditText groupCodeEditText = (TextInputEditText) findViewById(R.id.group_code_input);
        String groupCode = groupCodeEditText.getText().toString().trim();
        if (myRef.child(getString(R.string.users))
                .child(Userdetails.UID)
                .child(getString(R.string.groups))
                .child(getString(R.string.groups) + groupCode)
                .setValue(getString(R.string.groups) + groupCode).isComplete()) {

            Bundle extras = new Bundle();
            extras.putString(getString(R.string.groups), groupCode + "");
            Intent intent = new Intent(this, ChatScreenActivity.class);
            intent.putExtras(extras);
            startActivity(intent);
        }
    }
}
