package com.example.foodrescue;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


       // Timer timer=new Timer();
       // TimerTask task=new TimerTask() {
          //  @Override
          //  public void run() {
                Intent intent=new Intent(SplashActivity.this, Intro_Pages.class);
                startActivity(intent);
       //     }
      //  };
        //timer.schedule(task,10000);

    }
}
