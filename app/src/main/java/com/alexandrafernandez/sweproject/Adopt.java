package com.alexandrafernandez.sweproject;

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

public class Adopt extends AppCompatActivity {

    TextView animal_adopt, age, animal_adopt_notes;
    EditText animal_adopt_field, age_field, notes_field;
    Button adoption_request_button;
    Switch background_check_switch, emails_switch;

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

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.adopt_menu, menu);
        return true;
    }

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


    public void notifyShelters(View view) {


        finish();
    }

}
