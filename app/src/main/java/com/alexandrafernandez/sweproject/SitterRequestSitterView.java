package com.alexandrafernandez.sweproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SitterRequestSitterView extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sitter_request_sitter_view);
        String ownerUser = "Owner"; //update appropriately
        setTitle("Request from " + ownerUser);

    }
}
