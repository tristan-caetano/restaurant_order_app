package com.example.restaurant_order_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class payActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        // Declaring buttons
        Button placeOrderButton = findViewById(R.id.placeOrderButton);
        Button cancelButton = findViewById(R.id.cancelButton);

        // Button that brings you back to the menu
        cancelButton.setOnClickListener( view -> {
            Intent menuIntent = new Intent(getApplicationContext(), checkoutActivity.class);
            startActivity(menuIntent);
        });

        // Button that checks if the store is open and notifies you that your order is complete
        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast dateToast = Toast.makeText(getApplicationContext(), "Your order was completed!", Toast.LENGTH_SHORT);
                dateToast.show();

            }
        });
    }
}