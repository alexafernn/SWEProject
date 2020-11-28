package com.alexandrafernandez.sweproject;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Feedback Class
 * This class allows users to send feedback about their experience
 * CS482 Software Engineering
 * Prof. Mehri
 * @author Jack Cannon
 * @author Alexandra Fernandez
 * @version 3.0 Final Release
 */
public class Feedback extends AppCompatActivity {

    /**
     * Text Views for identifying field components
     */
    TextView feedback_text, rating_select, feedback_notes;

    /**
     * Edit Text View for user input of relevant information
     */
    EditText feedback_text_field;

    /**
     * Spinner for selecting rating
     */
    Spinner star_rating_spinner;

    /**
     * Buttons used to confirm data and/or move to another activity
     */
    Button feedback_button;

    /**
     * On Create Method
     * Initializes the add pet View and instantiates other view objects for later use
     * @param savedInstanceState android system parameter
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);
        setTitle("Feedback Form");

        ScreenSize view = new ScreenSize(this);

        feedback_text = (TextView) findViewById(R.id.feedback_text);
        feedback_text.setTextSize((float) (0.7*view.getLabelTextSize()));

        feedback_text_field = (EditText) findViewById(R.id.feedback_text_field);
        feedback_text_field.setTextSize(view.getEditTextSize());

        star_rating_spinner= (Spinner) findViewById(R.id.star_rating_spinner);
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this, R.array.feedback_star_rating, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        star_rating_spinner.setAdapter(adapter5);

        rating_select = (TextView) findViewById(R.id.rating_select);
        rating_select.setTextSize(view.getLabelTextSize());

        feedback_button = (Button) findViewById(R.id.feedback_button);
        feedback_button.setTextSize(view.getButtonTextSize());

        feedback_notes = (TextView) findViewById(R.id.feedback_notes);
        feedback_notes.setTextSize((float) (0.7*view.getLabelTextSize()));

    }

    /**
     * Send Feedback method
     * Send data entered by user back to the server
     * @param view the reference object calling this method
     */
    public void doSendFeedback(View view) {

        //TODO implement server connection if time allows (secondary feature)

        Toast.makeText(this, "Thank you for your feedback!", Toast.LENGTH_SHORT).show();
        finish();
    }
}

