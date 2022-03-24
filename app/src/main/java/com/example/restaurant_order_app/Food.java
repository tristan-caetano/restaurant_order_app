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


    /**
     * Constructor for the Sport data model.
     *
     * @param title The name if the sport.
     */
    Food(String title, int imageResource, Context context) {
        this.title = title;
        this.imageResource = imageResource;
        fContext = context;
    }

    /**
     * Gets the title of the sport.
     *
     * @return The title of the sport.
     */
    String getFoodTitle() {
        return title;
    }

    int getAmount(){return amount;}
    void setAmount(int nAmt){
        SharedPreferences sharedPrefs = fContext.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor e = sharedPrefs.edit();
        amount = nAmt;
        e.putInt(title, amount);
        e.commit();

    }

    void init(){
        // Declaring shared prefs
        SharedPreferences sharedPrefs = fContext.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        amount = sharedPrefs.getInt(title, 0);
        System.out.println("INIT CALLED: " + fContext);
    }

    /**
     * Gets the info about the sport.
     *
     * @return The info about the sport.
     */

    public int getImageResource() {
        return imageResource;
    }

}
