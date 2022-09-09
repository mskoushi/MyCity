package com.example.mycity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class admin_department_forward extends AppCompatActivity {
    EditText dept_regno,dept_name;
    Button submit;
    database myDb;
    TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_department_forward);
        dept_regno = findViewById(R.id.forward_complaint_regno);
        dept_name = findViewById(R.id.forward_complaint_department);
        submit = findViewById(R.id.forward_complaint_submit);
        tv1=findViewById(R.id.details);
        myDb = new database(this);
        String udetails = getIntent().getStringExtra("user");
        tv1.setText(udetails);
        submitForward();
    }

    public void submitForward() {

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                boolean isUpdate = myDb.updateDepartment(dept_regno.getText().toString(), dept_name.getText().toString());
                if (isUpdate == true){
                    Toast.makeText(admin_department_forward.this, "Updated", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(admin_department_forward.this, admin_homepage.class);
                intent.putExtra("regno", dept_regno.getText().toString());
                startActivity(intent);
            }
    else
        Toast.makeText(admin_department_forward.this, "Not updated", Toast.LENGTH_SHORT).show();
            }
        });



    }


}