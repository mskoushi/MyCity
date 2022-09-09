package com.example.mycity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

import org.w3c.dom.Text;

public class database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "mycitydata";
    public static final String TABLE_NAME_REGISTER = "register_table";
    public static final String COL_1 = "register_username";
    public static final String COL_2 = "register_name";
    public static final String COL_3 = "register_password";
    public static final String COL_4 = "register_phno";
    public static final String COL_5 = "register_address";

    public static final String TABLE_NAME_LOGIN = "login_table";
    public static final String COL_11 = "login_username";
    public static final String COL_12 = "login_password";
    public static final String COL_13 = "login_role";

    public static final String TABLE_NAME_COMPLAINT = "complaint_table";
    public static final String COL_21 = "complaint_regno";
    public static final String COL_22 = "complaint_username";
    public static final String COL_23 = "complaint_name";
    public static final String COL_24 = "complaint_phno";
    public static final String COL_25 = "complaint_department";
    public static final String COL_26 = "complaint_details";

    public static final String TABLE_NAME_RESPONSE = "response_table";
    public static final String COL_31 = "response_regno";
    public static final String COL_32 = "response_status";
    public static final String COL_33 = "response_date";


    public database(Context context) {

        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME_REGISTER +" ( register_username TEXT primary key,register_name TEXT,register_phno TEXT,register_address TEXT)");
        db.execSQL("create table "+TABLE_NAME_LOGIN +" (login_username TEXT primary key,login_password TEXT,login_role TEXT)");
        db.execSQL("create table " + TABLE_NAME_COMPLAINT +" ( complaint_regno INTEGER PRIMARY KEY AUTOINCREMENT, complaint_username TEXT,complaint_name TEXT,complaint_phno TEXT,complaint_department TEXT,complaint_details TEXT)");
        db.execSQL("create table " + TABLE_NAME_RESPONSE +" (response_regno INTEGER primary key ,response_status TEXT,response_date DATE, foreign key (response_regno) references complaint_table(complaint_regno) on delete cascade)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_REGISTER);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_LOGIN);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_COMPLAINT);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_RESPONSE);

        onCreate(db);

    }

    public Integer deleteData(String regno)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_COMPLAINT,"complaint_regno = ?",new String[] {regno});
    }


    public boolean updateDepartment(String regno,String dept_name) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_25,dept_name);
        db.update(TABLE_NAME_COMPLAINT, contentValues, "complaint_regno = ?",new String[] {regno});

        return true;
    }
    public Cursor getAllComplaints()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME_COMPLAINT+" where complaint_department = ?",new String[]{""});

        return res;


    }

    public Cursor getDepartmentComplaint(String deptname)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME_COMPLAINT+" where complaint_department = ?",new String[]{deptname});

        return res;


    }


    public Cursor getNotification(String uname)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME_COMPLAINT+" inner join "+TABLE_NAME_RESPONSE+" on complaint_table.complaint_regno=response_table.response_regno where complaint_username = ?",new String[]{uname});

        return res;


    }



    public boolean insertComplaint(String cusername,String cname,String cphno,String cdetails) {
            SQLiteDatabase db1 = this.getWritableDatabase();
            ContentValues cvalue=new ContentValues();
//            Log.e("mytag", cusername+cname +cphno+cdetails);
//            Log.d("tag", cusername);

//            cvalue.put(COL_21, 5);
            cvalue.put(COL_22, cusername);
            cvalue.put(COL_23, cname);
            cvalue.put(COL_24,cphno);
            cvalue.put(COL_25,"");
            cvalue.put(COL_26,cdetails);

            long results= db1.insert(TABLE_NAME_COMPLAINT,null,cvalue);


            if(results==-1 )
                return false;
            else
                return true;
    }



    public boolean checkUser(String email, String password) {
        // array of columns to fetch
        String[] columns = {
                COL_11,COL_12
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COL_11+ " = ?" + " AND " + COL_12+ " = ?";
        // selection arguments
        String[] selectionArgs = {email, password};
        // query user table with conditions
        /*
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_NAME_LOGIN, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }


    public boolean insertData(String uname,String name,String password,String repassword,String phno,String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        ContentValues contentlogin = new ContentValues();
        contentValues.put(COL_1, uname);
        contentValues.put(COL_2, name);
        contentValues.put(COL_4, phno);
        contentValues.put(COL_5, address);

        long result1 = db.insert(TABLE_NAME_REGISTER, null, contentValues);

        contentlogin.put(COL_11, uname);
        contentlogin.put(COL_12, password);
        contentlogin.put(COL_13, "user");


        long result2 = db.insert(TABLE_NAME_LOGIN, null, contentlogin);
        if (result1 == -1 && result2 == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(String uname) {
        SQLiteDatabase db = this.getWritableDatabase();
       Cursor res = db.rawQuery("select * from "+TABLE_NAME_REGISTER+" where register_username = ?",new String[]{uname});
        return res;
    }

    public Cursor getAllCitizen() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME_REGISTER,null);
        return res;
    }

    public boolean insertResponse(String rno, String statusMessage, String d) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_31, rno);
        contentValues.put(COL_32, statusMessage);
        contentValues.put(COL_33, d);


        long result1 = db.insert(TABLE_NAME_RESPONSE, null, contentValues);


        if (result1 == -1)
            return false;
        else
            return true;
    }
//

//
//    public Integer deleteData (String id) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
//    }
//}
//MainActivity.java
//
//        package com.example.programmingknowledge.sqliteapp;
//
//        import android.app.AlertDialog;
//        import android.database.Cursor;
//        import android.support.v7.app.ActionBarActivity;
//        import android.os.Bundle;
//        import android.view.Menu;
//        import android.view.MenuItem;
//        import android.view.View;
//        import android.widget.Button;
//        import android.widget.EditText;
//        import android.widget.Toast;
//
//
//public class MainActivity extends ActionBarActivity {
//    DatabaseHelper myDb;
//    EditText editName,editSurname,editMarks ,editTextId;
//    Button btnAddData;
//    Button btnviewAll;
//    Button btnDelete;
//
//    Button btnviewUpdate;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        myDb = new DatabaseHelper(this);
//
//        editName = (EditText)findViewById(R.id.editText_name);
//        editSurname = (EditText)findViewById(R.id.editText_surname);
//        editMarks = (EditText)findViewById(R.id.editText_Marks);
//        editTextId = (EditText)findViewById(R.id.editText_id);
//        btnAddData = (Button)findViewById(R.id.button_add);
//        btnviewAll = (Button)findViewById(R.id.button_viewAll);
//        btnviewUpdate= (Button)findViewById(R.id.button_update);
//        btnDelete= (Button)findViewById(R.id.button_delete);
//        AddData();
//        viewAll();
//        UpdateData();
//        DeleteData();
//    }
//    public void DeleteData() {
//        btnDelete.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Integer deletedRows = myDb.deleteData(editTextId.getText().toString());
//                        if(deletedRows > 0)
//                            Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
//                        else
//                            Toast.makeText(MainActivity.this,"Data not Deleted",Toast.LENGTH_LONG).show();
//                    }
//                }
//        );
//    }
//    public void UpdateData() {
//        btnviewUpdate.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        boolean isUpdate = myDb.updateData(editTextId.getText().toString(),
//                                editName.getText().toString(),
//                                editSurname.getText().toString(),editMarks.getText().toString());
//                        if(isUpdate == true)
//                            Toast.makeText(MainActivity.this,"Data Update",Toast.LENGTH_LONG).show();
//                        else
//                            Toast.makeText(MainActivity.this,"Data not Updated",Toast.LENGTH_LONG).show();
//                    }
//                }
//        );
//    }
//    public  void AddData() {
//        btnAddData.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        boolean isInserted = myDb.insertData(editName.getText().toString(),
//                                editSurname.getText().toString(),
//                                editMarks.getText().toString() );
//                        if(isInserted == true)
//                            Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
//                        else
//                            Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
//                    }
//                }
//        );
//    }
//
//    public void viewAll() {
//        btnviewAll.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Cursor res = myDb.getAllData();
//                        if(res.getCount() == 0) {
//                            // show message
//                            showMessage("Error","Nothing found");
//                            return;
//                        }
//
//                        StringBuffer buffer = new StringBuffer();
//                        while (res.moveToNext()) {
//                            buffer.append("Id :"+ res.getString(0)+"\n");
//                            buffer.append("Name :"+ res.getString(1)+"\n");
//                            buffer.append("Surname :"+ res.getString(2)+"\n");
//                            buffer.append("Marks :"+ res.getString(3)+"\n\n");
//                        }
//
//                        // Show all data
//                        showMessage("Data",buffer.toString());
//                    }
//                }
//        );
//    }
//
//    public void showMessage(String title,String Message){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setCancelable(true);
//        builder.setTitle(title);
//        builder.setMessage(Message);
//        builder.show();
//    }
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//}
}