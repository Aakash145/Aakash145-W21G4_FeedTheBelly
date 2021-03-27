package com.example.foodrescue;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Ngo_profile extends AppCompatActivity {
    DatabaseHelper myDb;
    DatabaseReference databaseReference;
    TextView txtView;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_profile);
        myDb = new DatabaseHelper(this);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        txtView = findViewById(R.id.txtViewNGOProfile);
        Button btnBackToDash = findViewById(R.id.btnbacktodashNGO);
        ActionBar myBar = getSupportActionBar();
        myBar.setTitle("Organization Profile");
        Intent i = getIntent();
        String email = i.getStringExtra("Email");

        String Str = "NGO Details\n\n";
        txtView.setText(Str);

        Cursor res = myDb.getData1(email);
        if(res.getCount() == 0) {
            // show message
            // showMessage("Error","Nothing found");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("Email :"+ res.getString(0)+"\n");
            buffer.append("Name :"+ res.getString(1)+"\n");
            buffer.append("Phone :"+ res.getString(2)+"\n");
            buffer.append("NGO ID :"+ res.getString(3)+"\n");
            buffer.append("NGO Description :"+ res.getString(4)+"\n");
            buffer.append("Address :"+ res.getString(5)+", " +res.getString(6)+ ", " +res.getString(7)+
                    ", " +res.getString(8)+ " " +res.getString(9)+"\n\n" );
            // buffer.append("City :"+ res.getString(5)+"\n");
            // buffer.append("State :"+ res.getString(6)+"\n");
            // buffer.append("Country :"+ res.getString(7)+"\n");
            // buffer.append("Postal :"+ res.getString(8)+"\n\n");
        }

        // Show all data
        // showMessage("Data",buffer.toString());
        //String finalText = "\nID: " +  + "\nName: "+ name + "\nEmail: " + email+ "\nPhone: " + phone+ "\nAddress: " + Add1+ ", " + City + ", " + State + ", " + Country + " " + Postal;
        txtView.append(buffer.toString());
        btnBackToDash.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Ngo_profile.this, NGO_Dashboard.class);
                startActivity(intent);
            }
        });

    }
    }
