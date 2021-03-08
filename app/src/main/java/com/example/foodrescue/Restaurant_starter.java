package com.example.foodrescue;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Restaurant_starter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_starter);
        Button Confirm_Form = findViewById(R.id.btnViewConfirm);
        ActionBar myActionBar=getSupportActionBar();
        myActionBar.setTitle("Fill in the Food Details");


        Confirm_Form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(Restaurant_starter.this, recyclerViewActivity_Restaurant.class);
                startActivity(myIntent);


            }
        });
    }
}