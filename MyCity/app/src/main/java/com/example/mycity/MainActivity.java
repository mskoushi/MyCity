package com.example.mycity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    database mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  database mydb = new database(this,"complaint_management.db",null,1);
      mydb = new database(this);
      //getSupportActionBar().hide();

    }

    public void management_login_onclick(View view) {
        Intent intent = new Intent(MainActivity.this, management_login.class);
        startActivity(intent);
    }

    public void citizen_login_onclick(View view) {
        Intent intent = new Intent(MainActivity.this, user_login.class);
        startActivity(intent);
    }
}