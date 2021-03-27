package com.example.foodrescue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Make_Donations_Adapter extends RecyclerView.Adapter<Make_Donations_Adapter.ViewHolder> {
    Context context;
    List<Make_Donation> donationList;
    RecyclerView rvMakeDonations;
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView rowId;
        TextView rowName;
        TextView rowWeight;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowName=itemView.findViewById(R.id.dish_items);
            rowWeight=itemView.findViewById(R.id.item_weight);
        }
    }
    @NonNull
    @Override
    public Make_Donations_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_donation_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Make_Donations_Adapter.ViewHolder holder, int position) {
//        holder.rowName.setText("Dish Names: "+donationList.get(position).getdName());
      //  holder.rowWeight.setText("Total Weight: "+donationList.get(position).tWeight+" Kg");
    }

    @Override
    public int getItemCount() {
        return donationList.size();
    }
}
