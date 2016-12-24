package com.bookmeds.youngagain;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginAcitvity extends AppCompatActivity {
    TextInputEditText Phone;
    String phone;
    private FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acitvity);
        mAuth = FirebaseAuth.getInstance();
        Phone = (TextInputEditText) findViewById(R.id.Phone);
        phone = Phone.getText().toString().trim();
        DatabaseReference currentUser = myRef.child(getString(R.string.users)).child(phone);
        currentUser.child(getString(R.string.name)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Userdetails.name = value;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        currentUser.child(getString(R.string.phone)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Userdetails.phoneno = value;

                startActivity(new Intent(LoginAcitvity.this, MenuActivity.class));
                finish();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void Login(View view) {
        phone = Phone.getText().toString().trim();
        if (phone.length() > 0) {
            mAuth.signInWithEmailAndPassword(phone + "@youngagain.com", "password")
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()) {
                                CreateUser(phone + "@youngagain.com");
                                DatabaseReference currentUser = myRef.child(getString(R.string.users)).child(phone);
                                currentUser.child(getString(R.string.name)).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        String value = dataSnapshot.getValue(String.class);
                                        Userdetails.name = value;
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });

                                currentUser.child(getString(R.string.phone)).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        String value = dataSnapshot.getValue(String.class);
                                        Userdetails.phoneno = value;
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });

                                startActivity(new Intent(LoginAcitvity.this, MenuActivity.class));
                                finish();
                            }


                        }
                    });
        }

    }

    private void CreateUser(final String phone) {
        mAuth.createUserWithEmailAndPassword(phone, "password")
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Log.e("register", task.getException().toString());
                            Toast.makeText(LoginAcitvity.this, "UNABLE TO SIGN IN", Toast.LENGTH_SHORT).show();
                        } else {
                            Userdetails.phoneno = phone;
                            startActivity(new Intent(LoginAcitvity.this, DetailActivity.class));
                        }

                    }
                });

    }


}
