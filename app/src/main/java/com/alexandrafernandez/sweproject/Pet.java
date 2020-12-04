package com.alexandrafernandez.sweproject;

import android.annotation.SuppressLint;
import android.content.Intent;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
     * Spinner for selecting pet Type
     */
    Spinner petTypeSpinner;

    /**
     * Text Views for identifying field components
     */
    TextView pet_name, animal, pet_qualities, other_pet_info;

    /**
     * Edit Text Views for user input of relevant information
     */
    EditText pet_name_field, other_animal_type_field, other_info_field;

    /**
     * Buttons used to confirm data and/or move to another activity
     */
    Button settings_save_button;

    /**
     * Switches used to characteristics of adoption interest
     */
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch switch_energetic, switch_noisy, switch_trained;

    /**
     * Server interaction objects
     */
    SharedPreferences pref;
    String clientID, clientAuth, pet_id;

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

        //GET Request - get id/auth
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        clientID = pref.getString("id", "");
        clientAuth = pref.getString("auth", "");

        pet_id = getIntent().getStringExtra("pet_id");

        //Url connection
        UrlGet userInfo = new UrlGet("http://aiji.cs.loyola.edu/petinfo?id=" + clientID + "&pet_id=" + pet_id +"&auth=" + clientAuth ,"pet.userInfo", this);
        userInfo.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Save response
        String json = pref.getString("pet.userInfo", "");
        Log.w("MA", "json: " + json);
        String name="", attributes="";
        try {
            JSONObject jsonObject1 = new JSONObject(json);
            name = jsonObject1.getString( "name" );
            attributes = jsonObject1.getString("attributes");
        } catch( JSONException json_e ) {
            Log.w("MA", json_e.toString());
        }

        //Parse attributes
        String other_type="", other_info="";
        int pet_type = 0;
        boolean energetic=false, noisy=false, trained=false;
        try {
            JSONObject data = new JSONObject(attributes);
            pet_type = data.getInt("pet_type");
            other_type = data.getString("other_type");
            energetic = data.getBoolean("energetic");
            noisy = data.getBoolean("noisy");
            trained = data.getBoolean("trained");
            other_info = data.getString("other_info");

        } catch (JSONException json_e2) {
            Log.w("MA", json_e2.toString());
        }

        ScreenSize view = new ScreenSize(this);

        pet_name = (TextView) findViewById(R.id.pet_name_edit);
        pet_name.setTextSize(view.getLabelTextSize());

        pet_name_field = (EditText) findViewById(R.id.pet_name_field_edit);
        pet_name_field.setTextSize(view.getEditTextSize());
        pet_name_field.setText(name);

        animal = (TextView) findViewById(R.id.animal_edit);
        animal.setTextSize(view.getLabelTextSize());

        petTypeSpinner = (Spinner) findViewById(R.id.pet_type_spinner_edit);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.pet_type_choices, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        petTypeSpinner.setAdapter(adapter4);
        petTypeSpinner.setSelection(pet_type);

        other_animal_type_field = (EditText) findViewById(R.id.other_animal_type_field_edit);
        other_animal_type_field.setTextSize(view.getEditTextSize());
        other_animal_type_field.setText(other_type);

        pet_qualities = (TextView) findViewById(R.id.pet_qualities_edit);
        pet_qualities.setTextSize(view.getLabelTextSize());

        switch_energetic = (Switch) findViewById(R.id.switch_energetic_edit);
        switch_energetic.setTextSize(view.getSwitchTextSize());
        switch_energetic.setChecked(energetic);

        switch_noisy = (Switch) findViewById(R.id.switch_noisy_edit);
        switch_noisy.setTextSize(view.getSwitchTextSize());
        switch_noisy.setChecked(noisy);

        switch_trained = (Switch) findViewById(R.id.switch_trained_edit);
        switch_trained.setTextSize(view.getSwitchTextSize());
        switch_trained.setChecked(trained);

        other_pet_info = (TextView) findViewById(R.id.other_pet_info);
        other_pet_info.setTextSize(view.getLabelTextSize());

        other_info_field = (EditText) findViewById(R.id.other_animal_type_field_TextEdit);
        other_info_field.setTextSize(view.getEditTextSize());
        other_info_field.setText(other_info);

        settings_save_button = (Button) findViewById(R.id.settings_save_button_edit);
        settings_save_button.setTextSize(view.getButtonTextSize());
    }

    /**
     * Go To Pets method
     * After saving data to device/server, returns to the main My Pets view
     * @param view the reference object calling this method
     */
    public void goToPets(View view) {

        JSONObject attr = new JSONObject();
        try {
            attr.put("pet_name", pet_name_field.getText().toString());
            attr.put("pet_type", petTypeSpinner.getSelectedItemPosition());
            attr.put("other_type", other_animal_type_field.getText().toString());
            attr.put("energetic", switch_energetic.isChecked());
            attr.put("noisy", switch_noisy.isChecked());
            attr.put("trained", switch_trained.isChecked());
            attr.put("other_info", other_info_field.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String attributes = attr.toString();

        JSONObject data = new JSONObject();
        try {
            data.put("name", pet_name_field.getText().toString());
            data.put("attributes", attributes);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Post - create a new pet
        if(pet_id.equals(null)){
            UrlPut saveInfo = new UrlPut("http://aiji.cs.loyola.edu/petcreate", data.toString(), this, "pet.response");
            saveInfo.start();
        }

        //Put - modify an existing pet
        else {
            //TODO check this link later
            UrlPut saveInfo = new UrlPut("http://aiji.cs.loyola.edu/petmodify", data.toString(), this, "pet.response");
            saveInfo.start();
        }

        //give persistent data time to write
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        startActivity(new Intent(this, Pets.class));
        finish();
    }
}

