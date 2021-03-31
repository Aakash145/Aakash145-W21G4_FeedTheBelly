package com.example.foodrescue;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**SELECT *  FROM User_table JOIN donations ON User_table.userEmail=donations.emailID
 WHERE expDate IN (SELECT A.expDate FROM donations A, donations B
 WHERE A.emailID=B.emailID
 AND A.expDate=B.expDate);
 * Created by ProgrammingKnowledge on 4/3/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String dbname="FeedTheBelly";
    public static final String TABLE_NAME = "User_table";
    public static final String TABLE_NAME1 = "User_ngo_table";
    private static final String COLUMN_USER_ID="userID";
    private static final String COLUMN_USER_NAME="userName";
    private static final String COLUMN_USER_EMAIL="userEmail";
    private static final String COLUMN_USER_PHONE="userPhone";
    private static final String COLUMN_USER_ADDRESS="userAddress";
    private static final String COLUMN_USER_CITY="userCity";
    private static final String COLUMN_USER_STATE="userState";
    private static final String COLUMN_USER_COUNTRY="userCountry";
    private static final String COLUMN_USER_POSTAL="userPostal";

    private static final String COLUMN_USER_ID1="userID1";
    private static final String COLUMN_USER_NAME1="userName1";
    private static final String COLUMN_USER_EMAIL1="userEmail1";
    private static final String COLUMN_USER_PHONE1="userPhone1";
    private static final String COLUMN_USER_ADDRESS1="userAddress1";
    private static final String COLUMN_USER_CITY1="userCity1";
    private static final String COLUMN_USER_STATE1="userState1";
    private static final String COLUMN_USER_COUNTRY1="userCountry1";
    private static final String COLUMN_USER_POSTAL1="userPostal1";
    private static final String COLUMN_USER_DESC1="userDesc1";

    public DatabaseHelper(Context context) {
        super(context, dbname, null, 1);

    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String setPragmaForeignKeysOn = "PRAGMA foreign_keys=ON;";
        String createDishesTable = "CREATE TABLE dishes " +
                "(dishID INTEGER, " +
                "emailID TEXT,"+
                "cuisineType TEXT, " +
                "foodCategory TEXT," +
                "expDate TEXT, " +
                "name TEXT, " +
                "plates INTEGER, " +
                "weight DOUBLE, " +
                "PRIMARY KEY (name,emailID));";

        String createDonationsTable = "CREATE TABLE donations " +
                "(dishID INTEGER, " +
                "emailID TEXT,"+
                "cuisineType TEXT, " +
                "foodCategory TEXT," +
                "expDate TEXT, " +
                "name TEXT, " +
                "plates INTEGER, " +
                "weight DOUBLE, " +
                "PRIMARY KEY (name,emailID,expDate)," +
                "FOREIGN KEY(emailID) REFERENCES dishes(emailID)," +
                "FOREIGN KEY(name) REFERENCES dishes(name)," +
                "FOREIGN KEY(expDate) REFERENCES dishes(expDate));";

        String restaurant_users="create table " + TABLE_NAME +"(userEmail TEXT PRIMARY KEY, " +
                "userName TEXT," +
                "userPhone  TEXT," +
                "userID TEXT, " +
                "userAddress TEXT," +
                "userCity TEXT," +
                "userState TEXT," +
                "userCountry TEXT," +
                "userPostal TEXT" + ");";

        String ngo_users="create table " + TABLE_NAME1 + "(userEmail1 TEXT PRIMARY KEY, " +
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
        db.execSQL(createDishesTable);
        db.execSQL(restaurant_users);
        db.execSQL(ngo_users);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        String dropDishesTable = "DROP TABLE IF EXISTS " + "dishes;";
        String droptable1 = "DROP TABLE IF EXISTS " + "User_table;";
        String droptable2 = "DROP TABLE IF EXISTS " + "User_ngo_table;";
        String droptable3 = "DROP TABLE IF EXISTS " + "donations;";

        db.execSQL(dropDishesTable);
        db.execSQL(droptable1);
        db.execSQL(droptable2);
        db.execSQL(droptable3);
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

    public boolean insertData1(String email1,String name1, String phone1, String id1, String desc1, String address1, String city1, String state1, String country1, String postal1){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_EMAIL1, email1);
        values.put(COLUMN_USER_NAME1, name1);
        values.put(COLUMN_USER_PHONE1, phone1);
        values.put(COLUMN_USER_ID1, id1);
        values.put(COLUMN_USER_DESC1, desc1);
        values.put(COLUMN_USER_ADDRESS1, address1 );
        values.put(COLUMN_USER_CITY1, city1);
        values.put(COLUMN_USER_STATE1, state1);
        values.put(COLUMN_USER_COUNTRY1, country1);
        values.put(COLUMN_USER_POSTAL1, postal1);
        long result = db.insert(TABLE_NAME1,null ,values);
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
    public Cursor getAllData1() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME1, null);
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
    public boolean updateData1(String email1,String name1, String phone1, String id1, String desc1,String address1, String city1, String state1, String country1, String postal1){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_EMAIL1, email1);
        values.put(COLUMN_USER_NAME1, name1);
        values.put(COLUMN_USER_PHONE1, phone1);
        values.put(COLUMN_USER_ID1, id1);
        values.put(COLUMN_USER_DESC1, desc1);
        values.put(COLUMN_USER_ADDRESS1, address1 );
        values.put(COLUMN_USER_CITY1, city1);
        values.put(COLUMN_USER_STATE1, state1);
        values.put(COLUMN_USER_COUNTRY1, country1);
        values.put(COLUMN_USER_POSTAL1, postal1);
        db.update(TABLE_NAME1, values, "Email = ?",new String[] { email1 });
        return true;
    }

    public Integer deleteData (String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "Email = ?",new String[] { email });
    }


    public Integer deleteData1 (String email1) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME1, "Email = ?",new String[] { email1 });
    }
    public String addNewDish(Dishes dish,String emailID, int id, Spinner spinner, Spinner spinner2, EditText expiryDate){
        SQLiteDatabase db=this.getWritableDatabase();
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
        result=db.insert("dishes",null,val);
        if(result==-1)
            return "Failed";
        else
            return "Successfully inserted";
    }

    public String addDonation(String emailID,String plates, String weight, String name, String dishID, String cType, String fType, String expiryDate){
        SQLiteDatabase db=this.getWritableDatabase();
        long result;
        ContentValues val = new ContentValues();
        val.put("dishID", dishID);
        val.put("emailID", emailID);
        val.put("cuisineType", cType);
        val.put("foodCategory",fType);
        val.put("expDate", expiryDate);
        val.put("name", name);
        val.put("plates", plates);
        val.put("weight", weight);
        result=db.insert("donations",null,val);
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

    public Cursor readAllRestaurants()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String qry="select * from User_table";
        Cursor cursor=db.rawQuery(qry,null);
        return  cursor;
    }

    public Cursor readEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from dishes where emailID= '" + email + "'", null);
        return res;
    }

    public void deleteAllDishesOfEachUser(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        long s=db.delete("dishes", "emailID" + " = ?",
                new String[]{String.valueOf(email)});
    }

    public void deleteConfirmedRecord(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        long s=db.delete("dishes", "emailID" + " = ?",
                new String[]{String.valueOf(email)});
    }

    public Cursor readDonations(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryStr = "SELECT DISTINCT donations.foodCategory,donations.cuisineType,donations.expDate,User_table.userEmail FROM User_table JOIN donations " +
                            "ON User_table.userEmail=donations.emailID" +
                            " AND expDate IN (SELECT A.expDate FROM donations A, donations B" +
                                                " WHERE A.emailID=B.emailID" +
                                                " AND A.expDate=B.expDate)" +
                "WHERE donations.emailID='"+email+"'";
        Cursor cursor=db.rawQuery(queryStr,null);
        return  cursor;
    }

    public Cursor readDishes(String email,String exp) {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryStr = "SELECT donations.name,donations.weight,donations.plates FROM User_table JOIN donations " +
                "ON User_table.userEmail=donations.emailID " +
                "AND expDate IN (SELECT A.expDate FROM donations A, donations B " +
                "WHERE A.emailID=B.emailID " +
                "AND A.expDate=B.expDate)" +
                "WHERE donations.emailID= '"+email+ "'AND donations.expDate='"+exp+"';";
        Cursor cursor=db.rawQuery(queryStr,null);
        return  cursor;
    }

    public Cursor readDonationItems(String email) {
        SQLiteDatabase db = getReadableDatabase();
        String queryStr = "SELECT DISTINCT donation.name,User_table.userID,userName,donations.expDate FROM User_table JOIN donations " +
                "ON User_table.userEmail=donations.emailID" +
                " AND expDate IN (SELECT A.expDate FROM donations A, donations B" +
                " WHERE A.emailID=B.emailID" +
                " AND A.expDate=B.expDate)" +
                "WHERE donations.emailID='"+email+"'";
        Cursor cursor=db.rawQuery(queryStr,null);
        return  cursor;
    }

   /* public Cursor readDonationRestaurant() {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryStr = "SELECT *  FROM User_table JOIN donations " +
                "ON User_table.userEmail=donations.emailID" +
                " JOIN (SELECT DISTINCT A.expDate date,A.emailID email FROM donations A, donations B" +
                " WHERE A.emailID=B.emailID" +
                " AND A.expDate=B.expDate)" +
                "ON email=donations.emailID;";
        Cursor cursor=db.rawQuery(queryStr,null);
        return  cursor;
    }

    public Cursor getUser(String Email){
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

    //SELECT  * FROM donations A, donations B
    //WHERE  A.emailID=B.emailID
    // AND A.expDate=B.expDate
    public Cursor getData1(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from User_ngo_table where userEmail1= '"+email+"'", null );
        return res;
    }

}