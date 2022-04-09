package com.example.restaurant_order_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
                                editPayment.getText().toString().trim());
                    }else{
                        Toast.makeText(payActivity.this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                    }
                Intent menuIntent = new Intent(getApplicationContext(), order_list.class);
                startActivity(menuIntent);


            }
        });
    }
}