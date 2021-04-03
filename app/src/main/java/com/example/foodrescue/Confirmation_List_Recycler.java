package com.example.foodrescue;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class Confirmation_List_Recycler extends AppCompatActivity {

    SQLiteDatabase FeedTheBelly;
    RecyclerView rvItems;
    private FirebaseAuth mFirebaseAuth;
    ArrayList<String> restName;
    ArrayList<String> weight;
    ArrayList<String> plates;
    ArrayList<String> donation_date;
    ArrayList<String> confirmation_date;
    ArrayList<String> foodExpiry;
    DatabaseHelper myDb;
    Button nextClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation__list__recycler);

        myDb = new DatabaseHelper(this);
        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setTitle("Donations List");

        mFirebaseAuth= FirebaseAuth.getInstance();
        FirebaseUser mFirebaseUser=mFirebaseAuth.getCurrentUser();
        String ngo_email=mFirebaseUser.getEmail();
        rvItems = findViewById(R.id.rvConfirmedList);
        rvItems.setLayoutManager(new LinearLayoutManager(this));
        restName = new ArrayList<>();
        weight = new ArrayList<>();
        plates = new ArrayList<>();
        donation_date = new ArrayList<>();
        confirmation_date = new ArrayList<>();
        // String name=getIntent().getStringExtra("NAME");
        String exp = getIntent().getStringExtra("DATE");
        Cursor cursor = myDb.readConfirmedDonations(ngo_email);
        while (cursor.moveToNext()) {

            try {
                restName.add(cursor.getString(0));
                confirmation_date.add(cursor.getString(1));
                donation_date.add(cursor.getString(2));
                weight.add(cursor.getString(3));
                plates.add(cursor.getString(4));
                //addresses.add(address2);


            } catch (Exception e) {
                Log.d("Error", e.getMessage());
            }
        }
        Confirmation_List_Adapter myAdapter = new Confirmation_List_Adapter(Confirmation_List_Recycler.this, donation_date, confirmation_date, weight,restName,plates);
        rvItems.setAdapter(myAdapter);
    }
}