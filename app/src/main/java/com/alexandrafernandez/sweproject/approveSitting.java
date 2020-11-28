package com.alexandrafernandez.sweproject;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Approve Sitting Class
 * This class allows users to add pets to their profile
 * CS482 Software Engineering
 * Prof. Mehri
 * @author Jack Cannon
 * @author Alexandra Fernandez
 * @version 3.0 Final Release
 */
public class approveSitting extends AppCompatActivity {

    /**
     * On Create Method
     * Initializes the adopt View and instantiates other view objects for later use
     * @param savedInstanceState android system parameter
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm);
        setTitle("Approve Sitting");

        //need to get this working : right now it only prints out the value of startDate from that object but need it to print the other instance variables
        //so that we can set the edit texts from those values down below
        //checked toString and that wasnt the problem
        //talked to herve but he's not sure about this either

        Intent i = getIntent();
        Sitting result = (Sitting) i.getSerializableExtra("Test");
        String value = result.toString();
        System.out.println("the result is "+ value);

//        try {
//            Sitting result = (Sitting) getIntent().getExtras().get("Myobject");
//            System.out.println("let's see " + result.toString());
//        }
//        catch(Exception e)
//        {
//            Log.e("GETTING EXTRAS", e.toString());
//        }


        TextView startDate = (TextView) findViewById(R.id.start_date_label);
        startDate.setText("04/03/2021");

        TextView endDate = (TextView) findViewById(R.id.end_date_label);
        endDate.setText("04/04/2022");

        TextView allMyPets = (TextView) findViewById(R.id.all_my_pet_label);
        allMyPets.setText("false");

        TextView mustOccurLocation = (TextView) findViewById(R.id.must_occur_at_my_location_label);
        mustOccurLocation.setText("true");

        TextView notes = (TextView) findViewById(R.id.notes_for_sitter_label);
        notes.setText("sheds a lots");


    }

    /**
     * On Start method
     * Called each time activity is used
     */
    public void onStart()
    {
        super.onStart();
        //updateView();
    }

    /**
     * On Cancel
     * Returns to the previous activity
     * @param view the reference object calling this method
     */
    public void onCancel(View v)
    {
        this.finish();
    }

    /**
     * On Confirm
     * Return to the prvious activity after saving data
     * @param view the reference object calling this method
     */
    public void onConfirm(View v)
    {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
        //need to actually pass the confirmed sitting 
        //this.finish();
        //change this to a confirmed page
    }

}
