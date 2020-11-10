package com.alexandrafernandez.sweproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class Sitter extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sitter);
        setTitle("SITTER");
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sitter_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                finish();
                return true;
            case R.id.action_favorite:
                startActivity(new Intent(this, HomeActivity.class));
                return true;
            case R.id.rewards_menu:
                startActivity(new Intent(this, Rewards.class));
                return true;
            case R.id.past_sittings_menu:
                startActivity(new Intent(this, PastSittings.class));
                return true;
            case R.id.feedback_menu:
                startActivity(new Intent(this, Feedback.class));
                return true;
            case R.id.sitter_Availability:
                startActivity(new Intent(this, SitterAvailability.class));

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

