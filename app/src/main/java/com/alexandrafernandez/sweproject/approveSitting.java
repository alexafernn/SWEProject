package com.alexandrafernandez.sweproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class approveSitting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm);
        setTitle("Approve Sitting");

        Intent i = getIntent();
        i.getExtras();

        String value = i.toString();

        System.out.println("value = " + value);



        //Intent flagIntent = getIntent();
    }

    public void onStart()
    {
        super.onStart();
        //updateView();
    }

}
