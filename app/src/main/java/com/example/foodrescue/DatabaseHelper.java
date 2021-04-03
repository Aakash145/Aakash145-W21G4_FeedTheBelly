package com.example.foodrescue;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by ProgrammingKnowledge on 4/3/2015.
 */

//CREATED TABLES donated, donation,User_table,User_ngo_table,dishes
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String dbname = "FeedTheBelly";
    public static final String TABLE_NAME = "User_table";
    public static final String TABLE_NAME1 = "User_ngo_table";
    private static final String COLUMN_USER_ID = "userID";
    private static final String COLUMN_USER_NAME = "userName";
    private static final String COLUMN_USER_EMAIL = "userEmail";
    private static final String COLUMN_USER_PHONE = "userPhone";
    private static final String COLUMN_USER_ADDRESS = "userAddress";
    private static final String COLUMN_USER_CITY = "userCity";
    private static final String COLUMN_USER_STATE = "userState";
    private static final String COLUMN_USER_COUNTRY = "userCountry";
    private static final String COLUMN_USER_POSTAL = "userPostal";

    private static final String COLUMN_USER_ID1 = "userID1";
    private static final String COLUMN_USER_NAME1 = "userName1";
    private static final String COLUMN_USER_EMAIL1 = "userEmail1";
    private static final String COLUMN_USER_PHONE1 = "userPhone1";
    private static final String COLUMN_USER_ADDRESS1 = "userAddress1";
    private static final String COLUMN_USER_CITY1 = "userCity1";
    private static final String COLUMN_USER_STATE1 = "userState1";
    private static final String COLUMN_USER_COUNTRY1 = "userCountry1";
    private static final String COLUMN_USER_POSTAL1 = "userPostal1";
    private static final String COLUMN_USER_DESC1 = "userDesc1";

    public DatabaseHelper(Context context) {
        super(context, dbname, null, 1);
        //context.deleteDatabase(dbname);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String setPragmaForeignKeysOn = "PRAGMA foreign_keys=ON;";
        String createDishesTable = "CREATE TABLE dishes " +
                "(dishID INTEGER, " +
                "emailID TEXT," +
                "cuisineType TEXT, " +
                "foodCategory TEXT," +
                "expDate TEXT, " +
                "name TEXT, " +
                "plates INTEGER, " +
                "weight DOUBLE, " +
                "PRIMARY KEY (name,emailID));";

        String createDonationsTable = "CREATE TABLE donations " +
                "(dishID INTEGER, " +
                "emailID TEXT," +
                "cuisineType TEXT, " +
                "foodCategory TEXT," +
                "expDate TEXT, " +
                "name TEXT, " +
                "plates INTEGER, " +
                "weight DOUBLE, " +
                "donated_at DATETIME DEFAULT CURRENT_TIMESTAMP," +
                "PRIMARY KEY (name,emailID,donated_at)," +
                "FOREIGN KEY(emailID) REFERENCES dishes(emailID)," +
                "FOREIGN KEY(name) REFERENCES dishes(name));";

        String createDonatedTable = "CREATE TABLE donated " +
                "(dishID INTEGER, " +
                "emailID TEXT," +
                "cuisineType TEXT, " +
                "foodCategory TEXT," +
                "expDate TEXT, " +
                "name TEXT, " +
                "plates INTEGER, " +
                "weight DOUBLE, " +
                "ngo_email TEXT," +
                "donated_at TEXT," +
                "confirmed_at DATETIME DEFAULT CURRENT_TIMESTAMP," +
                "PRIMARY KEY (name,emailID,donated_at,confirmed_at)," +
                "FOREIGN KEY(emailID) REFERENCES dishes(emailID)," +
                "FOREIGN KEY(name) REFERENCES dishes(name)," +
                "FOREIGN KEY(donated_at) REFERENCES dishes(donated_at));";

        String restaurant_users = "create table " + TABLE_NAME + "(userEmail TEXT PRIMARY KEY, " +
                "userName TEXT," +
                "userPhone  TEXT," +
                "userID TEXT, " +
                "userAddress TEXT," +
                "userCity TEXT," +
                "userState TEXT," +
                "userCountry TEXT," +
                "userPostal TEXT" + ");";

        String ngo_users = "create table " + TABLE_NAME1 + "(userEmail1 TEXT PRIMARY KEY, " +
                "userName1 TEXT," +
                "userPhone1  TEXT," +
                "userID1 TEXT, " +
                "userDesc1 TEXT," +
                "userAddress1 TEXT," +
                "userCity1 TEXT," +
                "userState1 TEXT," +
                "userCountry1 TEXT," +
                "userPostal1 TEXT" + ");";

        db.execSQL(setPragmaForeignKeysOn);
        db.execSQL(createDonationsTable);
        db.execSQL(createDonatedTable);
        db.execSQL(createDishesTable);
        db.execSQL(restaurant_users);
        db.execSQL(ngo_users);

    }

    //UPGRADE
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        String dropDishesTable = "DROP TABLE IF EXISTS " + "dishes;";
        String droptable1 = "DROP TABLE IF EXISTS " + "User_table;";
        String droptable2 = "DROP TABLE IF EXISTS " + "User_ngo_table;";
        String droptable3 = "DROP TABLE IF EXISTS " + "donations;";
        String droptable4 = "DROP TABLE IF EXISTS " + "donated;";

        db.execSQL(dropDishesTable);
        db.execSQL(droptable1);
        db.execSQL(droptable2);
        db.execSQL(droptable3);
        db.execSQL(droptable4);
        onCreate(db);
    }

    //UPDATE
    public boolean updateData(String email, String name, String phone, String id, String address, String city, String state, String country, String postal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_EMAIL, email);
        values.put(COLUMN_USER_NAME, name);
        values.put(COLUMN_USER_PHONE, phone);
        values.put(COLUMN_USER_ID, id);
        values.put(COLUMN_USER_ADDRESS, address);
        values.put(COLUMN_USER_CITY, city);
        values.put(COLUMN_USER_STATE, state);
        values.put(COLUMN_USER_COUNTRY, country);
        values.put(COLUMN_USER_POSTAL, postal);
        db.update(TABLE_NAME, values, "Email = ?", new String[]{email});
        return true;
    }

    public boolean updateData1(String email1, String name1, String phone1, String id1, String desc1, String address1, String city1, String state1, String country1, String postal1) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_EMAIL1, email1);
        values.put(COLUMN_USER_NAME1, name1);
        values.put(COLUMN_USER_PHONE1, phone1);
        values.put(COLUMN_USER_ID1, id1);
        values.put(COLUMN_USER_DESC1, desc1);
        values.put(COLUMN_USER_ADDRESS1, address1);
        values.put(COLUMN_USER_CITY1, city1);
        values.put(COLUMN_USER_STATE1, state1);
        values.put(COLUMN_USER_COUNTRY1, country1);
        values.put(COLUMN_USER_POSTAL1, postal1);
        db.update(TABLE_NAME1, values, "Email = ?", new String[]{email1});
        return true;
    }

    //INSERTING DATA IN TABLES
    public boolean insertData(String email, String name, String phone, String id, String address, String city, String state, String country, String postal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_EMAIL, email);
        values.put(COLUMN_USER_NAME, name);
        values.put(COLUMN_USER_PHONE, phone);
        values.put(COLUMN_USER_ID, id);
        values.put(COLUMN_USER_ADDRESS, address);
        values.put(COLUMN_USER_CITY, city);
        values.put(COLUMN_USER_STATE, state);
        values.put(COLUMN_USER_COUNTRY, country);
        values.put(COLUMN_USER_POSTAL, postal);
        long result = db.insert(TABLE_NAME, null, values);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insertData1(String email1, String name1, String phone1, String id1, String desc1, String address1, String city1, String state1, String country1, String postal1) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_EMAIL1, email1);
        values.put(COLUMN_USER_NAME1, name1);
        values.put(COLUMN_USER_PHONE1, phone1);
        values.put(COLUMN_USER_ID1, id1);
        values.put(COLUMN_USER_DESC1, desc1);
        values.put(COLUMN_USER_ADDRESS1, address1);
        values.put(COLUMN_USER_CITY1, city1);
        values.put(COLUMN_USER_STATE1, state1);
        values.put(COLUMN_USER_COUNTRY1, country1);
        values.put(COLUMN_USER_POSTAL1, postal1);
        long result = db.insert(TABLE_NAME1, null, values);
        if (result == -1)
            return false;
        else
            return true;
    }

    public String addNewDish(Dishes dish, String emailID, int id, Spinner spinner, Spinner spinner2, EditText expiryDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result;
        ContentValues val = new ContentValues();
        val.put("dishID", id);
        val.put("emailID", emailID);
        val.put("cuisineType", spinner.getSelectedItem().toString());
        val.put("foodCategory", spinner2.getSelectedItem().toString());
        val.put("expDate", expiryDate.getText().toString());
        val.put("name", dish.getDishName());
        val.put("plates", dish.getNoOfPlates());
        val.put("weight", dish.getWeight());
        result = db.insert("dishes", null, val);
        if (result == -1)
            return "Failed";
        else
            return "Successfully inserted";
    }

    public String addDonation(String emailID, String plates, String weight, String name, String dishID, String cType, String fType, String expiryDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result;
        ContentValues val = new ContentValues();
        val.put("dishID", dishID);
        val.put("emailID", emailID);
        val.put("cuisineType", cType);
        val.put("foodCategory", fType);
        val.put("expDate", expiryDate);
        val.put("name", name);
        val.put("plates", plates);
        val.put("weight", weight);
        result = db.insert("donations", null, val);
        if (result == -1)
            return "Failed";
        else
            return "Successfully inserted";
    }

    public String addDonated(String emailID, String ngo_email, String plates, String weight, String name, String dishID, String cType, String fType, String expiryDate, String donated_at) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result;
        ContentValues val = new ContentValues();
        val.put("dishID", dishID);
        val.put("emailID", emailID);
        val.put("cuisineType", cType);
        val.put("foodCategory", fType);
        val.put("expDate", expiryDate);
        val.put("name", name);
        val.put("plates", plates);
        val.put("weight", weight);
        val.put("donated_at", donated_at);
        val.put("ngo_email", ngo_email);
        result = db.insert("donated", null, val);
        if (result == -1)
            return "Failed";
        else
            return "Successfully inserted";
    }

    //SQL QUERIES
    public Cursor readAllRestaurants() {
        SQLiteDatabase db = this.getWritableDatabase();
        String qry = "select DISTINCT userEmail,userName,userPhone,userID,userAddress,userCity,userState,userCountry,userPostal from User_table JOIN donations WHERE User_table.userEmail=donations.emailID";
        Cursor cursor = db.rawQuery(qry, null);
        return cursor;
    }

    public Cursor readEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from dishes where emailID= '" + email + "'", null);
        return res;
    }

    public Cursor readEmailFromDonation(String email, String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from donations where emailID= '" + email + "'AND strftime('%d/%m/%Y %H:%M', donations.donated_at)='" + date + "';", null);
        return res;
    }


    public void deleteAllDishesOfEachUser(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        long s = db.delete("dishes", "emailID" + " = ?",
                new String[]{String.valueOf(email)});
    }


    public void deleteDonation(String date, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
     // String qry = "DELETE FROM donations WHERE emailID='" + email + "' AND " +
       // "strftime('%d/%m/%Y %H:%M', donations.donated_at='" + date + "';";
      long s= db.delete("donations", "strftime('%d/%m/%Y %H:%M', donations.donated_at)" + "=? AND" + " emailID" + "=?",new String[]{date,email});
       // db.rawQuery(qry,null);
    }

    public Cursor readDonations(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryStr = "SELECT strftime('%d/%m/%Y %H:%M', donated_at),SUM(donations.weight),userName FROM donations JOIN User_table ON User_table.userEmail=donations.emailID AND expDate IN (SELECT A.expDate FROM donations A, donations B WHERE A.emailID=B.emailID AND strftime('%d/%m/%Y %H:%M', A.donated_at)=strftime('%d/%m/%Y %H:%M', B.donated_at)) WHERE donations.emailID='" + email + "'GROUP BY strftime('%d/%m/%Y %H:%M', donated_at);";
        Cursor cursor = db.rawQuery(queryStr, null);
        return cursor;
    }

    public Cursor readDishes(String email, String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryStr = "SELECT donations.name,donations.weight,donations.plates,donations.expDate,donations.foodCategory,donations.cuisineType FROM User_table JOIN donations " +
                "ON User_table.userEmail=donations.emailID " +
                "AND strftime('%d/%m/%Y %H:%M', donations.donated_at) IN (SELECT strftime('%d/%m/%Y %H:%M', A.donated_at)" +
                " FROM donations A, donations B " +
                "WHERE A.emailID=B.emailID " +
                "AND strftime('%d/%m/%Y %H:%M', A.donated_at)=strftime('%d/%m/%Y %H:%M', B.donated_at))" +
                "WHERE donations.emailID= '" + email + "'AND strftime('%d/%m/%Y %H:%M', donations.donated_at)='" + date + "';";
        Cursor cursor = db.rawQuery(queryStr, null);
        return cursor;
    }

    public Cursor readConfirmedDonations(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryStr = "SELECT User_table.userName,strftime('%d/%m/%Y %H:%M', donated.confirmed_at)," +
                "strftime('%d/%m/%Y %H:%M', donated.donated_at),SUM(donated.weight),SUM(donated.plates)" +
                "FROM User_table JOIN donated " +
                "ON User_table.userEmail=donated.emailID \n" +
                "AND strftime('%d/%m/%Y %H:%M', donated_at) IN" +
                "    (SELECT strftime('%d/%m/%Y %H:%M', A.donated_at)" +
                "     FROM donated A, donated B " +
                "     WHERE strftime('%d/%m/%Y %H:%M', A.donated_at)=strftime('%d/%m/%Y %H:%M', B.donated_at))" +
                "WHERE donated.ngo_email='"+email+"'" +
                "GROUP BY confirmed_at;";
        Cursor cursor = db.rawQuery(queryStr, null);
        return cursor;
    }

    public Cursor getData(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from user_table where userEmail= '" + email + "'", null);
        return res;
    }

    public Cursor getData1(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from User_ngo_table where userEmail1= '" + email + "'", null);
        return res;
    }

    //UNUSED QUERIES
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public Cursor getAllData1() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME1, null);
        return res;
    }

    public Integer deleteData(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "Email = ?", new String[]{email});
    }


    public Integer deleteData1(String email1) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME1, "Email = ?", new String[]{email1});
    }

    public Cursor readDonated(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryStr = "SELECT foodCategory, cuisineType, expDate, emailID FROM donated " +
                "WHERE emailID='" + email + "'";
        Cursor cursor = db.rawQuery(queryStr, null);
        return cursor;
    }

    public Cursor readDonationItems(String email) {
        SQLiteDatabase db = getReadableDatabase();
        String queryStr = "SELECT DISTINCT donation.name,User_table.userID,userName,donations.expDate FROM User_table JOIN donations " +
                "ON User_table.userEmail=donations.emailID" +
                " AND expDate IN (SELECT A.expDate FROM donations A, donations B" +
                " WHERE A.emailID=B.emailID" +
                " AND A.expDate=B.expDate)" +
                "WHERE donations.emailID='" + email + "'";
        Cursor cursor = db.rawQuery(queryStr, null);
        return cursor;
    }

    public Cursor readalldata() {
        SQLiteDatabase db = this.getWritableDatabase();
        String qry = "select * from dishes";
        Cursor cursor = db.rawQuery(qry, null);
        return cursor;
    }


}