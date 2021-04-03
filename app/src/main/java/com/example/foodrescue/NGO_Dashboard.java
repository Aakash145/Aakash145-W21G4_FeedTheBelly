package com.example.foodrescue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class NGO_Dashboard extends AppCompatActivity {
    CardView cardHome;
    CardView cardAdd;
    CardView cardListItems;
    CardView cardLogout;

    private static final int ERROR_DIALOG_REQUEST = 9001;

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
            myIntent.putExtra("Email", email);
            startActivity(myIntent);


        });
//        cardAdd.setOnClickListener((View view) -> {
//            Intent myIntent = new Intent(NGO_Dashboard.this, NGO_Add_Items.class);
//            startActivity(myIntent);
//        });

        cardListItems.setOnClickListener((View view) -> {
            Intent i = getIntent();
            String email = i.getStringExtra("Email");
            Intent myIntent = new Intent(NGO_Dashboard.this, Confirmation_List_Recycler.class);
            myIntent.putExtra("Email", email);
            //ntent myIntent = new Intent(NGO_Dashboard.this, GoogleMapsActivity.class);I
            startActivity(myIntent);
        });

        cardLogout.setOnClickListener((View view) -> {
            Intent myIntent = new Intent(NGO_Dashboard.this, MainActivity.class);
            startActivity(myIntent);
        });

        if(isServiceOk()){
            init();
        }
    }

    private void init(){
        cardAdd.setOnClickListener((View view) -> {
            //Intent myIntent = new Intent(NGO_Dashboard.this, NGO_Add_Items.class);
            Intent i = getIntent();
            String email = i.getStringExtra("Email");
            Intent myIntent = new Intent(NGO_Dashboard.this, Restaurants_List_Recycler.class );
            myIntent.putExtra("Email", email);
            startActivity(myIntent);
        });
    }

    public boolean isServiceOk(){
        Log.d("Checking Servics", "isServicesOK: checking google services version");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(NGO_Dashboard.this);
        if(available == ConnectionResult.SUCCESS){
            Log.d("Checking Services", "Its Working fine");
            return true;
        }else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            Log.d("Checking Services","isServicesOk(); an errorr occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(NGO_Dashboard.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(this, "Can't male request", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}