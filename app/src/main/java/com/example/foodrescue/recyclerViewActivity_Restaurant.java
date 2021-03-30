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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class recyclerViewActivity_Restaurant extends AppCompatActivity {
    SQLiteDatabase FeedTheBelly;
    RecyclerView rvDishes;
    ArrayList<Dishes_Fetched> dishesHolder;
    DatabaseHelper myDb;
    private FirebaseAuth mFirebaseAuth;
    Button donate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view__restaurant);

        myDb = new DatabaseHelper(this);
        ActionBar myActionBar=getSupportActionBar();
        myActionBar.setTitle("Select the Menu");

        rvDishes=findViewById(R.id.rvDishes);
        donate=findViewById(R.id.btnDonate);
        rvDishes.setLayoutManager(new LinearLayoutManager(this));
        dishesHolder=new ArrayList<>();
        mFirebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser mFirebaseUser=mFirebaseAuth.getCurrentUser();
        String email=mFirebaseUser.getEmail();
        Cursor cursor=myDb.readEmail(email);
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
       donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Cursor cursor1 = myDb.readEmail(email);
                    if (cursor1.getCount() != 0) {
                        cursor1.moveToFirst();

                        do {
                            //int id=cursor1.getInt(8)
                            String dishID = cursor1.getString(0);
                            String emailID = cursor1.getString(1);
                            String cuisineType = cursor1.getString(2);
                            String foodCategory = cursor1.getString(3);
                            String expDate = cursor1.getString(4);
                            String name = cursor1.getString(5);
                            String plates = cursor1.getString(6);
                            String weight = cursor1.getString(7);

                            myDb.addDonation(emailID, plates, weight, name, dishID, cuisineType, foodCategory, expDate);
                            Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(recyclerViewActivity_Restaurant.this, Restaurant_Dashboard.class);
                            startActivity(i);

                        }
                    while (cursor1.moveToNext()) ;
                }
                    myDb.deleteAllDishesOfEachUser(email);
            }

                catch (Exception e){
                    Toast.makeText(getApplicationContext(),""+e.getMessage(),Toast.LENGTH_SHORT).show();
                    Log.d("ERROR",e.getMessage());
                }
            }
        });
    }
}