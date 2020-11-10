package com.alexandrafernandez.sweproject;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SitterAvailabilityEvent extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sitter_availability_event);
        setTitle("Add Block Availability");
    }

    public void onCreateAvailabilityEvent(View view) {



        finish();
    }

}
