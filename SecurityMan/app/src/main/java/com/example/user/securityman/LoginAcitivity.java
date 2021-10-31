package com.example.user.securityman;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class LoginAcitivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    OrderDatabase orderDatabase;
    SQLiteDatabase sqLiteDatabase;
    EditText name,address,phoneNumber,event,worker,district,todate,fromdate,hitman;
    Button cnfrmbutton;
    RadioGroup radioGroup;
    RadioButton workerButton;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acitivity);
        this.setTitle("Order");

        orderDatabase = new OrderDatabase(this);
        SQLiteDatabase sqLiteDatabase= orderDatabase.getWritableDatabase();
        drawerLayout=findViewById(R.id.drawerID);

        NavigationView navigationView = findViewById(R.id.navigationID);
        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = findViewById(R.id.nameID);
        address=findViewById(R.id.addressID);
        phoneNumber=findViewById(R.id.phonenumberID);
        district=findViewById(R.id.disupID);
        event=findViewById(R.id.eventID);
        radioGroup=findViewById(R.id.radio_group);
        cnfrmbutton=findViewById(R.id.confrombutton);
        todate=findViewById(R.id.todatetID);
        fromdate=findViewById(R.id.fromdatetID);
        hitman=findViewById(R.id.hitmanID);

        cnfrmbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedID = radioGroup.getCheckedRadioButtonId();
                workerButton=findViewById(selectedID);
                String Name=name.getText().toString();
                String PhoneNumber=phoneNumber.getText().toString();
                String District=district.getText().toString();
                String SpecificAddress=address.getText().toString();
                String Event =event.getText().toString();
                String Todate=todate.getText().toString();
                String FromDate =fromdate.getText().toString();
                String Amount=hitman.getText().toString();
                String Worker = workerButton.getText().toString();


                if ( name.getText().length()==0 || phoneNumber.getText().length()==0|| district.getText().length()==0
                        || event.getText().length()==0 || todate.getText().length()==0 || fromdate.getText().length()==0 ||
                        hitman.getText().length()==0 || radioGroup.getCheckedRadioButtonId()==-1
                )
                {
                    Toast.makeText(LoginAcitivity.this, "Enter your Full information", Toast.LENGTH_SHORT).show();
                }
                else

                {
                  long ordid = orderDatabase.orderinsertdata(Name,PhoneNumber,District,SpecificAddress,Worker,Event,Todate,FromDate,Amount);

                    Toast.makeText(LoginAcitivity.this, "Order is Successful ", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item))
        {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


        if(menuItem.getItemId()==R.id.myProfileId)
        {
            Intent myprofileacitivityIntent = new Intent(LoginAcitivity.this,MyprofileAcitivity.class);
            startActivity(myprofileacitivityIntent);
        }

        else if (menuItem.getItemId()==R.id.myOrderId)
        {
            Intent myorderacitivityIntent = new Intent(LoginAcitivity.this,MyOrderAcivity.class);
            startActivity( myorderacitivityIntent);
        }

         else if (menuItem.getItemId()==R.id.logoutId)
         {
             Intent mainacitivityIntent = new Intent(LoginAcitivity.this,MainActivity.class);
             startActivity(mainacitivityIntent);
         }
        else if (menuItem.getItemId()==R.id.aboutusId)
        {
            Intent aboutusacitivity = new Intent(LoginAcitivity.this,Aboutus.class);
            startActivity(aboutusacitivity);
        }



        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}
