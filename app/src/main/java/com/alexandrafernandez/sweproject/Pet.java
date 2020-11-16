package com.alexandrafernandez.sweproject;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Pet extends AppCompatActivity {
    public String petName;
    public String animalType;
    //gonna need something to store the image of pet
    boolean energetic;
    boolean noisy;
    boolean trained;
    boolean insideOnly;
    String otherInfo;

    TextView pet_view_name_field, pet_view_animal_field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pet);
        setTitle("View My Pet");

        ScreenSize view = new ScreenSize(this);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

        Log.w("MA", "made it to Pet");

        pet_view_name_field = (TextView) findViewById(R.id.pet_view_name_field);
        pet_view_name_field.setTextSize(view.getLabelTextSize());
        pet_view_name_field.setText(pref.getString("petToView.name","null"));

        pet_view_animal_field = (TextView) findViewById(R.id.pet_view_animal_field);
        pet_view_animal_field.setTextSize(view.getLabelTextSize());
        pet_view_animal_field.setText(pref.getString("petToView.animal", "null"));
    }

    /*
    public Pet(String petName, String animalType, boolean energetic, boolean noisy, boolean trained, boolean insideOnly, String otherInfo)
    {
        this.petName = petName;
        this.animalType=animalType;
        this.energetic= energetic;
        this.noisy=noisy;
        this.trained = trained;
        this.insideOnly = insideOnly;
        this.otherInfo = otherInfo;
    }

     */

    //test constructor
    /*
    public Pet(String name, String animal) {
        petName = name;
        animalType = animal;
    }

     */

    /*
    @Override
    public String toString()
    {
        return petName;
        return("Pet Name:"+this.petName+
                " animal Type:"+this.animalType +
                " Energetic:"+ this.energetic+
                " Noisy:"+ this.noisy+
                " trained:"+this.trained+
                " insideOnly:"+this.insideOnly+
                " otherInfo:"+this.otherInfo);
    }
    */

    /*
    public String getPetName()
    {
        return this.petName;
    }

    */
}

