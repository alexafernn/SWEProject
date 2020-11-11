package com.alexandrafernandez.sweproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int height, width;
    TextView login_text, username, password;
    Button loginButton, signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

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

        login_text = (TextView) findViewById(R.id.loginText);
        login_text.setTextSize(height/70);

        username = (TextView) findViewById(R.id.username);

        password = (TextView) findViewById(R.id.password);

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