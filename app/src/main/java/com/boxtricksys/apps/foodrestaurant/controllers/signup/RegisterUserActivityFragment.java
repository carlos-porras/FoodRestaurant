package com.boxtricksys.apps.foodrestaurant.controllers.signup;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boxtricksys.apps.foodrestaurant.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class RegisterUserActivityFragment extends Fragment {

    public RegisterUserActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register_user, container, false);
    }
}
