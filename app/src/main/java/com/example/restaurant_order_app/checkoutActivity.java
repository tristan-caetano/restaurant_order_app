package com.example.restaurant_order_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

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
        CheckBox sourceCheck = (CheckBox) findViewById(R.id.sourceCheck);
        CheckBox tip15Check = (CheckBox) findViewById(R.id.tip15Check);
        CheckBox tip20Check = (CheckBox) findViewById(R.id.tip20Check);

        // Declaring text views for summary
        TextView netSummaryView = (TextView) findViewById(R.id.netSummaryView);
        TextView finalSummaryView = (TextView) findViewById(R.id.finalSummaryView);

        // Declaring edit text field for tip
        EditText tipEdit = (EditText) findViewById(R.id.tipEdit);

        String netSummary = fillNetView();

        netSummaryView.setText(netSummary);
    }

    public String fillNetView(){

        String returnView = "";
        total = ((9.99 * plain) + (12.99 * buffalo) + (15.99 * bbq));

        System.out.println("BBQ: " + bbq + "\n");
        System.out.println("Buffalo: " + buffalo + "\n");
        System.out.println("Plain: " + plain + "\n");

        if(bbq > 0){
            returnView += ("BBQ Wings x " + bbq + "                          $" + (15.99 * bbq) + "\n\n");
        }

        if(buffalo > 0){
            returnView += ("Buffalo Wings x " + buffalo + "                     $" + (12.99 * buffalo) + "\n\n");
        }

        if(plain > 0){
            returnView += ("Plain Wings x " + plain + "                         $" + (9.99 * plain) + "\n\n");
        }

        returnView += "_______________________________________\n";
        returnView += "Pre Tip Total:                             $" + ((9.99 * plain) + (12.99 * buffalo) + (15.99 * bbq));

        return returnView;
    }
}