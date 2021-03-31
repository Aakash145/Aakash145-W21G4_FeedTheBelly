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

import java.util.ArrayList;

public class List_Of_Donated_Items extends AppCompatActivity {
    SQLiteDatabase FeedTheBelly;
    RecyclerView rvItems;
    ArrayList<String> foodName;
    ArrayList<String> weight;
    ArrayList<String> plates;
    ArrayList<String> emails;
    ArrayList<String> addresses;
    DatabaseHelper myDb;
    Button nextClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__of__donated__items);
        myDb = new DatabaseHelper(this);
        ActionBar myActionBar=getSupportActionBar();
        myActionBar.setTitle("Donations List");

        rvItems=findViewById(R.id.rvDonateItem);
        nextClick=findViewById(R.id.btnNext);
        rvItems.setLayoutManager(new LinearLayoutManager(this));
        foodName=new ArrayList<>();
        weight=new ArrayList<>();
        plates=new ArrayList<>();
        String email=getIntent().getStringExtra("EMAIL");
        String address2=getIntent().getStringExtra("ADDRESS");
       // String name=getIntent().getStringExtra("NAME");
        String exp=getIntent().getStringExtra("EXPIRY");
        Cursor cursor=myDb.readDishes(email,exp);
        while(cursor.moveToNext()){

            try {
                foodName.add(cursor.getString(0));
                weight.add(cursor.getString(1));
                plates.add(cursor.getString(2));
                //addresses.add(address2);


            }catch(Exception e){
                Log.d("Error",e.getMessage());
            }
        }
        List_of_Donated_Items_Adapter myAdapter = new List_of_Donated_Items_Adapter(List_Of_Donated_Items.this, foodName, weight, plates,address2,email,exp);
        rvItems.setAdapter(myAdapter);
        nextClick=findViewById(R.id.btnNext);
        nextClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(List_Of_Donated_Items.this,GoogleMapsActivity.class);
                intent.putExtra("ADDRESSES",address2);
                startActivity(intent);

            }
        });
    }
}