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

public class UrlPost extends Thread {

    String my_url;
    String dataLocation;
    Context context;

    public UrlPost(String url, String dataLocation, Context context) {
        this.my_url = url;
        this.dataLocation = dataLocation;
        this.context = context;
        Log.w("MA", my_url + " " + dataLocation);
    }

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