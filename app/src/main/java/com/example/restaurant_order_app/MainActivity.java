package com.example.restaurant_order_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Declaring main view and buttons
        TextView main = (TextView) findViewById(R.id.mainText);
        Button order = (Button) findViewById(R.id.menuButton);
        Button share = (Button) findViewById(R.id.shareButton);
        Button map = (Button) findViewById(R.id.mapsButton);

        // Setting the main text to display a phone number and address
        main.setText("Contact us at: 999-999-9999\n285 Old Westport Rd, North Dartmouth, MA 02747");

        // Button that bring you to the menu
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menuIntent = new Intent(getApplicationContext(), menuActivity.class);
                startActivity(menuIntent);
            }
        });

        // Button that allows you to share the address of the restaurant
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String shareBody = "Hey, check out this awesome restaurant, The Wing Bar at: 285 Old Westport Rd, North Dartmouth, MA 02747";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(shareIntent, "Share Using"));
            }
        });

        // Button that shows you the location of the restaurant on google maps
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loc = "University of Massachusetts Dartmouth";
                Uri addressUri = Uri.parse("geo:0,0?q=" + loc);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, addressUri);
                startActivity(mapIntent);
            }
        });
    }
}