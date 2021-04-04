package com.example.foodrescue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Confirmation_List_Adapter extends RecyclerView.Adapter<Confirmation_List_Adapter.ViewHolder> {
    Context context;
    ArrayList date_donated,date_confirmed,weight,rest_name,total_plates;

    public Confirmation_List_Adapter(Context context, ArrayList date_donated, ArrayList date_confirmed, ArrayList weight, ArrayList rest_name, ArrayList total_plates) {
        this.context = context;
        this.date_donated = date_donated;
        this.date_confirmed = date_confirmed;
        this.weight = weight;
        this.rest_name = rest_name;
        this.total_plates = total_plates;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView rowName;
        TextView rowDateDonated;
        TextView rowConfirmDate;
        TextView rowWeight;
        TextView rowPlates;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowName=itemView.findViewById(R.id.restaurant_name);
            rowConfirmDate=itemView.findViewById(R.id.confirmation_date);
            rowWeight=itemView.findViewById(R.id.weight);
            rowDateDonated=itemView.findViewById(R.id.donation_date);
            rowPlates=itemView.findViewById(R.id.plates);

        }
    }
    @NonNull
    @Override
    public Confirmation_List_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_confirm_item,parent,false);
        return new Confirmation_List_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Confirmation_List_Adapter.ViewHolder holder, int position) {
        holder.rowName.setText("Food Name: "+String.valueOf(rest_name.get(position)));
        holder.rowPlates.setText("Plates: "+String.valueOf(total_plates.get(position)));
        holder.rowWeight.setText("Weight: "+String.valueOf(weight.get(position))+"Kgs");
        holder.rowConfirmDate.setText("Confirmation Date: "+String.valueOf(date_confirmed.get(position)));
        holder.rowDateDonated.setText("Donation Date: "+String.valueOf(date_donated.get(position)));
    }

    @Override
    public int getItemCount() {
        return  date_donated.size();
    }
}
