package com.example.mycity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class user_register extends AppCompatActivity {
    database mydb;
    Button signup;
    EditText uname,name,password,repassword,phno,address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        mydb=new database(this);

        uname = findViewById(R.id.customer_uname);
        name = findViewById(R.id.customer_name);
        password = findViewById(R.id.customer_password);
        repassword = findViewById(R.id.customer_repassword);
        phno = findViewById(R.id.customer_phno);
        address = findViewById(R.id.customer_address);
        signup = findViewById(R.id.register_button);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname_get = uname.getText().toString();
                boolean result=mydb.insertData(uname.getText().toString(),name.getText().toString(),password.getText().toString(),repassword.getText().toString(),phno.getText().toString(),address.getText().toString());
                if(result) {
                  //  Toast.makeText(user_register.this, "Done", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(user_register.this, user_homepage.class);
                    intent.putExtra("username",uname_get);
                    startActivity(intent);
                }
                else
                    Toast.makeText(user_register.this, "not done", Toast.LENGTH_SHORT).show();
            }
        });


    }
}