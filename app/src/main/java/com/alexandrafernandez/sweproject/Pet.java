package com.alexandrafernandez.sweproject;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Pet extends AppCompatActivity {
    public String petName;
    public String animalType;
    //gonna need something to store the image of pet
    boolean energetic;
    boolean noisy;
    boolean trained;
    boolean insideOnly;
    String otherInfo;
    ArrayList<PetData> petList;

    TextView pet_view_name_field, pet_view_animal_field;

    Spinner petTypeSpinner;
    TextView pet_name, animal, pet_qualities, pet_sitting_logistics;
    EditText pet_name_field, other_animal_type_field;
    Button add_photo_button, settings_save_button;
    Switch switch_energetic, switch_noisy, switch_trained, switch_inside_only;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pet);
        setTitle("View My Pet");

        ScreenSize view = new ScreenSize(this);

        petList = new ArrayList<PetData>(); //replace with server pull
        petList.add(new PetData("test1", "animal1"));
        petList.add(new PetData("test2", "animal2"));

        pet_name = (TextView) findViewById(R.id.pet_name_edit);
        pet_name.setTextSize(view.getLabelTextSize());

        pet_name_field = (EditText) findViewById(R.id.pet_name_field_edit);
        pet_name_field.setTextSize(view.getEditTextSize());
        pet_name_field.setText(getIntent().getStringExtra("petToView.name"));

        animal = (TextView) findViewById(R.id.animal_edit);
        animal.setTextSize(view.getLabelTextSize());

        petTypeSpinner = (Spinner) findViewById(R.id.pet_type_spinner_edit);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.pet_type_choices, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        petTypeSpinner.setAdapter(adapter4);

        other_animal_type_field = (EditText) findViewById(R.id.other_animal_type_field_edit);
        other_animal_type_field.setTextSize(view.getEditTextSize());
        other_animal_type_field.setText(getIntent().getStringExtra("petToView.animal"));

        add_photo_button = (Button) findViewById(R.id.add_photo_button_edit);
        add_photo_button.setTextSize(view.getButtonTextSize());

        pet_qualities = (TextView) findViewById(R.id.pet_qualities_edit);
        pet_qualities.setTextSize(view.getLabelTextSize());

        switch_energetic = (Switch) findViewById(R.id.switch_energetic_edit);
        switch_energetic.setTextSize(view.getSwitchTextSize());

        switch_noisy = (Switch) findViewById(R.id.switch_noisy_edit);
        switch_noisy.setTextSize(view.getSwitchTextSize());

        switch_trained = (Switch) findViewById(R.id.switch_trained_edit);
        switch_trained.setTextSize(view.getSwitchTextSize());

        pet_sitting_logistics = (TextView) findViewById(R.id.pet_sitting_logistics_edit);
        pet_sitting_logistics.setTextSize(view.getLabelTextSize());

        switch_inside_only = (Switch) findViewById(R.id.switch_inside_only_edit);
        switch_inside_only.setTextSize(view.getSwitchTextSize());

        settings_save_button = (Button) findViewById(R.id.settings_save_button_edit);
        settings_save_button.setTextSize(view.getButtonTextSize());
    }

    public void goToPets(View view) {

        //write all pet data to server here

        finish();
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

