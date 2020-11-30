package com.alexandrafernandez.sweproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Pet Picker Class
 * This class serves as the Model for the Pet Class
 * CS482 Software Engineering
 * Prof. Mehri
 * @author Jack Cannon
 * @author Alexandra Fernandez
 * @version 3.0 Final Release
 */
public class PetPicker extends AppCompatActivity {

    /**
     * On Create Method
     * Initializes the add pet View and instantiates other view objects for later use
     * @param savedInstanceState android system parameter
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pet_picker);
        setTitle("Select my Pets");

        //TODO implement the pet picker if time allows (low priority feature)
    }
}
