package com.example.restaurant_order_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class menuActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String BBQ = "bbq";
    public static final String BUFFALO = "buffalo";
    public static final String PLAIN = "plain";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

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
}