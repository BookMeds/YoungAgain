package com.bookmeds.youngagain;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginAcitvity extends AppCompatActivity {
    private String phone;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();
    private TextInputEditText Phone;
    private EditText username;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acitvity);
        mAuth = FirebaseAuth.getInstance();
        Phone = (TextInputEditText) findViewById(R.id.Phone);

        username = (EditText) findViewById(R.id.username);
        phone = Phone.getText().toString().trim();

        mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    String[] data = user.getDisplayName().split(":");
                    Userdetails.name = data[0];
                    Userdetails.phoneno = data[1];
                    Userdetails.UID = user.getUid();
                    startActivity(new Intent(LoginAcitvity.this, MenuActivity.class));
                }

            }
        });
    }

    public void Login(View view) {
        findViewById(R.id.login_form).setVisibility(View.GONE);
        findViewById(R.id.login_loading_layout).setVisibility(View.VISIBLE);
        phone = Phone.getText().toString().trim();
        if (myRef.child(getString(R.string.users)).child(phone).child(getString(R.string.phone)).setValue(phone).isSuccessful()) {
            if (phone.length() > 0)
                mAuth.signInWithEmailAndPassword(phone + "@youngagain.com", "password")
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (!task.isSuccessful()) {
                                            CreateUser(phone + "@youngagain.com");

                                        }
                                    }
                                }
                        );
        }

        findViewById(R.id.login_form).setVisibility(View.VISIBLE);
        findViewById(R.id.login_loading_layout).setVisibility(View.GONE);
    }

    private void CreateUser(final String mPhone) {
        mAuth.createUserWithEmailAndPassword(mPhone, "password")
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginAcitvity.this, "UNABLE TO SIGN IN", Toast.LENGTH_SHORT).show();
                        } else {
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(username.getText().toString().trim()
                                                + ":" + Phone.getText().toString().trim())
                                        .build();
                                user.updateProfile(request);
                            }
                        }

                    }
                });
    }

}
