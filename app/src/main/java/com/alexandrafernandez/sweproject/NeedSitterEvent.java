package com.alexandrafernandez.sweproject;

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

public class NeedSitterEvent extends AppCompatActivity {

    private int sittingsRequest;

    TextView start_date_label, end_date_label, which_pets_title_info2, other_notes_info;
    EditText start_date, end_date, additional_info_text_field;
    Switch switch8;
    Button custom_pet_selection_button, confirm_sitter_request_button;

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

        which_pets_title_info2 = (TextView) findViewById(R.id.which_pets_title_info2);
        which_pets_title_info2.setTextSize(view.getLabelTextSize());

        switch8 = (Switch) findViewById(R.id.switch8);
        switch8.setTextSize(view.getSwitchTextSize());

        custom_pet_selection_button = (Button) findViewById(R.id.custom_pet_selection_button);
        custom_pet_selection_button.setTextSize(view.getButtonTextSize());

        other_notes_info = (TextView) findViewById(R.id.other_notes_info);
        other_notes_info.setTextSize((float) (0.75*view.getLabelTextSize()));

        additional_info_text_field = (EditText) findViewById(R.id.additional_info_text_field);
        additional_info_text_field.setTextSize(view.getEditTextSize());

        confirm_sitter_request_button = (Button) findViewById(R.id.confirm_sitter_request_button);
        confirm_sitter_request_button.setTextSize(view.getButtonTextSize());
    }

    public void doPetSelection(View view) {


        startActivity(new Intent(this, PetPicker.class));
    }

    public void onCreateRequestSitterEvent(View view)
    {

        Sitting sitting = MainActivity.sitting; //not sure if this is creating new one?
        sitting.updateNumberOfSittings();
        finish();
    }



}

