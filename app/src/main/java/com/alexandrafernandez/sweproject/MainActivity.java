package com.alexandrafernandez.sweproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends Activity {

    int height, width;
    TextView login_text, username, password;
    Button loginButton, signUpButton;
    EditText usernameEditText, passwordEditText;
    Switch loginSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        if(pref.getBoolean("loginSwitchChecked", false))
            startActivity(new Intent(this, Intro.class));

        ScreenSize view = new ScreenSize(this);


        login_text = (TextView) findViewById(R.id.loginText);
        login_text.setTextSize((float) (1.2*view.getLabelTextSize()));

        username = (TextView) findViewById(R.id.username);
        username.setTextSize((float) (0.5*view.getLabelTextSize()));

        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        usernameEditText.setTextSize(view.getEditTextSize());

        password = (TextView) findViewById(R.id.password);
        password.setTextSize((float) (0.5*view.getLabelTextSize()));

        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        passwordEditText.setTextSize(view.getEditTextSize());

        loginSwitch = (Switch) findViewById(R.id.password_switch);
        loginSwitch.setTextSize(view.getSwitchTextSize());

        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setTextSize(view.getButtonTextSize());

        signUpButton = (Button) findViewById(R.id.signUpButton);
        signUpButton.setTextSize(view.getButtonTextSize());

    }

        public void verifyLogin(View v)
        {
            if(loginSwitch.isChecked()) {
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = pref.edit();
                String username = usernameEditText.getText().toString();
                editor.putString("username", username);
                String password = passwordEditText.getText().toString();
                editor.putString("password", password);
                boolean loginSwitchChecked = loginSwitch.isChecked();
                editor.putBoolean("loginSwitchChecked", loginSwitchChecked);
                editor.commit();
            }

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