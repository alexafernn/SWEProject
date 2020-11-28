package com.alexandrafernandez.sweproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * UrlPost Class
 * This class allows the app to send data to the server
 * CS482 Software Engineering
 * Prof. Mehri
 * @author Jack Cannon
 * @author Alexandra Fernandez
 * @version 3.0 Final Release
 */
public class UrlPost extends Thread {

    /**
     * Strings for managing changing url and data
     */
    String my_url, dataLocation;

    /**
     * Activity and View data
     */
    Context context;

    /**
     * UrlPost constructor
     * @param url the url to send data to
     * @param dataLocation the location to pull the request from
     * @param context the relevant activity / view calling sending data
     */
    public UrlPost(String url, String dataLocation, Context context) {
        this.my_url = url;
        this.dataLocation = dataLocation;
        this.context = context;
        Log.w("MA", my_url + " " + dataLocation);
    }

    /**
     * Run method
     * Performs the url connection and post request
     */
    public void run( ) {

        OutputStream out = null;
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        String finalData = pref.getString(dataLocation, "");
        Log.w("MA", "POST:");
        Log.w("MA", "postString: " + finalData);

        try {
            URL url = new URL(my_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            out = new BufferedOutputStream(connection.getOutputStream());

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.write(finalData);
            writer.flush();
            writer.close();
            out.close();
            connection.connect();

        } catch( Exception e ) { }
    }
}