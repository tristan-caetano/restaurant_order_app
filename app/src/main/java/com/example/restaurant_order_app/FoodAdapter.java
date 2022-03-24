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

    // Member variables.
    private ArrayList<Food> foodData;
    private Context mContext;

    /**
     * Constructor that passes in the sports data and the context.
     *
     * @param foodData ArrayList containing the sports data.
     * @param context Context of the application.
     */
    FoodAdapter(Context context, ArrayList<Food> foodData) {
        this.foodData = foodData;
        this.mContext = context;
    }

    /**
     * Required method for creating the viewholder objects.
     *
     * @param parent The ViewGroup into which the new View will be added
     *               after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return The newly created ViewHolder.
     */
    @Override
    public FoodAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {


        return new ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.food_card, parent, false));
    }

    /**
     * Required method that binds the data to the viewholder.
     *
     * @param holder The viewholder into which the data should be put.
     * @param position The adapter position.
     */
    @Override
    public void onBindViewHolder(FoodAdapter.ViewHolder holder,
                                 int position) {
        // Get current sport.
        Food currentFood = foodData.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentFood);

    }

    /**
     * Required method for determining the size of the data set.
     *
     * @return Size of the data set.
     */
    @Override
    public int getItemCount() {
        return foodData.size();
    }


    /**
     * ViewHolder class that represents each row of data in the RecyclerView.
     */
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Member Variables for the TextViews
        private TextView mTitleText;
        private ImageView foodImage;
        private Button plusButton;
        private Button minusButton;
        private EditText editNum;
        private Food currentFood;

        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         *
         * @param itemView The rootview of the list_item.xml layout file.
         */
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

        @Override
        public void onClick(View view) {


        }

        void bindTo(Food currentFoodF){
            // Populate the textviews with data.
            currentFood = currentFoodF;
            currentFood.init();
            int amount = currentFood.getAmount();
            Glide.with(mContext).load(currentFood.getImageResource()).into(foodImage);
            mTitleText.setText(currentFood.getFoodTitle());
            editNum.setText(Integer.toString(amount), TextView.BufferType.EDITABLE);
            System.out.println("CONTEXT: " + mContext);

        }
    }
}