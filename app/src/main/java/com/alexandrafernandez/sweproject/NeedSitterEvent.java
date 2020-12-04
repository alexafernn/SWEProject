package com.alexandrafernandez.sweproject;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Need Sitter Event Class
 * This class allows owners to generate a request for sitter
 * CS482 Software Engineering
 * Prof. Mehri
 * @author Jack Cannon
 * @author Alexandra Fernandez
 * @version 3.0 Final Release
 */
public class NeedSitterEvent extends AppCompatActivity {

    /**
     * Text Views for identifying field components
     */
    TextView start_date_label, end_date_label, other_notes_info;

    /**
     * Edit Text Views for user input of relevant information
     */
    EditText start_date, end_date, additional_info_text_field;

    /**
     * Switches used to characteristics of need sitter events
     */
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch switch9;

    /**
     * Buttons used to confirm data and/or move to another activity
     */
    Button confirm_sitter_request_button;

    /**
     * On Create Method
     * Initializes the need sitter event View and instantiates other view objects for later use
     * @param savedInstanceState android system parameter
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.need_sitter_event);
        setTitle("Request a Sitter");

        ScreenSize view = new ScreenSize(this);

        start_date_label = (TextView) findViewById(R.id.calendar_title_info);
        start_date_label.setTextSize((float) (0.5*view.getLabelTextSize()));

        start_date = (EditText) findViewById(R.id.start_date);
        start_date.setTextSize(view.getEditTextSize());

        end_date_label = (TextView) findViewById(R.id.calendar_title_info2);
        end_date_label.setTextSize((float) (0.5*view.getLabelTextSize()));

        end_date = (EditText) findViewById(R.id.end_date);
        end_date.setTextSize(view.getEditTextSize());

        switch9 = (Switch) findViewById(R.id.switch9);
        switch9.setTextSize(view.getSwitchTextSize());

        other_notes_info = (TextView) findViewById(R.id.other_notes_info);
        other_notes_info.setTextSize((float) (0.75*view.getLabelTextSize()));

        additional_info_text_field = (EditText) findViewById(R.id.additional_info_text_field);
        additional_info_text_field.setTextSize(view.getEditTextSize());

        confirm_sitter_request_button = (Button) findViewById(R.id.confirm_sitter_request_button);
        confirm_sitter_request_button.setTextSize(view.getButtonTextSize());
    }

    /**
     * Request Sitter Event method
     * Performs appropriate actions and completes activity
     * @param view the reference object calling this method
     */
    public void onCreateRequestSitterEvent(View view)
    {
        //TODO connect to server (this is a mandatory feature)
        Sitting sitting = MainActivity.sitting; //not sure if this is creating new one?
        sitting.updateNumberOfSittings();
        finish();
    }



}

