package com.alexandrafernandez.sweproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class petBeingSat extends AppCompatActivity {

    /**
     * Text Views for identifying field components
     */
    TextView name_label, name_label_value, animal_label, animal_label_value, pet_qualities_label, other_label, other_label_value;

    Switch energetic_value, noisy_value, trained_value;

    Button goBackValue;

    /**
     * Server interaction objects
     */
    SharedPreferences pref;
    String clientID, clientAuth, pet_id;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pet_being_sat);
        setTitle("Pet Details");


        //GET Request - get id/auth
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        clientID = pref.getString("id", "");
        clientAuth = pref.getString("auth", "");

        pet_id = getIntent().getStringExtra("pet_id");

        String other_type = "", other_info = "";
        int pet_type = 0;
        boolean energetic = false, noisy = false, trained = false;
        String name = "", attributes = "";

        //Url connection
        UrlGet userInfo = new UrlGet("http://aiji.cs.loyola.edu/petinfo?id=" + clientID + "&pet_id=" + pet_id + "&auth=" + clientAuth, "pet.userInfo", this);
        userInfo.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Save response
        String json = pref.getString("pet.userInfo", "");
        Log.w("MA", "json: " + json);

        try {
            JSONObject jsonObject1 = new JSONObject(json);
            name = jsonObject1.getString("name");
            attributes = jsonObject1.getString("attributes");
        } catch (JSONException json_e) {
            Log.w("MA", json_e.toString());
        }

        //Parse attributes
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

        name_label = (TextView) findViewById(R.id.pet_name_edit2);
        name_label.setTextSize((float) (0.5*view.getLabelTextSize()));

        name_label_value = (TextView) findViewById(R.id.pet_name_field_edit1);
        name_label_value.setTextSize((float) (0.5*view.getLabelTextSize()));
        name_label_value.setText(name); //need to get from server

        animal_label = (TextView) findViewById(R.id.animalLabel);
        animal_label.setTextSize((float) (0.5*view.getLabelTextSize()));

        String textPetType ="";
        if(pet_type == 1) {
            textPetType = "Dog - Small";
        }
        else if(pet_type == 2) {
            textPetType = "Dog - Medium";
        }
        else if(pet_type == 3) {
            textPetType = "Dog - Small";
        }
        else if(pet_type == 4) {
            textPetType = "Cat";
        }
        else if(pet_type == 5) {
            textPetType = other_type;
        }

        animal_label_value = (TextView) findViewById(R.id.pet_type_text);
        animal_label_value.setTextSize((float) (0.5*view.getLabelTextSize()));
        animal_label_value.setText(textPetType); //need to get from server


        pet_qualities_label = (TextView) findViewById(R.id.pet_qualities_edit2);
        pet_qualities_label.setTextSize((float) (0.5*view.getLabelTextSize()));

        energetic_value = (Switch) findViewById(R.id.switch_energetic_edit2);
        energetic_value.setClickable(false);
        energetic_value.setChecked(energetic);

        noisy_value = (Switch) findViewById(R.id.switch_noisy_edit2);
        noisy_value.setClickable(false);
        noisy_value.setChecked(noisy);

        trained_value = (Switch) findViewById(R.id.switch_trained_edit2);
        trained_value.setClickable(false);
        trained_value.setChecked(trained);

        other_label = (TextView) findViewById(R.id.other_pet_info2);
        pet_qualities_label.setTextSize((float) (0.5*view.getLabelTextSize()));

        other_label_value = (TextView) findViewById(R.id.other_animal_type_field2);
        other_label_value.setTextSize((float)(0.5 * view.getLabelTextSize()));

        other_label_value.setText(other_info);

    }


    public void onGoBack()
    {
        startActivity(new Intent(this, petsSittingList.class));
        finish();
    }


}