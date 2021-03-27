package com.example.foodrescue;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class Make_Donation_Recycler extends AppCompatActivity {
    RecyclerView rvMake_Donation;
    ArrayList<Make_Donation> donations;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make__donation__recycler);

        myDb = new DatabaseHelper(this);
        ActionBar myActionBar=getSupportActionBar();
        myActionBar.setTitle("Select the Menu");

        rvMake_Donation=findViewById(R.id.rvDonations);
        rvMake_Donation.setLayoutManager(new LinearLayoutManager(this));
        donations=new ArrayList<>();

        Cursor cursor=myDb.readalldata();
      while(cursor.moveToNext()){
            try {
                Dishes_Fetched obj = new Dishes_Fetched(cursor.getString(0), cursor.getString(4), cursor.getString(1), cursor.getString(2), cursor.getString(6), cursor.getString(5));
                //dishesHolder.add(obj);
            }catch(Exception e){
                Log.d("Error",e.getMessage());
            }
        }
        //Dishes_Fetched_Adapter myAdapter=new Dishes_Fetched_Adapter(dishesHolder);
        //rvMake_Donation.setAdapter(myAdapter);
    }
}