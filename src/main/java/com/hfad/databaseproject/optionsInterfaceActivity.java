package com.hfad.databaseproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class optionsInterfaceActivity extends AppCompatActivity {

    private ListView listView;
    private SQLiteOpenHelper mDBH;
    public SQLiteDatabase db;
    public ArrayList<String> listItem;
    public ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_interface);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        listView = (ListView) findViewById(R.id.firstLineup);
        listView.setAdapter(adapter);
        listItem = new ArrayList<>();

        mDBH = new DBHelper(this);
        db = mDBH.getReadableDatabase();

    }


    public void onClickLogout(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void showEmployees(View view) {
        adapter.clear();
        int i;
        String query = "SELECT * FROM EMPLOYEE";
        Cursor c = db.rawQuery(query, null);
        String array[] = new String[c.getCount()];
        i=0;
        c.moveToFirst();
        while(!c.isAfterLast()){
            array[i] = c.getString(1)+" \n "+c.getString(2);
            i++;
            c.moveToNext();
        }
        adapter.addAll(array);
    }

    public void showCustomers(View view) {
        adapter.clear();
        int i;
        String query = "SELECT * FROM CUSTOMER";
        Cursor c = db.rawQuery(query, null);
        String array[] = new String[c.getCount()];
        i=0;
        c.moveToFirst();
        while(!c.isAfterLast()){
            array[i] = c.getString(1)+" \n "+c.getString(2);
            i++;
            c.moveToNext();
        }
        adapter.addAll(array);
    }

    public void showOrders(View view) {
        adapter.clear();
        int i;
        String query = "SELECT * FROM ORDERS";
        Cursor c = db.rawQuery(query, null);
        String array[] = new String[c.getCount()];
        i=0;
        c.moveToFirst();
        while(!c.isAfterLast()){
            array[i] = "Order Number: "+c.getString(1)+" \nPart Number: "+c.getString(2)+" \nDate Arrived: "+c.getString(4);
            i++;
            c.moveToNext();
        }
        adapter.addAll(array);
    }

    public void showODetails(View view) {
        adapter.clear();
        int i;
        String query = "SELECT * FROM ODETAILS";
        Cursor c = db.rawQuery(query, null);
        String array[] = new String[c.getCount()];
        i=0;
        c.moveToFirst();
        while(!c.isAfterLast()){
            array[i] = "Order Number: "+c.getString(1)+" \nPart Number: "+c.getString(2)+" \nQTY: "+c.getString(3)+" \nDate Ordered: "+c.getString(4);
            i++;
            c.moveToNext();
        }
        adapter.addAll(array);
    }

    public void showParts(View view) {
        adapter.clear();
        int i;
        String query = "SELECT * FROM PARTS";
        Cursor c = db.rawQuery(query, null);
        String array[] = new String[c.getCount()];
        i=0;
        c.moveToFirst();
        while(!c.isAfterLast()){
            array[i] = "Part Number: "+c.getString(1)+"\n"+c.getString(2)+" \n QOH: "+c.getString(3)+" \n Price: "+c.getString(4);
            i++;
            c.moveToNext();
        }
        adapter.addAll(array);
    }

    public void addNewPerson(View view) {
        Intent intent = new Intent(this, addPerson.class);
        startActivity(intent);
    }
}
