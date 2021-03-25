package com.example.foodrescue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class  Dishes_Fetched_Adapter extends RecyclerView.Adapter<Dishes_Fetched_Adapter.ViewHolder> {
    Context context;
    List<Dishes_Fetched> dishesList;
    RecyclerView rvDishes;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView rowId;
        TextView rowName;
        TextView rowCategory;
        TextView rowCuisine;
        TextView rowWeight;
        TextView rowPlates;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowId=itemView.findViewById(R.id.item_id);
            rowName=itemView.findViewById(R.id.item_name);
            rowCategory=itemView.findViewById(R.id.item_category);
            rowCuisine=itemView.findViewById(R.id.item_cuisine);
            rowWeight=itemView.findViewById(R.id.item_weight);
            rowPlates=itemView.findViewById(R.id.item_plates);
        }
    }

    public Dishes_Fetched_Adapter(List<Dishes_Fetched> dishesList){
        this.dishesList=dishesList;
    }

    @NonNull
    @Override
    public Dishes_Fetched_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Dishes_Fetched_Adapter.ViewHolder holder, int position) {
        holder.rowId.setText("Dish Number: "+dishesList.get(position).getId());
        holder.rowName.setText("Dish Name: "+dishesList.get(position).getName());
        holder.rowCuisine.setText("Cuisine Type: " +dishesList.get(position).getCuisine());
        holder.rowCategory.setText("Food Category: " +dishesList.get(position).getCategory());
        holder.rowPlates.setText("Number of Plates: "+dishesList.get(position).getPlates()+" Plates");
        holder.rowWeight.setText("Weight: "+dishesList.get(position).getWeight()+" Kg");

    }

    @Override
    public int getItemCount() {
        return dishesList.size();
    }

}
