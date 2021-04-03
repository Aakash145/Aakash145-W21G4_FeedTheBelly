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
    ArrayList restDate,restWeight,restName;
    String restAddress,restEmails;
    RecyclerView rvDonations;

    public Make_Donations_Adapter(Context context, ArrayList restDate, ArrayList restWeight, ArrayList restName,String restAddress, String restEmails) {
        this.context = context;
        this.restDate = restDate;
        this.restName=restName;
        this.restWeight = restWeight;
        this.restAddress = restAddress;
        this.restEmails = restEmails;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView rowDate;
        TextView rowWeight;
        TextView rowRest;
        Button details;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowDate=itemView.findViewById(R.id.dish_date);
            rowWeight=itemView.findViewById(R.id.dish_weight);
            rowRest=itemView.findViewById(R.id.dish_rest);
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
        holder.rowDate.setText("Date of Donation: "+String.valueOf(restDate.get(position)));
        holder.rowWeight.setText("Total Weight of Food:: "+String.valueOf(restWeight.get(position))+"Kgs");
        holder.rowRest.setText("Restaurant Name: "+String.valueOf(restName.get(position)));

        holder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,List_Of_Donated_Items.class);
           //     Toast.makeText(context,restEmails+","+restExp.get(position),Toast.LENGTH_LONG).show();
                intent.putExtra("EMAIL",restEmails);
                intent.putExtra("ADDRESS",restAddress);
              // intent.putExtra("NAME",restName.get(position).toString());
               intent.putExtra("DATE",restDate.get(position).toString());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return restDate.size();
    }

}
