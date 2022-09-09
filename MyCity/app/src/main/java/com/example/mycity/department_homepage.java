package com.example.mycity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class department_homepage extends AppCompatActivity {
    Button dept_citizen_list,dept_complaint_view,dept_complaint_notify,dept_logout;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_homepage);
        String uname = getIntent().getStringExtra("username");

        Toast.makeText(this, ""+uname, Toast.LENGTH_SHORT).show();

        dept_citizen_list=findViewById(R.id.dept_citizen_list);
        dept_complaint_view=findViewById(R.id.dept_complaint_view);
        title=findViewById(R.id.department_title);
title.setText(" DEPARTMENT OF "+uname.toUpperCase(Locale.ROOT));
        //dept_complaint_notify=findViewById(R.id.dept_complaint_notify);
        dept_logout=findViewById(R.id.dept_logout);
        dept_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l=new Intent(department_homepage.this,department_login.class);
                startActivity(l);
            }
        });

        dept_citizen_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(department_homepage.this, department_citizen_list.class);
                startActivity(intent);
            }
        });

        dept_complaint_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(department_homepage.this, department_complaint_views.class);
                intent.putExtra("username1",uname);
                startActivity(intent);



            }
        });








    }
}