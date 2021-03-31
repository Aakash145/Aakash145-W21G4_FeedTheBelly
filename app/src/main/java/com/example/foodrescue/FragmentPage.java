package com.example.foodrescue;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentPage extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view;
        Bundle bundle = getArguments();
        int imgNo=bundle.getInt("Image");
        String t1=bundle.getString("Title");
        String t2=bundle.getString("Description");
       // String t3=bundle.getString("Swipe");


        view =inflater.inflate(R.layout.page_fragment_layout,container,false);
        TextView textView=view.findViewById(R.id.textView);
        TextView textView2=view.findViewById(R.id.textView2);
        //TextView textView2=view.findViewById(R.id.textView2);
        ImageView img=view.findViewById(R.id.imageView);
        textView.setText(t1);
        textView2.setText(t2);
        Context context = null;

        img.setImageResource(imgNo);
        return view;
        // return super.onCreateView(inflater, container, savedInstanceState);
    }
}
