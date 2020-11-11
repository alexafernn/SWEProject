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
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Intro extends AppCompatActivity {

    TextView greeting, status_messages;
    Button owner, sitter, adoption;
    int width, height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setTitle("Current Sittings");

        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        Resources res = getResources();
        int statusBarHeight = 0;
        int statusBarID = res.getIdentifier("status_bar_height", "dimen", "android" );
        if(statusBarID > 0){
            statusBarHeight = res.getDimensionPixelSize(statusBarID);
        }
        width = size.x;
        height = size.y - statusBarHeight;

        String name = "<username>";
        String greetMe = "Hi, " + name;
        greeting = (TextView) findViewById(R.id.greeting);
        greeting.setText(greetMe);
        greeting.setTextSize(height/70);

        status_messages = (TextView) findViewById(R.id.status_messages);
        status_messages.setTextSize(height/85);
        status_messages.setHeight(height/3);

        owner = findViewById(R.id.owner_main_button);
        owner.setTextSize(height/50);

        sitter = findViewById(R.id.sitter_main_button);
        sitter.setTextSize(height/50);

        adoption = findViewById(R.id.adoption_main_button);
        adoption.setTextSize(height/50);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                startActivity(new Intent(this, HomeActivity.class));
                return true;
            case R.id.profile:
                startActivity(new Intent(this, Profile.class));
                return true;
            case R.id.feedback:
                startActivity(new Intent(this, Feedback.class));
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void toOwnerProfile(View view) {
        startActivity(new Intent(this, Owner.class));
    }

    public void toSitterProfile(View view) {
        startActivity(new Intent(this, Sitter.class));
    }

    public void toAdoptionPage(View view) {
        startActivity(new Intent(this, Adopt.class));
    }
}
