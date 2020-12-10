package com.alexandrafernandez.sweproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class OwnerAcceptedSittings extends AppCompatActivity
{

    /**
     * List managing the sittings the has been accepted - OWNER
     */
    ArrayList<OwnerSittingData> ownerAcceptedSittingList;

    /**
     * List View for managing multiple instance of an ownerSitting accepted
     */
    ListView ownerSitting_listview;

    /**
     *  Owner Sitting information to be selected and accessed after request
     */
    OwnerSittingData ownerSitting;

    /**
     * Activity and View data
     */
    private Context context;
    /**
     * Text Views for identifying field components
     */
    TextView textViewCurrentSittingsLabel;

    /**
     * seeing one instance of this
     */
    OwnerSittingData acceptedSitting;

    /**
     * Server interaction objects
     */
    SharedPreferences pref;
    String clientId, clientAuth;

    /**
     * On Create Method
     * Initializes the ownerSittings View and instantiates other view objects for later use
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_accepted);
        setTitle("OWNER");

        ScreenSize view = new ScreenSize(this);

        textViewCurrentSittingsLabel = findViewById(R.id.accepted_sittings_label);
        textViewCurrentSittingsLabel.setTextSize(view.getLabelTextSize());

        //GET Request - get id/auth
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        clientId = pref.getString("id", "");
        clientAuth = pref.getString("auth", "");

        //Url connection
        UrlGet userInfo = new UrlGet("http://aiji.cs.loyola.edu/ownerjoblist?id=" + clientId + "&auth=" + clientAuth + "&is_accepted=" + true,"requests.list", this);
        userInfo.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        ownerAcceptedSittingList = new ArrayList<OwnerSittingData>();
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

                    ownerAcceptedSittingList.add(new OwnerSittingData(id , startDateTime, endDateTime, true));
                }
                else success = true;
            }
        } catch( JSONException json_e ) {
            Log.w("MA", json_e.toString());
        }

        if(!success) {
            Toast.makeText(this, "No sittings found.", Toast.LENGTH_SHORT).show();
        }

        ownerSitting_listview = (ListView) findViewById(R.id.accepted_owner_sittings_listview);
        ArrayAdapter<OwnerSittingData> adapter = new ArrayAdapter<OwnerSittingData>(this, android.R.layout.simple_list_item_1, ownerAcceptedSittingList)
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
        ownerSitting_listview.setAdapter(adapter);


        context = this;

        ownerSitting_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                acceptedSitting = ownerAcceptedSittingList.get(i);

                Intent intent = new Intent(context, OwnerAcceptedSitting.class);
                intent.putExtra("the_job_id", acceptedSitting.sittingID);
                startActivity(intent);
                finish();
            }
        });


    }

    /**
     * on Create Options Menu
     * Initialize and connect the menu for this class
     * @param menu the appropriate menu object for this view (owner_menu.xml)
     * @return true if successful creation of menu
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.owner_accepted_menu, menu);
        return true;
    }


    /**
     * On Options Item Selected method
     * @param item the option selected by the user
     * @return true once an action is taken regarding this selection
     */
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }



}
