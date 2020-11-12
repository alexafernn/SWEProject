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

public class Feedback extends AppCompatActivity {

    TextView feedback_text, rating_select, feedback_notes;
    EditText feedback_text_field;
    Spinner star_rating_spinner;
    Button feedback_button;

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

    public void doSendFeedback(View view) {


        Toast.makeText(this, "Thank you for your feedback!", Toast.LENGTH_SHORT).show();
        finish();
    }
}

