package com.hfad.databaseproject;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

  public static String DB_PATH = "/data/data/com.hfad.databaseproject/databases/";

    public static String DB_NAME = "dpProjectAndroid";

    private SQLiteDatabase myDataBase;

    private final Context myContext;

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseHelper instance;


    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     *
     * @param context
     */
    public DatabaseHelper(Context context) {

        super(context, DB_NAME, null, 13);
        this.myContext = context;

    }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     */
    public void createDataBase() {

        boolean dbExist = checkDataBase();

        if (dbExist) {
            //do nothing - database already exist
        } else {

            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                //throw new Error("Error copying database");

            }
        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase() {

        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        } catch (SQLiteException e) {

            //database does't exist yet.

        }

        if (checkDB != null) {

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     */
    private void copyDataBase() throws IOException {

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        System.out.println("INSIDE COPY DB ______________________________");

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() {

        //Open the database
        //String myPath = DB_PATH + DB_NAME;
        //myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);


        String dbPath = myContext.getDatabasePath(DB_NAME).getPath();
        if (myDataBase != null && myDataBase.isOpen()) {
            return;
        }
        myDataBase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);

    }

    @Override
    public synchronized void close() {

        if (myDataBase != null)
            myDataBase.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createDataBase();
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Add your public helper methods to access and get content from the database.
    // You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
    // to you to create adapters for your views.


    public String getName() {

        openDataBase();

        try {

            Cursor cursor = database.rawQuery("SELECT cname FROM customer WHERE cname = 'Lee'",null);
            if (cursor != null) {
                cursor.moveToFirst();
            }
            if (cursor != null && cursor.getCount() > 0) {
                return cursor.getString(cursor.getColumnIndex("cname"));
            }
        } catch (SQLiteException e) {
            return null;
        }
        return null;
    }

//    String DB_PATH = null;
//    private static String DB_NAME = "dpProjectAndroid";
//    private SQLiteDatabase myDataBase;
//    private final Context myContext;
//
//    public DatabaseHelper(Context context) {
//        super(context, DB_NAME, null, 13);
//        this.myContext = context;
//        this.DB_PATH = "/data/data/" + context.getPackageName() + "/" + "databases/";
//        Log.e("Path 1", DB_PATH);
//    }
//
//
//    public void createDataBase() throws IOException {
//        boolean dbExist = checkDataBase();
//        if (dbExist) {
//        } else {
//            this.getReadableDatabase();
//            try {
//                copyDataBase();
//            } catch (IOException e) {
//                throw new Error("Error copying database");
//            }
//        }
//    }
//
//    private boolean checkDataBase() {
//        SQLiteDatabase checkDB = null;
//        try {
//            String myPath = DB_PATH + DB_NAME;
//            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
//        } catch (SQLiteException e) {
//        }
//        if (checkDB != null) {
//            checkDB.close();
//        }
//        return checkDB != null ? true : false;
//    }
//
//    private void copyDataBase() throws IOException {
//        InputStream myInput = myContext.getAssets().open(DB_NAME);
//        String outFileName = DB_PATH + DB_NAME;
//        OutputStream myOutput = new FileOutputStream(outFileName);
//        byte[] buffer = new byte[10];
//        int length;
//        while ((length = myInput.read(buffer)) > 0) {
//            myOutput.write(buffer, 0, length);
//        }
//        myOutput.flush();
//        myOutput.close();
//        myInput.close();
//
//    }
//
//    public void openDataBase() throws SQLException {
//        String myPath = DB_PATH + DB_NAME;
//        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
//
//    }
//
//    @Override
//    public synchronized void close() {
//        if (myDataBase != null)
//            myDataBase.close();
//        super.close();
//    }
//
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        if (newVersion > oldVersion)
//            try {
//                copyDataBase();
//            } catch (IOException e) {
//                e.printStackTrace();
//
//            }
//    }
//
//    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
//        return myDataBase.query("customers", null, null, null, null, null, null);
//    }


}
