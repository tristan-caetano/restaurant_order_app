package com.example.restaurant_order_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.TimeZone;

public class checkoutActivity extends AppCompatActivity {

    // Declaring global values
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String FINAL_TOTAL = "final_total";
    public float total;

    // Global values for date comparison
    private Date date;
    private Date dCompOne;
    private Date dCompTwo;
    private String sCompOne = "10:00";
    private String sCompTwo = "22:00";
    SimpleDateFormat inParser = new SimpleDateFormat("HH:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        // Declaring check boxes
        CheckBox tip15Check = (CheckBox) findViewById(R.id.tip15Check);
        CheckBox tip20Check = (CheckBox) findViewById(R.id.tip20Check);

        // Declaring button
        Button checkoutButton = findViewById(R.id.checkoutButton);

        // Declaring recycler
        RecyclerView mRecyclerView = findViewById(R.id.netRecyclerView);

        // Declaring text views for summary
        TextView finalSummaryView = (TextView) findViewById(R.id.finalSummaryView);

        // Declaring and setting toolbar
        Toolbar tempToolBar = findViewById(R.id.prevCToolbar);
        setSupportActionBar(tempToolBar);

        // Declaring edit text field for tip
        EditText tipEdit = (EditText) findViewById(R.id.tipEdit);

        // Declaring adapter for recyclerview
        WordListAdapter recycAdap = new WordListAdapter(this, fillNetView());
        mRecyclerView.setAdapter(recycAdap);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Setting the summary view
        finalSummaryView.setText(doTip(tip15Check, tip20Check, tipEdit));

        // Button that bring you to the pay screen
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isOpen();
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
                return true;

            case R.id.order_list:
                startActivity(new Intent(this, order_list.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Calculating menu prices for net view
    public LinkedList<String> fillNetView(){

        // Creating shared prefs
        SharedPreferences sharedPrefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        // Declaring list to contain food titles and cost
        LinkedList<String> netCostList = new LinkedList<>();

        // Filling recycler view with menu data
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

        // Shared Prefs for final total
        SharedPreferences sharedPrefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPrefs.edit();

        // Declaring variables
        String finalSummary = "";
        float cTip = 0;
        String cTipS = "0";
        float finalTotal = 0;
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
        finalTotal = cTip + total;
        cTipS = df.format(finalTotal);
        finalSummary += ("Final Total:                             $" + (cTipS));

        // Saving final total
        ed.putFloat(FINAL_TOTAL, finalTotal);

        return finalSummary;
    }

    public void isOpen(){

        // Declaring variables
        Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT-4:00"));
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        Toast dateToast = null;

        // Try catch in case the time couldnt be parsed
        try {
            date = inParser.parse(hour + ":" + minute);
            dCompOne = inParser.parse(sCompOne);
            dCompTwo = inParser.parse(sCompTwo);
        } catch (ParseException e) {
            System.err.println("Dates could not be parsed.");
        }

        // Showing toasts relative to current time
        if(dCompOne.before(date) && dCompTwo.after(date)){
            Intent menuIntent = new Intent(getApplicationContext(), payActivity.class);
            startActivity(menuIntent);
        }else{
            dateToast = Toast.makeText(getApplicationContext(), "Error: We are currently closed. We open at 10 AM.", Toast.LENGTH_SHORT);
            dateToast.show();
        }
    }
}