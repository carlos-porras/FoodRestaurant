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

    private String userTable = "CREATE TABLE "+USERS_TABLE+"(" +
            USER_ID_COLUMN+" INTEGER PRIMARY KEY AUTOINCREMENT," +
            USER_USERNAME_COLUMN+" VARCHAR(25) NOT NULL," +
            USER_PASSWORD_COLUMN+" VARCHAR(30) NOT NULL," +
            USER_FULLNAME_COLUMN+" VARCHAR(50) NOT NULL" +
            ")";


    public DataHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(userTable);
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

