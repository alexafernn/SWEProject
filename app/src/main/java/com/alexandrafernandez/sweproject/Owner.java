package com.alexandrafernandez.sweproject;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Owner extends AppCompatActivity {

    Button sitter_request_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner);
        setTitle("OWNER");

        ScreenSize view = new ScreenSize(this);

        sitter_request_button = findViewById(R.id.sitter_request_button);
        sitter_request_button.setTextSize(view.getButtonTextSize());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.owner_menu, menu);
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
            case R.id.my_pets_menu:
                startActivity(new Intent(this, Pets.class));
                return true;
            case R.id.subscription_menu:
                startActivity(new Intent(this, Subscription.class));
                return true;
            case R.id.past_sittings_menu:
                startActivity(new Intent(this, PastSittings.class));
                return true;
            case R.id.feedback_menu:
                startActivity(new Intent(this, Feedback.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onRequestSitterEvent(View view) {
        startActivity(new Intent(this, NeedSitterEvent.class));
    }
}
