package com.alexandrafernandez.sweproject;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Rewards extends AppCompatActivity {

    TextView rewards_menu_top, rewards_points_total, reward_types, reward_select, amount_select, rewards_delivery_info;
    Spinner spinner, spinner2;
    Button rewards_request_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rewards);
        setTitle("Track Your Rewards");

        ScreenSize view = new ScreenSize(this);

        rewards_menu_top = (TextView) findViewById(R.id.rewards_menu_top);
        rewards_menu_top.setTextSize(view.getLabelTextSize());

        rewards_points_total = (TextView) findViewById(R.id.rewards_points_total);
        rewards_points_total.setTextSize(3*view.getLabelTextSize());

        reward_types = (TextView) findViewById(R.id.reward_types);
        reward_types.setTextSize(view.getLabelTextSize());

        reward_select = (TextView) findViewById(R.id.reward_select);
        reward_select.setTextSize(view.getLabelTextSize());

        spinner = (Spinner) findViewById(R.id.gift_card_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gift_card_choices, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        amount_select = (TextView) findViewById(R.id.amount_select);
        amount_select.setTextSize(view.getLabelTextSize());

        spinner2 = (Spinner) findViewById(R.id.amount_spinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.gift_card_amounts, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        rewards_request_button = (Button) findViewById(R.id.rewards_request_button);
        rewards_request_button.setTextSize(view.getButtonTextSize());

        rewards_delivery_info = (TextView) findViewById(R.id.rewards_delivery_info);
        rewards_delivery_info.setTextSize((float) (0.8*view.getLabelTextSize()));
    }

    public void onRequestReward(View view) {



        finish();
    }
}
