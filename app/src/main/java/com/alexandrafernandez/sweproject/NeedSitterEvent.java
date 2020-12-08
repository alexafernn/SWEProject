package com.alexandrafernandez.sweproject;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class NeedSitterEvent extends AppCompatActivity implements LocationListener{

    /**
     * Text Views for identifying field components
     */
    TextView start_date_label, end_date_label, other_notes_info, start_time_info, end_time_info;

    /**
     * Edit Text Views for user input of relevant information
     */
    EditText start_date, end_date, start_time, end_time, additional_info_text_field;

    /**
     * Switches used to characteristics of need sitter events
     */
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch switch9,timePreference1, timePreference2;

    /**
     * Buttons used to confirm data and/or move to another activity
     */
    Button confirm_sitter_request_button, job_delete_button;

    /**
     * Server interaction objects
     */
    SharedPreferences pref;
    String clientID, clientAuth, job_id;

    Location location;


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

        job_delete_button = (Button) findViewById(R.id.job_delete_button);
        job_delete_button.setTextSize(view.getButtonTextSize());

        start_date_label = (TextView) findViewById(R.id.calendar_title_info);
        start_date_label.setTextSize((float) (0.5*view.getLabelTextSize()));

        start_date = (EditText) findViewById(R.id.start_date);
        start_date.setTextSize(view.getEditTextSize());

        start_time_info = (TextView) findViewById(R.id.start_time_info);
        start_time_info.setTextSize((float) (0.5*view.getLabelTextSize()));

        start_time = (EditText) findViewById(R.id.start_time);
        start_time.setTextSize(view.getEditTextSize());

        //timeSpecification1

        end_date_label = (TextView) findViewById(R.id.calendar_title_info2);
        end_date_label.setTextSize((float) (0.5*view.getLabelTextSize()));

        end_date = (EditText) findViewById(R.id.end_date);
        end_date.setTextSize(view.getEditTextSize());

        end_time_info = (TextView) findViewById(R.id.end_time_info);
        end_time_info.setTextSize((float) (0.5*view.getLabelTextSize()));

        end_time = (EditText) findViewById(R.id.end_time);
        end_time.setTextSize(view.getEditTextSize());

        switch9 = (Switch) findViewById(R.id.switch9);
        switch9.setTextSize(view.getSwitchTextSize());

        timePreference1 = (Switch) findViewById(R.id.timeSpecification1);
        timePreference1.setTextSize(view.getSwitchTextSize());

        timePreference2 = (Switch) findViewById(R.id.timeSpecification2);
        timePreference2.setTextSize(view.getSwitchTextSize());

        other_notes_info = (TextView) findViewById(R.id.other_notes_info);
        other_notes_info.setTextSize((float) (0.75*view.getLabelTextSize()));

        additional_info_text_field = (EditText) findViewById(R.id.additional_info_text_field);
        additional_info_text_field.setTextSize(view.getEditTextSize());

        confirm_sitter_request_button = (Button) findViewById(R.id.confirm_sitter_request_button);
        confirm_sitter_request_button.setTextSize(view.getButtonTextSize());

        //GET Request - get id/auth
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        clientID = pref.getString("id", "");
        clientAuth = pref.getString("auth", "");

        job_id = getIntent().getStringExtra("job_id");

        String startDateTime = "", endDateTime = "", location = "";
        boolean success = false, is_at_owner = false;
        float lat=0, lon=0;

        if(job_id != null) {

            //Url connection
            UrlGet userInfo = new UrlGet("http://aiji.cs.loyola.edu/jobinfo?id=" + clientID + "&auth=" + clientAuth + "&job_id=" + job_id, "requests.list", this);
            userInfo.start();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String json = pref.getString("requests.list", "");

            //Save response
            try {
                JSONObject jsonObject = new JSONObject(json);
                is_at_owner = jsonObject.getBoolean("is_at_owner");
                switch9.setChecked(is_at_owner);
                startDateTime = jsonObject.getString("start_datetime");
                start_date.setText(startDateTime.substring(0, startDateTime.indexOf(" ")));
                start_time.setText(startDateTime.substring(startDateTime.indexOf(" ")+1));
                endDateTime = jsonObject.getString("end_datetime");
                end_date.setText(endDateTime.substring(0, endDateTime.indexOf(" ")));
                end_time.setText(endDateTime.substring(endDateTime.indexOf(" ")+1));
                success = jsonObject.getBoolean("success");
            } catch (JSONException json_e) {
                Log.w("MA", json_e.toString());
            }

            if (!success) {
                Toast.makeText(this, "Request not found.", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            job_delete_button.setVisibility(View.INVISIBLE);
        }



    }

    /**
     * Request Sitter Event method
     * Performs appropriate actions and completes activity
     * @param view the reference object calling this method
     */
    public void onCreateRequestSitterEvent(View view)
    {
        Intent i = new Intent(this, Owner.class);

        Toast.makeText(this, "Getting Current Location", Toast.LENGTH_SHORT).show();
        while(location.getLatitude() == 0 || location.getLongitude() == 0) {
            //wait
        }

        JSONObject data = new JSONObject();
        try {
            data.put("id", clientID);
            data.put("auth", clientAuth);
            data.put("location", ""); //address
            data.put("lat", location.getLatitude()); //latitude
            data.put("long", location.getLongitude()); //longitude
            data.put("is_at_owner", switch9.isChecked());
            data.put("start_datetime", start_date.getText().toString() + " " + start_time.getText().toString());
            data.put("end_datetime", end_date.getText().toString() + " " + end_time.getText().toString());
            data.put("details", additional_info_text_field.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Post - create a new job
        if(job_id == null){
            UrlPost saveInfo = new UrlPost("http://aiji.cs.loyola.edu/jobcreation", data.toString(), this, "job.response");
            saveInfo.start();
        }

        //Put - modify an existing pet
        else {
            try {
                data.put("job_id", job_id);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            UrlPut saveInfo = new UrlPut("http://aiji.cs.loyola.edu/jobmodify", data.toString(), this, "job.response");
            saveInfo.start();
        }

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
                //showError();
                //return;
            }
        }


        //Sitting sitting = MainActivity.sitting; //not sure if this is creating new one?
        //sitting.updateNumberOfSittings();


        startActivity(i);
        finish();
    }


    @Override
    public void onLocationChanged(Location location) {
        this.location = location;
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}

