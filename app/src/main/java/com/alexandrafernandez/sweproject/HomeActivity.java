package com.alexandrafernandez.sweproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Home Activity Class
 * This class allows users to see the sittings they are scheduled for
 * CS482 Software Engineering
 * Prof. Mehri
 * @author Jack Cannon
 * @author Alexandra Fernandez
 * @version 3.0 Final Release
 */
public class HomeActivity extends AppCompatActivity {

    /**
     * List managing the sittings a user is scheduled for
     */
    ArrayList<Sitting> sittingList;

    /**
     * List View for managing multiple content instances of sittings
     */
    ListView sittings_listView;

    /**
     * On Create Method
     * Initializes the home activity View and instantiates other view objects for later use
     * @param savedInstanceState android system parameter
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_home);
        setContentView(R.layout.current_sittings);
        setTitle("My Sittings");
        ScreenSize view = new ScreenSize(this);
        TextView noSittings = (TextView) findViewById(R.id.noSittings);
        noSittings.setTextSize((float) (0.7 * view.getLabelTextSize()));


        final Sitting[] sitting = {MainActivity.sitting};

        System.out.println("number of sittings created is " + sitting[0].getNumberOfSittings());

        if (sitting[0].getNumberOfSittings() == 0)
        {
            noSittings.setVisibility(View.VISIBLE);



        }
        else
        {

            noSittings.setVisibility(View.INVISIBLE);

            sittingList = new ArrayList<Sitting>();
            sittingList.add(new Sitting("04/03/2021", "04/04/2022", false,true, "sheds a lot "));
            sittings_listView = (ListView) findViewById(R.id.sitting_listview);
            ArrayAdapter<Sitting> adapter = new ArrayAdapter<Sitting>(this, android.R.layout.simple_list_item_1, sittingList);
            sittings_listView.setAdapter(adapter);

           // do something when u click on the sitting

         }
    }

    //todo implement intent/finish()
}
