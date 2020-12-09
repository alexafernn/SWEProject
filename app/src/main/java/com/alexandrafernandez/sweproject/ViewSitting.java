package com.alexandrafernandez.sweproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ViewSitting extends AppCompatActivity
{
    /**
     * Text Views for identifying field components
     */
    TextView startDate, endDate, allMyPets, mustOccurLocation, notes;

    /**
     * Buttons used to confirm data and/or move to another activity
     */
    Button confirm_sitting_button, delete_button;



    private Context context;

    private Intent intent;
    private int position;

    /**
     * On Create Method
     * Initializes the approve sitting View and instantiates other view objects for later use
     *
     * @param savedInstanceState android system parameter
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sitting_details);
        setTitle("Approve Sitting");

        ScreenSize view = new ScreenSize(this);

        confirm_sitting_button = (Button) findViewById(R.id.confirm_availability_button2);
        confirm_sitting_button.setTextSize(view.getButtonTextSize());

        delete_button = (Button) findViewById(R.id.delete_button);
        delete_button.setTextSize(view.getButtonTextSize());


        //will  need to change this to pull data from the server
        startDate = (TextView) findViewById(R.id.start_date_label2);
        startDate.setText("04/03/2021");

        endDate = (TextView) findViewById(R.id.end_date_label2);
        endDate.setText("04/04/2022");

//        allMyPets = (TextView) findViewById(R.id.all_my_pet_label2);
//        allMyPets.setText("false");

        mustOccurLocation = (TextView) findViewById(R.id.must_occur_at_my_location_label2);
        mustOccurLocation.setText("true");

        notes = (TextView) findViewById(R.id.notes_for_sitter_label2);
        notes.setText("sheds a lots");

        intent = getIntent();
        position = intent.getIntExtra("position_in_list", -1);
    }


    /**
     * On Confirm
     * Return to the prvious activity after saving data
     * @param view the reference object calling this method
     */
    public void onConfirm2(View v)
    {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }

    public void onDelete(View v)
    {

        System.out.println("the value we are looking for is "+ position);

        //update server

        context = this;
        final Intent i = new Intent(this, HomeActivity.class);
        //final int delete =1;

        DialogInterface.OnClickListener dClickListener = new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int choice) {
                switch(choice) {
                    case DialogInterface.BUTTON_POSITIVE:

                        Intent intent3 = new Intent(context, HomeActivity.class);
                        intent3.putExtra("position_value", position);
                        startActivity(new Intent(ViewSitting.this, HomeActivity.class)); // yes cancel
                        //mark it was cancelled
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                                startActivity(new Intent(ViewSitting.this, ViewSitting.class)); // no dont cancel
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(ViewSitting.this);
        builder.setMessage("Are you sure you want to cancel this sitting?").setPositiveButton("YES", dClickListener).setNegativeButton("NO", dClickListener).show();



    }


}

