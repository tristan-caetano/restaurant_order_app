package com.example.restaurant_order_app;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

public class Food extends AppCompatActivity {

    // Member variables representing the title and information about the sport.
    private String title;
    private final int imageResource;
    private int amount;
    private Context fContext;
    public static final String SHARED_PREFS = "sharedPrefs";

    // Food constructor
    Food(String title, int imageResource, Context context) {
        this.title = title;
        this.imageResource = imageResource;
        fContext = context;
    }

    // Getter for food title
    String getFoodTitle() {
        return title;
    }

    // Getter and setter for
    int getAmount(){return amount;}
    void setAmount(int nAmt){
        SharedPreferences sharedPrefs = fContext.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor e = sharedPrefs.edit();
        amount = nAmt;
        e.putInt(title, amount);
        e.commit();
    }

    // Initiating the amount of fooditems for the menu
    void init(){
        // Declaring shared prefs
        SharedPreferences sharedPrefs = fContext.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        amount = sharedPrefs.getInt(title, 0);
    }

    public int getImageResource() {
        return imageResource;
    }

}
