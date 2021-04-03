package com.example.foodrescue;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class  List_of_Donated_Items_Adapter extends RecyclerView.Adapter<List_of_Donated_Items_Adapter.ViewHolder> {
    Context context;
    ArrayList foodName,plates,weight,category,expiry,cuisine;
    String addresses,date,email;
    Button next;
    RecyclerView rvDishes;
    //foodName, weight, plates,foodCategory,foodCuisine,foodExpiry, address2, email, exp
    public List_of_Donated_Items_Adapter(Context context, ArrayList foodName, ArrayList plates, ArrayList weight, ArrayList category, ArrayList expiry, ArrayList cuisine, String addresses, String date, String email) {
        this.context = context;
        this.foodName = foodName;
        this.plates = plates;
        this.weight = weight;
        this.category = category;
        this.expiry = expiry;
        this.cuisine = cuisine;
        this.addresses = addresses;
        this.date = date;
        this.email = email;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView rowName;
        TextView rowPlates;
        TextView rowWeight;
        TextView rowExpiry;
        TextView rowCuisine;
        TextView rowCategory;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowName=itemView.findViewById(R.id.food_name);
            rowPlates=itemView.findViewById(R.id.food_plates);
            rowWeight=itemView.findViewById(R.id.food_weight);
            rowExpiry=itemView.findViewById(R.id.food_expiry);
            rowCuisine=itemView.findViewById(R.id.food_cuisine);
            rowCategory=itemView.findViewById(R.id.food_category);

        }
    }

    @NonNull
    @Override
    public List_of_Donated_Items_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.single_dish_donated,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull List_of_Donated_Items_Adapter.ViewHolder holder, int position) {
        holder.rowName.setText("Food Name: "+String.valueOf(foodName.get(position)));
        holder.rowPlates.setText("Plates: "+String.valueOf(plates.get(position)));
        holder.rowWeight.setText("Weight: "+String.valueOf(weight.get(position))+"Kgs");
        holder.rowCategory.setText("Food Category: "+String.valueOf(category.get(position)));
        holder.rowCuisine.setText("Food Cuisine: "+String.valueOf(expiry.get(position)));
        holder.rowExpiry.setText("Expiry in: "+String.valueOf(cuisine.get(position))+" days");

    }

    @Override
    public int getItemCount() {
        return foodName.size();
    }

}
