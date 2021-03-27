package com.example.foodrescue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button registerButton, loginButton;
    FirebaseAuth mAuth;
    EditText editTextEmail, editTextPassword;
   // Spinner spinnerUserType;
    DatabaseHelper myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       myDb = new DatabaseHelper(this);
        mAuth = FirebaseAuth.getInstance();
       // myDb.delete();
        //myDb.delete();
       ;
      //  mydb.rawQuery("drop Database User.db");
        editTextEmail = (EditText) findViewById(R.id.emailText);
        editTextPassword = (EditText) findViewById(R.id.passwordText);
       // spinnerUserType = findViewById(R.id.spinnerUsrType);

        ActionBar myBar = getSupportActionBar();
        myBar.setTitle("Feed The Belly");
        myBar.setDisplayHomeAsUpEnabled(true);
        myBar.setDisplayUseLogoEnabled(true);
        myBar.setLogo(R.mipmap.ic_launcher_round);

        registerButton = findViewById(R.id.registerButton);
        loginButton = findViewById(R.id.loginButton);

    //    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.UserType, R.layout.support_simple_spinner_dropdown_item);
//        spinnerUserType.setAdapter(adapter);
        registerButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);
    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
      //s  String item = spinnerUserType.getSelectedItem().toString().trim();
      //  int index = spinnerUserType.getSelectedItemPosition();

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Minimum length of password should be 6");
            editTextPassword.requestFocus();
            return;
        }
      /*  if(index == 0){
            Toast.makeText(this, "Please select User Type", Toast.LENGTH_LONG).show();
            return;
        }*/
//        boolean isExist = new DBmanager(this).checkUser(email);



        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //  if (isExist) {
                   // finish();
                    try {
                        Cursor c = myDb.getData(email);
                        Cursor d = myDb.getData1(email);

                        if (c.getCount() != 0 && d.getCount() == 0) {
                            Intent myIntent = new Intent(MainActivity.this, Restaurant_Dashboard.class);
                            myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            myIntent.putExtra("Email", email);
                            startActivity(myIntent);
                        }if((c.getCount() == 0 && d.getCount() != 0) ){
                            Intent myIntent = new Intent(MainActivity.this, NGO_Dashboard.class);
                            myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            myIntent.putExtra("Email", email);
                            startActivity(myIntent);
                        }
                        else {
                            // Toast.makeText(MainActivity.this, "Login failed. Invalid username or password.", Toast.LENGTH_SHORT).show();
                            return;
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        return;


                    }
                       /* if (index == 1) {
                            Intent myIntent = new Intent(MainActivity.this, NGO_Dashboard.class);
                            myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            myIntent.putExtra("Email", email);
                            startActivity(myIntent);
                        }
                        if (index == 2) {
                            Intent myIntent = new Intent(MainActivity.this, Restaurant_Dashboard.class);
                            myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            myIntent.putExtra("Email", email);
                            startActivity(myIntent);
                        }
                   // } else {
                     //   Toast.makeText(MainActivity.this, "Login failed. Invalid username or password.", Toast.LENGTH_SHORT).show();
                    //}
                }
                  else{  Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }*/
                }
                else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.registerButton:
                        Intent myIntent = new Intent(MainActivity.this, RegisterationActivity.class);
                        finish();
                        startActivity(myIntent);
                        break;
                    case R.id.loginButton:
                        userLogin();
                        break;
                }

            }
}
