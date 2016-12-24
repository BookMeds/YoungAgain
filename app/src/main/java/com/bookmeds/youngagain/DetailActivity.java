package com.bookmeds.youngagain;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailActivity extends AppCompatActivity {
    EditText username;

    int PLACE_PICKER_REQUEST = 1;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        username = (EditText) findViewById(R.id.username);
    }

    public void PICKPLACE(View view) {

    }

    public void next(View view) {
        myRef.child(getString(R.string.users)).child(getString(R.string.users)).child(Userdetails.phoneno).child(getString(R.string.name)).setValue(username.getText());
        myRef.child(getString(R.string.users)).child(getString(R.string.users)).child(Userdetails.phoneno).child(getString(R.string.phone)).setValue(Userdetails.phoneno);
        finish();
    }
}
