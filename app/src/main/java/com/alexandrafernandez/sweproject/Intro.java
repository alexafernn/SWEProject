package com.alexandrafernandez.sweproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Intro Class
 * This class allows users to add pets to their profile
 * CS482 Software Engineering
 * Prof. Mehri
 * @author Jack Cannon
 * @author Alexandra Fernandez
 * @version 3.0 Final Release
 */
public class Intro extends AppCompatActivity {
    /**
     * Text Views for identifying field components
     */
    TextView greeting;

    /**
     * Buttons used to confirm data and/or move to another activity
     */
    Button owner, sitter;

    /**
     * Server interaction objects
     */
    SharedPreferences pref;
    String clientID, clientAuth;

    /**
     * On Create Method
     * Initializes the intro View and instantiates other view objects for later use
     * @param savedInstanceState android system parameter
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("HOME");

        //GET Request - get id/auth
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        clientID = pref.getString("id", "");
        clientAuth = pref.getString("auth", "");

        //Url connection
        UrlGet userInfo = new UrlGet("http://aiji.cs.loyola.edu/accountinfo?id=" + clientID + "&auth=" + clientAuth ,"profile.userInfo", this);
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
        String first_name="";
        try {
            JSONObject jsonObject1 = new JSONObject(json);
            first_name = jsonObject1.getString( "first_name" );
        } catch( JSONException json_e ) {
            Log.w("MA", json_e.toString());
        }

        ScreenSize view = new ScreenSize(this);

        //String greetMe = "Hi, " + myName;
        String greetMe = "HI " + first_name.toUpperCase() + "!";
        greeting = (TextView) findViewById(R.id.greeting);
        greeting.setText(greetMe);
        greeting.setTextSize(view.getLabelTextSize());

        boolean own = pref.getBoolean("ownerSwitchChecked", true);
        boolean sit = pref.getBoolean("sitterSwitchChecked", true);
        Log.w("MA", sit + " " + own);

        owner = findViewById(R.id.owner_main_button); //i need a sitter
        owner.setTextSize(view.getButtonTextSize());

        if(pref.getBoolean("ownerSwitchChecked", true))
            owner.setVisibility(View.VISIBLE);
        else owner.setVisibility(View.INVISIBLE);

        sitter = findViewById(R.id.sitter_main_button); //i want to sit
        sitter.setTextSize(view.getButtonTextSize());

        if(pref.getBoolean("sitterSwitchChecked", true))
            sitter.setVisibility(View.VISIBLE);
        else sitter.setVisibility(View.INVISIBLE);
    }

    /**
     * On Restart method
     * Called only when re-starting an activity
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

    /**
     * on Create Options Menu
     * Initialize and connect the menu for this class
     * @param menu the appropriate menu object for this view (app_menu.xml)
     * @return true if successful creation of menu
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    /**
     * On Options Item Selected method
     * @param item the option selected by the user
     * @return true once an action is taken regarding this selection
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                if(pref.getBoolean("ownerSwitchChecked", true) && !pref.getBoolean("sitterSwitchChecked", true))
                    startActivity(new Intent(Intro.this, sittingsForMyPet.class));

                else if(pref.getBoolean("sitterSwitchChecked", true) && !pref.getBoolean("ownerSwitchChecked", true))
                    startActivity(new Intent(Intro.this, HomeActivity.class));

                else {
                    DialogInterface.OnClickListener dClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int choice) {
                            switch (choice) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    startActivity(new Intent(Intro.this, HomeActivity.class)); // i am sitter
                                    break;
                                case DialogInterface.BUTTON_NEGATIVE:
                                    startActivity(new Intent(Intro.this, sittingsForMyPet.class)); // i am owner
                            }
                        }
                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(Intro.this);
                    builder.setMessage("Which sitting appointments would you like to see?").setPositiveButton("I'm a Sitter", dClickListener).setNegativeButton("I'm an Owner", dClickListener).show();
                }
                return true;
            case R.id.profile:
                startActivity(new Intent(this, Profile.class));
                return true;
            case R.id.feedback:
                startActivity(new Intent(this, Feedback.class));
                return true;
            case R.id.logout:
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = pref.edit();
                editor.putBoolean("loginSwitchChecked", false);
                editor.apply();
                startActivity(new Intent(this, MainActivity.class));
                //clear all profile data

                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * To Owner Profile method
     * Sends to owner activity
     * @param view the reference object calling this method
     */
    public void toOwnerProfile(View view) {
       // startActivity(new Intent(this, Owner.class));
        startActivity(new Intent (this, OwnerSittings.class));
    }

    /**
     * To Sitter Profile method
     * Sends to sitter activity
     * @param view the reference object calling this method
     */
    public void toSitterProfile(View view)
    {
        startActivity(new Intent(this, SitterSittings.class));
       // startActivity(new Intent(this, Sitter.class));
    }
}
