package com.example.foodrescue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class  Make_Donations_Adapter extends RecyclerView.Adapter<Make_Donations_Adapter.ViewHolder> {
    Context context;
    ArrayList restIDs,restNames,restEmails,restDates;
    RecyclerView rvDonations;

    public Make_Donations_Adapter(Context context, ArrayList restIDs, ArrayList restNames, ArrayList restEmails,ArrayList restDates) {
        this.context = context;
        this.restIDs = restIDs;
        this.restNames = restNames;
        this.restEmails = restEmails;
        this.restDates = restDates;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView rowId;
        TextView rowName;
        TextView rowEmail;
        TextView rowDates;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowId=itemView.findViewById(R.id.rest_ID);
            rowName=itemView.findViewById(R.id.restaurant_Name);
            rowEmail=itemView.findViewById(R.id.rest_Email);
            rowDates=itemView.findViewById(R.id.rest_Date);
        }
    }


    @NonNull
    @Override
    public Make_Donations_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.single_donation_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Make_Donations_Adapter.ViewHolder holder, int position) {
        holder.rowId.setText("Restaurant ID: "+String.valueOf(restIDs.get(position)));
        holder.rowName.setText("Restaurant Name: "+String.valueOf(restNames.get(position)));
        holder.rowEmail.setText("Restaurant Email: "+String.valueOf(restEmails.get(position)));
        holder.rowDates.setText("Date: "+String.valueOf(restDates.get(position)));

    }

    @Override
    public int getItemCount() {
        return restIDs.size();
    }

}
