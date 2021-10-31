package com.example.user.securityman;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class RegisterDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "myregisterdatabase.db";
    public static final String TABLE_NAME = "register_details";
    private static final String FIRST_NAME = "FirstName";
    private static final String LAST_NAME = "LastName";
    private static final String ADDRESS = "Address";
    private static final String PHONE_NUMBER = "PhoneNumber";
    private static final String EMAIL = "Email";
    public static final String USER_NAME = "UserName";
    public static final String PASSWORD = "Password";
    private static final int VERSION_NUMBER = 33;
    private static final String SELECT_ALL = "SELECT * FROM " + TABLE_NAME;

    private Context context;


    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + FIRST_NAME + " text NOT NULL," + LAST_NAME + " text NOT NULL," + ADDRESS + " TEXT NOT NULL," + PHONE_NUMBER + " text NOT NULL," + EMAIL + " TEXT NOT NULL," + USER_NAME + " TEXT NOT NULL," + PASSWORD + " TEXT NOT NULL);";
    private static String DROP_TABLE = "DROP TABLE IF EXISTS" + TABLE_NAME;

    public RegisterDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(CREATE_TABLE);
            Toast.makeText(context, "OnCreate is called", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(context, "Exception : " + e, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        try {
            sqLiteDatabase.execSQL(CREATE_TABLE);
            Toast.makeText(context, "OnUpgrade is called", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(context, "Exception : " + e, Toast.LENGTH_LONG).show();
        }

    }

    public long insertdata(String FirstName, String LastName, String Address, String PhoneNumber, String Email, String UserName, String Password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIRST_NAME, FirstName);
        contentValues.put(LAST_NAME, LastName);
        contentValues.put(ADDRESS, Address);
        contentValues.put(PHONE_NUMBER, PhoneNumber);
        contentValues.put(EMAIL, Email);
        contentValues.put(USER_NAME, UserName);
        contentValues.put(PASSWORD, Password);
        long rowid = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        return rowid;
    }

    public Cursor displayAllData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL, null);
        return cursor;
    }




}
