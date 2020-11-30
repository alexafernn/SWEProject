package com.alexandrafernandez.sweproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Pet Class
 * This class allows create and/or modify a pet
 * CS482 Software Engineering
 * Prof. Mehri
 * @author Jack Cannon
 * @author Alexandra Fernandez
 * @version 3.0 Final Release
 */
public class Pet extends AppCompatActivity {

    /**
     * List managing the pets an owner has
     */
    ArrayList<PetData> petList;

    /**
     * Spinner for selecting pet Type
     */
    Spinner petTypeSpinner;

    /**
     * Text Views for identifying field components
     */
    TextView pet_name, animal, pet_qualities, pet_sitting_logistics;

    /**
     * Edit Text Views for user input of relevant information
     */
    EditText pet_name_field, other_animal_type_field;

    /**
     * Buttons used to confirm data and/or move to another activity
     */
    Button add_photo_button, settings_save_button;

    /**
     * Switches used to characteristics of adoption interest
     */
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch switch_energetic, switch_noisy, switch_trained, switch_inside_only;

    /**
     * On Create Method
     * Initializes the pet View and instantiates other view objects for later use
     * @param savedInstanceState android system parameter
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pet);
        setTitle("View My Pet");

        ScreenSize view = new ScreenSize(this);

        petList = new ArrayList<PetData>(); //replace with server pull
        petList.add(new PetData("test1", "animal1", "", true, false, true, false));
        petList.add(new PetData("test2", "animal2","", false, true, false, true));

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
        other_animal_type_field.setText(getIntent().getStringExtra("petToView.other_type"));

        add_photo_button = (Button) findViewById(R.id.add_photo_button_edit);
        add_photo_button.setTextSize(view.getButtonTextSize());

        pet_qualities = (TextView) findViewById(R.id.pet_qualities_edit);
        pet_qualities.setTextSize(view.getLabelTextSize());

        switch_energetic = (Switch) findViewById(R.id.switch_energetic_edit);
        switch_energetic.setTextSize(view.getSwitchTextSize());
        switch_energetic.setChecked(getIntent().getBooleanExtra("petToView.energetic", false));

        switch_noisy = (Switch) findViewById(R.id.switch_noisy_edit);
        switch_noisy.setTextSize(view.getSwitchTextSize());
        switch_noisy.setChecked(getIntent().getBooleanExtra("petToView.noisy", false));

        switch_trained = (Switch) findViewById(R.id.switch_trained_edit);
        switch_trained.setTextSize(view.getSwitchTextSize());
        switch_trained.setChecked(getIntent().getBooleanExtra("petToView.trained", false));

        pet_sitting_logistics = (TextView) findViewById(R.id.pet_sitting_logistics_edit);
        pet_sitting_logistics.setTextSize(view.getLabelTextSize());

        switch_inside_only = (Switch) findViewById(R.id.switch_inside_only_edit);
        switch_inside_only.setTextSize(view.getSwitchTextSize());
        switch_inside_only.setChecked(getIntent().getBooleanExtra("petToView.inside_only", false));

        settings_save_button = (Button) findViewById(R.id.settings_save_button_edit);
        settings_save_button.setTextSize(view.getButtonTextSize());
    }

    /**
     * Go To Pets method
     * After saving data to device/server, returns to the main My Pets view
     * @param view the reference object calling this method
     */
    public void goToPets(View view) {

        //TODO write all pet data to server here (mandatory feature)

        startActivity(new Intent(this, Pets.class));
        finish();
    }
}

