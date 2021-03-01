package com.example.foodrescue;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
//form for hotel registration

public class Regestration_Restauarant extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regestration__restauarant);

        ActionBar myActionBar=getSupportActionBar();
        myActionBar.setTitle("Restaurant Registration");
    }
}