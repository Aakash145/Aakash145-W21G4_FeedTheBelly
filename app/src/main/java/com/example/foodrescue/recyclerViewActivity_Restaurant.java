package com.example.foodrescue;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class recyclerViewActivity_Restaurant extends AppCompatActivity {

    RecyclerView rvDishes;
    ArrayList<Dishes_Fetched> dishesHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view__restaurant);

        ActionBar myActionBar=getSupportActionBar();
        myActionBar.setTitle("Select the Menu");

        rvDishes=findViewById(R.id.rvDishes);
        rvDishes.setLayoutManager(new LinearLayoutManager(this));
        dishesHolder=new ArrayList<>();

        Cursor cursor=new DBmanager(this).readalldata();
        while(cursor.moveToNext()){
            try {
                Dishes_Fetched obj = new Dishes_Fetched(cursor.getString(0), cursor.getString(5), cursor.getString(2), cursor.getString(3), cursor.getString(7), cursor.getString(6));
                dishesHolder.add(obj);
            }catch(Exception e){
                Log.d("Error",e.getMessage());
            }
        }
        Dishes_Fetched_Adapter myAdapter=new Dishes_Fetched_Adapter(dishesHolder);
        rvDishes.setAdapter(myAdapter);
    }
}