package com.example.foodrescue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NGO_Dashboard extends AppCompatActivity {
    CardView cardHome;
    CardView cardAdd;
    CardView cardListItems;
    CardView cardLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_g_o__dashboard);

        cardHome = findViewById(R.id.cardHome);
        cardAdd = findViewById(R.id.cardAdd);
        cardListItems = findViewById(R.id.cardListItems);
        cardLogout = findViewById(R.id.cardLogout);

        cardHome.setOnClickListener((View view) -> {

            Intent i = getIntent();
           // Detail details = (Detail) i.getSerializableExtra("Details");
            //user user = (User) i.getSerializableExtra("User");
            String email = i.getStringExtra("Email");
            Intent myIntent = new Intent(NGO_Dashboard.this, Ngo_profile.class);
          //  myIntent.putExtra("Details", details);
            //myIntent.putExtra("User", user);
            startActivity(myIntent);


        });
        cardAdd.setOnClickListener((View view) -> {
            Intent myIntent = new Intent(NGO_Dashboard.this, NGO_Add_Items.class);
            startActivity(myIntent);
        });

        cardListItems.setOnClickListener((View view) -> {
            Intent myIntent = new Intent(NGO_Dashboard.this, recyclerView_NGO.class);
            startActivity(myIntent);
        });

        cardLogout.setOnClickListener((View view) -> {
            Intent myIntent = new Intent(NGO_Dashboard.this, MainActivity.class);
            startActivity(myIntent);
        });
    }
}