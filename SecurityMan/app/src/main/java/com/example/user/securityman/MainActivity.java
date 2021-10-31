package com.example.user.securityman;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {
    SQLiteOpenHelper sqLiteOpenHelper;
    SQLiteDatabase db;
    RegisterDatabase registerDatabase;

    EditText textUsername, textpassword;
    Button buttonLogin;
    TextView vRegister;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Security Man Hire");

       sqLiteOpenHelper = new RegisterDatabase(this);
       db=sqLiteOpenHelper.getReadableDatabase();

        textUsername = findViewById(R.id.etusername);
        textpassword = findViewById(R.id.etpassword);
        buttonLogin = findViewById(R.id.blogin);
        vRegister = findViewById(R.id.tvregister);

        vRegister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        buttonLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = textUsername.getText().toString().trim();
                String password = textpassword.getText().toString().trim();


                cursor=db.rawQuery(" SELECT * FROM " + RegisterDatabase.TABLE_NAME  + " WHERE " + RegisterDatabase.USER_NAME + "=? AND " + RegisterDatabase.PASSWORD + "=?", new String[]{username,password});
               if(cursor!=null) {
                   if (cursor.getCount() > 0)
                   {
                       cursor.moveToNext();
                       Intent loginIntent = new Intent(MainActivity.this, LoginAcitivity.class);
                       startActivity(loginIntent);
                   }


                   else
                       {
                       Toast.makeText(getApplicationContext(),"username and password didn't match",Toast.LENGTH_LONG).show();
                   }
               }
            }


        });




    }




}