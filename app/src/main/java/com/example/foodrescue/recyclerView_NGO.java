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
    ArrayList<String> foodCategory;
    ArrayList<String> cuisineType;
    ArrayList<String> expireIn;
    ArrayList<String> dates;
    ArrayList<String> address;
    ArrayList<String> emails;
    DatabaseHelper myDb;
    Button details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view__n_g_o);
        myDb = new DatabaseHelper(this);
        ActionBar myActionBar=getSupportActionBar();
        myActionBar.setTitle("Donations List");

        rvDonations=findViewById(R.id.rvRestaurants);
        details=findViewById(R.id.btn_Details);
        rvDonations.setLayoutManager(new LinearLayoutManager(this));
        foodCategory=new ArrayList<>();
        cuisineType=new ArrayList<>();
        expireIn=new ArrayList<>();
        dates=new ArrayList<>();
        address=new ArrayList<>();
        String email=getIntent().getStringExtra("EMAIL");
        String address2=getIntent().getStringExtra("ADDRESS");
        Cursor cursor=myDb.readDonations(email);
        while(cursor.moveToNext()){

            try {
                foodCategory.add(cursor.getString(0));
                cuisineType.add(cursor.getString(1));
                expireIn.add(cursor.getString(2));


            }catch(Exception e){
                Log.d("Error",e.getMessage());
            }
        }
        Make_Donations_Adapter myAdapter = new Make_Donations_Adapter(recyclerView_NGO.this, foodCategory, cuisineType, expireIn,address2,email);
        rvDonations.setAdapter(myAdapter);


    }
}