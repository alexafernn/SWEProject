package com.alexandrafernandez.sweproject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

/**
 * Add Pet Class
 * This class allows users to add pets to their profile
 * CS482 Software Engineering
 * Prof. Mehri
 * @author Jack Cannon
 * @author Alexandra Fernandez
 * @version 3.0 Final Release
 */
public class AddPet extends AppCompatActivity {

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
     * Spinner for selecting pet Type
     */
    Spinner petTypeSpinner;

    /**
     * Switches used to characteristics of pets
     */
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch switch_energetic, switch_noisy, switch_trained, switch_inside_only;

    /**
     * On Create Method
     * Initializes the add pet View and instantiates other view objects for later use
     * @param savedInstanceState android system parameter
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_pet);
        setTitle("Add a New Pet");

        ScreenSize view = new ScreenSize(this);

        pet_name = (TextView) findViewById(R.id.pet_name);
        pet_name.setTextSize(view.getLabelTextSize());

        pet_name_field = (EditText) findViewById(R.id.pet_name_field);
        pet_name_field.setTextSize(view.getEditTextSize());

        animal = (TextView) findViewById(R.id.animal);
        animal.setTextSize(view.getLabelTextSize());

        petTypeSpinner = (Spinner) findViewById(R.id.pet_type_spinner);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.pet_type_choices, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        petTypeSpinner.setAdapter(adapter4);

        other_animal_type_field = (EditText) findViewById(R.id.other_animal_type_field);
        other_animal_type_field.setTextSize(view.getEditTextSize());

        add_photo_button = (Button) findViewById(R.id.add_photo_button);
        add_photo_button.setTextSize(view.getButtonTextSize());

        pet_qualities = (TextView) findViewById(R.id.pet_qualities);
        pet_qualities.setTextSize(view.getLabelTextSize());

        switch_energetic = (Switch) findViewById(R.id.switch_energetic);
        switch_energetic.setTextSize(view.getSwitchTextSize());

        switch_noisy = (Switch) findViewById(R.id.switch_noisy);
        switch_noisy.setTextSize(view.getSwitchTextSize());

        switch_trained = (Switch) findViewById(R.id.switch_trained);
        switch_trained.setTextSize(view.getSwitchTextSize());

        pet_sitting_logistics = (TextView) findViewById(R.id.pet_sitting_logistics);
        pet_sitting_logistics.setTextSize(view.getLabelTextSize());

        switch_inside_only = (Switch) findViewById(R.id.switch_inside_only);
        switch_inside_only.setTextSize(view.getSwitchTextSize());

        settings_save_button = (Button) findViewById(R.id.settings_save_button);
        settings_save_button.setTextSize(view.getButtonTextSize());
    }

    /**
     * Go To Pets method
     * After saving data to device/server, returns to the main My Pets view
     * @param view the reference object calling this method
     */
    public void goToPets(View view)
    {
        System.out.println("in go topets");
        Boolean energeticBoolean;
        Boolean noisyBoolean;
        Boolean trainedBoolean;
        Boolean insideOnlyBoolean;

        String choice = petTypeSpinner.getSelectedItem().toString();


        if(choice.equals("Other (please specify)") && (TextUtils.isEmpty(other_animal_type_field.getText())))
        {
            other_animal_type_field.setError("Type of animal and size is required!");

        }
        else
        {
             finish();
             /*
            JSONObject data = new JSONObject();
            try {
                data.put("is_owner",userTypePetOwner.isChecked());
                data.put("is_sitter", userTypeSitter.isChecked());
                data.put("is_admin",false);
                data.put("is_shelter",false);
                data.put("first_name", firstNameEditText.getText().toString());
                data.put("last_name", lastNameEditText.getText().toString());
                data.put("email", emailEditText.getText().toString());
                data.put("password", passwordEditText.getText().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Log.w("MA", "Creating post");
            UrlPost saveInfo = new UrlPost("http://aiji.cs.loyola.edu/accountcreate", data.toString(), this);
            Log.w("MA", "--------URL POST------------");
            saveInfo.start();

             */
        }

        //do something with entered info, then go back to pets menu
        //EditText animalName = (EditText) findViewById(R.id.pet_name_field);
        //Switch energetic = (Switch) findViewById(R.id.switch3);
        //Switch noisy = (Switch) findViewById(R.id.switch4);
        //Switch trained = (Switch) findViewById(R.id.switch5);

        /*
        String strAnimalName = animalName.getText().toString();

        if(energetic.isChecked())
            energeticBoolean= true;
        else
            energeticBoolean= false;

        if(noisy.isChecked())
            noisyBoolean= true;
        else
            noisyBoolean= false;

        if(trained.isChecked())
            trainedBoolean= true;
        else
            trainedBoolean= false;


         */
        //p = new Pet(strAnimalName, strAnimalType, energeticBoolean, noisyBoolean, trainedBoolean, insideOnlyBoolean, strOtherInfo);
        //System.out.println("the pet u just made is " + p.toString());
       // pets.addPettoList(p);



        //testing
        //System.out.println("the boolean for energetic is " + energeticBoolean);
        //System.out.println("animal name entered is " + strAnimalName);


        //when you go back need to make the text with id no pets invisible
    }

    /**
     * Add Photo method
     * Adds a photo to the pet profile and updates data appropriately
     * @param view
     */
    public void addPhoto(View view) {
        //TODO implement if time allows
    }
}
