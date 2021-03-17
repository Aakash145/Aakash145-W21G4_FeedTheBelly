package com.example.foodrescue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.FirebaseDatabase;

public class login_Activity_ngo extends AppCompatActivity implements View.OnClickListener{
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
        setContentView(R.layout.activity_login__ngo);
        editTextName = findViewById(R.id.editTextNGONo);
        editTextEmail = findViewById(R.id.editTextNGOEmailAddress);
        editTextPassword = findViewById(R.id.edit_text_password_NGO);
        editTextPhone = findViewById(R.id.editNGOContactNumber);
        progressBar = findViewById(R.id.progressbar1);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.buttonSubmit_NGO).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser() != null) {
          //go to registration page
        }
    }

    private void registerUser() {
        final String name1 = editTextName.getText().toString().trim();
        final String email1 = editTextEmail.getText().toString().trim();
        String password1 = editTextPassword.getText().toString().trim();
        final String phone1 = editTextPhone.getText().toString().trim();

        if (name1.isEmpty()) {
            editTextName.setError(getString(R.string.input_error_name));
            editTextName.requestFocus();
            Toast.makeText(getApplicationContext(),"Name is empty",Toast.LENGTH_SHORT).show();
            return;
        }

        if (email1.isEmpty()) {
            editTextEmail.setError(getString(R.string.input_error_email));
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email1).matches()) {
            editTextEmail.setError(getString(R.string.input_error_email_invalid));
            editTextEmail.requestFocus();
            return;
        }

        if (password1.isEmpty()) {
            editTextPassword.setError(getString(R.string.input_error_password));
            editTextPassword.requestFocus();
            return;
        }

        if (password1.length() < 6) {
            editTextPassword.setError(getString(R.string.input_error_password_length));
            editTextPassword.requestFocus();
            return;
        }

        if (phone1.isEmpty()) {
            editTextPhone.setError(getString(R.string.input_error_phone));
            editTextPhone.requestFocus();
            return;
        }

        if (phone1.length() != 10) {
            editTextPhone.setError(getString(R.string.input_error_phone_invalid));
            editTextPhone.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email1, password1)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            User user = new User(name1, email1, phone1);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "User Registration Successful", Toast.LENGTH_LONG).show();
                                        Intent myIntent = new Intent(login_Activity_ngo.this, NGO_Activity.class);
                                        myIntent.putExtra(User_Name, user.getName());
                                        myIntent.putExtra(User_Email, user.getEmail());
                                        myIntent.putExtra(User_Phone, user.getPhone());
                                        startActivity(myIntent);
                                    } else {
                                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                            Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(login_Activity_ngo.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSubmit_NGO:
                registerUser();
                break;
        }
    }
}
