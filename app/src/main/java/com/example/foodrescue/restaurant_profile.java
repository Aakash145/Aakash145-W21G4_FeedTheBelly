package com.example.foodrescue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class restaurant_profile extends AppCompatActivity {
    DatabaseReference databaseReference;
    TextView txtView;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_profile);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        txtView = findViewById(R.id.txtViewProfile);
        Button btnBackToDash = findViewById(R.id.btnbacktodash);
        ActionBar myBar = getSupportActionBar();
        myBar.setTitle("Restaurant Profile");

        Intent i = getIntent();

        String Str = "Restaurant Details\n\n";
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

                Intent intent = new Intent(restaurant_profile.this, Restaurant_Dashboard.class);
                startActivity(intent);
            }
        });

    }

    private void loadUserInformation() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user.getDisplayName() != null) {
            txtView.append("\nName: " + user.getDisplayName());
        }

        if (user.isEmailVerified()) {
            txtView.append("\nEmail: " + user.getEmail());
        }
        if (user.getPhoneNumber() != null) {
            txtView.append("\nPhone: " + user.getPhoneNumber());
        }
    }
}

