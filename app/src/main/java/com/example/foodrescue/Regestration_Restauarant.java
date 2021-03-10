package com.example.foodrescue;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//form for hotel registration

public class Regestration_Restauarant extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regestration__restauarant);

        Button Rest_Submit = findViewById(R.id.buttonSubmit);

        ActionBar myActionBar=getSupportActionBar();
        myActionBar.setTitle("Restaurant Registration");

        Rest_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(Regestration_Restauarant.this, Restaurant_Dashboard.class);
                startActivity(myIntent);


            }
        });
    }
}