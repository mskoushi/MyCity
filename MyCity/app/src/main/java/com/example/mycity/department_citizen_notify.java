package com.example.mycity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class department_citizen_notify extends AppCompatActivity {
TextView tv1;
EditText regno,date1;
Button submit;
database mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_citizen_notify);
        mydb=new database(this);

        tv1=findViewById(R.id.tv);
        regno=findViewById(R.id.regno);
        date1=findViewById(R.id.date1);
        submit=findViewById(R.id.submit);
        String udetails = getIntent().getStringExtra("user");
        tv1.setText(udetails);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rno=regno.getText().toString();
                String d=date1.getText().toString();
                boolean result=mydb.insertResponse(rno,"Done",d);
                if(result)
                    Toast.makeText(department_citizen_notify.this, "Done", Toast.LENGTH_SHORT).show();
    else
                    Toast.makeText(department_citizen_notify.this, "Already Done", Toast.LENGTH_SHORT).show();


            }
        });



    }
}