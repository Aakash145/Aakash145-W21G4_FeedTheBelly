package com.example.foodrescue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
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
    DatabaseHelper myDb;
    DatabaseReference databaseReference;
    TextView txtView;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_profile);
        myDb = new DatabaseHelper(this);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser mFirebaseUser=mAuth.getCurrentUser();
        String email=mFirebaseUser.getEmail();
        txtView = findViewById(R.id.txtViewProfile);
        Button btnBackToDash = findViewById(R.id.btnbacktodash);
        ActionBar myBar = getSupportActionBar();
        myBar.setTitle("Restaurant Profile");

        Intent i = getIntent();
      //  String email = i.getStringExtra("Email");

        String Str = "Restaurant Details\n\n";
        txtView.setText(Str);
       // loadUserInformation();
      //  Detail details= (Detail) i.getSerializableExtra("Details");
        Cursor res = myDb.getData(email);
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
            buffer.append("Restaurant ID :"+ res.getString(3)+"\n");
            buffer.append("Address :"+ res.getString(4)+", " +res.getString(5)+ ", " +res.getString(6)+
                   ", " +res.getString(7)+ " " +res.getString(8)+"\n\n" );
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

                Intent intent = new Intent(restaurant_profile.this, Restaurant_Dashboard.class);
                intent.putExtra("Email", email);
                startActivity(intent);
            }
        });

    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
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

