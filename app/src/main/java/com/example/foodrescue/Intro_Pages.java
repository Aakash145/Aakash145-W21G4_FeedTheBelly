package com.example.foodrescue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Intro_Pages extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro__pages);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(1);
        SwipeAdapter swipeAdapter = new SwipeAdapter(getSupportFragmentManager());
        viewPager.setAdapter(swipeAdapter);
        viewPager.setCurrentItem(0);

        Button btnSkip = findViewById(R.id.btnSkip);
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intro_Pages.this, MainActivity.class);
                startActivity(intent);
            }
        });


        try {
            if (isOpen()) {
                Intent intent = new Intent(Intro_Pages.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } else {
                SharedPreferences.Editor editor = getSharedPreferences("preferences", MODE_PRIVATE).edit();
                editor.putBoolean("FirstTimeInstall", true);
                editor.commit();
            }
        }catch (Exception ex){
            Log.d("Error",ex.getMessage()+"");
        }
    }

    private Boolean isOpen() {
        SharedPreferences preferences=getSharedPreferences("preferences",MODE_PRIVATE);
        Boolean firstTime=preferences.getBoolean("FirstTimeInstall",false);
        return firstTime;
    }
}