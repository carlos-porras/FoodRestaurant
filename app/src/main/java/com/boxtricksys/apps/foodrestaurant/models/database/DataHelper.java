package com.boxtricksys.apps.foodrestaurant.models.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import java.sql.SQLException;
import com.boxtricksys.apps.foodrestaurant.models.User;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DataHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "FoodRestaurandDB";
    private static final int DATABASE_VERSION = 1;

    private Dao<User, Integer> userDao;

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, User.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        onCreate(db, connectionSource);
    }

    public Dao<User, Integer> getUserDao() throws SQLException {
        if (userDao == null) {
            userDao = getDao(User.class);
        }
        return userDao;
    }


    @Override
    public void close() {
        super.close();
        userDao = null;
    }

}
