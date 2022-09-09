package com.example.mycity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class management_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_login);
        //getSupportActionBar().hide();
    }

public void admin_login_onclick(View view) {
       Intent intent = new Intent(management_login.this,admin_login.class);
        startActivity(intent);
    }

    public void department_login(View view) {
        Intent intent = new Intent(management_login.this, department_login.class);
       startActivity(intent);
    }

//    public void department_login_onclick(View view) {
//        Intent intent = new Intent(management_login.this, department_login.class);
//        startActivity(intent);
//    }



}