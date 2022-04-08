package com.example.restaurant_order_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class order_list extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        // Initializing toolbar
        Toolbar tempToolBar = findViewById(R.id.listToolbar);
        setSupportActionBar(tempToolBar);
    }

    // Inflating toolbar to get toolbar buttons
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_buttons, menu);
        return true;
    }

    // Method for defining what the toolbar buttons do
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){

            case R.id.homeButton:
                startActivity(new Intent(this, MainActivity.class));
                return true;

            case R.id.menuButton:
                startActivity(new Intent(this, menuActivity.class));
                return true;

            case R.id.cartButton:
                startActivity(new Intent(this, checkoutActivity.class));
                return true;

            case R.id.order_list:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}