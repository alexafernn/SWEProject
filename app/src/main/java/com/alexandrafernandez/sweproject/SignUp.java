package com.alexandrafernandez.sweproject;

import android.annotation.SuppressLint;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * SignUp Class
 * This class allows a sitter to sign up for an account if they don't have one already
 * CS482 Software Engineering
 * Prof. Mehri
 * @author Jack Cannon
 * @author Alexandra Fernandez
 * @version 3.0 Final Release
 */
public class SignUp extends AppCompatActivity {

    /**
     * Text Views for identifying field components
     */
    TextView  signupFirstName, signupLastName, phoneNumber, email, address, password, profile_types;

    /**
     * Switches used to indicate profile type
     */
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch userTypePetOwner, userTypeSitter;

    /**
     * Buttons used to confirm data and/or move to another activity
     */
    Button saveButton;

    /**
     * Edit Text Views for user input of relevant information
     */
    EditText firstNameEditText, lastNameEditText, phoneEditText, emailEditText, addyEditText, passwordEditText;

    /**
     * Initialize components of a sign up request
     */
    String first_name = ""; String last_name = ""; String myEmail = "";

    /**
     * On Create Method
     * Initializes the signup View and instantiates other view objects for later use
     * @param savedInstanceState android system parameter
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        setTitle("New Account Sign Up");

        ScreenSize view = new ScreenSize(this);

        //sign up
        signupFirstName = (TextView) findViewById(R.id.signupFirstName);
        signupFirstName.setTextSize((float) (0.5*view.getLabelTextSize()));

        signupLastName = (TextView) findViewById(R.id.signupLastName);
        signupLastName.setTextSize((float) (0.5*view.getLabelTextSize()));


        //first name
        firstNameEditText = (EditText) findViewById(R.id.FirstNameEditText);
        firstNameEditText.setTextSize((float) (0.5*view.getEditTextSize()));
        firstNameEditText.setText(first_name);

        //last name
        lastNameEditText = (EditText) findViewById(R.id.LastNameEditText);
        lastNameEditText.setTextSize((float) (0.5*view.getEditTextSize()));
        lastNameEditText.setText(last_name);

        phoneNumber = (TextView) findViewById(R.id.phoneNumber);
        phoneNumber.setTextSize((float) (0.5*view.getLabelTextSize()));

        phoneEditText = (EditText) findViewById(R.id.PhoneEditText);
        phoneEditText.setTextSize((float) (0.5*view.getEditTextSize()));

        email = (TextView) findViewById(R.id.email);
        email.setTextSize((float) (0.5*view.getLabelTextSize()));

        emailEditText = (EditText) findViewById(R.id.EmailEditText);
        emailEditText.setTextSize((float) (0.5*view.getEditTextSize()));
        emailEditText.setText(myEmail);

        address = (TextView) findViewById(R.id.addressSignup);
        address.setTextSize((float) (0.5*view.getLabelTextSize()));

        addyEditText = (EditText) findViewById(R.id.AddyEditText);
        addyEditText.setTextSize((float) (0.5*view.getEditTextSize()));

        password = (TextView) findViewById(R.id.passwordSignUp);
        password.setTextSize((float) (0.5*view.getLabelTextSize()));

        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        passwordEditText.setTextSize((float) (0.5*view.getEditTextSize()));

        profile_types = (TextView) findViewById(R.id.profile_types_signup);
        profile_types.setTextSize((float) (0.70*view.getLabelTextSize()));

        userTypePetOwner = (Switch) findViewById(R.id.userTypePetOwner);
        userTypePetOwner.setTextSize((float) (0.85 * view.getSwitchTextSize()));

        userTypeSitter = (Switch) findViewById(R.id.userTypeSitter);
        userTypeSitter.setTextSize((float) (.85 * view.getSwitchTextSize()));

        saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setTextSize(view.getButtonTextSize());

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("loginSwitchChecked", false);
        editor.putBoolean("ownerSwitchChecked", userTypePetOwner.isChecked());
        editor.putBoolean("sitterSwitchChecked", userTypeSitter.isChecked());
        editor.apply();

    }

    /**
     * Save method
     * Saves the user's input information and requests that server requests account creation
     * @param v the reference object calling this method
     */
    public void save(View v)
    {
        startActivity(new Intent(this, MainActivity.class));

        //POST REQUEST - json
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
            data.put("address", addyEditText.getText().toString());
            data.put("phone_number", phoneEditText.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Url Connection
        UrlPost saveInfo = new UrlPost("http://aiji.cs.loyola.edu/accountcreate", data.toString(), this, "signup.response");
        Log.w("MA", "--------URL POST------------");
        saveInfo.start();

        //Save Response
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String response = pref.getString("signup.response", "");
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

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
