package com.hfad.databaseproject;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class addPerson extends AppCompatActivity {

    TextView choice;
    EditText name;
    EditText uname;
    EditText password;
    EditText salary;
    EditText zip;
    EditText phone;
    SQLiteDatabase db;
    Cursor c;
    SQLiteOpenHelper DBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        choice = (TextView) findViewById(R.id.tableChosen);
        name = (EditText) findViewById(R.id.newName);
        uname = (EditText) findViewById(R.id.newUserName);
        password = (EditText) findViewById(R.id.newPassword);
        salary = (EditText) findViewById(R.id.newSalary);
        zip = (EditText) findViewById(R.id.newZip);
        phone = (EditText) findViewById(R.id.newPhone);

        DBHelper = new DBHelper(this);
        db = DBHelper.getWritableDatabase();
        c = null;

    }

    public void makeCustomer(View view) {
        choice.setText("CUSTOMER");
        uname.setVisibility(View.INVISIBLE);
        password.setVisibility(View.INVISIBLE);
        salary.setVisibility(View.INVISIBLE);
        zip.setVisibility(View.VISIBLE);
        phone.setVisibility(View.VISIBLE);
    }

    public void makeEmployee(View view) {
        choice.setText("EMPLOYEE");
        uname.setVisibility(View.VISIBLE);
        password.setVisibility(View.VISIBLE);
        salary.setVisibility(View.VISIBLE);
        zip.setVisibility(View.INVISIBLE);
        phone.setVisibility(View.INVISIBLE);
    }

    public void makeNewEntry(View view) {
        if(choice==null){
            Toast.makeText(this,"Choose Person Type",Toast.LENGTH_SHORT).show();
        }else if(choice.getText() == "CUSTOMER"){
            ContentValues customerValues = new ContentValues();
            String cno = "440";
            customerValues.put("CNO", cno);
            customerValues.put("CNAME", name.toString());
            customerValues.put("ZIP", zip.toString());
            customerValues.put("PHONE",phone.toString());
            db.insert("CUSTOMER",null,customerValues);
        }else if(choice.getText() == "EMPLOYEE"){

            String eid = "850";
            ContentValues employeeValues = new ContentValues();
            employeeValues.put("EID", eid);
            employeeValues.put("ENAME", name.toString());
            employeeValues.put("UNAME", uname.toString());
            employeeValues.put("PASSWORD", password.toString());
            employeeValues.put("SALARY", salary.toString());
            db.insert("EMPLOYEE",null,employeeValues);
        }
    }
}
