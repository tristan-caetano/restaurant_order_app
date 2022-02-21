package com.example.restaurant_order_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class menuActivity extends AppCompatActivity {

    // Declaring global values
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String BBQ = "bbq";
    public static final String BUFFALO = "buffalo";
    public static final String PLAIN = "plain";
    private int buffalo, bbq, plain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Creating shared prefs
        SharedPreferences sharedPrefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        bbq = sharedPrefs.getInt(BBQ, 0);
        buffalo = sharedPrefs.getInt(BUFFALO, 0);
        plain = sharedPrefs.getInt(PLAIN, 0);

        // Declaring plus and minus buttons for food quantities
        Button bbqPlus = (Button) findViewById(R.id.bbqPlus);
        Button buffPlus = (Button) findViewById(R.id.buffPlus);
        Button plainPlus = (Button) findViewById(R.id.plainPlus);
        Button bbqMinus = (Button) findViewById(R.id.bbqMinus);
        Button buffMinus = (Button) findViewById(R.id.buffMinus);
        Button plainMinus = (Button) findViewById(R.id.plainMinus);

        // Declaring edit text fields for quantity
        EditText bbqEdit = (EditText) findViewById(R.id.bbqEdit);
        EditText buffEdit = (EditText) findViewById(R.id.buffEdit);
        EditText plainEdit = (EditText) findViewById(R.id.plainEdit);

        // Setting default vals in set text
        bbqEdit.setText("0", TextView.BufferType.EDITABLE);
        buffEdit.setText("0", TextView.BufferType.EDITABLE);
        plainEdit.setText("0", TextView.BufferType.EDITABLE);

        // Floating checkout button
        FloatingActionButton checkout = (FloatingActionButton) findViewById(R.id.floatingCheckout);

        // Increase how many bbq wings you want
        bbqPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextField(bbqEdit, 1);
            }
        });

        // Decrease how many bbq wings you want
        bbqMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextField(bbqEdit, -1);
            }
        });

        // Increase how many buffalo wings you want
        buffPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextField(buffEdit, 1);
            }
        });

        // Decrease how many buffalo wings you want
        buffMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextField(buffEdit, -1);
            }
        });

        // Increase how many plain wings you want
        plainPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextField(plainEdit, 1);
            }
        });

        // Decrease how many plain wings you want
        plainMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextField(plainEdit, -1);
            }
        });

        // Goes to checkout screen with values
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkoutValidation(bbqEdit, buffEdit, plainEdit);
            }
        });

    }

    // Method that controls how the order values are changed
    void editTextField(EditText wingEdit, int change){

        // Getting the current value in the edit text field
        int currentVal = Integer.valueOf(wingEdit.getText().toString());

        // If the minus button is clicked while the current value is greater than 0, subtract it
        if((currentVal > 0) && (change < 0)){
            currentVal += change;

        //If the plus button is clicked, add it
        } else if(change > 0){
            currentVal += change;
        }

        // Setting integer back to string and putting it back into the edit text
        wingEdit.setText(Integer.toString(currentVal), TextView.BufferType.EDITABLE);
    }

    // Making sure the user is ready for checkout
    void checkoutValidation(EditText bbqE, EditText buffE, EditText plainE){

        // Declaring shared prefs
        SharedPreferences sharedPrefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();

        // Declaring toast
        Toast toast = null;

        // Getting wing values from edit texts
        bbq = Integer.valueOf(bbqE.getText().toString());
        buffalo = Integer.valueOf(buffE.getText().toString());
        plain = Integer.valueOf(plainE.getText().toString());

        // Making sure the user selected wings
        if(bbq > 0 || buffalo > 0 || plain > 0){

            // Saving values
            editor.putInt(BBQ, bbq);
            editor.putInt(BUFFALO, buffalo);
            editor.putInt(PLAIN, plain);
            editor.commit();

            // Going to checkout screen
            Intent checkoutIntent = new Intent(getApplicationContext(), checkoutActivity.class);
            startActivity(checkoutIntent);

        }else {
            toast = Toast.makeText(getApplicationContext(), "Your cart is empty.", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}