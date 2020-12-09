package com.alexandrafernandez.sweproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;
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

        final Intent i = new Intent(this, Owner.class);

        new AlertDialog.Builder(this)
                .setTitle("Delete Job")
                .setMessage("Are you sure you want to delete this job?")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        JSONObject data = new JSONObject();
                        try {
                            data.put("id", clientID);
                            data.put("auth", clientAuth);
                            data.put("job_id", job_id);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        UrlDelete saveInfo = new UrlDelete("http://aiji.cs.loyola.edu/jobdelete", data.toString(), "job.response", getContext());
                        saveInfo.start();

                        //give persistent data time to write
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        //check response
                        String response = pref.getString("job.response", "");
                        boolean success = false;
                        try {
                            JSONObject jsonObject1 = new JSONObject(response);
                            success = jsonObject1.getBoolean("success");
                        } catch( JSONException json_e ) {
                            if(!success) {
                                showError();
                                //return;
                            }
                        }
                        startActivity(i);
                        finish();
                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setIcon(android.R.drawable.ic_menu_delete)
                .show();

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
