package com.example.mycity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class department_login extends AppCompatActivity {

    EditText username;
    EditText password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_login);

       username = findViewById(R.id.department_login_name);
       password = findViewById(R.id.department_login_password);
       login = findViewById(R.id.department_login_signin);
        //getSupportActionBar().hide();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname = username.getText().toString();
                String pswd = password.getText().toString();

                if(uname.equals("water") && pswd.equals("water") || uname.equals("waste") && pswd.equals("waste"))
                {
                    Intent intent = new Intent(department_login.this, department_homepage.class);
                    intent.putExtra("username",uname);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(department_login.this, "Enter valid credential", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}