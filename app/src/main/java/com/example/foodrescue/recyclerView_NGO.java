package com.example.foodrescue;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class recyclerView_NGO extends AppCompatActivity {
    SQLiteDatabase FeedTheBelly;
    RecyclerView rvDonations;
    ArrayList<String> weight;
    ArrayList<String> dates;
    ArrayList<String> restName;
    ArrayList<String> address;
    ArrayList<String> emails;
    DatabaseHelper myDb;
    Button details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view__n_g_o);
        myDb = new DatabaseHelper(this);
        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setTitle("Donations List");

        rvDonations = findViewById(R.id.rvRestaurants);
        details = findViewById(R.id.btn_Details);
        rvDonations.setLayoutManager(new LinearLayoutManager(this));
        weight = new ArrayList<>();
        dates = new ArrayList<>();
        address = new ArrayList<>();
        restName=new ArrayList<>();
        String email = getIntent().getStringExtra("EMAIL");
        String address2 = getIntent().getStringExtra("ADDRESS");
        Cursor cursor = myDb.readDonations(email);
        while (cursor.moveToNext()) {

            try {
                dates.add(cursor.getString(0));
                weight.add(cursor.getString(1));
                restName.add(cursor.getString(2));

                Make_Donations_Adapter myAdapter = new Make_Donations_Adapter(recyclerView_NGO.this, dates, weight,restName, address2, email);
                rvDonations.setAdapter(myAdapter);
            } catch (Exception e) {
                Log.d("Error", e.getMessage());
            }
        }


    }
}