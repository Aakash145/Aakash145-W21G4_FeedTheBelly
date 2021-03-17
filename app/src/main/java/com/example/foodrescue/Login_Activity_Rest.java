package com.example.foodrescue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.FirebaseDatabase;

public class Login_Activity_Rest extends AppCompatActivity implements View.OnClickListener{
    public static final String User_Name ="UserName";
    public static final String User_Email = "UserEmail";
    public static final String UserId = "UserId";
    public static final String User_Phone = "UserPhone";
    private EditText editTextName, editTextEmail, editTextPassword, editTextPhone;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login___rest);


            editTextName = findViewById(R.id.editTextRestaurantNo);
            editTextEmail = findViewById(R.id.editTextRestuarantEmailAddress);
            editTextPassword = findViewById(R.id.edit_text_password);
            editTextPhone = findViewById(R.id.editRestuarantContactNumber);
            progressBar = findViewById(R.id.progressbar);

            mAuth = FirebaseAuth.getInstance();

            findViewById(R.id.buttonSubmit1).setOnClickListener(this);
        }

        @Override
        protected void onStart() {
            super.onStart();

            if (mAuth.getCurrentUser() != null) {
                //go to registration page
            }
        }

        private void registerUser() {
            final String name = editTextName.getText().toString().trim();
            final String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            final String phone = editTextPhone.getText().toString().trim();

            if (name.isEmpty()) {
                editTextName.setError(getString(R.string.input_error_name));
                editTextName.requestFocus();
                Toast.makeText(getApplicationContext(), "Name is empty", Toast.LENGTH_SHORT).show();
                return;
            }

            if (email.isEmpty()) {
                editTextEmail.setError(getString(R.string.input_error_email));
                editTextEmail.requestFocus();
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                editTextEmail.setError(getString(R.string.input_error_email_invalid));
                editTextEmail.requestFocus();
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
                                User user = new User(name, email, phone);
                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        progressBar.setVisibility(View.GONE);
                                        if (task.isSuccessful()) {
                                            Toast.makeText(getApplicationContext(), "User Registration Successful", Toast.LENGTH_LONG).show();
                                            Intent myIntent = new Intent(Login_Activity_Rest.this, Regestration_Restauarant.class);
                                            //myIntent.putExtra(UserId, user.getId());
                                           // myIntent.putExtra("Users", user);
                                            myIntent.putExtra(User_Name, user.getName());
                                            myIntent.putExtra(User_Email, user.getEmail());
                                            myIntent.putExtra(User_Phone, user.getPhone());
                                            startActivity(myIntent);

                                        } else {
                                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                                Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);
                                            } else {
                                                Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);
                                            }
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(Login_Activity_Rest.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
        }




        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.buttonSubmit1:
                    registerUser();
                    break;
            }
        }
    }
