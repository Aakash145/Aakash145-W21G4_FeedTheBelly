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
    ArrayList restFoodCat,restFoodCousine,restExp,restName;
    String restAddress,restEmails;
    RecyclerView rvDonations;

    public Make_Donations_Adapter(Context context, ArrayList restFoodCat, ArrayList restFoodCousine, ArrayList restExp, String restAddress,String restEmails) {
        this.context = context;
        this.restFoodCat = restFoodCat;
        this.restFoodCousine = restFoodCousine;
        this.restExp = restExp;
        this.restAddress = restAddress;
        this.restEmails=restEmails;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView rowCat;
        TextView rowCousine;
        TextView rowExp;
        Button details;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowCat=itemView.findViewById(R.id.dish_cat);
            rowCousine=itemView.findViewById(R.id.dish_type);
            rowExp=itemView.findViewById(R.id.dish_exp);
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
        holder.rowCat.setText("Food Category: "+String.valueOf(restFoodCat.get(position)));
        holder.rowCousine.setText("Food Cuisine: "+String.valueOf(restFoodCousine.get(position)));
        holder.rowExp.setText("Expiry in: "+String.valueOf(restExp.get(position))+"day(s)");

        holder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,List_Of_Donated_Items.class);
           //     Toast.makeText(context,restEmails+","+restExp.get(position),Toast.LENGTH_LONG).show();
                intent.putExtra("EMAIL",restEmails);
                intent.putExtra("ADDRESS",restAddress);
              // intent.putExtra("NAME",restName.get(position).toString());
               intent.putExtra("EXPIRY",restExp.get(position).toString());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return restFoodCousine.size();
    }

}
