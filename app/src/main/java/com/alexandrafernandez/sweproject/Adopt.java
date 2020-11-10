package com.alexandrafernandez.sweproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Adopt extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adopt);
        setTitle("Adopt");
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.adopt_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.back:
                finish();
                return true;
            case R.id.check_status:
                startActivity(new Intent(this, AdoptionStatus.class));
                return true;
            case R.id.feedback:
                startActivity(new Intent(this, Feedback.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void notifyShelters(View view) {


        finish();
    }

}
