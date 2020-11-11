package com.alexandrafernandez.sweproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class Profile extends Activity {
    EditText name, phone, address;
    Button save, payPal;
    Switch owner, sitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        setTitle("Profile Settings");

        name = (EditText) findViewById(R.id.name_field);
        phone = (EditText) findViewById(R.id.phone_field);
        address = (EditText) findViewById(R.id.address_field);
        save = (Button) findViewById(R.id.settings_save_button);
        payPal = (Button) findViewById(R.id.paypal_link_button);
        owner = (Switch) findViewById(R.id.switch1);
        sitter = (Switch) findViewById(R.id.switch2);
    }

    public void goHome(View view) {
        //add persistent data, do all updating here
        finish();
    }

    public void doPaypalLink(View view) {

    }
}
