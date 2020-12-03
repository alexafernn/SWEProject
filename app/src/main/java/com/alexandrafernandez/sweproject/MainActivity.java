package com.alexandrafernandez.sweproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Main Activity Class
 * This class allows users to select from and navigate to the owner/sitter/adoption options
 * CS482 Software Engineering
 * Prof. Mehri
 * @author Jack Cannon
 * @author Alexandra Fernandez
 * @version 3.0 Final Release
 */
public class MainActivity extends Activity {

    /**
     * Text Views for identifying field components
     */
    TextView login_text, username, password;

    /**
     * Buttons used to confirm data and/or move to another activity
     */
    Button loginButton, signUpButton;

    /**
     * Edit Text Views for user input of relevant information
     */
    EditText usernameEditText, passwordEditText;

    /**
     * Switches used to request saving of login details
     */
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch loginSwitch;

    //adding
    public static Sitting sitting;

    /**
     * On Create Method
     * Initializes the Main Activity View and instantiates other view objects for later use
     * @param savedInstanceState android system parameter
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //adding
        sitting = new Sitting(this);

        setContentView(R.layout.login);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        if(pref.getBoolean("loginSwitchChecked", false))
            startActivity(new Intent(this, Intro.class));

        ScreenSize view = new ScreenSize(this);


        login_text = (TextView) findViewById(R.id.loginText);
        login_text.setTextSize((float) (1.2*view.getLabelTextSize()));

        username = (TextView) findViewById(R.id.username);
        username.setTextSize((float) (0.8*view.getLabelTextSize()));

        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        usernameEditText.setTextSize(view.getEditTextSize());

        password = (TextView) findViewById(R.id.password);
        password.setTextSize((float) (0.6*view.getLabelTextSize()));

        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        passwordEditText.setTextSize(view.getEditTextSize());

        loginSwitch = (Switch) findViewById(R.id.password_switch);
        loginSwitch.setTextSize(view.getSwitchTextSize());

        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setTextSize(view.getButtonTextSize());

        signUpButton = (Button) findViewById(R.id.signUpButton);
        signUpButton.setTextSize(view.getButtonTextSize());

    }

    /**
     * Verify Login method
     * Performs appropriate actions and completes activity
     * @param v the reference object calling this method
     */
    public void verifyLogin(View v)
    {
            if(usernameEditText.getText().toString().equals("") || passwordEditText.getText().toString().equals("") ) {
                Toast.makeText(this, "Username/Password cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            if(loginSwitch.isChecked()) {
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = pref.edit();
                boolean loginSwitchChecked = loginSwitch.isChecked();
                editor.putBoolean("loginSwitchChecked", loginSwitchChecked);
                editor.apply();
            }

        JSONObject data = new JSONObject();
        try {
            data.put("email",usernameEditText.getText().toString());
            data.put("password", passwordEditText.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        UrlPost saveInfo = new UrlPost("http://aiji.cs.loyola.edu/account/login", data.toString(), this, "login.response");
        saveInfo.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String response = pref.getString("login.response", "");
        String id="";
        String auth="";
        try {
            JSONObject jsonObject1 = new JSONObject(response);
            id = jsonObject1.getString("id");
            auth = jsonObject1.getString("auth");
        } catch( JSONException json_e ) {
            Toast.makeText(this, "Username/Password don't match an existing account", Toast.LENGTH_LONG).show();
            return;
        }
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("id", id);
        editor.putString("auth", auth);
        editor.apply();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //if login verified
        startActivity(new Intent(this, Intro.class));
        //else
        //error message

    }

    /**
     * Sign Up method
     * Sends user to activity for signUp
     * @param v the reference object calling this method
     */
    public void signUp(View v)
        {
            startActivity(new Intent(this, SignUp.class));
        }

}