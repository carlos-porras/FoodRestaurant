package com.boxtricksys.apps.foodrestaurant.controllers.signup;

import android.database.SQLException;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.boxtricksys.apps.foodrestaurant.R;
import com.boxtricksys.apps.foodrestaurant.models.User;
import com.boxtricksys.apps.foodrestaurant.models.database.DataHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

/**
 * A placeholder fragment containing a simple view.
 */
public class RegisterUserActivityFragment extends Fragment {

    EditText editTextUsername;
    EditText editTextNames;
    EditText editTextPassword;
    FloatingActionButton fabSaveUser;
    private DataHelper mDBHelper;

    public RegisterUserActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_register_user, container, false);
        initializeVisualComponents(rootView);
        initializeEvents();
        return rootView;
    }

    private void initializeVisualComponents(View rootView){
        editTextUsername = (EditText) rootView.findViewById(R.id.editTextUsername);
        editTextNames = (EditText) rootView.findViewById(R.id.editTextNames);
        editTextPassword = (EditText) rootView.findViewById(R.id.editTextPassword);
        fabSaveUser = (FloatingActionButton) rootView.findViewById(R.id.fabSaveUser);
    }

    private void initializeEvents(){
        fabSaveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInToDb();
            }
        });
    }

    private void saveUserInToDb(){
        Dao dao;
        try {
            dao = getHelper().getUserDao();
            User user = new User();
            user.setUsername(editTextUsername.toString());
            user.setNames(editTextNames.toString());
            user.setPassword(editTextPassword.toString());
            dao.create(user);
            Toast.makeText(getContext(), R.string.signup_toast_signupsuccess, Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(getContext(), R.string.signup_toast_errorsignup, Toast.LENGTH_LONG).show();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }
    private DataHelper getHelper() {
        if (mDBHelper == null) {
            mDBHelper = OpenHelperManager.getHelper(getContext(), DataHelper.class);
        }
        return mDBHelper;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDBHelper != null) {
            OpenHelperManager.releaseHelper();
            mDBHelper = null;
        }
    }
}
