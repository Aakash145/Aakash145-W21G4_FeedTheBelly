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

public class recyclerView_NGO extends AppCompatActivity {
    SQLiteDatabase FeedTheBelly;
    RecyclerView rvDonations;
    ArrayList<Dishes_Fetched> dishesHolder;
    DatabaseHelper myDb;
    private FirebaseAuth mFirebaseAuth;
    Button details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view__n_g_o);
        myDb = new DatabaseHelper(this);
        ActionBar myActionBar=getSupportActionBar();
        myActionBar.setTitle("Select the Menu");

        rvDonations=findViewById(R.id.rvDonations);
        details=findViewById(R.id.btn_Details);
        rvDonations.setLayoutManager(new LinearLayoutManager(this));
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
       // rvDishes.setAdapter(myAdapter);

    }
}