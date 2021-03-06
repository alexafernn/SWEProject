package com.alexandrafernandez.sweproject;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

/**
 * Subscription Class
 * This class allows a sitter to view and redeem their rewards
 * CS482 Software Engineering
 * Prof. Mehri
 * @author Jack Cannon
 * @author Alexandra Fernandez
 * @version 3.0 Final Release
 */
public class Subscription extends AppCompatActivity {

    /**
     * Text Views for identifying field components
     */
    TextView subscription_menu_top, subscription_status, subscription_expiration, extend_title, plan_select, payment_select, subscription_payment_info;

    /**
     * Spinner for selecting payment Type
     */
    Spinner spinner3, spinner4;

    /**
     * Buttons used to confirm data and/or move to another activity
     */
    Button subscription_extend_button;

    /**
     * On Create Method
     * Initializes the subscription View and instantiates other view objects for later use
     * @param savedInstanceState android system parameter
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subscription);
        setTitle("Manage Your Subscription");

        ScreenSize view = new ScreenSize(this);

        subscription_menu_top = (TextView) findViewById(R.id.subscription_menu_top);
        subscription_menu_top.setTextSize(view.getLabelTextSize());

        subscription_status = (TextView) findViewById(R.id.subscription_status);
        subscription_status.setTextSize((float) (2.25*view.getLabelTextSize()));

        subscription_expiration = (TextView) findViewById(R.id.subscription_expiration);
        subscription_expiration.setTextSize((float) (0.8*view.getLabelTextSize()));

        extend_title = (TextView) findViewById(R.id.extend_title);
        extend_title.setTextSize(view.getLabelTextSize());

        plan_select = (TextView) findViewById(R.id.plan_select);
        plan_select.setTextSize(view.getLabelTextSize());

        spinner3 = (Spinner) findViewById(R.id.plan_spinner);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.plan_choices, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        payment_select = (TextView) findViewById(R.id.payment_select);
        payment_select.setTextSize(view.getLabelTextSize());

        spinner4 = (Spinner) findViewById(R.id.payment_spinner);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.payment_choices, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);

        subscription_extend_button = (Button) findViewById(R.id.subscription_extend_button);
        subscription_extend_button.setTextSize(view.getButtonTextSize());

        subscription_payment_info = (TextView) findViewById(R.id.subscription_payment_info);
        subscription_payment_info.setTextSize((float) (0.8*view.getLabelTextSize()));
    }

    /**
     * Subscription Update method
     * After saving data to device/server, returns to the main Owner view
     * @param view the reference object calling this method
     */
    public void doSubscriptionUpdate(View view) {

        //TODO implement if time allows (low priority feature)

        finish();
    }
}
