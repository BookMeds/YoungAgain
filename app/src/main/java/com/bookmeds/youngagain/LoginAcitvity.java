package com.bookmeds.youngagain;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginAcitvity extends AppCompatActivity {
    private String phone;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();
    private TextInputEditText Phone;
    private TextInputEditText username;
    private FirebaseAuth mAuth;
    private boolean newUser = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acitvity);
        mAuth = FirebaseAuth.getInstance();
        Phone = (TextInputEditText) findViewById(R.id.Phone);

        username = (TextInputEditText) findViewById(R.id.username);
        phone = Phone.getText().toString().trim();

        mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    if (newUser) {
                        save(getString(R.string.name), username.getText().toString());
                        save(getString(R.string.phone), Phone.getText().toString());
                        save("UID", user.getUid());
                    }
                    Userdetails.name = read(getString(R.string.name), "Unknown User");
                    Userdetails.phoneno = read(getString(R.string.phone), "0");
                    Userdetails.UID = read("UID", user.getUid());
                    startActivity(new Intent(LoginAcitvity.this, MenuActivity.class));
                    finish();
                }
            }
        });
    }

    public void Login(View view) {
        newUser = true;
        findViewById(R.id.login_form).setVisibility(View.GONE);
        findViewById(R.id.login_loading_layout).setVisibility(View.VISIBLE);
        phone = Phone.getText().toString().trim();
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

    private void CreateUser(final String mPhone) {
        mAuth.createUserWithEmailAndPassword(mPhone, "password")
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginAcitvity.this, "UNABLE TO SIGN IN", Toast.LENGTH_SHORT).show();
                            findViewById(R.id.login_form).setVisibility(View.VISIBLE);
                            findViewById(R.id.login_loading_layout).setVisibility(View.GONE);
                        }

                    }
                });
    }

    public void save(String valueKey, String value) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(this);
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString(valueKey, value);
        edit.commit();
    }

    public String read(String valueKey, String valueDefault) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(this);
        return prefs.getString(valueKey, valueDefault);
    }

}
