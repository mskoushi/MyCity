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

public class admin_complaint_view extends AppCompatActivity {
ListView admin_complaintListViews;
database myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_complaint_view);
        admin_complaintListViews = (ListView) findViewById(R.id.admin_complaint);
        myDb = new database(this);
        adminViewComplaints();





    }

    private void adminViewComplaints() {
        ArrayList<String> arrayList=new ArrayList<String>();
        Cursor res = myDb.getAllComplaints();
        if (res.getCount() == 0) {
            Toast.makeText(admin_complaint_view.this, "No record found", Toast.LENGTH_SHORT).show();

            return;
        }
        StringBuffer buffer;

        while (res != null && res.moveToNext()) {
            buffer=new StringBuffer();
            buffer.append("\nREG NO:" + res.getString(0) + "\n");
            buffer.append("COMPLAINER:" + res.getString(1) + "\n");
            buffer.append("COMPLAINT NAME:" + res.getString(2) + "\n");
            buffer.append("PHONE NO:" + res.getString(3) + "\n");
            buffer.append("DETAILS:" + res.getString(5) + "\n");
            arrayList.add(buffer.toString());
        }

        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
        admin_complaintListViews.setAdapter(arrayAdapter);

        admin_complaintListViews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(admin_complaint_view.this,admin_department_forward.class);
                intent.putExtra("user",arrayList.get(position).toString());
                startActivity(intent);
            }
        });
    }
}