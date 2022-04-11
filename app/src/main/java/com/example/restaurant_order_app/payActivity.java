package com.example.restaurant_order_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class payActivity extends AppCompatActivity {

    // Declaring global values
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String FINAL_TOTAL = "final_total";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        // Declaring buttons
        Button placeOrderButton = findViewById(R.id.placeOrderButton);
        Button cancelButton = findViewById(R.id.cancelButton);

        // Declaring input fields
        EditText editName = findViewById(R.id.editName);
        EditText editEmail = findViewById(R.id.editEmail);
        EditText editPhone = findViewById(R.id.editPhoneNumber);
        EditText editDate = findViewById(R.id.editDate);
        EditText editPayment = findViewById(R.id.editPayment);

        // Button that brings you back to the menu
        cancelButton.setOnClickListener( view -> {
            Intent menuIntent = new Intent(getApplicationContext(), checkoutActivity.class);
            startActivity(menuIntent);
        });

        // Button that checks if the store is open and notifies you that your order is complete
        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseInterfacer myDB = new DatabaseInterfacer(payActivity.this);

                final DecimalFormat df = new DecimalFormat("#.##");

                // Shared Preferences
                SharedPreferences sharedPrefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

                // Getting total
                float finalTotal = sharedPrefs.getFloat(FINAL_TOTAL, 0);
                String strFinal = df.format(finalTotal);

                    // Making sure all of the fields are filled in
                    if( editName.getText().toString().trim() != "" &&
                        editEmail.getText().toString().trim() != "" &&
                        editPhone.getText().toString().trim() != "" &&
                        editDate.getText().toString().trim() != "" &&
                        editPayment.getText().toString().trim() != "") {

                        // Adding order to database
                        myDB.addOrder(editName.getText().toString().trim(),
                                editEmail.getText().toString().trim(),
                                editPhone.getText().toString().trim(),
                                editDate.getText().toString().trim(),
                                editPayment.getText().toString().trim(),
                                strFinal);
                    }else{
                        Toast.makeText(payActivity.this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                    }
                Intent menuIntent = new Intent(getApplicationContext(), order_list.class);
                startActivity(menuIntent);


            }
        });
    }
}