package com.alexandrafernandez.sweproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    String my_url, data;

    /**
     * Activity and View data
     */
    Context context;

    /**
     * UrlPost constructor
     * @param url the url to send data to
     * @param data the location to pull the request from
     * @param context the relevant activity / view calling sending data
     */
    public UrlPost(String url, String data, Context context) {
        this.my_url = url;
        this.data = data;
        this.context = context;
        Log.w("MA", my_url + " " + data);
    }

    /**
     * Run method
     * Performs the url connection and post request
     */
    public void run( ) {

        Log.w("MA", "POST:");
        Log.w("MA", "postString: " + data);

        try {

            URL url = new URL(my_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            String params = data;
            OutputStream os = conn.getOutputStream();
            byte[] input = params.getBytes("utf-8");
            os.write(input, 0, input.length);
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder s= new StringBuilder();
            while ((line = rd.readLine()) != null) {
                Log.w("MA", "--------LINE:" + line);
                s.append(line);
            }
            Log.w("MA", "Post request complete");
            Log.w("MA", s.toString());


        } catch( Exception e ) { Log.w("MA" , e.getMessage());}
    }
}