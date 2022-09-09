package com.example.mycity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class admin_citizen_list extends AppCompatActivity {
ListView listview;
database myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_citizen_list);
        listview=(ListView)findViewById(R.id.listview);
        myDb=new database(this);
        ViewAllCitizen();
    }

    private void ViewAllCitizen() {
        ArrayList<String> arrayList=new ArrayList<String>();

        Cursor res = myDb.getAllCitizen();
        if (res.getCount() == 0) {
            Toast.makeText(admin_citizen_list.this, "No Citizen found", Toast.LENGTH_SHORT).show();

            return;
        }
        StringBuffer buffer;

        while (res != null && res.moveToNext()) {
buffer = new StringBuffer();
            buffer.append("\nUSERNAME:" + res.getString(0) + "\n");
            buffer.append("PHONE NO:" + res.getString(2) + "\n");
            buffer.append("ADDRESS:" + res.getString(3) + "\n");
            arrayList.add(buffer.toString());

        }









        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
        listview.setAdapter(arrayAdapter);
    }
}