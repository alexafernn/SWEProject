package com.alexandrafernandez.sweproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
    Switch owner, sitter, adoption;

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

        ScreenSize view = new ScreenSize(this);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

        account_info = (TextView) findViewById(R.id.account_info);
        account_info.setTextSize(view.getLabelTextSize());

        name = (TextView) findViewById(R.id.name);
        name.setTextSize(view.getLabelTextSize());

        name_field = (EditText) findViewById(R.id.name_field);
        name_field.setTextSize(view.getEditTextSize());
        name_field.setText(pref.getString("username", ""));

        phone = (TextView) findViewById(R.id.phone);
        phone.setTextSize(view.getLabelTextSize());

        phone_field = (EditText) findViewById(R.id.phone_field);
        phone_field.setTextSize(view.getEditTextSize());
        phone_field.setText(pref.getString("phone", ""));

        address = (TextView) findViewById(R.id.address);
        address.setTextSize(view.getLabelTextSize());

        address_field = (EditText) findViewById(R.id.address_field);
        address_field.setTextSize(view.getEditTextSize());
        address_field.setText(pref.getString("address", ""));

        save = (Button) findViewById(R.id.settings_save_button);
        save.setTextSize(view.getButtonTextSize());

        payPal = (Button) findViewById(R.id.paypal_link_button);

        profile_types = (TextView) findViewById(R.id.profile_types);
        profile_types.setTextSize(view.getLabelTextSize());

        owner = (Switch) findViewById(R.id.switch1);
        owner.setTextSize(view.getSwitchTextSize());
        owner.setChecked(!pref.getBoolean("ownerProfileSwitch", false));

        sitter = (Switch) findViewById(R.id.switch2);
        sitter.setTextSize(view.getSwitchTextSize());
        sitter.setChecked(!pref.getBoolean("sitterProfileSwitch", false));

        adoption = (Switch) findViewById(R.id.switch3);
        adoption.setChecked(!pref.getBoolean("adoptionProfileSwitch", false));

    }

    /**
     * Go Home Method
     * After saving data to device/server, returns to the main view
     * @param view  the reference object calling this method
     */
    public void goHome(View view) {

        //TODO add server connections in addition to persistent data (mandatory feature)

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();
        String name = name_field.getText().toString();
        editor.putString("username", name);
        String phone = phone_field.getText().toString();
        editor.putString("phone", phone);
        String address = address_field.getText().toString();
        editor.putString("address", address);
        boolean ownerSwitch = owner.isChecked();
        editor.putBoolean("ownerProfileSwitch", !ownerSwitch);
        boolean sitterSwitch = sitter.isChecked();
        editor.putBoolean("sitterProfileSwitch", !sitterSwitch);
        boolean adoptionSwitch = adoption.isChecked();
        editor.putBoolean("adoptionProfileSwitch", !adoptionSwitch);
        editor.commit();

        startActivity(new Intent(this, Intro.class));
        finish();
    }

    /**
     * Paypal Link method
     * Used to link paypal information to user profile
     * @param view the reference object calling this method
     */
    public void doPaypalLink(View view) {
        //TODO implement if time allows (secondary feature)
    }
}
