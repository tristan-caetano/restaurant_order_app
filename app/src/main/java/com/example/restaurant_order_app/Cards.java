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

    // Member variables.
    private RecyclerView mRecyclerView;
    private ArrayList<Food> foodData;
    private FoodAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Initialize the RecyclerView.
        mRecyclerView = findViewById(R.id.menuRecycler);

        // Set the Layout Manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the ArrayList that will contain the data.
        foodData = new ArrayList<>();

        // Initialize the adapter and set it to the RecyclerView.
        mAdapter = new FoodAdapter(this, foodData);
        mRecyclerView.setAdapter(mAdapter);

        // Get the data.
        initializeData();


    }

    /**
     * Initialize the sports data from resources.
     */
    private void initializeData() {
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
        mAdapter.notifyDataSetChanged();
    }

}

