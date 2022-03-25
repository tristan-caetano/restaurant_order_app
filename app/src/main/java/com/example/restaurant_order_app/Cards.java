package com.example.restaurant_order_app;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Cards extends AppCompatActivity {

    // Global variables
    private RecyclerView mRecyclerView;
    private ArrayList<Food> foodData;
    private FoodAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Initializing the recyclerview
        mRecyclerView = findViewById(R.id.menuRecycler);

        // Setting the layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the list that contains the data from xml
        foodData = new ArrayList<>();

        // Initializing the adapter
        mAdapter = new FoodAdapter(this, foodData);

        // Setting the adapter to the recyclerview
        mRecyclerView.setAdapter(mAdapter);

        // Getting the data
        initializeData();
    }

    // Method that gets the xml data, returns the list, and notifies the adapter
    public ArrayList<Food> initializeData() {

        // Getting the food names and images from the XML
        String[] foodList = getResources()
                .getStringArray(R.array.food_titles);
        TypedArray foodImageResources =
                getResources().obtainTypedArray(R.array.food_images);

        // Cleaning the array to prevent multiple array items
        foodData.clear();

        // For loop that saves each item from the xml to the list
        for(int i=0;i<foodList.length;i++){
            foodData.add(new Food(foodList[i],foodImageResources.getResourceId(i,0), this));
        }

        foodImageResources.recycle();

        // Notifying the adapter
        mAdapter.notifyDataSetChanged();

        return(foodData);
    }
}

