package com.example.user.securityman;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MyprofileAcitivity extends AppCompatActivity {
    RegisterDatabase registerDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile_acitivity);
        this.setTitle("My Profile");

        registerDatabase = new RegisterDatabase(this);
        SQLiteDatabase sqLiteDatabase=registerDatabase.getWritableDatabase();
        Cursor cursor= registerDatabase.displayAllData();

        if(cursor.getCount()==0)
        {
            showdata("Error","No data Found");
        }
        StringBuffer stringBuffer = new StringBuffer();
        while (cursor.moveToNext())
        {
            stringBuffer.append("First Name : "+cursor.getString(0)+"\n");
            stringBuffer.append("Last Name : "+cursor.getString(1)+"\n");
            stringBuffer.append("Address : "+cursor.getString(2)+"\n");
            stringBuffer.append("Phone Number : "+cursor.getString(3)+"\n");
            stringBuffer.append("Email : "+cursor.getString(4)+"\n");
            stringBuffer.append("User Name : "+cursor.getString(5)+"\n\n\n");

        }
        showdata("My Profile",stringBuffer.toString());
    }

    public  void showdata(String title,String message)
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message );
        builder.setCancelable(true);
        builder.show();

    }
}









