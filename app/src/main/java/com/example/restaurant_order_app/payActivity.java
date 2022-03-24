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

    private Date date;
    private Date dCompOne;
    private Date dCompTwo;
    private String sCompOne = "10:00";
    private String sCompTwo = "22:00";
    SimpleDateFormat inParser = new SimpleDateFormat("HH:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        Button placeOrderButton = findViewById(R.id.placeOrderButton);
        Button cancelButton = findViewById(R.id.cancelButton);


        // Button that brings you back to the menu
        cancelButton.setOnClickListener( view -> {
            Intent menuIntent = new Intent(getApplicationContext(), checkoutActivity.class);
            startActivity(menuIntent);
        });

        // Button that brings you back to the menu
        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT-4:00"));
                int hour = now.get(Calendar.HOUR_OF_DAY);
                int minute = now.get(Calendar.MINUTE);
                Toast dateToast = null;
                System.out.println(hour + ":" + minute);

                try {
                    date = inParser.parse(hour + ":" + minute);
                    dCompOne = inParser.parse(sCompOne);
                    dCompTwo = inParser.parse(sCompTwo);
                } catch (ParseException e) {
                    System.err.println("Dates could not be parsed.");
                }

                if(dCompOne.before(date) && dCompTwo.after(date)){
                    dateToast = Toast.makeText(getApplicationContext(), "Order has been submitted.", Toast.LENGTH_SHORT);
                    dateToast.show();
                }else{
                    dateToast = Toast.makeText(getApplicationContext(), "Error: Order could not be submitted, we are currently closed.", Toast.LENGTH_SHORT);
                    dateToast.show();
                }


            }
        });
    }
}