package com.example.restaurant_order_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class checkoutActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String BBQ = "bbq";
    public static final String BUFFALO = "buffalo";
    public static final String PLAIN = "plain";
    public int buffalo, bbq, plain;
    public double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        // Creating shared prefs
        SharedPreferences sharedPrefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        bbq = sharedPrefs.getInt(BBQ, 0);
        buffalo = sharedPrefs.getInt(BUFFALO, 0);
        plain = sharedPrefs.getInt(PLAIN, 0);

        // Declaring buttons
        Button orderEditButton = (Button) findViewById(R.id.orderEditButton);
        Button submitButton = (Button) findViewById(R.id.submitButton);

        // Declaring check boxes
        CheckBox tip15Check = (CheckBox) findViewById(R.id.tip15Check);
        CheckBox tip20Check = (CheckBox) findViewById(R.id.tip20Check);

        // Declaring text views for summary
        TextView netSummaryView = (TextView) findViewById(R.id.netSummaryView);
        TextView finalSummaryView = (TextView) findViewById(R.id.finalSummaryView);

        // Declaring edit text field for tip
        EditText tipEdit = (EditText) findViewById(R.id.tipEdit);

        String netSummary = fillNetView();

        netSummaryView.setText(netSummary);

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
                Toast toast = Toast.makeText(getApplicationContext(), "Your order has been submitted!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        // 15% Tip Checkbox
        tip15Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(tip15Check.isChecked()){
                    tip20Check.setEnabled(false);
                }else{
                    tip20Check.setEnabled(true);
                }

                finalSummaryView.setText(doTip(tip15Check, tip20Check, tipEdit));

            }
        });

        // 20% Tip Checkbox
        tip20Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(tip20Check.isChecked()){
                    tip15Check.setEnabled(false);
                }else{
                    tip15Check.setEnabled(true);
                }

                finalSummaryView.setText(doTip(tip15Check, tip20Check, tipEdit));

            }
        });

        // Edit text tip
        tipEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(tipEdit.getText().length() != 0){
                    tip15Check.setEnabled(false);
                    tip20Check.setEnabled(false);
                }else{
                    tip15Check.setEnabled(true);
                    tip20Check.setEnabled(true);
                }

                finalSummaryView.setText(doTip(tip15Check, tip20Check, tipEdit));
            }
        });
    }

    // Calculating menu prices for net view
    public String fillNetView(){

        // Declaring values
        String returnView = "";
        total = ((9.99 * plain) + (12.99 * buffalo) + (15.99 * bbq));

        // Only displaying bbq wings
        if(bbq > 0){
            returnView += ("BBQ Wings x " + bbq + "                          $" + (15.99 * bbq) + "\n\n");
        }

        // Only displaying buffalo wings
        if(buffalo > 0){
            returnView += ("Buffalo Wings x " + buffalo + "                     $" + (12.99 * buffalo) + "\n\n");
        }

        // Only displaying plain wings
        if(plain > 0){
            returnView += ("Plain Wings x " + plain + "                         $" + (9.99 * plain) + "\n\n");
        }

        // Pre tip total
        returnView += "_______________________________________\n";
        returnView += "Pre Tip Total:                             $" + ((9.99 * plain) + (12.99 * buffalo) + (15.99 * bbq));

        return returnView;
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