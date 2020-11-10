package com.alexandrafernandez.sweproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
    }

    public void save(View v)
    {
        startActivity(new Intent(this, MainActivity.class));
        //make sure to save account to the server and be able to verify if user name already exist
    }

    public void payPalConnection(View v)
    {
        //make sure we connect with PayPal
    }
}
