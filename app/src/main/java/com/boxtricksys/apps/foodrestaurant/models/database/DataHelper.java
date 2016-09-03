package com.boxtricksys.apps.foodrestaurant.models.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "FoodRestaurantDB";
    private static final int DB_VERSION = 1;

    //User
    public static final String USERS_TABLE = "users";
    public static final String USER_ID_COLUMN = "_id";
    public static final String USER_USERNAME_COLUMN = "username";
    public static final String USER_PASSWORD_COLUMN = "password";
    public static final String USER_FULLNAME_COLUMN = "fullname";

    //RestaurantsDataAccess
    public static final String RESTAURANTS_TABLE = "restaurants";
    public static final String RESTAURANT_ID_COLUMN = "_id";
    public static final String RESTAURANT_SPECIALITY_COLUMN = "speciality";
    public static final String RESTAURANT_NAME_COLUMN = "name";
    public static final String RESTAURANT_LATITUDE_COLUMN = "latitude";
    public static final String RESTAURANT_LONGITUDE_COLUMN = "longitude";

    private String userTable = "CREATE TABLE "+USERS_TABLE+"(" +
            USER_ID_COLUMN+" INTEGER PRIMARY KEY," +
            USER_USERNAME_COLUMN+" VARCHAR(25) NOT NULL," +
            USER_PASSWORD_COLUMN+" VARCHAR(30) NOT NULL," +
            USER_FULLNAME_COLUMN+" VARCHAR(50) NOT NULL" +
            ")";

    private String restaurantsTable = "CREATE TABLE "+RESTAURANTS_TABLE+"("+
            RESTAURANT_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            RESTAURANT_NAME_COLUMN + " VARCHAR(30) NOT NULL," +
            RESTAURANT_SPECIALITY_COLUMN + " VARCHAR(30) NOT NULL," +
            RESTAURANT_LONGITUDE_COLUMN +" DECIMAL(11,8) NOT NULL," +
            RESTAURANT_LATITUDE_COLUMN + " DECIMAL(10,8) NOT NULL" +
            ")";


    public DataHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(userTable);
        sqLiteDatabase.execSQL(restaurantsTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        switch (newVersion){
            case 2:
                //Cuando la versión sea 2 se crea AQUÎ la actualización en la base de datos
                break;
        }
    }
}

