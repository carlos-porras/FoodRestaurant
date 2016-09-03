package com.boxtricksys.apps.foodrestaurant.controllers.home;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import com.boxtricksys.apps.foodrestaurant.services.RequestService;
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
        //callRestaurantsEndpoint(); // ya no se utiliza, solo  para (para asynctasks)
        registerAlarm();
        registerBroadcastFilter();
        callResquestService();
        return rootView;
    }

    private void registerAlarm() {
        Intent intent = new Intent(getActivity().getApplicationContext(), RequestService.class);
        intent.putExtra(RequestService.ENDPOINT_KEY, RestConstants.RESTAURANTS_ENDPOINT);
        intent.putExtra(RequestService.REQUEST_METHOD_KEY, RestConstants.POST_REQUEST);

        PendingIntent pintent = PendingIntent.getService(getActivity().getApplicationContext(), 44544, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getActivity().getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC, System.currentTimeMillis(),5000, pintent);
    }

    /**
     * Con este método se registra el BroadcastReceiver que creamos
     * #RequestReceiver, primero se crea el intent filter
     * luego se le añade la acción de la cual estará pendiente el RequestReceiver (Broadcast)
     * y por último se registra el Broadcast en la actividad
     * */
    private void registerBroadcastFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(RequestService.ACTION_SEND_RESTAURANTS);

        RequestReceiver requestReceiver = new RequestReceiver();
        getActivity().registerReceiver(requestReceiver, intentFilter);
    }

    private void callResquestService() {
        Intent intent = new Intent(getActivity().getApplicationContext(), RequestService.class);
        intent.putExtra(RequestService.ENDPOINT_KEY, RestConstants.RESTAURANTS_ENDPOINT);
        intent.putExtra(RequestService.REQUEST_METHOD_KEY, RestConstants.POST_REQUEST);
        getActivity().startService(intent);
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

    public class RequestReceiver extends BroadcastReceiver{

        public RequestReceiver(){
            super();
        }
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equalsIgnoreCase(RequestService.ACTION_SEND_RESTAURANTS)){
                try {
                    Toast.makeText(getActivity().getApplicationContext(), "Vengo del servicio", Toast.LENGTH_SHORT).show();
                    String response = intent.getStringExtra(RequestService.RESTAURANT_KEY);
                    JSONObject jsonObject = new JSONObject(response);
                    validateJsonResponse(jsonObject);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
