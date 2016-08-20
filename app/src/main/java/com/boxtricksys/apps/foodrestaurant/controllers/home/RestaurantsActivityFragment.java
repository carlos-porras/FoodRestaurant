package com.boxtricksys.apps.foodrestaurant.controllers.home;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.boxtricksys.apps.foodrestaurant.R;
import com.boxtricksys.apps.foodrestaurant.api.Requests;
import com.boxtricksys.apps.foodrestaurant.api.RestConstants;
import com.boxtricksys.apps.foodrestaurant.controllers.home.adapters.RestaurantsAdapter;
import com.boxtricksys.apps.foodrestaurant.models.Restaurant;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class RestaurantsActivityFragment extends Fragment {

    ListView listViewRestaurants;
    private List<Restaurant> restaurants;
    public RestaurantsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_restaurants, container, false);
        listViewRestaurants = (ListView) rootView.findViewById(R.id.listViewRestaurants);
        callRestaurantsEndpoint();
        return rootView;
    }

    private void callRestaurantsEndpoint(){
        Requests requests = new Requests();
        requests.setOnRequestSuccess(new Requests.Listener() {
            @Override
            public void OnRequestSuccess(JSONObject jsonObject) {
                validateJsonResponse(jsonObject);
            }
        });

        requests.execute(RestConstants.RESTAURANTS_ENDPOINT, RestConstants.POST_REQUEST);
    }

    private void validateJsonResponse(JSONObject jsonObject) {
        try {
            if(jsonObject.getString("status").equalsIgnoreCase("success")){
                JSONArray JsonArrayRestaurants = jsonObject.getJSONArray("data");
                Type typeTokenListRestaurants = new TypeToken<List<Restaurant>> () {}.getType();
                restaurants = new Gson().fromJson(JsonArrayRestaurants.toString(), typeTokenListRestaurants);
                RestaurantsAdapter restaurantsAdapter = new RestaurantsAdapter(getActivity().getApplicationContext(), restaurants);
                listViewRestaurants.setAdapter(restaurantsAdapter);
            }else{
                Toast.makeText(getActivity().getApplicationContext(), R.string.toast_string_error_get_info_restaurant, Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
