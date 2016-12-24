package com.bookmeds.youngagain;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginAcitvity extends AppCompatActivity {
    EditText Phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acitvity);
        Phone = (EditText) findViewById(R.id.Phone);
    }

    public void Login(View view) {
        String phone = Phone.getText().toString().trim();

    }
}
