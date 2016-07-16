package com.boxtricksys.apps.foodrestaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText user;
    EditText password;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_login);
        this.initComponents();

    }

    private void initComponents(){
        this.user = (EditText) findViewById(R.id.userEditText);
        this.password = (EditText) findViewById(R.id.PasswordEditText);
        this.login = (Button) findViewById(R.id.loginButton);
        this.login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.loginButton:
                Toast.makeText(getApplicationContext(), "Bien", Toast.LENGTH_LONG);
                break;
        }
    }
}
