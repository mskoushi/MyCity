package com.example.mycity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class user_notification_view extends AppCompatActivity {
    ListView response;
    database myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_notification_view);
        response = (ListView) findViewById(R.id.response);
        myDb = new database(this);
        notifications();
    }

    private void notifications() {

        String uname = getIntent().getStringExtra("username");
        ArrayList<String> arrayList=new ArrayList<String>();

        Cursor res = myDb.getNotification(uname);
        if (res.getCount() == 0) {
            Toast.makeText(user_notification_view.this, "No Citizen found", Toast.LENGTH_SHORT).show();

            return;
        }

        //int i=0;
        StringBuffer buffer;
        while (res != null && res.moveToNext()) {
            buffer=new StringBuffer();
            buffer.append("\nREG NO:" + res.getString(0) + "\n");
            buffer.append("COMPLAINER:" + res.getString(2) + "\n");
            buffer.append("PHONE NO:" + res.getString(3) + "\n");
            buffer.append("DEPARTMENT:" + res.getString(4) + "\n");
            buffer.append("DETAILS:" + res.getString(5) + "\n");
            buffer.append("DATE:" + res.getString(8) + "\n");
            buffer.append("STATUS:" + res.getString(7) + "\n");
            arrayList.add(buffer.toString());

        }



        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
        response.setAdapter(arrayAdapter);
    }
}