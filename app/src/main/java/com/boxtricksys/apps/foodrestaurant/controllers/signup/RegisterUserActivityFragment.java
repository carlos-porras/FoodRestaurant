package com.boxtricksys.apps.foodrestaurant.controllers.signup;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.boxtricksys.apps.foodrestaurant.R;
import com.boxtricksys.apps.foodrestaurant.models.database.DataHelper;
/**
 * A placeholder fragment containing a simple view.
 */
public class RegisterUserActivityFragment extends Fragment {

    EditText editTextUsername;
    EditText editTextFullname;
    EditText editTextPassword;
    FloatingActionButton fabSaveUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_register_user, container, false);
        initializeVisualComponents(rootView);
        initializeEvents();
        return rootView;
    }

    private void initializeEvents() {
        fabSaveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserIntoDatabase();
            }
        });
    }

    private void saveUserIntoDatabase() {
        //Se crea una instancia de la clase DataHelper, que es la gestora de la base de datos
        DataHelper dataHelper = new DataHelper(getContext());
        //Se crea una instancia de escritura de SQLiteDatabase que se encarga de los procesos CRUD
        SQLiteDatabase sqLiteDatabase = dataHelper.getWritableDatabase();
        //Contenedor de datos de SQLite para guardar datos en columnas
        ContentValues newUser = new ContentValues();
        newUser.put(DataHelper.USER_USERNAME_COLUMN, editTextUsername.getText().toString());
        newUser.put(DataHelper.USER_PASSWORD_COLUMN, editTextPassword.getText().toString());
        newUser.put(DataHelper.USER_FULLNAME_COLUMN, editTextFullname.getText().toString());
        //Uso del método insert del API de android para crear un nuevo usuario basado en los ContentValues
        long  userId = sqLiteDatabase.insert(DataHelper.USERS_TABLE, null, newUser);
        validateUserCreated(userId);
        //Se cierra la instancia que se usó
        sqLiteDatabase.close();
        getActivity().finish();
    }

    private void validateUserCreated(long userId) {
        String message = getString(R.string.signup_toast_signupsuccess);
        if(userId == -1){
            message = getString(R.string.signup_toast_errorsignup);
        }
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    private void initializeVisualComponents(View rootView) {
        editTextUsername = (EditText) rootView.findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) rootView.findViewById(R.id.editTextPassword);
        editTextFullname = (EditText) rootView.findViewById(R.id.editTextFullname);
        fabSaveUser = (FloatingActionButton) rootView.findViewById(R.id.fabSaveUser);
    }
}
