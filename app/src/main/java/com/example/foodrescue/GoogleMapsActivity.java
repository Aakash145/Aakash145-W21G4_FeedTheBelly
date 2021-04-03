package com.example.foodrescue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Camera;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.util.List;

public class GoogleMapsActivity<mFirebaseAuth> extends AppCompatActivity implements OnMapReadyCallback {


    private FirebaseAuth mFirebaseAuth;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST = 1234;
    private static final float DEFAULT_ZOOM = 15;
    private FusedLocationProviderClient mFusedLocationProviderClient;

    private GoogleMap mMap;
    TextView address;
    Button accept;
    Button BacktoDash;
    String email;
    String donateddate;
    DatabaseHelper myDb;

    private Boolean mLocationPermissionGranted = false;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //Toast.makeText(this, "Map is Ready", Toast.LENGTH_SHORT).show();
        Log.d("Check Services", "Map is ready");
        mMap = googleMap;
        //Adding addresses
        address=findViewById(R.id.idRestInfo);
        String street=getIntent().getStringExtra("ADDRESSES");
        donateddate=getIntent().getStringExtra("DATE");
        email=getIntent().getStringExtra("EMAIL");
        getLocationFromAddress(street);
        address.setText("Address: "+street);

    }

    public void getLocationFromAddress(String strAddress)
    {
        Geocoder coder = new Geocoder(this);
        List<Address> address;

        try {
            //Get latLng from String
            address = coder.getFromLocationName(strAddress,5);

            //check for null
            if (address == null) {
                return;
            }

            //Getting the first possibility
            Address location=address.get(0);
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            Log.d("Changed", "Added Address");
            //Put marker on map on that LatLng
            mMap.addMarker(new MarkerOptions().position(latLng).title("Destination")).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(latLng)
                    .zoom(15)
                    .build();
            CameraUpdate update = CameraUpdateFactory.newCameraPosition(cameraPosition);
            mMap.animateCamera(update);

//            mMap.getUiSettings().setZoomControlsEnabled(true);
//            mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
//            //Animate and Zoom on that map location
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));



            Log.d("Changed", "Changed Address");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps);
        myDb = new DatabaseHelper(this);
        mFirebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser mFirebaseUser=mFirebaseAuth.getCurrentUser();
        String ngo_email=mFirebaseUser.getEmail();

        initMap();
        accept = findViewById(R.id.idAcceptOrder);
        accept.setOnClickListener((View view) -> {
            final Dialog dialog = new Dialog(GoogleMapsActivity.this);
            BacktoDash = findViewById(R.id.buttonbacktodashNGO);
            // Include dialog.xml file
                dialog.setContentView(R.layout.dialog);
            // Set dialog title
                dialog.setTitle("Custom Dialog");

            // set values for custom dialog components - text, image and button
            TextView text = (TextView) dialog.findViewById(R.id.textViewAccept);
                text.setText("Your order is placed and the email is sent to the restaurant on "+ email+ "" );

                dialog.show();
             Button BackButton = (Button) dialog.findViewById(R.id.buttonbacktodashNGO);
            // if decline button is clicked, close the custom dialog
            BackButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Cursor cursor1 = myDb.readEmailFromDonation(email,donateddate);
                    if (cursor1.getCount() != 0) {
                        cursor1.moveToFirst();

                        do {
                            //int id=cursor1.getInt(8)
                            String dishID = cursor1.getString(0);
                            String emailID = cursor1.getString(1);
                            String cuisineType = cursor1.getString(2);
                            String foodCategory = cursor1.getString(3);
                            String expDate = cursor1.getString(4);
                            String name = cursor1.getString(5);
                            String plates = cursor1.getString(6);
                            String weight = cursor1.getString(7);
                            String donated_date=cursor1.getString(8);
                            Toast.makeText(GoogleMapsActivity.this, "Confirmed Order", Toast.LENGTH_SHORT).show();
                            myDb.addDonated(emailID,ngo_email, plates, weight, name, dishID, cuisineType, foodCategory, expDate,donated_date);
                        }
                        while (cursor1.moveToNext());
                        Intent myIntent = new Intent(GoogleMapsActivity.this, NGO_Dashboard.class);
                        startActivity(myIntent);
                        Toast.makeText(GoogleMapsActivity.this, "Confirmed", Toast.LENGTH_SHORT).show();


                    }
                    myDb.deleteDonation(donateddate,email);
                    Intent myIntent = new Intent(GoogleMapsActivity.this, NGO_Dashboard.class);
                    startActivity(myIntent);
                }
            });
            });

    }


    private void initMap(){
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(GoogleMapsActivity.this);
    }

}