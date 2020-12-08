package com.alexandrafernandez.sweproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OwnerSitting extends AppCompatActivity
{
    /**
     * Text Views for identifying field components
     */
    TextView textViewStartDate, textViewStartTime, textViewEndDate, textViewEndTime, textViewDetails;

    /**
     * Switches used for requesting a sitter
     */
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch start_am_or_pm_switch, end_am_or_pm_switch, at_owner_location_switch;

    /**
     * Edit Text Views for user input for relevant information in requesting a sitter
     */
    EditText editTextStartDate, editTextStartTime, editTextEndDate,editTextEndTime, editTextDetails;

    /**
     *  Buttons used to confirm data and/or move to another activity
     */
    Button buttonConfirm, buttonDelete;

    /**
     * Server interaction objects
     */
    SharedPreferences pref;
    String clientID, clientAuth, pet_id;


    /**
     * On Create Method
     * Initializes the need_sitter_event Viewß
     * @param savedInstanceState android system parameter
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.need_sitter_event);
        setTitle("REQUEST A SITTER");

        ScreenSize view = new ScreenSize(this);

        //setting Buttons
        buttonConfirm = (Button) findViewById(R.id.confirm_sitter_request_button);
        buttonConfirm.setTextSize(view.getButtonTextSize());

        buttonDelete = (Button) findViewById(R.id.job_delete_button);
        buttonDelete.setTextSize(view.getButtonTextSize());
        buttonDelete.setVisibility(View.INVISIBLE);

        //TODO add the server get Request, with the url connection and then save the response and parse arguments
        /** Things commented out are so it can work with not server connection **/


        //setting textViews
        textViewStartDate = (TextView) findViewById(R.id.start_date);
        textViewStartDate.setTextSize(view.getLabelTextSize());

        textViewStartTime = (TextView) findViewById(R.id.start_time);
        textViewStartTime.setTextSize(view.getLabelTextSize());

        textViewEndDate = (TextView) findViewById(R.id.end_date);
        textViewEndDate.setTextSize(view.getLabelTextSize());

        textViewEndTime = (TextView) findViewById(R.id.end_time);
        textViewEndTime.setTextSize(view.getLabelTextSize());

        textViewDetails = (TextView) findViewById(R.id.other_notes_info);
        textViewDetails.setTextSize(view.getLabelTextSize());


        //setting Switches
        start_am_or_pm_switch = (Switch) findViewById(R.id.timeSpecification1);
        start_am_or_pm_switch.setTextSize(view.getSwitchTextSize());
        //start_am_or_pm_switch.setChecked();

        end_am_or_pm_switch = (Switch) findViewById(R.id.timeSpecification2);
        end_am_or_pm_switch.setTextSize(view.getSwitchTextSize());
        //end_am_or_pm_switch.setChecked();

        at_owner_location_switch = (Switch) findViewById(R.id.switch9);
        at_owner_location_switch.setTextSize(view.getSwitchTextSize());
        //at_owner_location_switch.setChecked();

        //setting editText
        editTextStartDate = (EditText) findViewById(R.id.start_date);
        //editTextStartTime.setTextSize(view.getEditTextSize());
        //editTextStartDate.setText();

        editTextStartTime =  (EditText) findViewById(R.id.start_time);
        //editTextStartTime.setTextSize(view.getEditTextSize());
        //editTextStartTime.setText();

        editTextEndDate = (EditText) findViewById(R.id.end_date);
        //editTextEndDate.setTextSize(view.getEditTextSize());
        //editTextEndDate.setText();

        editTextEndTime = (EditText) findViewById(R.id.end_time);
        //editTextEndTime.setTextSize(view.getEditTextSize());
        //editTextEndTime.setText();

        editTextDetails = (EditText) findViewById(R.id.additional_info_text_field);
        //editTextDetails.setTextSize(view.getEditTextSize());
        //editTextDetails.setText();





    }

    /**
     * Go to sittins requested method
     * After saving data to device/server, returns to main mySittings (for owner) view
     * @param view the reference object calling this method
     */
    public void onCreateRequestSitterEvent(View v)
    {

        Intent i = new Intent(this, OwnerSittings.class);

        //TODO Json object , post to create a new sitting, give persistenet data time to write, check response,

        startActivity(i);
        finish();

    }


    /**
     * Get Context
     * @return the activity
     */
    public Context getContext()
    {
        return this;
    }



}
