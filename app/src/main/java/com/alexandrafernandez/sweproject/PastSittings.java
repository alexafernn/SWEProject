package com.alexandrafernandez.sweproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Past Sittings Class
 * This class allows users to see their past sittings
 * CS482 Software Engineering
 * Prof. Mehri
 * @author Jack Cannon
 * @author Alexandra Fernandez
 * @version 3.0 Final Release
 */
public class PastSittings extends AppCompatActivity {

    /**
     * On Create Method
     * Initializes the add pet View and instantiates other view objects for later use
     * @param savedInstanceState android system parameter
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.past_sittings);
        setTitle("Past Sittings");

        //TODO implement if time allows (low priority feature)
    }
}
