package com.example.foodrescue;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class recyclerView_NGO extends AppCompatActivity {
    SQLiteDatabase FeedTheBelly;
    RecyclerView rvDonations;
    ArrayList<String> ids;
    ArrayList<String> names;
    ArrayList<String> emails;
    ArrayList<String> dates;
    ArrayList<String> id;
    ArrayList<String> name;
    ArrayList<String> email;
    ArrayList<String> date;
    String date2;
    String email2;
    String id2;
    String name2;
    DatabaseHelper myDb;
    private FirebaseAuth mFirebaseAuth;
    Button details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view__n_g_o);
        myDb = new DatabaseHelper(this);
        ActionBar myActionBar=getSupportActionBar();
        myActionBar.setTitle("Donations List");

        rvDonations=findViewById(R.id.rvDonations);
        details=findViewById(R.id.btn_Details);
        rvDonations.setLayoutManager(new LinearLayoutManager(this));
        ids=new ArrayList<>();
        names=new ArrayList<>();
        emails=new ArrayList<>();
        dates=new ArrayList<>();
        Cursor cursor=myDb.readDonations();
        while(cursor.moveToNext()){

            try {
                ids.add(cursor.getString(1));
                names.add(cursor.getString(2));
                emails.add(cursor.getString(0));
                dates.add(cursor.getString(3));
                //int count=ids.size();
                /*for (int i = 0; i < count; i++) {
                    for (int j = i+1; j < count; j++) {
                        if(dates.get(i)==dates.get(j) && emails.get(i)==emails.get(j) && ids.get(i)==ids.get(j)
                        && names.get(i)==names.get(j)) {
                            date2 = dates.get(i);
                            email2 = emails.get(i);
                            name2 = names.get(i);
                            id2 = ids.get(i);
                            date.add(date2);
                            email.add(email2);
                            name.add(name2);
                            id.add(id2);
                        }
                    }
                }*/

            }catch(Exception e){
                Log.d("Error",e.getMessage());
            }
        }
        Make_Donations_Adapter myAdapter = new Make_Donations_Adapter(recyclerView_NGO.this, ids, names, emails, dates);
        rvDonations.setAdapter(myAdapter);


    }
}