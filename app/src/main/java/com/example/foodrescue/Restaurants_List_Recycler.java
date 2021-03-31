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
import android.widget.Toast;

import java.util.ArrayList;

public class Restaurants_List_Recycler extends AppCompatActivity {
    SQLiteDatabase FeedTheBelly;
    RecyclerView rvRestaurants;
    ArrayList<String> ids;
    ArrayList<String> names;
    ArrayList<String> address;
    ArrayList<String> city;
    ArrayList<String> state;
    ArrayList<String> country;
    ArrayList<String> postal;
    ArrayList<String> emails;
    ArrayList<String> phones;
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants__list__recycler);
        myDb = new DatabaseHelper(this);
        ActionBar myActionBar=getSupportActionBar();
        myActionBar.setTitle("Restaurants List");

        rvRestaurants=findViewById(R.id.rvRestaurantsList);
        rvRestaurants.setLayoutManager(new LinearLayoutManager(this));
        ids=new ArrayList<>();
        names=new ArrayList<>();
        emails=new ArrayList<>();
        address=new ArrayList<>();
        city=new ArrayList<>();
        state=new ArrayList<>();
        country=new ArrayList<>();
        postal=new ArrayList<>();
        phones=new ArrayList<>();
        Cursor cursor=myDb.readAllRestaurants();
        while(cursor.moveToNext()){

            Cursor cursor1 = myDb.readEmailFromDonated(cursor.getString(0));
            if (cursor1.getCount() == 0) {

                try {
                    emails.add(cursor.getString(0));
                    names.add(cursor.getString(1));
                    phones.add(cursor.getString(2));
                    ids.add(cursor.getString(3));
                    postal.add(cursor.getString(8));
                    String completeAddress=(cursor.getString(4))+", "+(cursor.getString(5))
                            +", "+(cursor.getString(6))+", "+(cursor.getString(7));
                    address.add(completeAddress);
                }catch(Exception e){
                    Log.d("Error",e.getMessage());
                }

            }

        }
        Restaurant_List_Adapter myAdapter = new Restaurant_List_Adapter(Restaurants_List_Recycler.this, emails, names, phones,
                ids, address,postal);
        rvRestaurants.setAdapter(myAdapter);

    }
}