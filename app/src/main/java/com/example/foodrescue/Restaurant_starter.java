package com.example.foodrescue;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;

import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class Restaurant_starter extends AppCompatActivity {

    SQLiteDatabase FeedTheBelly;
    String emailNew;
    private static final int OPEN_REQUEST_CODE = 41;
    TextView totalItems, txtView;
    Button Confirm_Form;
    Button uploadFile;
    Spinner cuisineType;
    Spinner catagoryType;
    EditText expiryDate;
    EditText noOfPlates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_starter);
        Confirm_Form = findViewById(R.id.btnViewConfirm);
        uploadFile = findViewById(R.id.btnViewItemsAdded);
        totalItems = findViewById(R.id.txtViewShowItemAdded);
        cuisineType = findViewById(R.id.cuisine_types);
        catagoryType = findViewById(R.id.category_types);
        expiryDate = findViewById(R.id.editTextDate);
        noOfPlates = findViewById(R.id.editNumberOfPlates);
        createDB();
        createTable();

        //Intent i = getIntent();

        // loadUserInformation();
//        Detail details = (Detail) i.getSerializableExtra("Details");
//        User user = (User)i.getSerializableExtra("User");
//        emailNew =  user.getEmail();

        ActionBar myActionBar=getSupportActionBar();
        myActionBar.setTitle("Fill in the Food Details");
     //   txtView = findViewById(R.id.txtViewProfile);
        Intent i = getIntent();
        Detail details = (Detail) i.getSerializableExtra("Details");
        User user = (User)i.getSerializableExtra("User");
        String email =  user.getEmail();
        totalItems.setText(email);

        uploadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                startActivityForResult(intent, OPEN_REQUEST_CODE);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == OPEN_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

                if (data != null && data.getData() != null) {

                    try {
                        List<Dishes> dishes = readFileContent(data.getData());
                        totalItems.setText("Total Items added:" + dishes.size());
                    } catch (IOException e) {
                        Log.d("IOException", "Unable to find File");
                    }
                }
            }
    }

    private List<Dishes> readFileContent(Uri uri) throws IOException {
//        Intent myIntent = getIntent();
//        User user = (User)myIntent.getSerializableExtra("User");
//        email =  user.getEmail();
        List<Dishes> newDish = new ArrayList<>();

            InputStream inputStream =
                    getContentResolver().openInputStream(uri);

//        XSSFWorkbook myWorkBook = new XSSFWorkbook (inputStream);
//
//        XSSFSheet mySheet = myWorkBook.getSheetAt(0);
//        Iterator<Row> rowIterator = mySheet.iterator();
//
//        while (rowIterator.hasNext()) {
//            Row row = rowIterator.next();
//            Iterator<Cell> cellIterator = row.cellIterator();
//            List<String> myList = new ArrayList<>();
//            while (cellIterator.hasNext()) {
//                Cell cell = cellIterator.next();
//                myList.add(cell.getStringCellValue());
//            }
//            Dishes dish = new Dishes(myList.get(0).toString(),Integer.parseInt(myList.get(1).toString()),Double.parseDouble(myList.get(2).toString()));
//            newDish.add(dish);
//        }

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(
                            inputStream));
            String currentLine;
            currentLine = reader.readLine();
            int i =1;
            while ((currentLine = reader.readLine()) != null) {
                String[] eachLine = currentLine.split(",");
                String nameOfDish = eachLine[0].toString();
                int noOfItems = Integer.parseInt(eachLine[1]);
                double weight = Double.parseDouble(eachLine[2]);
                Dishes dish = new Dishes(nameOfDish, noOfItems,  weight);
                newDish.add(dish);
                addNewDish(dish, i);
                i++;
            }
            i = 0;
            inputStream.close();

        return newDish;
    }

    //Creating Database
    private void createDB(){
        try{

            FeedTheBelly = openOrCreateDatabase("FeedTheBelly.db", MODE_PRIVATE, null);
            Toast.makeText(this, "Database Open", Toast.LENGTH_SHORT).show();

        }catch(Exception e){
            Log.e("Creating database", e.getMessage());
        }
    }

    //Creating Registration and Dishes Table
    private void createTable(){
        try{
            String setPragmaForeignKeysOn = "PRAGMA foreign_keys=ON;";
            String dropDishesTable = "DROP TABLE IF EXISTS " + "dishes;";
            String droptable1 = "DROP TABLE IF EXISTS " + "dishesType;";
            String droptable2 = "DROP TABLE IF EXISTS " + "restaurents;";
            String createDishesTable = "CREATE TABLE dishes " +
                    "(dishID INTEGER, emailID TEXT, cuisineType TEXT, foodCategory TEXT" +
                    ", expDate TEXT, name TEXT, plates INTEGER, weight DOUBLE, " +
                    "PRIMARY KEY (dishID, emailID));";

            FeedTheBelly.execSQL(setPragmaForeignKeysOn);
            FeedTheBelly.execSQL(dropDishesTable);
            FeedTheBelly.execSQL(droptable1);
            FeedTheBelly.execSQL(droptable2);
            FeedTheBelly.execSQL(createDishesTable);
        }catch(Exception e){
            Log.d("DB", "Unable to create table");
        }
    }

    //Adding the Dishes..
    private void addNewDish(Dishes dish, int id){

        long result;
        ContentValues val = new ContentValues();
        val.put("dishID", id);
        //val.put("emailID", null);
        val.put("cuisineType", cuisineType.getSelectedItem().toString());
        val.put("foodCategory", catagoryType.getSelectedItem().toString());
        val.put("expDate", expiryDate.getText().toString());
        val.put("name", dish.getDishName());
        val.put("plates", dish.getNoOfPlates());
        val.put("weight", dish.getWeight());
        try{
            result = FeedTheBelly.insert("dishes", null, val);
            if(result != 1){
                Log.d("DB", "rowid ");
            }else{
                Log.d("DB", "Error");
            }
        }catch(Exception e){
            Log.d("DB", "Error");
        }

    }


}