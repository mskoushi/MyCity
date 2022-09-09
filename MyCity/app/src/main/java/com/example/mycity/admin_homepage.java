package com.example.mycity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class admin_homepage extends AppCompatActivity {
    Button admin_complaint_view,admin_complaint_forward,admin_citizen_lists,logout;
    database myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_homepage);
        admin_complaint_view = findViewById(R.id.admin_complaint_view);
        //admin_complaint_forward=findViewById(R.id.admin_complaint_forward);
        admin_citizen_lists=findViewById(R.id.admin_citizen_list);
        logout=findViewById(R.id.admin_logout);
        myDb = new database(this);
//        deleteComplaint();
        complaintView();
       // forwardComplaint();
        userView();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l=new Intent(admin_homepage.this,admin_login.class);
                startActivity(l);
            }
        });

    }

    private void userView() {
        admin_citizen_lists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(admin_homepage.this, admin_citizen_list.class);
                startActivity(intent);
            }
        });
    }

//    private void deleteComplaint() {
//        String updated_regno = getIntent().getStringExtra("regno");
//        Integer res = myDb.deleteData(updated_regno);
//        if (res > 0){
//            Toast.makeText(admin_homepage.this, "Data Deleted", Toast.LENGTH_LONG).show();
//        complaintView();
//    }
//        else
//            Toast.makeText(admin_homepage.this,"Data not Deleted",Toast.LENGTH_LONG).show();
//    }

//    private void forwardComplaint() {
//        admin_complaint_forward.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(admin_homepage.this,admin_department_forward.class);
//                startActivity(intent);
//            }
//        });
//
//    }

    public void complaintView() {
       // String updated_regno = getIntent().getStringExtra("regno");


    admin_complaint_view.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                Intent nextpage=new Intent(admin_homepage.this,admin_complaint_view.class);
                startActivity(nextpage);








        }

    });
}
    public void showMessage(String title, String message) {

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }


}