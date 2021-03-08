package com.example.foodrescue;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class recyclerViewActivity_Restaurant extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view__restaurant);

        ActionBar myActionBar=getSupportActionBar();
        myActionBar.setTitle("Select The Menu you want to Donate Today");
    }
}