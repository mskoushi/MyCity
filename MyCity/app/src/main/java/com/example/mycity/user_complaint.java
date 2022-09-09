package com.example.mycity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class user_complaint extends AppCompatActivity {
   EditText cname,cphno,cdesc;

   Button csubmit;
   database mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_complaint);

        cname = findViewById(R.id.complaint_name);
        cphno = findViewById(R.id.complaint_phno);
        cdesc = findViewById(R.id.complaint_desc);
        csubmit = findViewById(R.id.complaint_submit);
        mydb=new database(this);
        String uname = getIntent().getStringExtra("username");

        csubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String cname_typed =  cname.getText().toString();
               String cphno_typed =  cphno.getText().toString();
               String cdesc_typed =  cdesc.getText().toString();

               //  Toast.makeText(user_complaint.this, ""+uname+cname_typed+cphno_typed+cdesc_typed, Toast.LENGTH_SHORT).show();

                boolean result=mydb.insertComplaint(uname,cname_typed,cphno_typed,cdesc_typed);
                if(result)
                    Toast.makeText(user_complaint.this, "Done", Toast.LENGTH_SHORT).show();
            }





        });



    }
}