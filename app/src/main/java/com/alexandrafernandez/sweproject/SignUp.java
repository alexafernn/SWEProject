package com.alexandrafernandez.sweproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        UrlGet userInfo = new UrlGet("http://aiji.cs.loyola.edu/accountinfo/1","signUp.userInfo", this);
        userInfo.start();
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String json = pref.getString("signUp.userInfo", "");

        String first_name = ""; String last_name = ""; int type = 0; String myEmail = "";
        try {
            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObject1 = jsonArray.getJSONObject( 1 );
            first_name = jsonObject1.getString( "first_name" );
            last_name = jsonObject1.getString("last_name");
            type = jsonObject1.getInt("type");
            myEmail = jsonObject1.getString("email");
        } catch( JSONException json_e ) { }

        ScreenSize view = new ScreenSize(this);

        signupName = (TextView) findViewById(R.id.signupName);
        signupName.setTextSize((float) (0.5*view.getLabelTextSize()));

        nameEditText = (EditText) findViewById(R.id.NameEditText);
        nameEditText.setTextSize((float) (0.5*view.getEditTextSize()));
        nameEditText.setText(first_name);

        phoneNumber = (TextView) findViewById(R.id.phoneNumber);
        phoneNumber.setTextSize((float) (0.5*view.getLabelTextSize()));

        phoneEditText = (EditText) findViewById(R.id.PhoneEditText);
        phoneEditText.setTextSize((float) (0.5*view.getEditTextSize()));

        email = (TextView) findViewById(R.id.email);
        email.setTextSize((float) (0.5*view.getLabelTextSize()));

        emailEditText = (EditText) findViewById(R.id.EmailEditText);
        emailEditText.setTextSize((float) (0.5*view.getEditTextSize()));
        emailEditText.setText(myEmail);

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

        if(type == 1)
            userTypePetOwner.setChecked(true);
        else if(type == 2)
            userTypeSitter.setChecked(true);
        else if(type == 3) {
            userTypePetOwner.setChecked(true);
            userTypeSitter.setChecked(true);
        }

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
