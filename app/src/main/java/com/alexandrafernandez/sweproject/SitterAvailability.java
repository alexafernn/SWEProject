package com.alexandrafernandez.sweproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Sitter Availability Class
 * This class allows a sitter to manage their availability
 * CS482 Software Engineering
 * Prof. Mehri
 * @author Jack Cannon
 * @author Alexandra Fernandez
 * @version 3.0 Final Release
 */
public class SitterAvailability extends AppCompatActivity {

    /**
     * List of data from sitter availability events
     */
    ArrayList<SitterAvailabilityData> availabilityList;

    /**
     * List View object for organizing sitter availability events
     */
    ListView sitter_availability_listview;

    /**
     * Sitter Availability information to be selected and accessed after assignment
     */
    SitterAvailabilityData event;

    /**
     * Activity and View data
     */
    private Context context;

    /**
     * On Create Method
     * Initializes the sitter availability View and instantiates other view objects for later use
     * @param savedInstanceState android system parameter
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sitter_availability);
        setTitle("Availability");

        availabilityList = new ArrayList<SitterAvailabilityData>(); //replace with server pull
        availabilityList.add(new SitterAvailabilityData("01/01/2021","02/02/2021", ""));

        sitter_availability_listview = (ListView) findViewById(R.id.sitter_availabilty_listview);
        ArrayAdapter<SitterAvailabilityData> adapter = new ArrayAdapter<SitterAvailabilityData>(this, android.R.layout.simple_list_item_1, availabilityList)
                ;
        sitter_availability_listview.setAdapter(adapter);

        context = this;

        sitter_availability_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                event = availabilityList.get(i);

                Intent intent = new Intent(context, SitterAvailabilityEvent.class);
                intent.putExtra("viewAvailability.dateStart", event.startDate);
                intent.putExtra("viewAvailability.dateEnd", event.endDate);
                intent.putExtra("viewAvailability.notes", event.notes);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * on Create Options Menu
     * Initialize and connect the menu for this class
     * @param menu the appropriate menu object for this view (availability_menu.xml)
     * @return true if successful creation of menu
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.availability_menu, menu);
        return true;
    }

    /**
     * On Options Item Selected method
     * @param item the option selected by the user
     * @return true once an action is taken regarding this selection
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home_from_availability:
                finish();
                return true;
            case R.id.add_availability:
                startActivity(new Intent(this, SitterAvailabilityEvent.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
