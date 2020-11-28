package com.alexandrafernandez.sweproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Sitter Availability Event Class
 * This class serves allows Sitters to create an availability event
 * CS482 Software Engineering
 * Prof. Mehri
 * @author Jack Cannon
 * @author Alexandra Fernandez
 * @version 3.0 Final Release
 */
public class SitterAvailabilityEvent extends AppCompatActivity {

    /**
     * Text Views for identifying field components
     */
    TextView dateStart, dateEnd, notesInfo;

    /**
     * Edit Text Views for user input of relevant information
     */
    EditText dateStartField, dateEndField, notesInfoField;

    /**
     * Buttons used to confirm data and/or move to another activity
     */
    Button confirmAvailability;

    /**
     * List of data from sitter availability events
     */
    ArrayList<SitterAvailabilityData> availabilityList;

    /**
     * On Create Method
     * Initializes the sitter availability event View and instantiates other view objects for later use
     * @param savedInstanceState android system parameter
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sitter_availability_event);
        setTitle("Add Block Availability");

        ScreenSize view = new ScreenSize(this);

        availabilityList = new ArrayList<SitterAvailabilityData>(); //replace with server pull
        availabilityList.add(new SitterAvailabilityData("01/01/2021","02/02/2021", ""));

        dateStart = (TextView) findViewById(R.id.calendar_title_info3);
        dateStart.setTextSize(view.getLabelTextSize());

        dateStartField = (EditText) findViewById(R.id.availability_start_date);
        dateStartField.setTextSize(view.getEditTextSize());
        dateStartField.setText(getIntent().getStringExtra("viewAvailability.dateStart"));

        dateEnd = (TextView) findViewById(R.id.calendar_title_info4);
        dateEnd.setTextSize(view.getLabelTextSize());

        dateEndField = (EditText) findViewById(R.id.availability_end_date);
        dateEndField.setTextSize(view.getEditTextSize());
        dateEndField.setText(getIntent().getStringExtra("viewAvailability.dateEnd"));

        notesInfo = (TextView) findViewById(R.id.other_notes_info2);
        notesInfo.setTextSize(view.getLabelTextSize());

        notesInfoField = (EditText) findViewById(R.id.additional_info_text_field2);
        notesInfoField.setTextSize(view.getEditTextSize());
        notesInfoField.setText(getIntent().getStringExtra("viewAvailability.notes"));

        confirmAvailability = (Button) findViewById(R.id.confirm_availability_button);
        confirmAvailability.setTextSize(view.getButtonTextSize());
    }

    /**
     * Create Availability Event method
     * After saving data to device/server, returns to the main Sitter view
     * @param view the reference object calling this method
     */
    public void onCreateAvailabilityEvent(View view) {

        //TODO write all availability data to server here (mandatory feature)

        startActivity(new Intent(this, SitterAvailability.class));
        finish();
    }
}
