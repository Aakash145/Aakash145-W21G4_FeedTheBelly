package com.example.foodrescue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterationActivity extends AppCompatActivity {

    Button signInButton;

    Button donateFood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        signInButton = findViewById(R.id.signInButton);
        donateFood = findViewById(R.id.donateButton);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(RegisterationActivity.this, MainActivity.class);
                startActivity(myIntent);


            }
        });
        donateFood.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View v1){

                Intent myIntent = new Intent(RegisterationActivity.this,Regestration_Restauarant.class);
                startActivity(myIntent);


            }

        });
    }


}
}