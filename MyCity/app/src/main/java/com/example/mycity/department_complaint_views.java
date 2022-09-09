package com.example.mycity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class department_complaint_views extends AppCompatActivity {
    ListView complaintListViews;
database myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_complaint_views);

        complaintListViews = (ListView) findViewById(R.id.complaintListViews);
        myDb = new database(this);

        ViewComplaints();
    }
        private void ViewComplaints() {
            String uname = getIntent().getStringExtra("username1");
        ArrayList<String> arrayList=new ArrayList<String>();

        Cursor res = myDb.getDepartmentComplaint(uname);
        if (res.getCount() == 0) {
            Toast.makeText(department_complaint_views.this, "No Citizen found", Toast.LENGTH_SHORT).show();

            return;
        }
        StringBuffer buffer;

        while (res != null && res.moveToNext()) {
buffer=new StringBuffer();
            buffer.append("\nREG NO:" + res.getString(0) + "\n");
            buffer.append("COMPLAINER:" + res.getString(1) + "\n");
            buffer.append("PHONE NO:" + res.getString(3) + "\n");
            buffer.append("DETAILS:" + res.getString(5) + "\n");
            arrayList.add(buffer.toString());

        }









        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
        complaintListViews.setAdapter(arrayAdapter);

        complaintListViews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent=new Intent(department_complaint_views.this,department_citizen_notify.class);
                intent.putExtra("user",arrayList.get(position).toString());
                startActivity(intent);
            }
        });
    }



}