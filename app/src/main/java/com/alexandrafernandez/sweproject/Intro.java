package com.alexandrafernandez.sweproject;


import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Intro extends AppCompatActivity {

    TextView greeting, status_messages;
    Button owner, sitter, adoption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("HOME");

        ScreenSize view = new ScreenSize(this);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String myName = pref.getString("username", "username");
        String greetMe = "Hi, " + myName;
        greeting = (TextView) findViewById(R.id.greeting);
        greeting.setText(greetMe);
        greeting.setTextSize(view.getLabelTextSize());

        status_messages = (TextView) findViewById(R.id.status_messages);
        status_messages.setTextSize(view.getLabelTextSize());
        status_messages.setHeight(view.getHeight()/3);

        owner = findViewById(R.id.owner_main_button); //i need a sitter
        owner.setTextSize(view.getButtonTextSize());
        if(pref.getBoolean("ownerProfileSwitch", false))
            owner.setVisibility(View.INVISIBLE);

        sitter = findViewById(R.id.sitter_main_button); //i want to sit
        sitter.setTextSize(view.getButtonTextSize());
        if(pref.getBoolean("sitterProfileSwitch", false))
            sitter.setVisibility(View.INVISIBLE);

        adoption = findViewById(R.id.adoption_main_button); //adoption
        adoption.setTextSize(view.getButtonTextSize());
        if(pref.getBoolean("adoptionProfileSwitch", false))
            adoption.setVisibility(View.INVISIBLE);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                startActivity(new Intent(this, HomeActivity.class));
                return true;
            case R.id.profile:
                startActivity(new Intent(this, Profile.class));
                finish();
                return true;
            case R.id.feedback:
                startActivity(new Intent(this, Feedback.class));
                return true;
            case R.id.logout:
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = pref.edit();
                editor.putBoolean("loginSwitchChecked", false);
                editor.commit();
                startActivity(new Intent(this, MainActivity.class));
                //clear all profile data

                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void toOwnerProfile(View view) {
        startActivity(new Intent(this, Owner.class));
    }

    public void toSitterProfile(View view) {
        startActivity(new Intent(this, Sitter.class));
    }

    public void toAdoptionPage(View view) {
        startActivity(new Intent(this, Adopt.class));
    }
}
