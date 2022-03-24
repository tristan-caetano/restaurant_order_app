package com.example.restaurant_order_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class menuActivity extends AppCompatActivity {

    // Declaring global values
    public static final String SHARED_PREFS = "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button checkout = findViewById(R.id.checkout);

        // Goes to checkout screen with values
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("GO TO CHECKOUT");
                Intent menuIntent = new Intent(getApplicationContext(), checkoutActivity.class);
                startActivity(menuIntent);
            }
        });


    }
}