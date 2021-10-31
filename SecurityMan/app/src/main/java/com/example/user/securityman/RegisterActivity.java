package com.example.user.securityman;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    RegisterDatabase registerDatabase;

    EditText fname,lname,address,phone_number,email,user_name,password;
    Button regbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerDatabase = new RegisterDatabase(this);
        SQLiteDatabase sqLiteDatabase= registerDatabase.getWritableDatabase();

        this.setTitle("Registration");
        fname=findViewById(R.id.fn);
        lname=findViewById(R.id.ln);
        address=findViewById(R.id.address);
        phone_number=findViewById(R.id.pn);
        email=findViewById(R.id.email);
        user_name=findViewById(R.id.us);
        password=findViewById(R.id.pass);


        regbutton=findViewById(R.id.regbutton);
        regbutton.setOnClickListener(new View.OnClickListener() {
                                         @Override

                                         public void onClick(View v) {

                                             String FirstName=fname.getText().toString();
                                             String LasttName=lname.getText().toString();
                                             String Address=address.getText().toString();
                                             String PhoneNumber=phone_number.getText().toString();
                                             String Email=email.getText().toString();
                                             String UserName=user_name.getText().toString();
                                             String Password=password.getText().toString();

                                             userRegister();

                                             if (fname.getText().length() == 0 || lname.getText().length() == 0 || address.getText().length() == 0 ||
                                                     phone_number.getText().length() == 0 || email.getText().length() == 0 || user_name.getText().length() == 0
                                                     || password.getText().length() == 0
                                                     )
                                             {
                                                 Toast.makeText(RegisterActivity.this, "Enter your Full information", Toast.LENGTH_SHORT).show();
                                             }


                                             else

                                             {
                                                long rowid=  registerDatabase.insertdata(FirstName,LasttName,Address,PhoneNumber,Email,UserName,Password);
                                                 Intent registerIntent = new Intent(RegisterActivity.this, MainActivity.class);
                                                 startActivity(registerIntent);

                                                 Toast.makeText(RegisterActivity.this, "You have been Registered", Toast.LENGTH_SHORT).show();
                                             }
                                         }

                                         private void userRegister() {
                                         }
                                     }
        );
    }
}
