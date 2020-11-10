package com.alexandrafernandez.sweproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class PetPicker extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pet_picker);
        setTitle("Select my Pets");
    }
}
