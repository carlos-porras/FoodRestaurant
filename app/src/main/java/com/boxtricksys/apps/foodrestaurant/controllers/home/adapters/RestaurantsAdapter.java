package com.boxtricksys.apps.foodrestaurant.controllers.home.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.boxtricksys.apps.foodrestaurant.R;
import com.boxtricksys.apps.foodrestaurant.models.Restaurant;

import java.util.List;

/**
 * Created by Capacitaciones on 20/08/2016.
 */
public class RestaurantsAdapter  extends ArrayAdapter<Restaurant>{
     List<Restaurant> restaurants;
     public RestaurantsAdapter(Context context, List<Restaurant> restaurantList){
         super(context, R.layout.item_restaurant,restaurantList);
     }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_restaurant, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (position % 2 == 0){
            viewHolder.textRestaurantName.setTextColor(Color.WHITE);
            convertView.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.darker_gray));
        }else{
            viewHolder.textRestaurantName.setTextColor(Color.LTGRAY);
            convertView.setBackgroundColor(Color.WHITE);
        }

        Restaurant restaurant = getItem(position);

        viewHolder.textRestaurantName.setText(restaurant.getName());
        viewHolder.textRestaurantSpeciality.setText(restaurant.getSpeciality());

        return convertView;
    }

    private class ViewHolder{
        TextView textRestaurantName;
        TextView textRestaurantSpeciality;

        public ViewHolder(View view){
            textRestaurantName = (TextView) view.findViewById(R.id.textViewRestaurantName);
            textRestaurantSpeciality = (TextView) view.findViewById(R.id.textViewRestaurantSpeciality);
        }
    }
}
