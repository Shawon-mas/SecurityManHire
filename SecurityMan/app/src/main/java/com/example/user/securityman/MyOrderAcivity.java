package com.example.user.securityman;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MyOrderAcivity extends AppCompatActivity {
    OrderDatabase orderDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order_acivity);
        this.setTitle("My Order");

       orderDatabase = new OrderDatabase(this);
        SQLiteDatabase sqLiteDatabase=orderDatabase.getWritableDatabase();
        Cursor cursor= orderDatabase.displayorderData();

        if(cursor.getCount()==0)
        {
            showdata("Error","No data Found");
        }
        StringBuffer stringBuffer = new StringBuffer();
        while (cursor.moveToNext())
        {
            stringBuffer.append("Name : "+cursor.getString(0)+"\n");
            stringBuffer.append("Phone Number : "+cursor.getString(1)+"\n");
            stringBuffer.append("District: "+cursor.getString(2)+"\n");
            stringBuffer.append("Specific Address : "+cursor.getString(3)+"\n");
            stringBuffer.append("Event Name: "+cursor.getString(4)+"\n");
            stringBuffer.append("To Date: "+cursor.getString(5)+"\n");
            stringBuffer.append("From Date: "+cursor.getString(6)+"\n");
            stringBuffer.append("Worker Amount: "+cursor.getString(7)+"\n");
            stringBuffer.append("User Name : "+cursor.getString(8)+"\n");

        }
        showdata("My Order",stringBuffer.toString());
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

