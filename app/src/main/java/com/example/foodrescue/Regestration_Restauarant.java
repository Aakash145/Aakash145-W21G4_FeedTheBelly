package com.example.foodrescue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


//import com.google.firebase.database.FirebaseDatabase;
//form for hotel registration



public class Regestration_Restauarant extends AppCompatActivity {
    final String TAG = "Food Rescue";

    Button Rest_Submit;
    EditText rest_ID, city, state, country, postal_code, add1, add2, deliveryExtraTime, website;
    RadioGroup radGroupDelivery, radGroupTakeAway;
    TextView txtView;

    DatabaseReference databaseDetails;
    FirebaseDatabase db = FirebaseDatabase.getInstance();


    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regestration__restauarant);

        rest_ID = findViewById(R.id.editTextRestaurantID);
        deliveryExtraTime =findViewById(R.id.editTextDeliveryTime);
        city = findViewById(R.id.editTextRestuarantCity);
        state = findViewById(R.id.editTextRestuarantState);
        country = findViewById(R.id.editTextRestuarantCountry);
        postal_code = findViewById(R.id.editTextPostalAddress);
        add1 = findViewById(R.id.editTextStreetName);
        add2 = findViewById(R.id.editTextStreet2);
        website = findViewById(R.id.editTextRestuarantWebsite);
        Rest_Submit = findViewById(R.id.buttonSubmit);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Restaurant Registration");
        mAuth  = FirebaseAuth.getInstance();
        radGroupDelivery = findViewById(R.id.radiogroup1);
        radGroupTakeAway = findViewById(R.id.radiogroup2);
        txtView = findViewById(R.id.txtViewWelcome);

        Rest_Submit=findViewById(R.id.buttonSubmit);

      Intent myIntent = getIntent();
      String name = myIntent.getStringExtra(Login_Activity_Rest.User_Name);
      String email = myIntent.getStringExtra(Login_Activity_Rest.User_Email);
        //String str = "Welcome" + name;
        txtView.setText(name);
       databaseDetails = db.getReference("Details");
      // databaseDetails.child("users").child(email).child("email");


        Rest_Submit.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View view) {
                try {
                    saveUserInformation();
                } catch (Exception e) {
                    e.getMessage();
                }
            }
            });
    }

    public void saveUserInformation()  {
        final String ID = rest_ID.getText().toString().trim();
        final String City = city.getText().toString().trim();
        final String State = state.getText().toString().trim();
        final String Postal = postal_code.getText().toString().trim();
        final String Country = country.getText().toString().trim();
        final String AddLine1 = add1.getText().toString().trim();
        final String AddLine2 = add2.getText().toString().trim();
        final String Website = website.getText().toString().trim();

        try{

        if (ID.isEmpty()) {
            rest_ID.setError(getString(R.string.empty_ID));
            rest_ID.requestFocus();
            Toast.makeText(getApplicationContext(), "ID is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (AddLine1.isEmpty()) {
            add1.setError(getString(R.string.Addline_error));
            add1.requestFocus();
            return;
        }

        if (City.isEmpty()) {
            city.setError(getString(R.string.City_error));
            city.requestFocus();
            return;
        }
        if (State.isEmpty()) {
            state.setError(getString(R.string.State_error));
            state.requestFocus();
            return;
        }
        if (Country.isEmpty()) {
            country.setError(getString(R.string.Country_error));
            country.requestFocus();
            return;
        }
        if (Postal.isEmpty()) {
            postal_code.setError(getString(R.string.Postal_error));
            postal_code.requestFocus();
            return;
        }

        if (!Patterns.WEB_URL.matcher(Website).matches()) {
            website.setError(getString(R.string.website_error));
            website.requestFocus();
            return;
        }
        if (Postal.length() != 6) {
            postal_code.setError(getString(R.string.postal_length_error));
            postal_code.requestFocus();
            return;
        }
        if(radGroupDelivery.getCheckedRadioButtonId() == -1){
            Toast.makeText( this, "Please select Delivery Preferences", Toast.LENGTH_LONG).show();
        }
        if(radGroupTakeAway.getCheckedRadioButtonId() == -1){
            Toast.makeText( this, "Please select TakeAway Preferences", Toast.LENGTH_LONG).show();
        }
        else{
            String id = databaseDetails.push().getKey();
            details detail = new details(ID,AddLine1, AddLine2, City, State, Country, Postal);
            databaseDetails.child(id).setValue(detail);
            Toast.makeText( this, "Details saved successfully", Toast.LENGTH_LONG).show();
            Intent myIntent = new Intent(Regestration_Restauarant.this, Restaurant_Dashboard.class);
            startActivity(myIntent);

        }}catch(Exception ex){
            ex.getMessage();
            Log.d("DB", "Error with registration");
            Toast.makeText( this, "Error ", Toast.LENGTH_LONG).show();
        }

    }
}
            /* Rest_Submit = findViewById(R.id.buttonSubmit);
         rest_ID = findViewById(R.id.editTextRestaurantID);
         rest_Name = findViewById(R.id.editTextRestaurantNo);
         rest_Desc = findViewById(R.id.editTxtRestuarantDesc);
         rest_phone = findViewById(R.id.editRestuarantContactNumber);
        //String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
         rest_email = findViewById(R.id.editTextRestuarantEmailAddress);
        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setTitle("Restaurant Registration");

        mAuth = FirebaseAuth.getInstance();

        Rest_Submit.setOnClickListener(View view);

        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            //handle the already login user
        }
    }

    private void registerUser() {
        final String name = rest_Name.getText().toString().trim();
        final String email = rest_email.getText().toString().trim();
     //   String password = editTextPassword.getText().toString().trim();
        final String phone = rest_phone.getText().toString().trim();

        if (name.isEmpty()) {
            rest_Name.setError(getString(R.string.input_error_name));
            rest_Name.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            rest_email.setError(getString(R.string.input_error_email));
            rest_email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            rest_email.setError(getString(R.string.input_error_email_invalid));
            rest_email.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError(getString(R.string.input_error_password));
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError(getString(R.string.input_error_password_length));
            editTextPassword.requestFocus();
            return;
        }

        if (phone.isEmpty()) {
            editTextPhone.setError(getString(R.string.input_error_phone));
            editTextPhone.requestFocus();
            return;
        }

        if (phone.length() != 10) {
            editTextPhone.setError(getString(R.string.input_error_phone_invalid));
            editTextPhone.requestFocus();
            return;
        }


        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            User user = new User(
                                    name,
                                    email,
                                    phone
                            );

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        Toast.makeText(MainActivity.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
                                    } else {
                                        //display a failure message
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_register:
                registerUser();
                break;
        }
    }
}
© 2021 GitHub, Inc.

        Rest_Submit.setOnClickListener((View view) -> {
            if (rest_ID.getText().toString().equals("")) {
                Log.d(TAG, "Restaurant ID is empty");
                Toast.makeText(this, "Restaurant ID cannot be empty", Toast.LENGTH_LONG).show();

            } else if (rest_Name.getText().toString().equals("")) {
                Log.d(TAG, "Restaurant Name is empty");
                Toast.makeText(this, "Restaurant Name cannot be empty", Toast.LENGTH_LONG).show();

            } else if (rest_Desc.getText().toString().equals("")) {
                Log.d(TAG, "Restaurant Desc is empty");
                Toast.makeText(this, "Restaurant Description cannot be empty", Toast.LENGTH_LONG).show();
            } else if (rest_email.getText().toString().equals("")) {
                Log.d(TAG, "Restaurant Email is empty");
                Toast.makeText(this, "Restaurant Email cannot be empty", Toast.LENGTH_LONG).show();
            } else if (rest_Desc.getText().toString().equals("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                Log.d(TAG, "Restaurant Email is invalid");
                Toast.makeText(this, "Restaurant Email is invalid. Enter the right email address", Toast.LENGTH_SHORT).show();
            } else {

                    Intent myIntent = new Intent(Regestration_Restauarant.this, Restaurant_Dashboard.class);
                    startActivity(myIntent);



            }
        });
    }*/
