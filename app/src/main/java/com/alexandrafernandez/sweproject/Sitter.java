package com.alexandrafernandez.sweproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.util.Log;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

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
     * Event to be selected and accessed after assignment
     */
    Sitting event;

    /**
     * Activity and View data
     */
    private Context context;

    /**
     * Server interaction objects
     */
    SharedPreferences pref;
    String clientID, clientAuth, job_id;

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

        if (sitting[0].getNumberOfSittings() == 0)
        {
            Toast.makeText(getApplicationContext(),"No reservations open for sitting",Toast.LENGTH_SHORT).show();
        }

        //GET Request - get id/auth
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        clientID = pref.getString("id", "");
        clientAuth = pref.getString("auth", "");

        //Url connection
        UrlGet userInfo = new UrlGet("http://aiji.cs.loyola.edu/availablejoblist?id=" + clientID + "&auth=" + clientAuth,"available.sitter", this);
        userInfo.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        sittingList = new ArrayList<Sitting>();
        boolean success = false;

        //Save response
        String json = pref.getString("available.sitter", "");
        String id="", startDateTime="", endDateTime="", ownerName="";
        JSONObject jobData;
        try {
            JSONObject jsonObject = new JSONObject(json);
            Iterator<String> keys = jsonObject.keys();
            while(keys.hasNext()) {
                id = keys.next();
                jobData = jsonObject.getJSONObject(id);
                ownerName = jobData.getString("owner_name");
                startDateTime = jobData.getString("start_datetime");
                endDateTime = jobData.getString("end_datetime");
                if(!id.equals("success"))
                    sittingList.add(new Sitting(startDateTime, endDateTime, ownerName, id));
                else success = true;
            }
        } catch( JSONException json_e ) {
            Log.w("MA", json_e.toString());
        }

        if(!success) {
            Toast.makeText(this, "No sittings found.", Toast.LENGTH_SHORT).show();
        }

        context = this;

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

        sittings_listView = (ListView) findViewById(R.id.sitting2_listview);
        sittings_listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                event = sittingList.get(i);

                Intent intent = new Intent(context, Sitting.class);
                intent.putExtra("job_id", event.id);
                startActivity(intent);
                finish();
            }
        });
        sittings_listView.setAdapter(adapter);
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

