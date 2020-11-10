package com.alexandrafernandez.sweproject;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class AddPet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_pet);
        setTitle("Add a New Pet");
    }

    public void goToPets(View view) {
        //do something with entered info, then go back to pets menu
        finish();
    }

    public void addPhoto(View view) {

    }
}
