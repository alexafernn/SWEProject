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

    TextView signupName, phoneNumber, email, address, username, password, profile_types;
    Switch userTypePetOwner, userTypeSitter;
    Button paypal_link_button, saveButton;
    EditText nameEditText, phoneEditText, emailEditText, addyEditText, usernameEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        setTitle("New Account Sign Up");

        ScreenSize view = new ScreenSize(this);

        signupName = (TextView) findViewById(R.id.signupName);
        signupName.setTextSize((float) (0.5*view.getLabelTextSize()));

        nameEditText = (EditText) findViewById(R.id.NameEditText);
        nameEditText.setTextSize((float) (0.5*view.getEditTextSize()));

        phoneNumber = (TextView) findViewById(R.id.phoneNumber);
        phoneNumber.setTextSize((float) (0.5*view.getLabelTextSize()));

        phoneEditText = (EditText) findViewById(R.id.PhoneEditText);
        phoneEditText.setTextSize((float) (0.5*view.getEditTextSize()));

        email = (TextView) findViewById(R.id.email);
        email.setTextSize((float) (0.5*view.getLabelTextSize()));

        emailEditText = (EditText) findViewById(R.id.EmailEditText);
        emailEditText.setTextSize((float) (0.5*view.getEditTextSize()));

        address = (TextView) findViewById(R.id.addressSignup);
        address.setTextSize((float) (0.5*view.getLabelTextSize()));

        addyEditText = (EditText) findViewById(R.id.AddyEditText);
        addyEditText.setTextSize((float) (0.5*view.getEditTextSize()));

        username = (TextView) findViewById(R.id.usernameSignUp);
        username.setTextSize((float) (0.5*view.getLabelTextSize()));

        usernameEditText = (EditText) findViewById(R.id.UserNameEditText);
        usernameEditText.setTextSize((float) (0.5*view.getEditTextSize()));

        password = (TextView) findViewById(R.id.passwordSignUp);
        password.setTextSize((float) (0.5*view.getLabelTextSize()));

        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        passwordEditText.setTextSize((float) (0.5*view.getEditTextSize()));

        profile_types = (TextView) findViewById(R.id.profile_types_signup);
        profile_types.setTextSize((float) (0.5*view.getLabelTextSize()));

        userTypePetOwner = (Switch) findViewById(R.id.userTypePetOwner);
        userTypePetOwner.setTextSize(view.getSwitchTextSize());

        userTypeSitter = (Switch) findViewById(R.id.userTypeSitter);
        userTypeSitter.setTextSize(view.getSwitchTextSize());

        paypal_link_button = (Button) findViewById(R.id.paypal_link_button);
        paypal_link_button.setTextSize(view.getButtonTextSize());

        saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setTextSize(view.getButtonTextSize());

    }

    public void save(View v)
    {
        startActivity(new Intent(this, MainActivity.class));

        //make sure to save account to the server and be able to verify if user name already exist

        finish();
    }

    public void payPalConnection(View v)
    {
        //make sure we connect with PayPal
    }
}
