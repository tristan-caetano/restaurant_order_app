package com.example.restaurant_order_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class order_list extends AppCompatActivity {

    // Global variables
    DatabaseInterfacer myDB = new DatabaseInterfacer(order_list.this);
    ArrayList<String> id, name, email, phone, date, card;
    OrderListAdapter oLAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        // Initializing toolbar
        Toolbar tempToolBar = findViewById(R.id.listToolbar);
        setSupportActionBar(tempToolBar);

        // Initializing Recycler View
        RecyclerView recyclerView = findViewById(R.id.listRecyclerView);

        // Database access
        id = new ArrayList<>();
        name = new ArrayList<>();
        email = new ArrayList<>();
        phone = new ArrayList<>();
        date = new ArrayList<>();
        card =  new ArrayList<>();

        // Saving data from db to arrays
        displayData();

        // Creating adapter for recycler view with arrays
        oLAdapter = new OrderListAdapter(order_list.this, order_list.this, id, name, email, phone, date, card);
        recyclerView.setAdapter(oLAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager((order_list.this)));
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

    // Getting data and saving it to arrays
    void displayData(){

        // Getting cursor info
        Cursor cursor = myDB.readAllData();

        // Making sure the db isnt empty
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();

        // Adding values to arrays
        }else{
            while(cursor.moveToNext()){
                id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                email.add(cursor.getString(2));
                phone.add(cursor.getString(3));
                date.add(cursor.getString(4));
                card.add(cursor.getString(5));

            }
        }
    }
}