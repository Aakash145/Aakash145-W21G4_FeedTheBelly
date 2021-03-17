package com.example.foodrescue;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Ngo_profile extends AppCompatActivity {
    DatabaseReference databaseReference;
    TextView txtView;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_profile);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        txtView = findViewById(R.id.txtViewNGOProfile);
        Button btnBackToDash = findViewById(R.id.btnbacktodashNGO);
        ActionBar myBar = getSupportActionBar();
        myBar.setTitle("Organization Profile");

        Intent i = getIntent();

        String Str = "Organization Details\n\n";
        txtView.setText(Str);
        // loadUserInformation();
        Detail details = (Detail) i.getSerializableExtra("Details");
        User user = (User)i.getSerializableExtra("User");
        String ID = details.getID();
        String Add1 = details.getAdd1();
        String City = details.getCity();
        String State = details.getState();
        String Country = details.getCountry();
        String Postal = details.getPostal();
        String name = user.getName();
        String email =  user.getEmail();
        String phone = user.getPhone();
        String finalText = "\nID: " + ID + "\nName: "+ name + "\nEmail: " + email+ "\nPhone: " + phone+ "\nAddress: " + Add1+ ", " + City + ", " + State + ", " + Country + " " + Postal;
        txtView.append(finalText);

        btnBackToDash.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Ngo_profile.this, NGO_Dashboard.class);
                startActivity(intent);
            }
        });

    }
    }
