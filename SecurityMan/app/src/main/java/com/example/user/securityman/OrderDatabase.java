package com.example.user.securityman;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class OrderDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "myorderdatabase.db";
    private static final String TABLE_NAME = "order_details";
    private static final String NAME = "Name";
    private static final String PHONE_NUMBER = "PhoneNumber";
    private static final String DISTRICT = "District";
    private static final String SPEADDRESS = "Specific_Address";
    private static final String EVENT = "Event_Name";
    private static final String TO_DATE= "To_Date";
    private static final String FROM_DATE= "From_Date";
    private static final String AMOUNT= "Worker_Amount";
    private static final String WORKER = "Worker";
    private static final int VERSION_NUMBER = 4;
    private static final String SELECT_ALL = "SELECT * FROM " + TABLE_NAME;

    private Context context;
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + NAME + " text NOT NULL," + PHONE_NUMBER + " text NOT NULL,"+DISTRICT + " TEXT NOT NULL," +SPEADDRESS+" TEXT NOT NULL ,"+EVENT+" TEXT NOT NULL,"+TO_DATE+" TEXT NOT NULL,"+FROM_DATE+" TEXT NOT NULL,"+AMOUNT+" TEXT NOT NULL,"+WORKER+" TEXT NOT NULL);";



    public OrderDatabase(Context context) {
        super(context,DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL(CREATE_TABLE);
            Toast.makeText(context, "OnCreate is called", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(context, "Exception : " + e, Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public long orderinsertdata(String Name ,String PhoneNumber, String District, String SpecificAddress, String Worker , String Event,String ToDate,String FromDate,String Amount) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, Name);
        contentValues.put(PHONE_NUMBER, PhoneNumber);
        contentValues.put(SPEADDRESS, SpecificAddress);
        contentValues.put(DISTRICT, District);
        contentValues.put(WORKER, Worker);
        contentValues.put(TO_DATE, ToDate);
        contentValues.put(FROM_DATE, FromDate);
        contentValues.put(AMOUNT, Amount);
        contentValues.put(EVENT,Event);
        long odrid= sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        return odrid;
    }

    public Cursor displayorderData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL, null);
        return cursor;
    }

}
