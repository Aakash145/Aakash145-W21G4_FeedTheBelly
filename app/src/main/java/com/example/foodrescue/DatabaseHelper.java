package com.example.foodrescue;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by ProgrammingKnowledge on 4/3/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String dbname="FeedTheBelly";
    public static final String DATABASE_NAME = "User.db";
    public static final String TABLE_NAME = "User_table";
    private static final String COLUMN_USER_ID="userID";
    private static final String COLUMN_USER_NAME="userName";
    private static final String COLUMN_USER_EMAIL="userEmail";
    private static final String COLUMN_USER_PHONE="userPhone";
    private static final String COLUMN_USER_ADDRESS="userAddress";
    private static final String COLUMN_USER_CITY="userCity";
    private static final String COLUMN_USER_STATE="userState";
    private static final String COLUMN_USER_COUNTRY="userCountry";
    private static final String COLUMN_USER_POSTAL="userPostal";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
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

        db.execSQL(setPragmaForeignKeysOn);
        db.execSQL(createDishesTable);

        db.execSQL("create table " + TABLE_NAME +"(userEmail TEXT PRIMARY KEY, " +
                "userName TEXT," +
                "userPhone  TEXT," +
                "userID TEXT, " +
                "userAddress TEXT," +
                "userCity TEXT," +
                "userState TEXT," +
                "userCountry TEXT," +
                "userPostal TEXT" + ");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        String dropDishesTable = "DROP TABLE IF EXISTS " + "dishes;";
        String droptable1 = "DROP TABLE IF EXISTS " + "dishesType;";
        String droptable2 = "DROP TABLE IF EXISTS " + "restaurents;";

        db.execSQL(dropDishesTable);
        db.execSQL(droptable1);
        db.execSQL(droptable2);
        onCreate(db);
    }

    public boolean insertData(String email,String name,String phone, String id, String address, String city, String state, String country, String postal){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_EMAIL, email);
        values.put(COLUMN_USER_NAME, name);
        values.put(COLUMN_USER_PHONE, phone);
        values.put(COLUMN_USER_ID, id);
        values.put(COLUMN_USER_ADDRESS, address );
        values.put(COLUMN_USER_CITY, city);
        values.put(COLUMN_USER_STATE, state);
        values.put(COLUMN_USER_COUNTRY, country);
        values.put(COLUMN_USER_POSTAL, postal);
        long result = db.insert(TABLE_NAME,null ,values);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME, null);
        return res;
    }

    public boolean updateData(String email,String name,String phone, String id, String address, String city, String state, String country, String postal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_EMAIL, email);
        values.put(COLUMN_USER_NAME, name);
        values.put(COLUMN_USER_PHONE, phone);
        values.put(COLUMN_USER_ID, id);
        values.put(COLUMN_USER_ADDRESS, address );
        values.put(COLUMN_USER_CITY, city);
        values.put(COLUMN_USER_STATE, state);
        values.put(COLUMN_USER_COUNTRY, country);
        values.put(COLUMN_USER_POSTAL, postal);
        db.update(TABLE_NAME, values, "Email = ?",new String[] { email });
        return true;
    }

    public Integer deleteData (String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "Email = ?",new String[] { email });
    }
    public String addNewDish(Dishes dish, int id, Spinner spinner, Spinner spinner2, EditText expiryDate){
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

  /* public Cursor getUser(String Email){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME
                + " WHERE " + COLUMN_USER_EMAIL + " = " + Email;
        Cursor cursor = db.rawQuery(sql,new String[]{Email});

        return  cursor;
    }*/
  public Cursor getData(String email) {
      SQLiteDatabase db = this.getReadableDatabase();
      Cursor res =  db.rawQuery( "select * from user_table where userEmail= '"+email+"'", null );
      return res;
  }
    public Cursor fetchDayRecords(String Email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[]{
                COLUMN_USER_EMAIL, COLUMN_USER_NAME,
                COLUMN_USER_PHONE, COLUMN_USER_ID, COLUMN_USER_ADDRESS,
                COLUMN_USER_CITY, COLUMN_USER_STATE, COLUMN_USER_COUNTRY,
                COLUMN_USER_POSTAL
        };

        Cursor cursor = db.query(TABLE_NAME, columns,
                COLUMN_USER_EMAIL + " =?", new String[]{Email},
                null, null, null);
        cursor.moveToFirst();
        return cursor;
    }

}