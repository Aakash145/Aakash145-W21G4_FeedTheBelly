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

public class  Make_Donations_Adapter extends RecyclerView.Adapter<Make_Donations_Adapter.ViewHolder> {
    Context context;
    ArrayList restIDs,restNames,restEmails,restDates,restAddress;
    RecyclerView rvDonations;


    public Make_Donations_Adapter(Context context, ArrayList restIDs, ArrayList restNames, ArrayList restEmails,ArrayList restDates,ArrayList restAddress) {
        this.context = context;
        this.restIDs = restIDs;
        this.restNames = restNames;
        this.restEmails = restEmails;
        this.restDates = restDates;
        this.restAddress=restAddress;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView rowId;
        TextView rowName;
        TextView rowEmail;
        TextView rowDates;
        Button details;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowId=itemView.findViewById(R.id.rest_ID);
            rowName=itemView.findViewById(R.id.restaurant_Name);
            rowEmail=itemView.findViewById(R.id.rest_Email);
            rowDates=itemView.findViewById(R.id.rest_Date);
            details=itemView.findViewById(R.id.btn_Details);
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

        holder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,GoogleMapsActivity.class);
                intent.putExtra("EMAIL",restEmails.get(position).toString());
                intent.putExtra("ADDRESS",restAddress.get(position).toString());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return restIDs.size();
    }

}
