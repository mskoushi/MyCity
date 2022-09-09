package com.example.mycity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class citizen_loginandregister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen_loginandregister);
    }

    public void customer_login_onclick(View view) {
        Intent intent = new Intent(citizen_loginandregister.this,user_login.class);
        startActivity(intent);
    }

    public void signup(View view) {
        Intent intent = new Intent(citizen_loginandregister.this, user_register.class);
        startActivity(intent);
    }
}