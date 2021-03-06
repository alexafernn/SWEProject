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
import android.widget.Toast;

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
    EditText first_name_field, last_name_field, phone_field, address_field,email_field, password_field;

    /**
     * Buttons used to confirm data and/or move to another activity
     */
    Button save;

    /**
     * Switches used to assign profile type
     */
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch owner, sitter;

    /**
     * Text Views for identifying field components
     */
    TextView account_info, profile_types;

    /**
     * Server interaction objects
     */
    SharedPreferences pref;
    String clientID, clientAuth;

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
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        clientID = pref.getString("id", "");
        clientAuth = pref.getString("auth", "");

        //Url connection
        UrlGet userInfo = new UrlGet("http://aiji.cs.loyola.edu/accountinfo?id=" + clientID + "&auth=" + clientAuth ,"profile.userInfo", this);
        Log.w("MA", "--------Profile URL GET------------");
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
            phone_number = jsonObject1.getString("phone_number");
            my_address = jsonObject1.getString("address");
            is_owner = jsonObject1.getBoolean("is_owner");
            is_sitter = jsonObject1.getBoolean("is_sitter");
            //can we get password back ?
        } catch( JSONException json_e ) {
            Log.w("MA", json_e.toString());
        }

        ScreenSize view = new ScreenSize(this);

        account_info = (TextView) findViewById(R.id.account_info);
        account_info.setTextSize(view.getLabelTextSize());

        first_name_field = (EditText) findViewById(R.id.first_name_field_editText);
        first_name_field.setTextSize((float) (0.9*view.getEditTextSize()));
        //adding the information from server
        first_name_field.setText(first_name);


        last_name_field = (EditText) findViewById(R.id.LastNameEditText);
        last_name_field.setTextSize((float) (0.9*view.getEditTextSize()));
        //adding the information from server
        last_name_field.setText(last_name);

        email_field = (EditText) findViewById(R.id.emailEditText);
        email_field.setTextSize((float) (0.9*view.getEditTextSize()));
        //adding info from the server
        email_field.setText(email);

        phone_field = (EditText) findViewById(R.id.phoneFieldEditText);
        phone_field.setTextSize((float) (0.9*view.getEditTextSize()));
        phone_field.setText(phone_number);

        address_field = (EditText) findViewById(R.id.addressEditText);
        address_field.setTextSize((float) (0.9*view.getEditTextSize()));
        address_field.setText(my_address);

        save = (Button) findViewById(R.id.settings_save_button);
        save.setTextSize(view.getButtonTextSize());

        profile_types = (TextView) findViewById(R.id.profile_types);
        profile_types.setTextSize(view.getLabelTextSize());

        owner = (Switch) findViewById(R.id.switch1);
        owner.setTextSize((float) (0.85* view.getSwitchTextSize()));
        owner.setChecked(is_owner);

        sitter = (Switch) findViewById(R.id.switch2);
        sitter.setTextSize((float) (0.85 * view.getSwitchTextSize()));
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
            data.put("first_name", first_name_field.getText().toString());
            data.put("last_name", last_name_field.getText().toString());
            data.put("email", email_field.getText().toString());
            data.put("auth", clientAuth);
            data.put("id", clientID);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        UrlPut saveInfo = new UrlPut("http://aiji.cs.loyola.edu/accountmodify", data.toString(), this, "profile.response");
        saveInfo.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SharedPreferences pref2 = PreferenceManager.getDefaultSharedPreferences(this);
        String response = pref2.getString("profile.response", "");
        boolean success = false;
        try {
            JSONObject jsonObject1 = new JSONObject(response);
            success = jsonObject1.getBoolean("success");
        } catch( JSONException json_e ) {
            if(!success) {
                Toast.makeText(this, "Unable to complete request.", Toast.LENGTH_LONG).show();
                return;
            }
        }

        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("ownerSwitchChecked", owner.isChecked());
        editor.putBoolean("sitterSwitchChecked", sitter.isChecked());
        editor.apply();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        finish();
    }
}
