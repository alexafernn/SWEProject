package com.alexandrafernandez.sweproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;


public class SitterSitting extends AppCompatActivity
{
    /**
     * Text Views for identifying field components
     */
    TextView textViewStartDateLabel, textViewEndDateLabel, textViewAllMyPetsLabel, textViewMustOccurAtOwnerLocationLabel,
             textViewNotesFromOwnerLabel, textViewStartDate, textViewEndDate, textViewAllMyPets, textViewMustOccurAtOwnerLocation,
             textViewNotesFromOwner;

    /**
     * Buttons used to confirm data and/or move to another activity
     */
    Button buttonConfirm, buttonCancel, buttonViewPets;

    /**
     * On Create Method
     * Initializes the  View
     * @param savedInstanceState android system parameter
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm);
        setTitle("Approve Sitting");

        ScreenSize view  = new ScreenSize(this);

        //setting up buttons
        buttonConfirm = (Button) findViewById(R.id.confirm_availability_button);
        buttonConfirm.setTextSize(view.getButtonTextSize());

        buttonCancel = (Button) findViewById(R.id.cancel_button);
        buttonCancel.setTextSize(view.getButtonTextSize());

        buttonViewPets = (Button) findViewById(R.id.view_pets_button);
        buttonViewPets.setTextSize(view.getButtonTextSize());

        //TODO get from server
        /**Things commented out are so it can work without server connection**/

        //setting up TextViews
        textViewStartDate = (TextView) findViewById(R.id.start_date_label);
        textViewStartDate.setTextSize(view.getLabelTextSize());
        textViewStartDate.setText("hi");

        textViewEndDate = (TextView) findViewById(R.id.end_date_label);
        //textViewStart.setText();

        textViewAllMyPets = (TextView) findViewById(R.id.all_my_pet_label);
        //textViewAllMyPets.setText();

        textViewMustOccurAtOwnerLocation = (TextView) findViewById(R.id.must_occur_at_my_location_label);
        //textViewMustOccurAtOwnerLocation.setText();

        textViewNotesFromOwner = (TextView) findViewById(R.id.notes_for_sitter_label);
        //textViewNotesFromOwner.setText();

    }


    /**
     * Go to sittins requested method
     * After saving data to device/server, returns to main mySittings (for sitter) view
     * @param view the reference object calling this method
     */
    public void onConfirm(View v)
    {
        Intent i = new Intent(this, SitterSittings.class);

        //TODO Json object, communicate it was accepted

        startActivity(i);
        finish();
    }


    /**
     * Go to sittins requested method
     * dont save anything to server, returns to main mySittings (for sitter) view
     * @param view the reference object calling this method
     */
    public void onCancel(View v)
    {
      finish();
    }

    /**
     * Go to view pets in sitting view
     * dont save anything to server, goes to pets in sitting view
     * @param view the reference object calling this method
     */
    public void onViewSittingPets(View v)
    {
        Intent i= new Intent(this, petsSittingList.class); //TODO: fix petSittingList to account with server and show properly
        startActivity(i);
    }


    /**
     * Get context
     * @return the activity
     */
    public Context getContext()
    {
        return this;
    }



}
