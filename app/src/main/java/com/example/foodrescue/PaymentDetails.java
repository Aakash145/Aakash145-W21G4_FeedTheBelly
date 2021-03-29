package com.example.foodrescue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class PaymentDetails extends AppCompatActivity {

    TextView paymentId;
    TextView paymentAmount1;
    TextView paymentStatus;


    Button registerBackBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);

        paymentId = findViewById(R.id.paymentId);
        paymentAmount1 = findViewById(R.id.amountPaidId);
        paymentStatus = findViewById(R.id.statusId);
        registerBackBtn = findViewById(R.id.registerId);

        Intent intent = getIntent();
        try{
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("PaymentDetails"));
            showDetails(jsonObject.getJSONObject("response"), intent.getStringExtra("PaymentAmount"));
        } catch (JSONException e){
            e.printStackTrace();
        }

        registerBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(PaymentDetails.this, RegisterationActivity.class);
                startActivity(myIntent);
            }
        });
    }

    private void showDetails(JSONObject response, String paymentAmount) {

        try {
            paymentId.setText("Payment ID: " + response.getString("id"));
            paymentStatus.setText("Payment Status: " + response.getString("state"));
            paymentAmount1.setText("Payment Amount: " + "$"+paymentAmount);
        } catch (JSONException e){
            e.printStackTrace();
        }

    }
}