package com.alexandrafernandez.sweproject;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class NeedSitterEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.need_sitter_event);
        setTitle("Request a Sitter");
    }

    public void doPetSelection(View view) {


        startActivity(new Intent(this, PetPicker.class));
    }

    public void onCreateRequestSitterEvent(View view) {


        finish();
    }
}

