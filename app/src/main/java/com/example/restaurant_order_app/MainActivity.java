package com.example.restaurant_order_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView main = (TextView) findViewById(R.id.mainText);

        main.setText("Contact us at: 1-800-995-2345\n285 Old Westport Rd, North Dartmouth, MA 02747");

    }
}