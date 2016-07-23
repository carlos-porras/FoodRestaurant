package com.boxtricksys.apps.foodrestaurant.models;

/**
 * Created by Nicolas on 23/07/2016.
 */

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class User {

    public static final String ID = "_id";
    public static final String NAMES = "names";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    @DatabaseField(generatedId = true, columnName = ID)
    private int id;

    @DatabaseField(columnName = NAMES)
    private String names;

    @DatabaseField(columnName = USERNAME)
    private String username;

    @DatabaseField(columnName = PASSWORD)
    private String getPassword;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String nombre) {
        this.names = nombre;
    }


}