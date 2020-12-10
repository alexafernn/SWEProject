package com.alexandrafernandez.sweproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Rating extends AppCompatActivity {

    Button b;

    /**
     * On Create Method
     * Initializes the rating View and instantiates other view objects for later use
     *
     * @param savedInstanceState android system parameter
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating);
        setTitle("Rate a user");

        b = (Button) findViewById(R.id.rating_button);

    }

    /**
     * On Submit Rating method
     * Return to the previous activity after saving data
     * @param view the reference object calling this method
     */
    public void onSubmitRating(View view) {
        Toast.makeText(this, "Rating Submitted.", Toast.LENGTH_SHORT).show();
        finish();
    }
}
