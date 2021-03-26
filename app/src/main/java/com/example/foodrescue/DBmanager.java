package com.example.foodrescue;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import static android.database.sqlite.SQLiteDatabase.openDatabase;

public class DBmanager extends SQLiteOpenHelper {
    private static final String dbname="FeedTheBelly";

    public DBmanager(@Nullable Context context) {
        super(context, dbname, null, 1);
    }
    private final String USER_TABLE = "User";
    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            String setPragmaForeignKeysOn = "PRAGMA foreign_keys=ON;";

            String createDishesTable = "CREATE TABLE dishes " +
                    "(dishID INTEGER, " +
                    "cuisineType TEXT, " +
                    "foodCategory TEXT," +
                    "expDate TEXT, " +
                    "name TEXT, " +
                    "plates INTEGER, " +
                    "weight DOUBLE, " +
                    "PRIMARY KEY (dishID));";

            String createUserTable= "CREATE TABLE User "+
                    "(userEmail TEXT, " +
                    "userName TEXT," +
                    "userPhone  TEXT," +
                    "userID TEXT, " +
                    "userAddress TEXT," +
                    "userCity TEXT," +
                    "userState TEXT," +
                    "userCountry TEXT," +
                    "userPostal TEXT,"+
                    "PRIMARY KEY (userEmail));";

            db.execSQL(setPragmaForeignKeysOn);
            db.execSQL(createDishesTable);
            db.execSQL(createUserTable);

        }catch(Exception e){
            Log.d("DB", "Unable to create table");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropDishesTable = "DROP TABLE IF EXISTS " + "dishes;";
        String droptable1 = "DROP TABLE IF EXISTS " + "dishesType;";
        String droptable2 = "DROP TABLE IF EXISTS " + "restaurents;";

        db.execSQL(dropDishesTable);
        db.execSQL(droptable1);
        db.execSQL(droptable2);



    }

    public String addNewDish(Dishes dish, int id, Spinner spinner,Spinner spinner2, EditText expiryDate){
        SQLiteDatabase db=this.getWritableDatabase();
        long result;
        ContentValues val = new ContentValues();
        val.put("dishID", id);
        //val.put("emailID", null);
        val.put("cuisineType", spinner.getSelectedItem().toString());
        val.put("foodCategory", spinner2.getSelectedItem().toString());
        val.put("expDate", expiryDate.getText().toString());
        val.put("name", dish.getDishName());
        val.put("plates", dish.getNoOfPlates());
        val.put("weight", dish.getWeight());
        result=db.insert("dishes",null,val);
        if(result==-1)
            return "Failed";
        else
            return "Successfully inserted";
        }

    public Cursor readalldata()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String qry="select * from dishes";
        Cursor cursor=db.rawQuery(qry,null);
        return  cursor;
    }


}

