package com.alexandrafernandez.sweproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {

    TextView loginText, signupName, phoneNumber, email, address, username, password, profile_types;
    Switch userTypePetOwner, userTypeSitter;
    Button paypal_link_button, saveButton;
    EditText nameEditText, phoneEditText, emailEditText, addyEditText, usernameEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        ScreenSize view = new ScreenSize(this);

        loginText = (TextView) findViewById(R.id.loginText);
        loginText.setTextSize(view.getLabelTextSize());

        signupName = (TextView) findViewById(R.id.signupName);
        signupName.setTextSize(view.getLabelTextSize());

        phoneNumber = (TextView) findViewById(R.id.phoneNumber);
        phoneNumber.setTextSize(view.getLabelTextSize());

        email = (TextView) findViewById(R.id.email);
        email.setTextSize(view.getLabelTextSize());

        address = (TextView) findViewById(R.id.addressSignup);
        address.setTextSize(view.getLabelTextSize());

        username = (TextView) findViewById(R.id.usernameSignUp);
        username.setTextSize(view.getLabelTextSize());

        password = (TextView) findViewById(R.id.passwordSignUp);
        password.setTextSize(view.getLabelTextSize());

        profile_types = (TextView) findViewById(R.id.profile_types_signup);
        profile_types.setTextSize(view.getLabelTextSize());

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
