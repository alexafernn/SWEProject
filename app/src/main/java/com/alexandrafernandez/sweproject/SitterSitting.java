package com.alexandrafernandez.sweproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
     * Server interaction objects
     */
    SharedPreferences pref;
    String clientID, clientAuth, job_id;


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


        //setting up textViews


        textViewNotesFromOwnerLabel = (TextView) findViewById(R.id.notes_for_sitter);
        textViewNotesFromOwnerLabel.setTextSize(view.getLabelTextSize());

        //exist
        textViewStartDateLabel = (TextView) findViewById(R.id.start);
        textViewStartDateLabel.setTextSize(view.getLabelTextSize());

        //exist
        textViewStartDate = (TextView) findViewById(R.id.start_date_label);
        textViewStartDate.setTextSize(view.getLabelTextSize());

        //exist
        textViewEndDateLabel = (TextView) findViewById(R.id.end);
        textViewEndDateLabel.setTextSize(view.getLabelTextSize());

        //exist
        textViewEndDate = (TextView) findViewById(R.id.end_date_label);
        textViewEndDate.setTextSize(view.getLabelTextSize());

        //exist
        textViewMustOccurAtOwnerLocation = (TextView) findViewById(R.id.must_occur_at_my_location_label);
        textViewMustOccurAtOwnerLocation.setTextSize(view.getLabelTextSize());

        textViewMustOccurAtOwnerLocationLabel = (TextView) findViewById(R.id.must_occur_at_my_location);
        textViewMustOccurAtOwnerLocationLabel.setTextSize(view.getLabelTextSize());


        textViewNotesFromOwner = (TextView) findViewById(R.id.notes_for_sitter_label);
        textViewNotesFromOwner.setTextSize(view.getLabelTextSize());

        textViewAllMyPets = (TextView) findViewById(R.id.all_my_pet_label);
        textViewAllMyPets.setTextSize(view.getLabelTextSize());

        //TODO get from server
        /**Things commented out are so it can work without server connection**/

        //GET Request - get id/auth
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        clientID = pref.getString("id", "");
        clientAuth = pref.getString("auth", "");

        job_id = getIntent().getStringExtra("job_id");

        String startDateTime = "", endDateTime = "", details ="";
        boolean success = false, is_at_owner = false;
        float lat=0, lon=0;

        //Url connection
        UrlGet userInfo = new UrlGet("http://aiji.cs.loyola.edu/jobinfo?id=" + clientID + "&auth=" + clientAuth + "&job_id=" + job_id, "requests.list", this);
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
                textViewMustOccurAtOwnerLocation.setText("true");
            }
            else
            {
                textViewMustOccurAtOwnerLocation.setText("false");
            }
            startDateTime = jsonObject.getString("start_datetime");
            textViewStartDate.setText(startDateTime);
            endDateTime = jsonObject.getString("end_datetime");
            textViewEndDate.setText(endDateTime);
            details = jsonObject.getString("details");
            textViewNotesFromOwner.setText(details);

        } catch (JSONException json_e) {
            Log.w("MA", json_e.toString());
        }



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
