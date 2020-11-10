package com.alexandrafernandez.sweproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView greeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

        public void verifyLogin(View v)
        {
            //if login verified
            startActivity(new Intent(this, Intro.class));
            //else
                //error message

        }

        public void signUp(View v)
        {
            startActivity(new Intent(this, SignUp.class));
        }

}