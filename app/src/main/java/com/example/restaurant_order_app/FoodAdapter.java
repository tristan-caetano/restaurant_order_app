package com.example.restaurant_order_app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    // Global values
    private ArrayList<Food> foodData;
    private Context mContext;

    // Food adapter constructor
    FoodAdapter(Context context, ArrayList<Food> foodData) {
        this.foodData = foodData;
        this.mContext = context;
    }

    // Inflating adapter
    @Override
    public FoodAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.food_card, parent, false));
    }

    // Binder
    @Override
    public void onBindViewHolder(FoodAdapter.ViewHolder holder,
                                 int position) {
        // Get current Food
        Food currentFood = foodData.get(position);

        // Populating textviews with data
        holder.bindTo(currentFood);

    }

    // Getting item count per menu item
    @Override
    public int getItemCount() {
        return foodData.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Member Variables for the TextViews
        private TextView mTitleText;
        private ImageView foodImage;
        private Button plusButton;
        private Button minusButton;
        private EditText editNum;
        private Food currentFood;


        ViewHolder(View itemView) {
            super(itemView);

            // Initialize the views.
            mTitleText = itemView.findViewById(R.id.foodText);
            foodImage = itemView.findViewById(R.id.foodImage);
            plusButton = itemView.findViewById(R.id.plusButton);
            minusButton = itemView.findViewById(R.id.minusButton);
            editNum = itemView.findViewById(R.id.editNum);

            // Plus button for quantity
            plusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int foodAmt = currentFood.getAmount();
                    foodAmt ++;
                    currentFood.setAmount(foodAmt);
                    editNum.setText(Integer.toString(foodAmt), TextView.BufferType.EDITABLE);
                    System.out.println("This is the amt: " + foodAmt);

                }
            });

            // Minus button for quantity
            minusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int foodAmt = currentFood.getAmount();

                    if(foodAmt > 0) {
                        foodAmt--;
                        currentFood.setAmount(foodAmt);
                        editNum.setText(Integer.toString(foodAmt), TextView.BufferType.EDITABLE);
                    }
                }
            });
        }

        //Blank onclick
        @Override
        public void onClick(View view) {}

        void bindTo(Food currentFoodF){
            // Populate the textviews with data.
            currentFood = currentFoodF;
            currentFood.init();

            int amount = currentFood.getAmount();

            Glide.with(mContext).load(currentFood.getImageResource()).into(foodImage);
            mTitleText.setText(currentFood.getFoodTitle());
            editNum.setText(Integer.toString(amount), TextView.BufferType.EDITABLE);


        }
    }
}