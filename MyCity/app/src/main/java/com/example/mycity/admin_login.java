package com.example.mycity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class admin_login extends AppCompatActivity {
        EditText username;
        EditText password;
        Button login;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_admin_login);
                username = findViewById(R.id.user_login_name);
                password = findViewById(R.id.user_login_password);
                login = findViewById(R.id.user_login_signin);
                //getSupportActionBar().hide();
                login.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                String uname = username.getText().toString();
                                String pswd = password.getText().toString();

                                if(uname.equals("admin") && pswd.equals("admin") )
                                {
                                        Intent intent = new Intent(admin_login.this, admin_homepage.class);
                                        startActivity(intent);
                                }
                                else
                                {
                                        Toast.makeText(admin_login.this, "Enter valid credential", Toast.LENGTH_SHORT).show();
                                }

                        }
                });



        }
}
