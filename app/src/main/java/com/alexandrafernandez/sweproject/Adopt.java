package com.alexandrafernandez.sweproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Adopt Class
 * This class allows users to express interest in adopting a pet
 * CS482 Software Engineering
 * Prof. Mehri
 * @author Jack Cannon
 * @author Alexandra Fernandez
 * @version 3.0 Final Release
 */
public class Adopt extends AppCompatActivity {

    /**
     * Text Views for identifying field components
     */
    TextView animal_adopt, age, animal_adopt_notes;

    /**
     * Edit Text Views for user input of relevant information
     */
    EditText animal_adopt_field, age_field, notes_field;

    /**
     * Buttons used to confirm data and/or move to another activity
     */
    Button adoption_request_button;

    /**
     * Switches used to characteristics of adoption interest
     */
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch background_check_switch, emails_switch;

    /**
     * On Create Method
     * Initializes the adopt View and instantiates other view objects for later use
     * @param savedInstanceState android system parameter
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adopt);
        setTitle("Adopt");

        ScreenSize view = new ScreenSize(this);

        animal_adopt = (TextView) findViewById(R.id.animal_adopt);
        animal_adopt.setTextSize(view.getLabelTextSize());

        age = (TextView) findViewById(R.id.age);
        age.setTextSize(view.getLabelTextSize());

        animal_adopt_field = (EditText) findViewById(R.id.animal_adopt_field);
        animal_adopt_field.setTextSize(view.getEditTextSize());

        age_field = (EditText) findViewById(R.id.age_field);
        age_field.setTextSize(view.getEditTextSize());

        notes_field = (EditText) findViewById(R.id.notes_field);
        notes_field.setTextSize(view.getEditTextSize());
        notes_field.setHeight(10*view.getEditTextSize());

        background_check_switch = (Switch) findViewById(R.id.switch6);
        background_check_switch.setTextSize(view.getSwitchTextSize());

        emails_switch = (Switch) findViewById(R.id.switch7);
        emails_switch.setTextSize(view.getSwitchTextSize());

        adoption_request_button = (Button) findViewById(R.id.adoption_request_button);
        adoption_request_button.setTextSize(view.getButtonTextSize());

        animal_adopt_notes = (TextView) findViewById(R.id.animal_adopt_notes);
        animal_adopt_notes.setTextSize((float) (0.6*view.getLabelTextSize()));

    }

    /**
     * on Create Options Menu
     * Initialize and connect the menu for this class
     * @param menu the appropriate menu object for this view (adopt_menu.xml)
     * @return true if successful creation of menu
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.adopt_menu, menu);
        return true;
    }

    /**
     * On Options Item Selected method
     * @param item the option selected by the user
     * @return true once an action is taken regarding this selection
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.check_status:
                startActivity(new Intent(this, AdoptionStatus.class));
                return true;
            case R.id.feedback:
                startActivity(new Intent(this, Feedback.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Notify Shelters method
     * Performs appropriate actions and completes activity
     * @param view the reference object calling this method
     */
    public void notifyShelters(View view) {

        //TODO implement post to website if time allows

        finish();
    }

}
