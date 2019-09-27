package com.hfad.databaseproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    public TextView textView;
    public EditText usernameBox;
    public EditText passwordBox;
    private String testWord;
    public SQLiteOpenHelper myDbHelper;
    public Cursor c = null;
    public SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myDbHelper = new DBHelper(this);

        usernameBox = (EditText) findViewById(R.id.usernameBox);
        passwordBox = (EditText) findViewById(R.id.passwordBox);
        db = myDbHelper.getReadableDatabase();


    }




  public void onSubmitClick(View view) {

        String uname = usernameBox.getText().toString();
        String password = passwordBox.getText().toString();
        Intent intent = new Intent(this, optionsInterfaceActivity.class);

        if(Login(uname,password)){
            startActivity(intent);
        }else{
            Toast.makeText(this,"Invalid username/password",Toast.LENGTH_SHORT).show();
        }


    }


    public boolean Login(String username, String password) throws SQLException{

        Cursor mCursor = db.rawQuery("SELECT * FROM EMPLOYEE WHERE UNAME=? AND PASSWORD=?", new String[]{username,password});
        if (mCursor != null) {
            if(mCursor.getCount() > 0)
            {
                return true;
            }
        }
        return false;
    }

}

