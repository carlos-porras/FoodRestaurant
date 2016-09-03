package com.boxtricksys.apps.foodrestaurant.dataAccess;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import com.boxtricksys.apps.foodrestaurant.models.Restaurant;
import com.boxtricksys.apps.foodrestaurant.models.contentProviders.RestaurantsContentProvider;

import java.util.List;

/**
 * Created by jdrexler on 9/3/16.
 */
public class Restaurants {

    public void registrarUsuarios(List<Restaurant> restaurants, Context context){
        ContentResolver contentResolver = context.getContentResolver();

        for(Restaurant restaurant : restaurants){
            /** En este punto estamos creando nuestros valores para guardar**/
            /** RECUERDEN: contentValues.put(nombre de la columna, valor a guardar) **/
            ContentValues contentValues = new ContentValues();
            contentValues.put(RestaurantsContentProvider.Restaurants.NAME, restaurant.getName());
            contentValues.put(RestaurantsContentProvider.Restaurants.SPECIALITY, restaurant.getSpeciality());
            contentValues.put(RestaurantsContentProvider.Restaurants.LATITUDE, restaurant.getLatitude());
            contentValues.put(RestaurantsContentProvider.Restaurants.LONGITUDE, restaurant.getLongitude());

            Uri uriNewRestaurant = contentResolver.insert(RestaurantsContentProvider.CONTENT_URI, contentValues);

            long _id = ContentUris.parseId(uriNewRestaurant);
        }
    }
}
