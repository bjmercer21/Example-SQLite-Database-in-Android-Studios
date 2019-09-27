package com.hfad.databaseproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.sql.Date;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "databaseProject";
    private static final int DB_VERSION = 4;

    public DBHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       createDefaultTables(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS CUSTOMER");
        db.execSQL("DROP TABLE IF EXISTS EMPLOYEE");
        db.execSQL("DROP TABLE IF EXISTS ORDERS");
        db.execSQL("DROP TABLE IF EXISTS ODETAILS");
        db.execSQL("DROP TABLE IF EXISTS ZIPCODES");
        db.execSQL("DROP TABLE IF EXISTS PARTS ");
        db.execSQL("DROP TABLE IF EXISTS PRIVILAGE");
        createDefaultTables(db);
    }

    public Cursor veiwData(String TABLE_NAME){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME;
        Cursor c = db.rawQuery(query,null);

        return c;
    }

    private static void createDefaultTables(SQLiteDatabase db){
        db.execSQL("CREATE TABLE CUSTOMER( "
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "CNO TEXT, "
                + "CNAME TEXT, "
                + "ZIP TEXT, "
                + "PHONE TEXT);");

        db.execSQL("CREATE TABLE EMPLOYEE( "
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "EID TEXT, "
                + "ENAME TEXT, "
                + "UNAME TEXT, "
                + "PASSWORD TEXT, "
                + "SALARY TEXT);");

        db.execSQL("CREATE TABLE ODETAILS( "
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "ONO TEXT, "
                + "PNO TEXT, "
                + "QTY TEXT, "
                + "DATEORDERED TEXT);");

        db.execSQL("CREATE TABLE ORDERS( "
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "ONO TEXT, "
                + "PNO TEXT, "
                + "EID TEXT, "
                + "DATEARRIVED TEXT, "
                + "QTY INTEGER);");

        db.execSQL("CREATE TABLE PARTS( "
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "PNO TEXT, "
                + "PNAME TEXT, "
                + "QOH INTEGER, "
                + "PRICE DOUBLE);");

        db.execSQL("CREATE TABLE PRIVILAGE( "
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "EID TEXT, "
                + "ADMIN INTEGER, "
                + "USER INTEGER);");

        db.execSQL("CREATE TABLE ZIPCODES( "
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "ZIP INTEGER, "
                + "STATE TEXT, "
                + "CITY TEXT);");





        insertCustomer(db,"5502945","Lee","32808","475-152-2554");
        insertCustomer(db,"5504579","Yasin","33187","887-878-8978");
        insertCustomer(db,"5503110","Kira","32808","579-336-9965");
        insertCustomer(db,"5505439","Alexander","32808","411-411-7447");
        insertCustomer(db,"5501702","Ross","33629","522-458-0202");
        insertCustomer(db,"5506491","Albert","33124","704-624-2468");
        insertCustomer(db,"5504488","Owain","33629","682-668-7605");
        insertCustomer(db,"5509441","Travis","33124","554-879-1284");
        insertCustomer(db,"5507309","Taylor","33629","453-335-7666");
        insertCustomer(db,"5507473","Harry","32808","453-967-7525");

        insertEmployee(db,"7808546","Garcia","LGarcia12","startStop","32000");
        insertEmployee(db,"7806054","Miller","YMiller34","Jameie_sonon","55000");
        insertEmployee(db,"7807202","Wilson","KWilson63","niKEy@Rox","38000");
        insertEmployee(db,"7804917","Williams","AWilliams78","ALLdAy55","40000");
        insertEmployee(db,"7809616","Jones","JJones87","breAKfAsT","35000");
        insertEmployee(db,"7807735","Smith","OSmith56","mikeyMikey","35000");
        insertEmployee(db,"7801495","Davis","TDavis14","oliVERKeeyz","32000");
        insertEmployee(db,"7808693","Johnson","TJohnson55","_dangerous","42000");
        insertEmployee(db,"7801898","Rodriquez","HRodriquez98","XxcabanaxX","58000");
        insertEmployee(db,"7801616","Brown","JBrown46","ISaidNo_bu","42000");
        insertEmployee(db,"0000000", "admin", "admin", "password","000000");

        insertODetails(db,"72914","6104","3","20180101");
        insertODetails(db,"72457","6186","4","20180115");
        insertODetails(db,"72219","6111","1","20180202");
        insertODetails(db,"72423","6154","3","20180701");
        insertODetails(db,"72423","6039","10","20181010");
        insertODetails(db,"72629","5973","20","20181101");
        insertODetails(db,"72988","5973","30","20181101");

        insertOrders(db,"72914","6104","7806054","20180123",1);
        insertOrders(db,"72457","6186","7804917","20180203",2);
        insertOrders(db,"72219","6111","7806054","20180204",2);
        insertOrders(db,"72423","6154","7808546","20180718",3);
        insertOrders(db,"72312","6039","7808546","20180720",2);
        insertOrders(db,"72629","5973","7801616","20181113",1);
        insertOrders(db,"72988","5973","7801616","20181117",1);

        insertParts(db,"6112","Drill",5,30.99);
        insertParts(db,"6098","Hammer",5,9.99);
        insertParts(db,"5925","Nail Sm",10,0.5);
        insertParts(db,"6104","Nail Lg",11,0.75);
        insertParts(db,"5973","Screw 25mm",25,0.75);
        insertParts(db,"5946","Wrench",7,10.99);
        insertParts(db,"6105","Motor 305",2,2500.5);
        insertParts(db,"6111","Motor 350",1,3500.5);
        insertParts(db,"6186","Nitros Oxide",1,150.99);
        insertParts(db,"6074","Welding Tor",13,350.99);
        insertParts(db,"6143","Copper Rod",13,0.5);
        insertParts(db,"6064","Screwdriver",12,5.99);
        insertParts(db,"6154","Headlight",7,12.99);
        insertParts(db,"6078","Flashlight",8,15.99);
        insertParts(db,"6039","Hydro. Lift",5,5000.75);


        insertPrivilage(db,"7808546",0,1);
        insertPrivilage(db,"7806054",1,1);
        insertPrivilage(db,"7807202",0,1);
        insertPrivilage(db,"7804917",0,1);
        insertPrivilage(db,"7809616",0,1);
        insertPrivilage(db,"7807735",0,1);
        insertPrivilage(db,"7801495",0,1);
        insertPrivilage(db,"7808693",0,1);
        insertPrivilage(db,"7801898",1,1);
        insertPrivilage(db,"7801616",0,1);

        inserZipcode(db,33124,"Flordia","Miami");
        inserZipcode(db,33187,"Flordia","Miami");
        inserZipcode(db,32808,"Flordia","Orlando");
        inserZipcode(db,33629,"Flordia","Tampa Bay");
    }

    public static void insertCustomer(SQLiteDatabase db, String cno, String cname,
                                       String zip, String phone){
        ContentValues customerValues = new ContentValues();
        customerValues.put("CNO", cno);
        customerValues.put("CNAME", cname);
        customerValues.put("ZIP", zip);
        customerValues.put("PHONE", phone);
        db.insert("CUSTOMER",null,customerValues);
    }

    public static void insertEmployee(SQLiteDatabase db, String eid, String ename,
                                       String uname,String password, String salary){
        ContentValues employeeValues = new ContentValues();
        employeeValues.put("EID", eid);
        employeeValues.put("ENAME", ename);
        employeeValues.put("UNAME", uname);
        employeeValues.put("PASSWORD", password);
        employeeValues.put("SALARY", salary);
        db.insert("EMPLOYEE",null,employeeValues);
    }
    private static void insertODetails(SQLiteDatabase db, String ono, String pno,
                                       String qty, String dateOrdered){
        ContentValues detailValues = new ContentValues();
        detailValues.put("ONO", ono);
        detailValues.put("PNO", pno);
        detailValues.put("QTY", qty);
        detailValues.put("DATEORDERED", dateOrdered);
        db.insert("ODETAILS",null,detailValues);
    }
    public static void insertOrders(SQLiteDatabase db, String ono, String pno,
                                     String eid, String dateArrived, Integer qty){
        ContentValues orderValues = new ContentValues();
        orderValues.put("ONO", ono);
        orderValues.put("PNO", pno);
        orderValues.put("EID", eid);
        orderValues.put("DATEARRIVED", dateArrived);
        orderValues.put("QTY", qty);
        db.insert("ORDERS",null,orderValues);
    }
    public static void insertParts(SQLiteDatabase db, String pno, String pname,
                                       int qoh, double price){
        ContentValues partsValues = new ContentValues();
        partsValues.put("PNO", pno);
        partsValues.put("PNAME", pname);
        partsValues.put("QOH", qoh);
        partsValues.put("PRICE", price);
        db.insert("PARTS",null, partsValues);
    }
    private static void insertPrivilage(SQLiteDatabase db, String eid, Integer admin,
                                       Integer user){
        ContentValues privilageValues = new ContentValues();
        privilageValues.put("EID", eid);
        privilageValues.put("ADMIN", admin);
        privilageValues.put("USER", user);

        db.insert("PRIVILAGE",null,privilageValues);
    }
    public static void inserZipcode(SQLiteDatabase db, int zip, String state,
                                       String city){
        ContentValues zipValues = new ContentValues();
        zipValues.put("ZIP", zip);
        zipValues.put("STATE", state);
        zipValues.put("CITY", city);

        db.insert("ZIPCODES",null,zipValues);
    }



}
