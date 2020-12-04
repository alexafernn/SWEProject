package com.alexandrafernandez.sweproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * UrlDelete Class
 * This class allows the app to delete data from the server
 * CS482 Software Engineering
 * Prof. Mehri
 * @author Jack Cannon
 * @author Alexandra Fernandez
 * @version 3.0 Final Release
 */
public class UrlDelete extends Thread {

    /**
     * Strings for managing changing url and data
     */
    String my_url, data, responseLocation;

    /**
     * Activity and View data
     */
    Context context;

    /**
     * UrlDelete constructor
     * @param url the url to request data from
     * @param responseLocation the location to save the response to
     * @param context the relevant activity / view calling requesting data
     * @param data the data to delete
     */
    public UrlDelete(String url, String data, String responseLocation, Context context) {
        this.my_url = url;
        this.responseLocation = responseLocation;
        this.context = context;
        this.data = data;
    }

    /**
     * Run method
     * Performs the url connection and post request
     */
    public void run( ) {

        Log.w("MA", "DELETE: " + my_url + " --> " + data);

        try {
            //Request
            URL url = new URL(my_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type", "application/json; utf-8");

            //Response
            String params = data;
            OutputStream os = conn.getOutputStream();
            byte[] input = params.getBytes("utf-8");
            os.write(input, 0, input.length);
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder s= new StringBuilder();
            while ((line = rd.readLine()) != null) {
                s.append(line);
            }
            Log.w("MA", "DELETE SUCCESSFUL - response: " + s.toString());

            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString(responseLocation, s.toString());
            editor.apply();

        } catch( Exception e ) { Log.w("MA" , "DELETE FAILED: " + e.getMessage());}
    }
}
