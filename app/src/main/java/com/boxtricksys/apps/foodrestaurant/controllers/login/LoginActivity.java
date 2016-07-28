package com.boxtricksys.apps.foodrestaurant.controllers.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.boxtricksys.apps.foodrestaurant.R;
import com.boxtricksys.apps.foodrestaurant.controllers.signup.RegisterUserActivity;
import com.boxtricksys.apps.foodrestaurant.models.User;
import com.boxtricksys.apps.foodrestaurant.models.database.DataHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity{

    EditText editTextUser;
    EditText editTextPassword;
    Button buttonLogin;
    TextView textViewSignup;
    private DataHelper mDBHelper;

    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        createPreferences();
        initComponents();
        initEvents();
    }

    private void initComponents(){
        editTextUser = (EditText) findViewById(R.id.userEditText);
        editTextPassword = (EditText) findViewById(R.id.PasswordEditText);
        buttonLogin = (Button) findViewById(R.id.loginButton);
        textViewSignup = (TextView) findViewById(R.id.loginTextViewRegistrarUsuario);
    }

    private  void createPreferences(){
        sharedPreferences = getSharedPreferences("FoodRestaurantPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("KEY_USER", "test");
        editor.putString("KEY_PASS", "123");
        editor.apply();
    }

    private void queryCredentials(String username, String password){
        Dao dao;

        try {
            dao = getHelper().getUserDao();
            QueryBuilder queryBuilder = dao.queryBuilder();
            Log.v("Datos", username +" - "+ password);
            queryBuilder.setWhere(queryBuilder.where().eq(User.USERNAME.toLowerCase(), username.toLowerCase()).and().eq(User.PASSWORD, password));
            List <User> users = dao.query(queryBuilder.prepare());
            if (users.isEmpty()) {
                Toast.makeText(getApplicationContext(), R.string.toast_loginerror, Toast.LENGTH_LONG).show();
            } else {
                String welcomeUser = String.format(getString(R.string.toast_welcome), users.get(0).getNames());
                Toast.makeText(getApplicationContext(), welcomeUser, Toast.LENGTH_LONG).show();
            }
        } catch (SQLException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    private void initEvents(){
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUser.getText().toString();
                String password = editTextPassword.getText().toString();
                queryCredentials(username, password);
            }
        });

        textViewSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSigupIntent = new Intent(getApplicationContext(), RegisterUserActivity.class);
                startActivity(intentSigupIntent);
            }
        });
    }

    private DataHelper getHelper() {
        if (mDBHelper == null) {
            mDBHelper = OpenHelperManager.getHelper(getApplicationContext(), DataHelper.class);
        }
        return mDBHelper;
    }

}
