package com.alexandrafernandez.sweproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * UrlGet Class
 * This class allows the app to request data from the server
 * CS482 Software Engineering
 * Prof. Mehri
 * @author Jack Cannon
 * @author Alexandra Fernandez
 * @version 3.0 Final Release
 */
public class UrlGet extends Thread {

    /**
     * Strings for managing changing url and data
     */
    String my_url, dataLocation;

    /**
     * Activity and View data
     */
    Context context;

    /**
     * UrlGet constructor
     * @param url the url to request data from
     * @param dataLocation the location to save the response to
     * @param context the relevant activity / view calling requesting data
     */
    public UrlGet(String url, String dataLocation, Context context) {
        this.my_url = url;
        this.dataLocation = dataLocation;
        this.context = context;
    }

    /**
     * Run method
     * Performs the url connection, request and encapsulates the server response
     */
    public void run( ) {

        Log.w("MA", "get: " + my_url);

        try {

            URL url = new URL(my_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder s= new StringBuilder();
            while ((line = rd.readLine()) != null) {
                s.append(line);
            }

            Log.w("MA", "received from server:");
            Log.w("MA", s.toString());

            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString(dataLocation, s.toString());
            editor.apply();

        } catch( Exception e ) {
            Log.w("MA", e.toString());
        }
    }
}
