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

public class OwnerAcceptedSitting extends AppCompatActivity {
    /**
     * TextViews
     */
    TextView startDateLabel, startDate, endDateLabel, endDate, sitterInformationLabel, sitterInformation, sitterPhoneNumberLabel, sitterPhoneNumber,
            sitterRatingLabel, sitterRating;

    /**
     * Buttons
     */
    Button cancelButton, ratingButton, backButton;

    /**
     * Server interaction objects
     */
    SharedPreferences pref;
    String clientId, clientAuth, job_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_owner_accepted_sitting);
        setTitle("Sitting Details");


        ScreenSize view = new ScreenSize(this);

        //setting up buttons
        cancelButton = (Button) findViewById(R.id.owner_accepted_cancel);

        ratingButton = (Button) findViewById(R.id.rate_sitter__button);

        backButton = (Button) findViewById(R.id.back_button_owner_accepted);


        //textView setup
        startDateLabel = (TextView) findViewById(R.id.accepted_owner_sitting_start_date_label);
        startDate = (TextView) findViewById(R.id.accepted_owner_sitting_start_date);

        endDateLabel = (TextView) findViewById(R.id.accepted_owner_sitting_end_date_label);
        endDate = (TextView) findViewById(R.id.accepted_owner_sitting_end_date);

        sitterInformationLabel = (TextView) findViewById(R.id.accepted_owner_sitter_information_label);
        sitterInformation = (TextView) findViewById(R.id.accepted_owner_sitter_information);

        sitterPhoneNumberLabel = (TextView) findViewById(R.id.accepted_owner_sitter_phone_number_label);
        sitterPhoneNumber = (TextView) findViewById(R.id.accepted_owner_sitter_phone_number);

        sitterRatingLabel = (TextView) findViewById(R.id.accepted_owner_sitter_rating_label);
        sitterRating = (TextView) findViewById(R.id.accepted_owner_sitter_rating);
        sitterRating.setText("5.0");


        //GET Request - get id/auth
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        clientId = pref.getString("id", "");
        clientAuth = pref.getString("auth", "");

        job_id = getIntent().getStringExtra("the_job_id");

        String startDateTime = "", endDateTime = "", sitterName = "";
        boolean success = false;
        boolean is_at_owner = false;
        float lat = 0, lon = 0;


        //Url connection
        UrlGet userInfo = new UrlGet("http://aiji.cs.loyola.edu/jobinfo?id=" + clientId + "&auth=" + clientAuth + "&job_id=" + job_id, "requests.list", this);
        userInfo.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String json = pref.getString("requests.list", "");
        try {
            JSONObject jsonObject = new JSONObject(json);
            startDateTime = jsonObject.getString("start_datetime");
            System.out.println("the startDate time is " + startDateTime);
            startDate.setText(startDateTime);
            endDateTime = jsonObject.getString("end_datetime");
            endDate.setText(endDateTime);
            sitterName = jsonObject.getString("sitter_name");
            sitterInformation.setText(sitterName);
            //grab phone number
            //need line for sitter information

        } catch (JSONException json_e) {
            System.out.println("in the catch");
            Log.w("MA", json_e.toString());
        }

    }


    public void onOwnerCancel(View v)
    {
        final Intent i = new Intent(this, OwnerAcceptedSittings.class);

        new AlertDialog.Builder(this)
                .setTitle("Cancel Job")
                .setMessage("Are you sure you want to cancel this job?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        JSONObject data = new JSONObject();
                        try {
                            data.put("id", clientId);
                            data.put("auth", clientAuth);
                            data.put("the_job_id", job_id);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.w("MA" ," the exception is ",  e);
                        }

                        UrlDelete saveInfo = new UrlDelete("http://aiji.cs.loyola.edu/jobdelete", data.toString(), "job.delete", getContext());
                        saveInfo.start();

                        //give persistent data time to write
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        //check response
                        String response = pref.getString("job.delete", "");
                        boolean success = false;
                        try {
                            JSONObject jsonObject1 = new JSONObject(response);
                            success = jsonObject1.getBoolean("success");
                        } catch( JSONException json_e ) {
                            if(!success) {
                               // showError();
                                //return;
                                Log.w("MA" ," the exception is ",  json_e);

                            }
                         }
                        startActivity(i);
                        finish();
                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setIcon(android.R.drawable.ic_menu_delete)
                .show();

    }

    /**
     * On Rate Sitter method
     *
     */
    public void onRateSitter(View v)
    {
        startActivity(new Intent(this, Rating.class));
    }
    /**
     * Get context
     * @return the activity
     */
    public Context getContext()
    {
        return this;
    }


    /**
     * Go Back method
     * @param view the view calling the method
     */
    public void goBack(View view) {
        startActivity(new Intent(this, OwnerAcceptedSittings.class));
        finish();
    }
}
