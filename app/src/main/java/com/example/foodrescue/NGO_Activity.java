package com.example.foodrescue;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NGO_Activity extends AppCompatActivity {
 Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_g_o_);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Not-For-Profit Registration");

        submit=findViewById(R.id.buttonSubmit);
        submit.setOnClickListener((View view)->{
            Intent myIntent = new Intent(NGO_Activity.this, NGO_Dashboard.class);
            startActivity(myIntent);
        });

    }
}