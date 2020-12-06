package com.alexandrafernandez.sweproject;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Sitter Class
 * This class allows a sitter to perform actions sitters must access
 * CS482 Software Engineering
 * Prof. Mehri
 * @author Jack Cannon
 * @author Alexandra Fernandez
 * @version 3.0 Final Release
 */
public class Sitter extends AppCompatActivity {

    /**
     * List managing the sittings nearby owners have requested
     */
    ArrayList<Sitting> sittingList;

    /**
     * List View for managing multiple content instances of sitting requests
     */
    ListView sittings_listView;

    /**
     * On Create Method
     * Initializes the sitter View and instantiates other view objects for later use
     * @param savedInstanceState android system parameter
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sitter);
        setTitle("SITTER");

        Bundle b;
        final Sitting[] sitting = {MainActivity.sitting};
        final Sitting test = new Sitting("04/03/2021", "04/04/2022", false,true, "sheds a lot ");

        //if(sitting[0] !=null)
        //{


        if (sitting[0].getNumberOfSittings() == 0)
        {
            Toast.makeText(getApplicationContext(),"No reservations open for sitting",Toast.LENGTH_SHORT).show();
        }
       // else {

            sittingList = new ArrayList<Sitting>();
            // sittingList.add(new Sitting("04/03/2021", "04/04/2022", false,true, "sheds a lot "));
            sittingList.add(test);
            sittings_listView = (ListView) findViewById(R.id.sitting2_listview);
            ArrayAdapter<Sitting> adapter = new ArrayAdapter<Sitting>(this, android.R.layout.simple_list_item_1, sittingList)
            {
                @Override
                public View getView(int position, View convertView, ViewGroup parent)
                {
                    TextView textView = (TextView) super.getView(position, convertView, parent);
                    textView.setTextColor(Color.WHITE);
                    textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 22);
                    return textView;

                }
            };


            //ArrayAdapter<Sitting> adapter = new ArrayAdapter<Sitting>(this, R.layout.list_view_details, sittingList);
            // CustomAdapter myAdapter = new CustomAdapter(getApplicationContext(), sittingList);
            sittings_listView.setAdapter(adapter);

            sittings_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView parent, View v, int position, long id) {
//                    Sitting list_row = sittingList.get(position);
//                    //if position = 0
//                    Intent intent = new Intent(Sitter.this, approveSitting.class); //idk
//                    intent.putExtra("Myobject", test);
//                    startActivity(intent);
//                    //else if postion == 1

                    Intent i = new Intent(Sitter.this, approveSitting.class);
                    i.putExtra("Test", test);
                    startActivity(i);

                }
            });
       // }
            // do something when u click on the sitting

            //TODO clean up / add server connections
        }



    /**
     * on Create Options Menu
     * Initialize and connect the menu for this class
     * @param menu the appropriate menu object for this view (sitter_menu.xml)
     * @return true if successful creation of menu
     */
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sitter_menu, menu);
        return true;
    }

    /**
     * On Options Item Selected method
     * @param item the option selected by the user
     * @return true once an action is taken regarding this selection
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                finish();
                return true;
            case R.id.action_favorite:
                startActivity(new Intent(this, HomeActivity.class));
                return true;
            case R.id.rewards_menu:
                startActivity(new Intent(this, Rewards.class));
                return true;
            case R.id.past_sittings_menu:
                startActivity(new Intent(this, PastSittings.class));
                return true;
            case R.id.feedback_menu:
                startActivity(new Intent(this, Feedback.class));
                return true;
            case R.id.sitter_Availability:
                startActivity(new Intent(this, SitterAvailability.class));

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

