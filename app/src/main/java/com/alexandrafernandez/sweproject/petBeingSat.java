package com.alexandrafernandez.sweproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class petBeingSat extends AppCompatActivity {

    /**
     * Text Views for identifying field components
     */
    TextView name_label, name_label_value, animal_label, animal_label_value, pet_qualities_label, other_label, other_label_value;

    Switch energetic_value, noisy_value, trained_value;

    Button goBackValue;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pet_being_sat);
        setTitle("Pet Details");

        ScreenSize view = new ScreenSize(this);

        name_label = (TextView) findViewById(R.id.pet_name_edit2);
        name_label.setTextSize((float) (0.5*view.getLabelTextSize()));

        name_label_value = (TextView) findViewById(R.id.pet_name_field_edit1);
        name_label_value.setTextSize((float) (0.5*view.getLabelTextSize()));
        name_label_value.setText("Cocco"); //need to get from server

        animal_label = (TextView) findViewById(R.id.animalLabel);
        animal_label.setTextSize((float) (0.5*view.getLabelTextSize()));

        animal_label_value = (TextView) findViewById(R.id.pet_type_text);
        animal_label_value.setTextSize((float) (0.5*view.getLabelTextSize()));
        animal_label_value.setText("Dog"); //need to get from server


        pet_qualities_label = (TextView) findViewById(R.id.pet_qualities_edit2);
        pet_qualities_label.setTextSize((float) (0.5*view.getLabelTextSize()));

        energetic_value = (Switch) findViewById(R.id.switch_energetic_edit2);
        energetic_value.setClickable(false);
        energetic_value.setChecked(true);

        noisy_value = (Switch) findViewById(R.id.switch_noisy_edit2);
        noisy_value.setClickable(false);
        noisy_value.setChecked(false);

        trained_value = (Switch) findViewById(R.id.switch_trained_edit2);
        trained_value.setClickable(false);
        trained_value.setChecked(true);

        other_label = (TextView) findViewById(R.id.other_pet_info2);
        pet_qualities_label.setTextSize((float) (0.5*view.getLabelTextSize()));

        other_label_value = (TextView) findViewById(R.id.other_animal_type_field2);
        other_label_value.setTextSize((float)(0.5 * view.getLabelTextSize()));

        other_label_value.setText("doesnt like being pet");

    }


    public void onGoBack()
    {
        finish();
        //startActivity(new Intent(this, petsSittingList.class));
    }


}