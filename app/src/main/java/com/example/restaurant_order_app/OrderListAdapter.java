package com.example.restaurant_order_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.MyViewHolder> {

    // Global Variables
    private Context context;
    private Activity activity;
    private ArrayList id, name, email, phone, date, card, total;

    // Setting constructor
    OrderListAdapter(Context context,
                     Activity activity,
                     ArrayList id,
                     ArrayList name,
                     ArrayList email,
                     ArrayList phone,
                     ArrayList date,
                     ArrayList card,
                     ArrayList total){
        this.activity = activity;
        this.context = context;
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.date = date;
        this.card = card;
        this.total = total;
    }

    // Setting up inflator
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.order_list_layout, parent, false);
        return new MyViewHolder(view);
    }

    // Assigning text fields and buttons for each row
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.idText.setText(String.valueOf(id.get(position)));
        holder.nameText.setText(String.valueOf(name.get(position)));
        holder.emailText.setText(String.valueOf(email.get(position)));
        holder.phoneText.setText(String.valueOf(phone.get(position)));
        holder.dateText.setText(String.valueOf(date.get(position)));
        holder.cardText.setText(String.valueOf(card.get(position)));
        holder.totalText.setText(String.valueOf(total.get(position)));

        // Button for deleting row in db
        holder.delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String delID = (holder.idText).getText().toString().trim();
                DatabaseInterfacer dbi = new DatabaseInterfacer(context);
                dbi.deleteRow(delID);
                Intent menuIntent = new Intent(context, order_list.class);
                activity.startActivity(menuIntent);
            }
        });
    }

    // Returning how many rows in db
    @Override
    public int getItemCount() {
        return id.size();
    }

    // ViewHolder class
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView idText, nameText, emailText, phoneText, dateText, cardText, totalText;
        Button delButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            idText = itemView.findViewById(R.id.id);
            nameText = itemView.findViewById(R.id.name);
            emailText = itemView.findViewById(R.id.email);
            phoneText = itemView.findViewById(R.id.phone);
            dateText = itemView.findViewById(R.id.date);
            cardText = itemView.findViewById(R.id.card);
            totalText = itemView.findViewById(R.id.total);
            delButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
