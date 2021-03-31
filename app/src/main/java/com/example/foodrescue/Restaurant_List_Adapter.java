package com.example.foodrescue;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class  Restaurant_List_Adapter extends RecyclerView.Adapter<Restaurant_List_Adapter.ViewHolder> {
    Context context;
    ArrayList restEmails,restNames,restPhones,restIDs,restAddress,restPostal;
    RecyclerView rvDonations;
    public Restaurant_List_Adapter(Context context, ArrayList restEmails, ArrayList restNames, ArrayList restPhones, ArrayList restIDs, ArrayList restAddress, ArrayList restPostal) {
        this.context = context;
        this.restEmails = restEmails;
        this.restNames = restNames;
        this.restPhones = restPhones;
        this.restIDs = restIDs;
        this.restAddress = restAddress;
        this.restPostal = restPostal;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView rowId;
        TextView rowName;
        TextView rowEmail;
        TextView rowAddress;
        TextView rowPhone;
        TextView rowPostal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowId=itemView.findViewById(R.id.rest_ID);
            rowName=itemView.findViewById(R.id.rest_name);
            rowEmail=itemView.findViewById(R.id.rest_Email);
            rowAddress=itemView.findViewById(R.id.rest_Address);
            rowPhone=itemView.findViewById(R.id.rest_Phone);
            rowPostal=itemView.findViewById(R.id.rest_Postal);
        }

    }


    @NonNull
    @Override
    public Restaurant_List_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.single_restaurant,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Restaurant_List_Adapter.ViewHolder holder, int position) {
        holder.rowId.setText("Restaurant ID: "+String.valueOf(restIDs.get(position)));
        holder.rowName.setText("Name: "+String.valueOf(restNames.get(position)));
        holder.rowAddress.setText("Address: "+String.valueOf(restAddress.get(position)));
        holder.rowEmail.setText("Email: "+String.valueOf(restEmails.get(position)));
        holder.rowPhone.setText("Phone: "+String.valueOf(restPostal.get(position)));
        holder.rowPostal.setText("Postal Code: "+String.valueOf(restPhones.get(position)));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,recyclerView_NGO.class);
                intent.putExtra("EMAIL",String.valueOf(restEmails.get(position)));
                intent.putExtra("ADDRESS",String.valueOf(restAddress.get(position)));
                intent.putExtra("RESTAURANTNAME",String.valueOf(restNames.get(position)));
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
