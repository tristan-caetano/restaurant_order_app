package com.example.restaurant_order_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class menuActivity extends AppCompatActivity {

    // Declaring global values
    public static final String SHARED_PREFS = "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button checkout = findViewById(R.id.checkout);
        RecyclerView menuRecycle = findViewById(R.id.menuRecycler);
        Cards cards = new Cards();

        FoodAdapter recycAdap = new FoodAdapter(this, initializeData());
        menuRecycle.setAdapter(recycAdap);
        menuRecycle.setLayoutManager(new LinearLayoutManager(this));

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

    public ArrayList<Food> initializeData() {

        ArrayList<Food> foodData =  new ArrayList<>();

        // Get the resources from the XML file.
        String[] foodList = getResources()
                .getStringArray(R.array.food_titles);
        TypedArray foodImageResources =
                getResources().obtainTypedArray(R.array.food_images);

        // Clear the existing data (to avoid duplication).
        foodData.clear();

        // Create the ArrayList of Sports objects with titles and
        // information about each sport.
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