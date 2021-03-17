package com.example.foodrescue;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class UserList extends ArrayAdapter<User> {
    private Activity context;
    private List<User> userList;

    public UserList(Activity context, List<User> userList){
        super(context, R.layout.activity_restaurant_profile, userList);
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_restaurant_profile, null, true);

        User user = userList.get(position);
        TextView textView = listViewItem.findViewById(R.id.txtViewProfile);
        String STR = "Name: " + user.getName() + "\nEmail: " + user.getEmail()+ "\nPhone: " + user.getPhone() + "\n" ;
        textView.setText(STR);

        return listViewItem;
    }


}
