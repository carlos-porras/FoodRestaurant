package com.boxtricksys.apps.foodrestaurant.controllers.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.boxtricksys.apps.foodrestaurant.R;

import java.util.Date;

public class LoginActivity extends AppCompatActivity{

    EditText editTextUser;
    EditText editTextPassword;
    Button buttonLogin;
    TextView textViewSignup;
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

    private void initEvents(){
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = sharedPreferences.getString("KEY_USER", "errorUser");
                String password = sharedPreferences.getString("KEY_PASS", "errorPass");
                String welcomeUser = String.format(getString(R.string.toast_welcome), user, new Date().toString());
                if(editTextUser.getText().toString().equalsIgnoreCase(user) && editTextPassword.getText().toString().equalsIgnoreCase(password)){
                    Toast.makeText(getApplicationContext(), welcomeUser, Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), R.string.toast_loginerror, Toast.LENGTH_LONG).show();
                }
            }
        });

        textViewSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
