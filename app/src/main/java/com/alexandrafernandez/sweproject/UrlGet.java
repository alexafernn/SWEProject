package com.alexandrafernandez.sweproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class UrlGet extends Thread {

    String my_url;
    String dataLocation;
    Context context;

    public UrlGet(String url, String dataLocation, Context context) {
        this.my_url = url;
        this.dataLocation = dataLocation;
        this.context = context;
    }

    public void run( ) {

        try {
            URL url = new URL(my_url);
            InputStream is = url.openStream();
            Scanner scan = new Scanner( is );
            String s = "";
            while(scan.hasNext()) {
                s += scan.nextLine();
            }

            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString(dataLocation, s);
            editor.commit();

        } catch( Exception e ) { }
    }
}
