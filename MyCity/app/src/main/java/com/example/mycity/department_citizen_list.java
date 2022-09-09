package com.example.mycity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class department_citizen_list extends AppCompatActivity {
    ListView citizenList;
    database myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_citizen_list);
        citizenList=(ListView)findViewById(R.id.citizenList);
        myDb=new database(this);
        ViewCitizen();


    }

    private void ViewCitizen() {

        ArrayList<String> arrayList=new ArrayList<String>();

        Cursor res = myDb.getAllCitizen();
        if (res.getCount() == 0) {
            Toast.makeText(department_citizen_list.this, "No Citizen found", Toast.LENGTH_SHORT).show();

            return;
        }
        StringBuffer buffer;

        while (res != null && res.moveToNext()) {
buffer=new StringBuffer();
            buffer.append("\nUSERNAME:" + res.getString(0) + "\n");
            buffer.append("PHONE NO:" + res.getString(2) + "\n");
            buffer.append("ADDRESS:" + res.getString(3) + "\n");
            arrayList.add(buffer.toString());

        }









        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
        citizenList.setAdapter(arrayAdapter);



    }
}