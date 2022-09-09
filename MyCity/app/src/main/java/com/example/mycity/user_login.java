package com.example.mycity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class user_login extends AppCompatActivity {

    EditText uname,upassword;
    Button usignin;
    database myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        myDb = new database(this);
        //getSupportActionBar().hide();


        uname = findViewById(R.id.user_login_name);
        upassword = findViewById(R.id.user_login_password);
        usignin = findViewById(R.id.user_login_signin);

        usignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname_typed = uname.getText().toString();
                String upassword_typed = upassword.getText().toString();

                boolean result = myDb.checkUser(uname_typed,upassword_typed);
                if(result) {
                    Toast.makeText(user_login.this, "Login Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(user_login.this, user_homepage.class);
                    intent.putExtra("username",uname_typed);
                    startActivity(intent);
                }
                else
                    Toast.makeText(user_login.this, "Login Unsuccessfull", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void register_page(View view) {
        Intent register=new Intent(user_login.this,user_register.class);
        startActivity(register);
    }
}