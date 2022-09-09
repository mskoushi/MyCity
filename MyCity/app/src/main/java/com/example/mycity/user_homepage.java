package com.example.mycity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class user_homepage extends AppCompatActivity {

    Button  ufrofile;
    Button register,notification,logout;
    TextView tv;


    database myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_homepage);
        String uname = getIntent().getStringExtra("username");
        Toast.makeText(this, ""+uname, Toast.LENGTH_SHORT).show();
        ufrofile = findViewById(R.id.user_homepage_profile);
        register = findViewById(R.id.user_homepage_complaint);
        notification = findViewById(R.id.user_homepage_response);
        logout = findViewById(R.id.user_homepage_logout);
        myDb = new database(this);
        tv=findViewById(R.id.name);
tv.setText("Welcome "+uname);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(user_homepage.this, user_complaint.class);
                intent.putExtra("username",uname);
                startActivity(intent);
            }
        });


logout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(user_homepage.this, user_login.class);
        startActivity(intent);
    }
});


        ufrofile.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor result = myDb.getAllData(uname);
                        if(result.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (result != null &&result.moveToNext()) {
                            buffer.append("username :"+ result.getString(0)+"\n");
                            buffer.append("Name :"+ result.getString(1)+"\n");
                            buffer.append("phno :"+ result.getString(2)+"\n");
                            buffer.append("address :"+ result.getString(3)+"\n\n");
                        }


//
                        // Show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );



        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(user_homepage.this, user_notification_view.class);
                intent.putExtra("username",uname);
                startActivity(intent);
            }
        });
    }


    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}
