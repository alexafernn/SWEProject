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
    //TextView signupName, phoneNumber, email, address, username, password, profile_types;
    TextView  signupFirstName, signupLastName, phoneNumber, email, address, password, profile_types;

    /**
     * Switches used to indicate profile type
     */
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch userTypePetOwner, userTypeSitter;

    /**
     * Buttons used to confirm data and/or move to another activity
     */
    Button paypal_link_button, saveButton;

    /**
     * Edit Text Views for user input of relevant information
     */
    //EditText nameEditText, phoneEditText, emailEditText, addyEditText, usernameEditText, passwordEditText;
    EditText firstNameEditText, lastNameEditText, phoneEditText, emailEditText, addyEditText, usernameEditText, passwordEditText;

    /**
     * Initialize components of a sign up request
     */
    String first_name = ""; String last_name = ""; int type = 0; String myEmail = "";

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

        UrlGet userInfo = new UrlGet("http://aiji.cs.loyola.edu/accountinfo?id=16211ef1-141a-4ba0-a677-da209f7c5c58","signUp.userInfo", this);
        Log.w("MA", "--------URL GET------------");
        userInfo.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

        String json = pref.getString("signUp.userInfo", "");
        Log.w("MA", "json: ");
        Log.w("MA", json);

        try {
            JSONObject jsonObject1 = new JSONObject(json);
            first_name = jsonObject1.getString( "first_name" );
            last_name = jsonObject1.getString("last_name");
            myEmail = jsonObject1.getString("email"); //john ?
        } catch( JSONException json_e ) {
            Log.w("MA", json_e.toString());
        }

        ScreenSize view = new ScreenSize(this);

//        signupName = (TextView) findViewById(R.id.signupName);
//        signupName.setTextSize((float) (0.5*view.getLabelTextSize()));


//        nameEditText = (EditText) findViewById(R.id.NameEditText);
//        nameEditText.setTextSize((float) (0.5*view.getEditTextSize()));
//        nameEditText.setText(first_name);

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

//        username = (TextView) findViewById(R.id.usernameSignUp);
//        username.setTextSize((float) (0.5*view.getLabelTextSize()));
//
//        usernameEditText = (EditText) findViewById(R.id.UserNameEditText);
//        usernameEditText.setTextSize((float) (0.5*view.getEditTextSize()));

        password = (TextView) findViewById(R.id.passwordSignUp);
        password.setTextSize((float) (0.5*view.getLabelTextSize()));

        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        passwordEditText.setTextSize((float) (0.5*view.getEditTextSize()));

        profile_types = (TextView) findViewById(R.id.profile_types_signup);
        profile_types.setTextSize((float) (0.5*view.getLabelTextSize()));

        userTypePetOwner = (Switch) findViewById(R.id.userTypePetOwner);
        userTypePetOwner.setTextSize(view.getSwitchTextSize());

        userTypeSitter = (Switch) findViewById(R.id.userTypeSitter);
        userTypeSitter.setTextSize(view.getSwitchTextSize());

        if(type == 1)
            userTypePetOwner.setChecked(true);
        else if(type == 2)
            userTypeSitter.setChecked(true);
        else if(type == 3) {
            userTypePetOwner.setChecked(true);
            userTypeSitter.setChecked(true);
        }

        paypal_link_button = (Button) findViewById(R.id.paypal_link_button);
        paypal_link_button.setTextSize(view.getButtonTextSize());

        saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setTextSize(view.getButtonTextSize());

    }

    /**
     * Save method
     * Saves the user's input information and requests that server requests account creation
     * @param v the reference object calling this method
     */
    public void save(View v)
    {
        startActivity(new Intent(this, MainActivity.class));

        if(userTypePetOwner.isChecked())
            type = 1;
        if(userTypeSitter.isChecked())
            type = 2;
        if(userTypePetOwner.isChecked() && userTypeSitter.isChecked())
            type = 3;

        /*
        String str = "is_owner=" + Boolean.toString(userTypePetOwner.isChecked())
                + "&is_sitter=" + Boolean.toString(userTypeSitter.isChecked())
                + "&is_admin=" + Boolean.toString(false)
                + "&is_shelter=" + Boolean.toString(false)
                + "&first_name=" + nameEditText.getText().toString()
                + "&last_name=" + "test"
                + "&email=" + emailEditText.getText().toString()
                + "&password=" + passwordEditText.getText().toString();

         */


        JSONObject data = new JSONObject();
        try {
            System.out.println("the name is "+ first_name); // printing out john
            data.put("is_owner",userTypePetOwner.isChecked());
            data.put("is_sitter", userTypeSitter.isChecked());
            data.put("is_admin",false);
            data.put("is_shelter",false);
            //data.put("first_name", nameEditText.getText().toString());
            //data.put("last_name", "test");
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

        finish();
    }

    /**
     * Paypal Connection method
     * Used to link paypal information to new user profile
     * @param v the reference object calling this method
     */
    public void payPalConnection(View v)
    {
        //TODO implement if time allows (secondary feature)
    }
}
