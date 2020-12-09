package com.alexandrafernandez.sweproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class OwnerAcceptedSitting extends AppCompatActivity
{
    /**
     * TextViews
     */
    TextView startDateLabel, startDate, endDateLabel, endDate, sitterInformationLabel, sitterInformation, locationLabel, location, notesLabel, notes;

    /**
     * Buttons
     */
    Button modifyButton, cancelButton;

    /**
     * Server interaction objects
     */
    SharedPreferences pref;
    String clientId, clientAuth, job_id;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_owner_accepted_sitting);
        setTitle("Sitting Details");


        ScreenSize view  = new ScreenSize(this);

        //setting up buttons
        modifyButton = (Button) findViewById(R.id.owner_accepted_modify_button);

        cancelButton = (Button) findViewById(R.id.owner_accepted_delete_button);

        //textView setup
        startDateLabel = (TextView) findViewById(R.id.accepted_owner_sitting_start_date_label);
        startDate = (TextView) findViewById(R.id.accepted_owner_sitting_start_date);

        endDateLabel = (TextView) findViewById(R.id.accepted_owner_sitting_end_date_label);
        endDate = (TextView) findViewById(R.id.accepted_owner_sitting_end_date);

        sitterInformationLabel = (TextView) findViewById(R.id.accepted_owner_sitter_information_label);
        sitterInformation = (TextView) findViewById(R.id.accepted_owner_sitter_information);

        locationLabel = (TextView) findViewById(R.id.owner_accepted_must_occur_at_my_location_label);
        location = (TextView) findViewById(R.id.must_occur_at_my_location);

        notesLabel = (TextView) findViewById(R.id.owner_accepted_notes_from_owner_label);
        notes = (TextView) findViewById(R.id.owner_accepted_notes_from_owner);

        //GET Request - get id/auth
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        clientId = pref.getString("id", "");
        clientAuth = pref.getString("auth", "");

        job_id = getIntent().getStringExtra("job_id");

        String startDateTime = "", endDateTime = "", sitterInformation ="",
                notesFromOwner ="";
        boolean success = false, is_at_owner = false;
        float lat=0, lon=0;


        //Url connection
        UrlGet userInfo = new UrlGet("http://aiji.cs.loyola.edu/ownerjoblist?id=" + clientId + "&auth=" + clientAuth + "&is_accepted=" + true,"requests.list", this);
        userInfo.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String json = pref.getString("requests.list", "");
        try {
            JSONObject jsonObject = new JSONObject(json);
            is_at_owner = jsonObject.getBoolean("is_at_owner");
            if(is_at_owner== true)
            {
                location.setText("true");
            }
            else
            {
                location.setText("false");
            }
            startDateTime = jsonObject.getString("start_datetime");
            startDate.setText(startDateTime);
            endDateTime = jsonObject.getString("end_datetime");
            endDate.setText(endDateTime);
            notesFromOwner = jsonObject.getString("details");
            notes.setText(notesFromOwner);
            //need line for sitter information

        } catch (JSONException json_e) {
            Log.w("MA", json_e.toString());
        }

    }


    public void onOwnerCancel(View v)
    {

//        final Intent i = new Intent(this, OwnerAcceptedSittings.class);
//
//        new AlertDialog.Builder(this)
//                .setTitle("Un-Accept Job")
//                .setMessage("Are you sure you want to ignore this job?")
//                .setPositiveButton("Un-Accept", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        JSONObject data = new JSONObject();
//                        try {
//                            data.put("id", clientId);
//                            data.put("auth", clientAuth);
//                            data.put("job_id", job_id);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                        UrlDelete saveInfo = new UrlDelete("http://aiji.cs.loyola.edu/jobunaccept", data.toString(), "job.unaccept", getContext());
//                        saveInfo.start();
//
//                        //give persistent data time to write
//                        try {
//                            Thread.sleep(500);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//
//                        //check response
//                        String response = pref.getString("job.unaccept", "");
//                        boolean success = false;
//                        try {
//                            JSONObject jsonObject1 = new JSONObject(response);
//                            success = jsonObject1.getBoolean("success");
//                        } catch( JSONException json_e ) {
//                            if(!success) {
//                                //showError();
//                                //return;
//                            }
//                        }
//                        startActivity(i);
//                        finish();
//                    }
//                })
//
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                })
//                .setIcon(android.R.drawable.ic_menu_delete)
//                .show();
    }


    public void onOwnerModify(View v)
    {

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
