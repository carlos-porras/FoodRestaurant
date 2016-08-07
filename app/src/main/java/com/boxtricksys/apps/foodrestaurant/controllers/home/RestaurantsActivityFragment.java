package com.boxtricksys.apps.foodrestaurant.controllers.home;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boxtricksys.apps.foodrestaurant.R;
import com.boxtricksys.apps.foodrestaurant.api.Requests;
import com.boxtricksys.apps.foodrestaurant.api.RestConstants;

import org.json.JSONObject;

/**
 * A placeholder fragment containing a simple view.
 */
public class RestaurantsActivityFragment extends Fragment {

    public RestaurantsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        callRestaurantsEndpoint();
        return inflater.inflate(R.layout.fragment_restaurants, container, false);
    }

    private void callRestaurantsEndpoint(){
        Requests requests = new Requests();
        requests.setOnRequestSuccess(new Requests.Listener() {
            @Override
            public void OnRequestSuccess(JSONObject jsonObject) {
                Log.i("RESPUESTA", jsonObject.toString());
            }
        });

        requests.execute(RestConstants.RESTAURANTS_ENDPOINT, RestConstants.POST_REQUEST);
    }
}
