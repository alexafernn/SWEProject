package com.alexandrafernandez.sweproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Profile Class
 * This class allows a user to view and modify their profile settings
 * CS482 Software Engineering
 * Prof. Mehri
 * @author Jack Cannon
 * @author Alexandra Fernandez
 * @version 3.0 Final Release
 */
public class Profile extends AppCompatActivity {

    /**
     * Edit Text Views for user input of relevant information
     */
    EditText name_field, phone_field, address_field;

    /**
     * Buttons used to confirm data and/or move to another activity
     */
    Button save, payPal;

    /**
     * Switches used to assign profile type
     */
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch owner, sitter;

    /**
     * Text Views for identifying field components
     */
    TextView account_info, name, phone, address, profile_types;

    /**
     * On Create Method
     * Initializes the profile View and instantiates other view objects for later use
     * @param savedInstanceState android system parameter
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        setTitle("Profile Settings");

        //GET Request - get id/auth
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String clientID = pref.getString("id", "");
        String clientAuth = pref.getString("auth", "");

        //Url connection
        UrlGet userInfo = new UrlGet("http://aiji.cs.loyola.edu/accountinfo?id=" + clientID + "&auth=" + clientAuth ,"profile.userInfo", this);
        Log.w("MA", "--------URL GET------------");
        userInfo.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Save response
        String json = pref.getString("profile.userInfo", "");
        Log.w("MA", "json: " + json);
        boolean is_owner=false, is_sitter=false;
        String first_name="", last_name="", email="", phone_number="", my_address="";
        try {
            JSONObject jsonObject1 = new JSONObject(json);
            first_name = jsonObject1.getString( "first_name" );
            last_name = jsonObject1.getString("last_name");
            email = jsonObject1.getString("email");
            is_owner = jsonObject1.getBoolean("is_owner");
            is_sitter = jsonObject1.getBoolean("is_sitter");
            phone_number = jsonObject1.getString("phone_number");
            my_address = jsonObject1.getString("address");
        } catch( JSONException json_e ) {
            Log.w("MA", json_e.toString());
        }

        ScreenSize view = new ScreenSize(this);

        account_info = (TextView) findViewById(R.id.account_info);
        account_info.setTextSize(view.getLabelTextSize());

        name = (TextView) findViewById(R.id.name);
        name.setTextSize(view.getLabelTextSize());
        name.setText(first_name + " " + last_name); //TODO Split into first and last name

        name_field = (EditText) findViewById(R.id.name_field);
        name_field.setTextSize(view.getEditTextSize());
        name_field.setText(pref.getString("username", ""));

        //TODO add email field

        phone = (TextView) findViewById(R.id.phone);
        phone.setTextSize(view.getLabelTextSize());

        phone_field = (EditText) findViewById(R.id.phone_field);
        phone_field.setTextSize(view.getEditTextSize());
        phone_field.setText(phone_number);

        address = (TextView) findViewById(R.id.address);
        address.setTextSize(view.getLabelTextSize());

        address_field = (EditText) findViewById(R.id.address_field);
        address_field.setTextSize(view.getEditTextSize());
        address_field.setText(my_address);

        save = (Button) findViewById(R.id.settings_save_button);
        save.setTextSize(view.getButtonTextSize());

        profile_types = (TextView) findViewById(R.id.profile_types);
        profile_types.setTextSize(view.getLabelTextSize());

        owner = (Switch) findViewById(R.id.switch1);
        owner.setTextSize(view.getSwitchTextSize());
        owner.setChecked(is_owner);

        sitter = (Switch) findViewById(R.id.switch2);
        sitter.setTextSize(view.getSwitchTextSize());
        sitter.setChecked(is_sitter);

    }

    /**
     * Go Home Method
     * After saving data to device/server, returns to the main view
     * @param view  the reference object calling this method
     */
    public void goHome(View view) {

        JSONObject data = new JSONObject();
        try {
            data.put("is_owner", owner.isChecked());
            data.put("is_sitter", sitter.isChecked());
            data.put("is_admin",false);
            data.put("is_shelter",false);
            data.put("address", address_field.getText().toString());
            data.put("phone_number", phone_field.getText().toString());
            //data.put("first_name", firstNameEditText.getText().toString());
            //data.put("last_name", lastNameEditText.getText().toString());   //TODO add these later
            //data.put("email", emailEditText.getText().toString());
            //data.put("password", passwordEditText.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        UrlPut saveInfo = new UrlPut("http://aiji.cs.loyola.edu/accountmodify", data.toString(), this, "profile.response");
        saveInfo.start();

        startActivity(new Intent(this, Intro.class));
        finish();
    }
}
