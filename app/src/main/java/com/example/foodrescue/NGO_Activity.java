package com.example.foodrescue;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NGO_Activity extends AppCompatActivity {
    Button Rest_Submit;
    EditText NGO_ID, city, state, country, postal_code, add1, add2, Ngo_desc, website;
    RadioGroup radGroupDelivery, radGroupTakeAway;
    TextView txtView;
    String Name, Phone, Email;

   // DatabaseReference databaseDetails;
  //  FirebaseDatabase db = FirebaseDatabase.getInstance();
   DatabaseHelper myDb;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDb = new DatabaseHelper(this);
        setContentView(R.layout.activity_n_g_o_);
        NGO_ID = findViewById(R.id.editTextCharitNo);
        Ngo_desc =findViewById(R.id.editTxtOrgDesc);
        city = findViewById(R.id.editTextCity);
        state = findViewById(R.id.editTextState);
        country = findViewById(R.id.editTextCountry);
        postal_code = findViewById(R.id.editTextPostalAddress);
        add1 = findViewById(R.id.editTextStreetName);
        // add2 = findViewById(R.id.editTextRestuarantStreet2);
        website = findViewById(R.id.editTextWebsite);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Not-For-Profit Registration");
        mAuth  = FirebaseAuth.getInstance();
        txtView = findViewById(R.id.txtViewWelcome_No);


        Rest_Submit=findViewById(R.id.buttonSubmit_Ngo2);
        Intent i = getIntent();
        User user = (User) i.getParcelableExtra("User");
        //User user = (User)i.getSerializableExtra("User");

        Name = user.getName();
        Email = user.getEmail();
        Phone = user.getPhone();

        String str = "Welcome " + Name;
        txtView.setText(str);
        Rest_Submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                saveUserInformation();


            }
        });
    }
    public void saveUserInformation()  {
        final String ID = NGO_ID.getText().toString().trim();
        final String City = city.getText().toString().trim();
        final String State = state.getText().toString().trim();
        final String Postal = postal_code.getText().toString();
        final String Country = country.getText().toString().trim();
        final String AddLine1 = add1.getText().toString().trim();
        final String Desc = Ngo_desc.getText().toString().trim();
        //  final String AddLine2 = add2.getText().toString().trim();
        final String Website = website.getText().toString().trim();
        final String name = Name;
        final String email = Email;
        final String phone = Phone;

        try{

            if (ID.isEmpty()) {
                NGO_ID.setError(getString(R.string.empty_ID));
                NGO_ID.requestFocus();
                Toast.makeText(getApplicationContext(), "ID is empty", Toast.LENGTH_SHORT).show();
                return;
            }
            if (ID.length() != 6) {
                NGO_ID.setError(getString(R.string.IDerror));
                NGO_ID.requestFocus();
                Toast.makeText(getApplicationContext(), "ID should be 6 digit", Toast.LENGTH_SHORT).show();
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
            if (!Website.isEmpty() && !Patterns.WEB_URL.matcher(Website).matches()) {
                website.setError(getString(R.string.website_error));
                website.requestFocus();
                return;
            }
            if (Postal.length() != 6) {
                postal_code.setError(getString(R.string.postal_length_error));
                postal_code.requestFocus();
                return;
            }
            if(Desc.isEmpty()) {
                Ngo_desc.setError(getString(R.string.Desc_error));
                Ngo_desc.requestFocus();
                return;
            }
            else{
             //   String id = databaseDetails.push().getKey();
               // User user = new User(name, email, phone);

        //        Detail detail = new Detail(email, name, phone, id, ID, AddLine1, City, State, Country, Postal);
         //       databaseDetails.child(id).setValue(detail);
                boolean isInserted = myDb.insertData(email,name, phone, ID, AddLine1, City, State, Country, Postal );
                if(isInserted == true){
                Toast.makeText( this, "Details saved successfully", Toast.LENGTH_LONG).show();
                finish();
                Intent myIntent = new Intent(NGO_Activity.this, NGO_Dashboard.class);
                    myIntent.putExtra("Email", email);
                //  myIntent.putExtra(User
             //   myIntent.putExtra("Details", detail);
           //     myIntent.putExtra("User", user);
                startActivity(myIntent);

            }
                else{
                    Toast.makeText( this, "Data not Inserted", Toast.LENGTH_LONG).show();
                    //  Toast.makeText(MainActivity.this,,Toast.LENGTH_LONG).show();
                }
            }}catch(Exception ex){
            ex.getMessage();
            Log.d("DB", "Error with registration");
            Toast.makeText( this, "Error ", Toast.LENGTH_LONG).show();
        }

    }
}
