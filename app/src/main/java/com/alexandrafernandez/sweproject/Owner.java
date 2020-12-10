package com.alexandrafernandez.sweproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Owner Class
 * This class allows serves as the main activity for Pet Owner usage
 * CS482 Software Engineering
 * Prof. Mehri
 * @author Jack Cannon
 * @author Alexandra Fernandez
 * @version 3.0 Final Release
 */
public class Owner extends AppCompatActivity {

    /**
     * Buttons used to confirm data and/or move to another activity
     */
    Button sitter_request_button;

    /**
     * Text Views for identifying field components
     */
    TextView requestLabel, noSittingsLabel;

    /**
     * List View for managing multiple content instances of sitting requests
     */
    ListView owner_listview;

    /**
     * List managing the sittings an owner has requested
     */
    ArrayList<NeedSitterEventData> requestList;

    /**
     * Event to be selected and accessed after assignment
     */
    NeedSitterEventData event;

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
     * Initializes the owner View and instantiates other view objects for later use
     * @param savedInstanceState android system parameter
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner);
        setTitle("OWNER");

        //GET Request - get id/auth
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        clientID = pref.getString("id", "");
        clientAuth = pref.getString("auth", "");

        //Url connection
        UrlGet userInfo = new UrlGet("http://aiji.cs.loyola.edu/ownerjoblist?id=" + clientID + "&auth=" + clientAuth + "&is_accepted=" + false,"requests.list", this);
        userInfo.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        requestList = new ArrayList<NeedSitterEventData>();
        boolean success = false;

        //Save response
        String json = pref.getString("requests.list", "");
        String id="", startDateTime="", endDateTime="";
        JSONObject jobData;
        try {
            JSONObject jsonObject = new JSONObject(json);
            Iterator<String> keys = jsonObject.keys();
            while(keys.hasNext()) {
                id = keys.next();
                if(!id.equals("success")) {
                    jobData = jsonObject.getJSONObject(id);
                    startDateTime = jobData.getString("start_datetime");
                    endDateTime = jobData.getString("end_datetime");
                    requestList.add(new NeedSitterEventData(startDateTime, endDateTime, id));
                }
                else success = true;
            }
        } catch( JSONException json_e ) {
            Log.w("MA", json_e.toString());
        }

        if(!success) {
            Toast.makeText(this, "No sittings found.", Toast.LENGTH_SHORT).show();
        }

        ScreenSize view = new ScreenSize(this);

        sitter_request_button = findViewById(R.id.sitter_request_button);
        sitter_request_button.setTextSize(view.getButtonTextSize());

        requestLabel = (TextView) findViewById(R.id.sitter_request_label);
        requestLabel.setTextSize(view.getLabelTextSize());

        noSittingsLabel = (TextView) findViewById(R.id.noSittings);
        noSittingsLabel.setTextSize(view.getLabelTextSize());

        owner_listview = (ListView) findViewById(R.id.owner_listview);
        ArrayAdapter<NeedSitterEventData> adapter = new ArrayAdapter<NeedSitterEventData>(this, android.R.layout.simple_list_item_1, requestList)
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
        owner_listview.setAdapter(adapter);

        for(int i=0; i<requestList.size(); i++) {
            Log.w("MA", "REquestList arary");
            Log.w("MA" , requestList.get(i).toString());
        }

        context = this;

        owner_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                event = requestList.get(i);

                Intent intent = new Intent(context, NeedSitterEvent.class);
                intent.putExtra("job_id", event.id);
                startActivity(intent);
                finish();
            }
        });

        ViewGroup.LayoutParams params = owner_listview.getLayoutParams();
        params.height = (int) (requestList.size()*view.getLabelTextSize()*4.5);
        owner_listview.setLayoutParams(params);
        owner_listview.requestLayout();
    }

    /**
     * on Create Options Menu
     * Initialize and connect the menu for this class
     * @param menu the appropriate menu object for this view (owner_menu.xml)
     * @return true if successful creation of menu
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.owner_menu, menu);
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
                //startActivity(new Intent(this, HomeActivity.class));
                //startActivity(new Intent(this, sittingsForMyPet.class));
                startActivity(new Intent(this, OwnerAcceptedSittings.class));
                return true;
            case R.id.my_pets_menu:
                startActivity(new Intent(this, Pets.class));
                return true;
            case R.id.subscription_menu:
                startActivity(new Intent(this, Subscription.class));
                return true;
            case R.id.past_sittings_menu:
                startActivity(new Intent(this, PastSittings.class));
                return true;
            case R.id.feedback_menu:
                startActivity(new Intent(this, Feedback.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Request Sitter Event method
     * Performs appropriate actions and completes activity
     * @param view the reference object calling this method
     */
    public void onRequestSitterEvent(View view) {
        startActivity(new Intent(this, NeedSitterEvent.class));
        finish();
    }
}
