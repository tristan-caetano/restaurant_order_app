package com.example.restaurant_order_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restaurant_order_app.WordListAdapter;

import java.text.DecimalFormat;
import java.util.LinkedList;

public class checkoutActivity extends AppCompatActivity {

    // Declaring global values
    public static final String SHARED_PREFS = "sharedPrefs";
    public double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        // Declaring buttons
        Button orderEditButton = (Button) findViewById(R.id.orderEditButton);
        Button submitButton = (Button) findViewById(R.id.submitButton);

        // Declaring check boxes
        CheckBox tip15Check = (CheckBox) findViewById(R.id.tip15Check);
        CheckBox tip20Check = (CheckBox) findViewById(R.id.tip20Check);

        // Declaring text views for summary
        RecyclerView mRecyclerView = findViewById(R.id.netRecyclerView);
        TextView finalSummaryView = (TextView) findViewById(R.id.finalSummaryView);

        // Declaring edit text field for tip
        EditText tipEdit = (EditText) findViewById(R.id.tipEdit);

        WordListAdapter recycAdap = new WordListAdapter(this, fillNetView());
        mRecyclerView.setAdapter(recycAdap);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        finalSummaryView.setText(doTip(tip15Check, tip20Check, tipEdit));

        // Button that brings you back to the menu
        orderEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menuIntent = new Intent(getApplicationContext(), menuActivity.class);
                startActivity(menuIntent);
            }
        });

        // Button that brings you back to the menu
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menuIntent = new Intent(getApplicationContext(), payActivity.class);
                startActivity(menuIntent);
            }
        });

        // 15% Tip Checkbox
        tip15Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(tip15Check.isChecked()){
                    tip20Check.setChecked(false);
                    tipEdit.setText("");
                }

                finalSummaryView.setText(doTip(tip15Check, tip20Check, tipEdit));

            }
        });

        // 20% Tip Checkbox
        tip20Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(tip20Check.isChecked()){
                    tip15Check.setChecked(false);
                    tipEdit.setText("");
                }

                finalSummaryView.setText(doTip(tip15Check, tip20Check, tipEdit));

            }
        });

        // Edit text tip
        tipEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(tipEdit.getText().length() != 0){
                    tip15Check.setChecked(false);
                    tip20Check.setChecked(false);
                }

                finalSummaryView.setText(doTip(tip15Check, tip20Check, tipEdit));
            }
        });
    }

    // Calculating menu prices for net view
    public LinkedList<String> fillNetView(){

        // Creating shared prefs
        SharedPreferences sharedPrefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        LinkedList<String> netCostList = new LinkedList<>();
        int tempAmt = 0;
        float tempSubAmt = 0;

        String[] foodList = getResources()
                .getStringArray(R.array.food_titles);

        String[] foodCost = getResources()
                .getStringArray(R.array.food_cost);

        for(int i=0;i<foodList.length;i++){
            tempAmt = sharedPrefs.getInt(foodList[i], 0);
            tempSubAmt = tempAmt * Float.parseFloat(foodCost[i]);
            total += tempSubAmt;
            netCostList.addLast(foodList[i] + " x " + tempAmt + "     $" + tempSubAmt);
        }

        return netCostList;
    }

    // Calculating tip for final view
    public String doTip(CheckBox tip15, CheckBox tip20, EditText customTip){

        // Declaring variables
        String finalSummary = "";
        double cTip = 0;
        String cTipS = "0";
        final DecimalFormat df = new DecimalFormat("#.##");

        // If a custom tip is entered
        if(customTip.getText().length() != 0) {

            cTip = (Integer.valueOf(customTip.getText().toString()) * total) / 100;
            cTipS = df.format(cTip);

            finalSummary += ("Tip " + Integer.valueOf(customTip.getText().toString()) + "%                                     $" + cTipS + "\n\n");

        }else{

            // If the 15% tip is checked
            if (tip15.isChecked()) {

                cTip = (15 * total) / 100;
                cTipS = df.format(cTip);

                finalSummary += ("Tip 15%                                  $" + cTipS + "\n\n");
            }

            // If the 20% tip is checked
            if (tip20.isChecked()) {

                cTip = (20 * total) / 100;
                cTipS = df.format(cTip);

                finalSummary += ("Tip 20%                                  $" + cTipS + "\n\n");
            }
        }

        // Calculating Final Total
        cTipS = df.format(cTip + total);
        finalSummary += ("Final Total:                             $" + (cTipS));

        return finalSummary;
    }
}