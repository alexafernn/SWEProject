package com.alexandrafernandez.sweproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class SitterAvailability extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sitter_availability);
        setTitle("Availability");
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.availability_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home_from_availability:
                finish();
                return true;
            case R.id.add_availability:
                startActivity(new Intent(this, SitterAvailabilityEvent.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
