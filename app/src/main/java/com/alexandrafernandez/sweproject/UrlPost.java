package com.alexandrafernandez.sweproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class UrlPost extends Thread {

    String my_url;
    String dataLocation;
    String dataType;
    Context context;

    public UrlPost(String url, String dataLocation, String dataType, Context context) {
        this.my_url = url;
        this.dataLocation = dataLocation;
        this.dataType = dataType;
        this.context = context;
    }

    public void run( ) {

        try {
            URL url = new URL(my_url);
            URLConnection connection = url.openConnection();
            connection.setDoOutput( true );
            OutputStream os = connection.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter( os );

            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
            String finalData;

            switch (dataType) {
                case "string":
                    finalData = pref.getString(dataLocation, "");
                    break;
                case "int":
                    finalData = Integer.toString(pref.getInt(dataLocation, 0));
                    break;
                case "float":
                    finalData = Float.toString(pref.getFloat(dataLocation, 0));
                    break;
                case "boolean":
                    finalData = Boolean.toString(pref.getBoolean(dataLocation, false));
                    break;
                default:
                    finalData = "error: data type not supported";
                    break;
            }

            osw.write(dataLocation + "=" + finalData);
            osw.flush();

        } catch( Exception e ) { }
    }
}