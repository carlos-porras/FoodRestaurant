package com.boxtricksys.apps.foodrestaurant.utils;

import android.util.Log;

import java.io.IOError;
import java.io.IOException;
import java.io.InterruptedIOException;

/**
 * Created by Capacitaciones on 06/08/2016.
 */
public class Network {
    private static final String TAG = Network.class.getName();
    public static boolean isOnline(){
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int exitValue = process.waitFor();
            return (exitValue == 0);
        }catch (IOException | InterruptedException e){
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

}
