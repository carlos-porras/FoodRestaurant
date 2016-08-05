package com.boxtricksys.apps.foodrestaurant.controllers.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import com.boxtricksys.apps.foodrestaurant.models.database.DataHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

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
        initComponents();
        initEvents();
    }

    private void initComponents(){
        editTextUser = (EditText) findViewById(R.id.userEditText);
        editTextPassword = (EditText) findViewById(R.id.PasswordEditText);
        buttonLogin = (Button) findViewById(R.id.loginButton);
        textViewSignup = (TextView) findViewById(R.id.loginTextViewRegistrarUsuario);
    }

    private void validateCredentials(String username, String password){
        DataHelper dataHelper = new DataHelper(getApplicationContext());
        SQLiteDatabase sqliteDatabase = dataHelper.getReadableDatabase();

        //Columnas a traer
        String[] columnsToBring = new String[]{DataHelper.USER_FULLNAME_COLUMN};
        //Argumentos del WHERE
        String[] whereArgs = new String[]{username, password};
        //Where
        String where = DataHelper.USER_USERNAME_COLUMN + " = ? AND " + DataHelper.USER_PASSWORD_COLUMN + " = ?";
        //Cursor que guarda el query
        Cursor cursor = sqliteDatabase.query(DataHelper.USERS_TABLE, columnsToBring , where, whereArgs, null, null, null);

        if (cursor.moveToFirst()) {
            String completeName = cursor.getString(cursor.getColumnIndex(DataHelper.USER_FULLNAME_COLUMN));
            String welcome = String.format(getString(R.string.toast_welcome), completeName);
            Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), getString(R.string.toast_loginerror), Toast.LENGTH_LONG).show();
        }

        //Se cierra el cursor de los datos
        cursor.close();
        //Se cierra la conexión de la BD
        sqliteDatabase.close();
    }


    private void initEvents(){
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUser.getText().toString();
                String password = editTextPassword.getText().toString();
                validateCredentials(username,password);
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

}
