package com.example.restaurant_order_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class menuActivity extends AppCompatActivity {

    // Declaring global values
    public static final String SHARED_PREFS = "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Declaring recyclerview and toolbar
        RecyclerView menuRecycle = findViewById(R.id.menuRecycler);
        Toolbar tempToolBar = findViewById(R.id.prevMToolbar);
        setSupportActionBar(tempToolBar);
        FoodAdapter recycAdap = new FoodAdapter(this, initializeData());
        menuRecycle.setAdapter(recycAdap);
        menuRecycle.setLayoutManager(new LinearLayoutManager(this));

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
                return true;

            case R.id.cartButton:
                startActivity(new Intent(this, checkoutActivity.class));
                return true;

            case R.id.order_list:
                startActivity(new Intent(this, order_list.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public ArrayList<Food> initializeData() {

        ArrayList<Food> foodData =  new ArrayList<>();

        // Get the resources from the XML file.
        String[] foodList = getResources()
                .getStringArray(R.array.food_titles);
        TypedArray foodImageResources =
                getResources().obtainTypedArray(R.array.food_images);

        // Clear the existing data (to avoid duplication).
        foodData.clear();

        // Create the ArrayList of Food objects with titles and
        // information about each menu item.
        for(int i=0;i<foodList.length;i++){
            foodData.add(new Food(foodList[i],foodImageResources.getResourceId(i,0), this));
            System.out.println("THIS: " + this);
        }

        foodImageResources.recycle();

        // Notify the adapter of the change.
        //fAdap.notifyDataSetChanged();

        return(foodData);
    }
}