package com.example.foodrescue;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class SwipeAdapter extends FragmentStatePagerAdapter {

    public SwipeAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment pageFragment=new FragmentPage();
        Bundle bundle =new Bundle();
        switch (position){
            case 0:
                bundle.putString("Title", "A Better Way to Donate");
                bundle.putString("Description", "NGOs can receive better food from restaurants");
                bundle.putInt("Image", R.drawable.world_food);
                break;
            case 1:
                bundle.putString("Title", "Donate Money");
                bundle.putString("Description", "Anyone can donate some money if you do not have any excess food");
                bundle.putInt("Image", R.drawable.donate);
                break;
            case 2:
                bundle.putString("Title", "Happy Customer ");
                bundle.putString("Description", "NGO can choose from different donations restaurants made");
                bundle.putInt("Image", R.drawable.happy);
                break;


        }
        pageFragment.setArguments(bundle);
        return pageFragment;
        //return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
