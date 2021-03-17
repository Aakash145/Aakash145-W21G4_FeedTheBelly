package com.example.foodrescue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Restaurant_Dashboard extends AppCompatActivity {
    CardView cardHome;
    CardView cardAdd;
    CardView cardMenu;
    CardView cardLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant__dashboard);

        cardHome=findViewById(R.id.cardHome);
        cardAdd=findViewById(R.id.cardAdd);
        cardMenu=findViewById(R.id.cardListItems);
        cardLogout=findViewById(R.id.cardLogout);

        cardHome.setOnClickListener((View view)->{
            Intent myIntent = new Intent(Restaurant_Dashboard.this, restaurant_profile.class);
            startActivity(myIntent);
        });
        cardAdd.setOnClickListener((View view)->{
            Intent myIntent = new Intent(Restaurant_Dashboard.this, Restaurant_starter.class);
            startActivity(myIntent);
        });

        cardMenu.setOnClickListener((View view)->{
            Intent myIntent = new Intent(Restaurant_Dashboard.this, recyclerViewActivity_Restaurant.class);
            startActivity(myIntent);
        });

        cardLogout.setOnClickListener((View view)->{
            Intent myIntent = new Intent(Restaurant_Dashboard.this, MainActivity.class);
            startActivity(myIntent);
        });
    }
}