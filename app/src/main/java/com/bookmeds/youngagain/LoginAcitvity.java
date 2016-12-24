package com.bookmeds.youngagain;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class LoginAcitvity extends AppCompatActivity {
    TextInputEditText Phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acitvity);
        Phone = (TextInputEditText) findViewById(R.id.Phone);
    }

    public void Login(View view) {
        String phone = Phone.getText().toString().trim();

    }
}
